package pro.sky.employee.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private int salary;
    private int departmentId;
    private static HashMap<Integer, List<Employee>> employeeMap = new HashMap<>();

    public Employee(String firstName, String lastName, int salary, int departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.departmentId = departmentId;
        if(employeeMap.containsKey(departmentId)) {
            employeeMap.get(departmentId).add(this);
        } else {
            List<Employee> list = new ArrayList<>();
            list.add(this);
            employeeMap.put(departmentId, list);
        }
    }

    public static HashMap<Integer, List<Employee>> getEmployeeMap() {
        return employeeMap;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
