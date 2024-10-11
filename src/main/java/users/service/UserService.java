package users.service;

import enumeration.Role;
import users.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {

    void saveUser(User user);

    List<User> getAllUsers();

    User getUserById(UUID id);

    void updateUser(User user);

    void deleteUser(UUID id);

    User login(String email, String password);

    User getUserByEmail(String email);
}