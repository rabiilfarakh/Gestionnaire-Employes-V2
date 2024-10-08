package users.service;

import users.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void registerUser(User user);

    List<User> listAllUsers();

    User findUserById(UUID id);

    void modifyUser(User user);

    void removeUser(UUID id);

    void login(String email, String password);

    User getUserByEmail(String email);
}