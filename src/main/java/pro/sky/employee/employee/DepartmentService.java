package pro.sky.employee.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.lang.module.FindException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

@Service
public class DepartmentService {
    private HashMap<Integer, List<Employee>> departmentMap = new HashMap<>();
    @Autowired
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    //Возвращать сотрудника с максимальной зарплатой на основе номера отдела, который приходит в запрос из браузера.
    public Employee findEmployeeWithMaxSalary(int departmentId) {
        return employeeService.getEmployeeList().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Такого сотрудника нет"));
    }

    //Возвращать сотрудника с минимальной зарплатой на основе номера отдела.
    public Employee findMinSalaryInDepartment(int departmentId) {
        return employeeService.getEmployeeList().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Такого сотрудника нет"));
    }

    //Возвращать всех сотрудников по отделу.
    public List<Employee> findAllEmployeeInDepartment(int departmentId) {
        return employeeService.getEmployeeList().stream().filter(d -> d.getDepartmentId() == departmentId).collect(Collectors.toList());
    }

    //Возвращать всех сотрудников с разделением по отделам.
    public HashMap<Integer, List<Employee>> findAllEmployeeInAllDepartment() {
        return Employee.getEmployeeMap();
    }

    public HashMap<Integer, List<Employee>> getDepartmentMap() {
        return departmentMap;
    }

    public void setDepartmentMap(HashMap<Integer, List<Employee>> departmentMap) {
        this.departmentMap = departmentMap;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }
}
