package crudApp.service;

import crudApp.model.User;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private RestTemplate restTemplate = new RestTemplate();

    public List<User> getUsers(String URL) {
        ResponseEntity<User[]> exchange = restTemplate.exchange(URL, HttpMethod.GET, getHttpEntity(), User[].class);
        List<User> list = new ArrayList<>();
        User[] arr = exchange.getBody();

        for (int i =0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    public User findById(String URL, Long value) {
        ResponseEntity<User> exchange = restTemplate.exchange(URL + "/" + value, HttpMethod.GET, getHttpEntity(), User.class);
        return exchange.getBody();
    }

    private HttpEntity<User> getHttpEntityWithUser(User user, HttpHeaders headers) {
        return new HttpEntity<>(user, headers);
    }

    public User updateUser(User updatedUser, String URL) {
        return restTemplate.postForObject(URL, getHttpEntityWithUser(updatedUser, getHeaders()), User.class);
    }

    public User addNewUser(User newUser, String URL) {
        return restTemplate.postForObject(URL, getHttpEntityWithUser(newUser, getHeaders()), User.class);
    }

    private HttpEntity<User> getHttpEntity() {
        return new HttpEntity<>(getHeaders());
    }

    private HttpHeaders getHeaders() {
        String auth = "admin:admin";
        HttpHeaders headers = new HttpHeaders();
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        return headers;
    }
}