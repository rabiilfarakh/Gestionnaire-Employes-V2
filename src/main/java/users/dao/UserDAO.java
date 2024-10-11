package users.dao;

import enumeration.Role;
import users.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserDAO {
    void saveUser(User user);

    List<User> getAllUsers();

    User getUserById(UUID id);

    void updateUser(User user);

    void deleteUser(UUID id);

    User getUserByEmail(String email);
}
