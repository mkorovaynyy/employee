package pro.sky.employee.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.sky.employee.employee.model.Employee;
import pro.sky.employee.employee.service.EmployeeService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PutMapping("/add")
    public Employee addEmployee(@RequestParam("firstName") @NotNull String firstName, @RequestParam("lastName") @NotNull String lastName, @RequestParam("salary") @NotNull int salary,
                                @RequestParam("departmentId") @NotNull int departmentId) {
        employeeService.addEmployee(firstName, lastName, salary, departmentId);
        return new Employee(firstName, lastName, salary, departmentId);
    }

    @DeleteMapping("/remove")
    public Employee removeEmployee(@RequestParam("firstName") @NotNull String firstName, @RequestParam("lastName") @NotNull String lastName, @RequestParam("salary") @NotNull int salary,
                                 @RequestParam("departmentId") @NotNull int departmentId) {
        employeeService.removeEmployee(firstName, lastName, salary, departmentId);
        return new Employee(firstName, lastName, salary, departmentId);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("firstName") @NotNull String firstName, @RequestParam("lastName") @NotNull String lastName, @RequestParam("salary") @NotNull int salary,
                               @RequestParam("departmentId") @NotNull int departmentId) {
        employeeService.findEmployee(firstName, lastName, salary, departmentId);
        return new Employee(firstName, lastName, salary, departmentId);
    }

    @GetMapping("/find")
    public List<Employee> printAllEmployee() {
        return employeeService.getEmployeeList();
    }
}
