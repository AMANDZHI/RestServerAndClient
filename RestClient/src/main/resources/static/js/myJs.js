$(document).ready(function () {

    function myForm() {
        $(".eBtn").on('click', function(event) {
            event.preventDefault();

            var id = $(this).attr('value');
            $.ajax({
                type : "GET",
                url : "/findById",
                data:  {
                    value: id
                },
                success: function(user){
                            $('.myForm #id').val(user.id);
                            $('.myForm #email').val(user.email);
                            $('.myForm #login').val(user.username);
                            $('.myForm #password').val(user.password);
                            $('.myForm #role').val(user.roles[0]);

                        $('.myForm #exampleModal').modal();
                            editUser();
                }
            });
        });
    }

        $.ajax({
            type : "GET",
            url : "/mainRest",
            success: function(result){
                $.each(result, function(i, user){
                    var userRow = "<tr>" +
                        "<td id=\"" + user.id + "\">" + user.id + "</td>" +
                        "<td>" + user.roles[0] + "</td>" +
                        "<td>" + user.username + "</td>" +
                        "<td>" + user.password + "</td>" +
                        "<td>" + user.email + "</td>" +
                        "<td>" +
                        "<button href=\"/findById\" class=\"btn btn-primary eBtn\" value=\"" + user.id + "\">" +
                    "Edit" +
                    "</button>" +
                        "</td>"+
                        "</tr>";

                    $('#usersTable .my-tbody').append(userRow);

                });
                myForm();
            }
        });

    function editUser() {
        $("#editUser").on("submit", function (event) {
            event.preventDefault();
            var id = $('.myForm #id').val();
            var email = $('.myForm #email').val();
            var username = $('.myForm #login').val();
            var password = $('.myForm #password').val();
            var role = $('.myForm #role').val();

            $.ajax({
                type:"POST",
                url: "/updateUser",
                data: {
                    id : id,
                    email: email,
                    username: username,
                    password: password,
                    role: role
                },
                success: function(user){
                    $('.myForm #exampleModal').modal('hide');

                    $("#" + user.id).html(user.id);
                    $("#" + user.id + "+td").html(user.roles[0]);
                    $("#" + user.id + "+td+td").html(user.username);
                    $("#" + user.id + "+td+td+td").html(user.password);
                    $("#" + user.id + "+td+td+td+td").html(user.email);
                }
            });
        })
    }

    $("#addNewUser").on("submit", function (event) {
        event.preventDefault();

        var email = $('#newEmail').val();
        var username = $('#newLogin').val();
        var password = $('#newPassword').val();
        var role = $('#newRole').val();

        $.ajax({
            type:"POST",
            url: "/addNewUser",
            data: {
                email: email,
                username: username,
                password: password,
                role: role
            },
            success: function(user){
                var userRow = "<tr>" +
                    "<td id=\"" + user.id + "\">" + user.id + "</td>" +
                    "<td>" + user.roles[0] + "</td>" +
                    "<td>" + user.username + "</td>" +
                    "<td>" + user.password + "</td>" +
                    "<td>" + user.email + "</td>" +
                    "<td>" +
                    "<button href=\"/findById\" class=\"btn btn-primary eBtn\" value=\"" + user.id + "\">" +
                    "Edit" +
                    "</button>" +
                    "</td>"+
                    "</tr>";

                $('#usersTable .my-tbody').append(userRow);
                $('#successAddUser').modal();
            }
        });
    })
});