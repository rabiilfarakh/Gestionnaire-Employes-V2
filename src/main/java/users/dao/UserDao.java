package users.dao;

import users.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {
    void saveUser(User user);

    List<User> getAllUsers();

    User getUserById(UUID id);

    void updateUser(User user);

    void deleteUser(UUID id);
}
