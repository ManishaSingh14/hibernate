package dao;

import model.User;

public interface BankDAO {
    public String addBranch(String branchName);
    public User register(User user);
}
