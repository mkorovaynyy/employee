package pro.sky.employee.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private final DepartmentService departmentService;
    public DepartmentController(EmployeeService employeeService, DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary", params = "departmentId")
    public Employee findEmployeeWithMaxSalaryInDepartment(@RequestParam("departmentId") @NotNull int departmentId) {
        return departmentService.findEmployeeWithMaxSalary(departmentId);
    }
    @GetMapping(path = "/min-salary", params = "departmentId")
    public Employee findEmployeeWithMinSalaryInDepartment(@RequestParam("departmentId") @NotNull int departmentId) {
        return departmentService.findMinSalaryInDepartment(departmentId);
    }
    @GetMapping(path = "/all", params = "departmentId")
    public List<Employee> findAllEmployeeInDepartment(@RequestParam("departmentId") @NotNull int departmentId) {
        return departmentService.findAllEmployeeInDepartment(departmentId);
    }
    @GetMapping()
    public HashMap<Integer, List<Employee>> findAllEmployeeInDepartment() {
        return departmentService.findAllEmployeeInAllDepartment();
    }
}
