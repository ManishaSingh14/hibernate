package dao;

import model.User;

import java.util.List;

public interface UserDAO {
    public void addUser(User user);

    public List<User> listUsers();

    public User getUser(int userId);

    public void updateUser(User user);

    public String withdraw(int userId,double balance);

    public String deposit(int userId, double balance);
}
