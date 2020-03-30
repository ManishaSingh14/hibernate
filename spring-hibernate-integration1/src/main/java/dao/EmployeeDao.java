package dao;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void save(Employee p) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.save(p);
        tr.commit();
        session.close();
    }


    public List<Employee> list() {
        Session session = this.sessionFactory.openSession();
        List<Employee>  employeeList = session.createQuery("FROM Employee").list();
        session.close();
        return employeeList;
    }

    public void update(Employee e){
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Employee em = (Employee) session.load(Employee.class, e.getId());
        em.setName(e.getName());
        em.setDepartment(e.getDepartment());
        tr.commit();
        session.close();
    }

    public void delete(int id){
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Employee em = (Employee) session.load(Employee.class, id);
        session.delete(em);
        tr.commit();
        session.close();
    }

}
