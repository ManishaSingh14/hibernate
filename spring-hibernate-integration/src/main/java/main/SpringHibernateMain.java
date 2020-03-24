package main;

import dao.EmployeeDAO;
import entity.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static dao.EmployeeDAO.*;

public class SpringHibernateMain {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring3.xml");

        EmployeeDAO employeeDAO = context.getBean(EmployeeDAO.class);

        Employee employee = new Employee();
        employee.setFirstName("AAA");
        employee.setLastName("zzz");
        employee.setSalary(30000);

        employeeDAO.addEmployee(employee);

        System.out.println("Employee::"+employee);

        List<Employee> employeeList = employeeDAO.getAllEmployees();

        for(Employee emp : employeeList){
            System.out.println("Person List::"+emp);
        }
        //close resources
        context.close();
    }
}

