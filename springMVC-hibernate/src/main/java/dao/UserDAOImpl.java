package dao;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import exception.InsufficientBalance;
import model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
            Session session = sessionFactory.getCurrentSession();
            session.persist(user);
            logger.info("Customer saved successfully, Customer Details=" + user);
        }


    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from Customer").list();
        for (User u : userList) {
           logger.info("User List::" + u);
        }
        return userList;
    }
      //  return (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).list();

    public User getUser(int userId) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(userId));
        logger.info("Customer loaded successfully, Customer details=" + user);
        return user;
       // return (User) sessionFactory.getCurrentSession().get(User.class, userId);
    }

    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User updated successfully, USER Details=" + user);
      //  sessionFactory.getCurrentSession().createQuery("UPDATE user WHERE userId = " + user.getUserId()).executeUpdate();
    }

    public String deposit(int id, double balance){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user1 = (User) session.get(User.class,id);
        user1.setBalance(user1.getBalance() + balance);
        session.update(user1);
        session.getTransaction().commit();
        session.close();
        return "Balance has been credited : " +user1;
    }

    public String withdraw(int id, double balance){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user1 = (User) session.get(User.class,id);
        if(user1 == null) {
            session.close();
            return "Customer with id : " + id + " does not exists ";
        }
        if(user1.getBalance() < balance) {
            throw new InsufficientBalance("!!! Not Enough balance in account ");
        }
        user1.setBalance(user1.getBalance() - balance);
        session.update(user1);
        session.getTransaction().commit();
        session.close();
        return "Balance has been credited : " +user1;
    }


}
