package dao;

import model.Bank;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class BankDAOImpl implements BankDAO {

    private SessionFactory sessionFactory;

    @Override
    public String addBranch(String branchName) {
        Session session = sessionFactory.getCurrentSession();
        Bank branch = new Bank();
        branch.setBranchName(branchName);
        session.persist(branch);
        session.getTransaction().commit();
        session.close();
        return "Branch has been successfully added  " + branch;
    }

    @Override
    public User register(User user) {
      user.setUserName("ABC");
      user.setUserId(1);
      Session session=sessionFactory.getCurrentSession();
      Transaction tx=session.beginTransaction();
      session.save(user);
      tx.commit();
      return user;
    }

}
