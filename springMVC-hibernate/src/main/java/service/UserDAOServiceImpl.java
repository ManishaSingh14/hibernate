package service;

import dao.UserDAO;
import model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserDAOServiceImpl implements UserDAOService {

    private UserDAO userDAO;
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    @Transactional
    public void addUser(User user) {
        this.userDAO.addUser(user);
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return this.userDAO.listUsers();
    }

    @Override
    @Transactional
    public User getUser(int userId) {
        return this.userDAO.getUser(userId);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.userDAO.updateUser(user);
    }

    @Override
    @Transactional
    public String withdraw(int userId, double balance) {
        return null;
    }

    @Override
    @Transactional
    public String deposit(int userId, double balance) {
        return null;
    }
}
