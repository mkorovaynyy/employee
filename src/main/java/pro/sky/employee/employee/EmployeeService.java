package pro.sky.employee.employee;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private int maxEmployeeCount;
    private List<Employee> employeeList = new ArrayList<>();

    public EmployeeService(int maxEmployeeCount) {
        this.maxEmployeeCount = maxEmployeeCount;
    }

    public void addEmployee(String firstName, String lastName, int salary, int departmentId) {
        if (employeeList.size() >= maxEmployeeCount) {
            throw new EmployeeStorageIsFullException("Превышен лимит кол-ва сотрудников в фирме");
        }
        if (!employeeList.contains(new Employee(firstName, lastName, salary, departmentId))) {
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже находится в фирме");
        }
        employeeList.add(new Employee(firstName, lastName, salary, departmentId));
    }

    public void removeEmployee(String firstName, String lastName, int salary, int departmentId) {
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

    //Возвращать сотрудника с максимальной зарплатой на основе номера отдела, который приходит в запрос из браузера.
    public Employee findMaxSalaryInDepartment(int departmentId) {
        List<Employee> departmentList = employeeList.stream().filter(d -> d.getDepartmentId() == departmentId).collect(Collectors.toList());
        Collections.sort(departmentList, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getSalary() > o2.getSalary()) {
                    return 1;
                } else if (o1.getSalary() < o2.getSalary()) {
                    return -1;
                } else return 0;
            }
        });
        return departmentList.get(departmentList.size() - 1);
    }

    //Возвращать сотрудника с минимальной зарплатой на основе номера отдела.
    public Employee findMinSalaryInDepartment(int departmentId) {
        List<Employee> departmentList = employeeList.stream().filter(d -> d.getDepartmentId() == departmentId).collect(Collectors.toList());
        Collections.sort(departmentList, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getSalary() > o2.getSalary()) {
                    return 1;
                } else if (o1.getSalary() < o2.getSalary()) {
                    return -1;
                } else return 0;
            }
        });
        return departmentList.get(0);
    }

    //Возвращать всех сотрудников по отделу.
    public List<Employee> findAllEmployeeInDepartment(int departmentId) {
        return employeeList.stream().filter(d -> d.getDepartmentId() == departmentId).collect(Collectors.toList());
    }

    //Возвращать всех сотрудников с разделением по отделам.
    public List<Employee> findAllEmployeeInAllDepartment() {
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getDepartmentId() > o2.getDepartmentId()) {
                    return 1;
                } else if (o1.getDepartmentId() < o2.getDepartmentId()) {
                    return -1;
                } else return 0;
            }
        });
        return employeeList;
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
