package main;

import config.HibernateConfiguration;
import model.Account;
import model.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import java.util.ArrayList.*;

import javax.persistence.criteria.Order.*;
import java.util.ArrayList;
import java.util.List;

public class HibernateTest {
    public static void main(String[] args) {
        Session session= HibernateConfiguration.getSessionFactory().openSession();
        session.beginTransaction();

        //HQL insert
        System.out.println("creating new employee");

        //Employee object 1
        Employee employee=new Employee();
        employee.setFirstName("manisha");
        employee.setLastName("singh");
        employee.setSalary(30000);

       //@one-to-one mapping
        Account ac = new Account();
        ac.setAccountId(1);
        ac.setAccountNumber("ABC1");
        System.out.println(employee);
        employee.setAccount(ac);
        ac.setEmployee(employee);
        session.save(employee);
        session.save(ac);
        System.out.println(ac);

        //one-to-many mapping
        //Employee object 2
        session.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee employee1=new Employee();
        employee1.setFirstName("abc");
        employee1.setLastName("singh");
        employee1.setSalary(35000);
        System.out.println(employee1);
        session.save(employee1);

        //account 1
        Account ac1 = new Account();
        ac1.setAccountId(2);
        ac1.setAccountNumber("ABC2");
        session.save(ac1);
        System.out.println(employee1);
        System.out.println(ac1);

        //account 2
        Account ac2 = new Account();
        ac2.setAccountId(3);
        ac2.setAccountNumber("ABC3");
        session.save(ac2);
        System.out.println(employee1);
        System.out.println(ac2);

        //account 3
        Account ac3 = new Account();
        ac3.setAccountId(4);
        ac3.setAccountNumber("ABC4");
        session.save(ac3);
        System.out.println(employee1);
        System.out.println(ac3);

         List<Account> accountList= new ArrayList<>();
        accountList.add(ac1);
        accountList.add(ac2);
        accountList.add(ac3);

        employee1.setAccountList(ac1);
        employee1.setAccountList(ac2);
        employee1.setAccountList(ac3);

        //Save Employee
        session.save(employee1);
        session.getTransaction().commit();

        //many-to-many mapping
        //Employee object 3
        session.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee employee2=new Employee();
        employee2.setFirstName("nisha");
        employee2.setLastName("singh");
        employee2.setSalary(40000);
        System.out.println(employee2);
        session.save(employee2);
        System.out.println("new employee saved");

        //Employee object 4
        Employee employee3=new Employee();
        employee3.setFirstName("isha");
        employee3.setLastName("singh");
        employee3.setSalary(45000);
        System.out.println(employee3);
        session.save(employee3);
        System.out.println("new employee saved");

        List employeeList= new ArrayList<>();
        employeeList.add(employee2);
        employeeList.add(employee3);

       //creating account objects
        Account acc1 = new Account();
        acc1.setAccountId(10);
        acc1.setAccountNumber("ABC10");
        session.save(acc1);
        System.out.println(employee2);
        System.out.println(acc1);

        //account 2
        Account acc2 = new Account();
        acc2.setAccountId(20);
        acc2.setAccountNumber("ABC20");
        session.save(acc2);
        System.out.println(employee2);
        System.out.println(acc2);

        List accounts = new ArrayList();
        accounts.add(acc1);
        accounts.add(acc2);

        //Setting the collection of accounts to a employee
        employee2.setAccounts(accounts);
        employee3.setAccounts(accounts);
        //Save Employee
        session.save(employee2);
        session.save(employee3);
        session.getTransaction().commit();

        //QUERY
        //select
        System.out.println("getting the employee");
        System.out.println("employee" +employee.getId());
        System.out.println("employee"+employee1.getId());
        System.out.println("employee"+employee2.getId());

        // HQL select
        session= HibernateConfiguration.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        System.out.println("getting student with id:" +employee.getId());
        Employee emp= (Employee) session.get(Employee.class, +employee.getId());
        System.out.println("get complete" +emp);
        session.getTransaction().commit();

        //HQL query
        session = HibernateConfiguration.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        System.out.println("querying");
        List<Employee> employees = (List<Employee>) session.createQuery("from Employee").list();
        for( Employee tempEmployee : employees){
            System.out.println(tempEmployee);
        }
        System.out.println("done!!");

       //update
        int empId = 5;
        session= HibernateConfiguration.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("updating");
        Employee e2= (Employee) session.get(Employee.class,empId);
        e2.setFirstName("scooby");
        System.out.println("updated name.....");
        session.getTransaction().commit();

        //HQL update
        session= HibernateConfiguration.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("another update");
        session.createQuery("update Employee set salary=50000").executeUpdate();
        session.getTransaction().commit();

        //delete
        empId = 2;
        session= HibernateConfiguration.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("deleting");
        Employee e3= (Employee) session.get(Employee.class,empId);
        session.delete(e3);
        System.out.println("deleted.....");
        session.getTransaction().commit();

        //HQL delete
        session= HibernateConfiguration.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("another delete");
        session.createQuery("delete from Employee where id=3").executeUpdate();
        session.getTransaction().commit();


        session=HibernateConfiguration.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("started!!!");
        List<Employee >employees11 = (List<Employee>) session.createQuery("from Employee e where e.firstName='nisha'");
        for(Employee e1:employees11){
            System.out.println(e1);
        }
        System.out.println("working");
        session.getTransaction().commit();

        //criteria query
        session = HibernateConfiguration.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("criteria");
        Criteria criteria = session.createCriteria(Employee.class)
               .addOrder(Order.asc("salary") );
        List<Employee> empList = criteria.list();
        for(Employee emp11 : empList){
            System.out.println("ID="+emp11.getId()+", name="+emp11.getFirstName());
        }
        session.getTransaction().commit();

        //another criteria query
       Criteria criteria1=session.createCriteria(Employee.class);
       //another criteria query
       criteria1.add(Restrictions.gt("salary",50000));
        criteria1.setProjection(Projections.rowCount());
        List rowCount = criteria1.list();
        System.out.println("Total Count: " + rowCount.get(0) );
        System.out.println("criteria done");
        //commit
        session.getTransaction().commit();
        //close
        HibernateConfiguration.getSessionFactory().close();
    }
}
