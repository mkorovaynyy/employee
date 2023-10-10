package pro.sky.employee.employee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/departments")
public class EmployeeWithDepartmentController {
    @Autowired
    private final EmployeeService employeeService;
    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public EmployeeWithDepartmentController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/max-salary")
    public String findEmployeeWithMaxSalaryInDepartment(@RequestParam("departmentId") @NotNull int departmentId) {
        return gson.toJson(employeeService.findMaxSalaryInDepartment(departmentId));
    }
    @GetMapping("/min-salary")
    public String findEmployeeWithMinSalaryInDepartment(@RequestParam("departmentId") @NotNull int departmentId) {
        return gson.toJson(employeeService.findMinSalaryInDepartment(departmentId));
    }
    @GetMapping("/all")
    public String findAllEmployeeInDepartment(@RequestParam("departmentId") @NotNull int departmentId) {
        return gson.toJson(employeeService.findAllEmployeeInDepartment(departmentId));
    }
    @GetMapping()
    public String findAllEmployeeInDepartment() {
        return gson.toJson(employeeService.findAllEmployeeInAllDepartment());
    }
}
