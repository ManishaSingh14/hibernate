package dao;

import entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    void addEmployee(Employee employee);

    public List<Employee> getAllEmployees();

}
