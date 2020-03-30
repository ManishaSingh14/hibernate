import dao.EmployeeDao;
import model.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {
    public static void main( String[] args ) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        EmployeeDao employeeDao = context.getBean(EmployeeDao.class);

        Employee employee = new Employee();
        employee.setName("Manisha");
        employee.setId(100);
        employee.setDepartment("FAWB");

        employeeDao.update(employee);

      //  employeeDao.delete(1);

        List<Employee> list = employeeDao.list();

        for(Employee p : list){
            System.out.println("Employee List::"+p);
        }

        context.close();
    }
}
