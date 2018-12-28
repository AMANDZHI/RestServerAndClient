package amandzhi.restserver.service;

import amandzhi.restserver.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    User save(User user);

    Optional<User> findById(Long id);

    User findByUsername(String username);
}
