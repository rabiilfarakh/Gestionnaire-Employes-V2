package users.service;

import enumeration.Role;
import jakarta.persistence.PersistenceException;
import org.mindrot.jbcrypt.BCrypt;
import users.User;
import users.dao.UserDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private UserDAO userDao;

    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }


    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(UUID id) {
        return userDao.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(UUID id) {
        userDao.deleteUser(id);
    }

    @Override
    public User login(String email, String password) {
        try {
            User user = userDao.getUserByEmail(email);

            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                return user;
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
}

