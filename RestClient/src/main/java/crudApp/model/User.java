package crudApp.model;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String email;

    private String username;

    private String password;

    private boolean active;

    private Set<Role> roles;

    public User(String email, String username, String password, boolean active, Set<Role> roles) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }
}