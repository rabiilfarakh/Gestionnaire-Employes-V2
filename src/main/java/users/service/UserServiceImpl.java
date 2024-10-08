package users.service;

import users.User;
import users.dao.UserDao;
import users.dao.UserDaoImpl;

import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public List<User> listAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User findUserById(UUID id) {
        return userDao.getUserById(id);
    }

    @Override
    public void modifyUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void removeUser(UUID id) {
        userDao.deleteUser(id);
    }
}

