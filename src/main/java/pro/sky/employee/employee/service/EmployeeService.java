package pro.sky.employee.employee.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.employee.employee.exception.InvalidInputException;
import pro.sky.employee.employee.model.Employee;
import pro.sky.employee.employee.exception.EmployeeAlreadyAddedException;
import pro.sky.employee.employee.exception.EmployeeNotFoundException;
import pro.sky.employee.employee.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private int maxEmployeeCount;
    private List<Employee> employeeList = new ArrayList<>();

    public EmployeeService(int maxEmployeeCount) {
        this.maxEmployeeCount = maxEmployeeCount;
    }

    public void addEmployee(String firstName, String lastName, int salary, int departmentId) {
        validateInput(firstName, lastName);
        if (employeeList.size() >= maxEmployeeCount) {
            throw new EmployeeStorageIsFullException("Превышен лимит кол-ва сотрудников в фирме");
        }
        if (!employeeList.contains(new Employee(firstName, lastName, salary, departmentId))) {
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже находится в фирме");
        }
        employeeList.add(new Employee(firstName, lastName, salary, departmentId));
    }

    public void removeEmployee(String firstName, String lastName, int salary, int departmentId) {
        validateInput(firstName, lastName);
        if (!employeeList.contains(new Employee(firstName, lastName, salary, departmentId))) {
            throw new EmployeeNotFoundException("Данный сотрудник не найден");
        }
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getFirstName().equals(firstName) && employeeList.get(i).getFirstName().equals(lastName)) {
                employeeList.remove(i);
            }
        }
    }

    public int findEmployee(String firstName, String lastName, int salary, int departmentId) {
        validateInput(firstName, lastName);
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getFirstName().equals(firstName) && employeeList.get(i).getFirstName().equals(lastName)) {
                return i;
            }
        }
        if (!employeeList.contains(new Employee(firstName, lastName, salary, departmentId))) {
            throw new EmployeeNotFoundException("Данный сотрудник не найден");
        }
        return -1;
    }

    private void validateInput(String firstName, String lastName) {
        if(!StringUtils.isAlpha(firstName) && !StringUtils.isAlpha(lastName)) {
            throw new InvalidInputException();
        }
    }




    public int getMaxEmployeeCount() {
        return maxEmployeeCount;
    }

    public void setMaxEmployeeCount(int maxEmployeeCount) {
        this.maxEmployeeCount = maxEmployeeCount;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
