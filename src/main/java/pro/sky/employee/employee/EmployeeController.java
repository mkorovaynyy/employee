package pro.sky.employee.employee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;
    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PutMapping("/add")
    public String addEmployee(@RequestParam("firstName") @NotNull String firstName, @RequestParam("lastName") @NotNull String lastName, @RequestParam("salary") @NotNull int salary,
                              @RequestParam("departmentId") @NotNull int departmentId) {
        employeeService.addEmployee(firstName, lastName, salary, departmentId);
        return gson.toJson(new Employee(firstName, lastName, salary, departmentId));
    }

    @DeleteMapping("/remove")
    public String removeEmployee(@RequestParam("firstName") @NotNull String firstName, @RequestParam("lastName") @NotNull String lastName, @RequestParam("salary") @NotNull int salary,
                                 @RequestParam("departmentId") @NotNull int departmentId) {
        employeeService.removeEmployee(firstName, lastName, salary, departmentId);
        return gson.toJson(new Employee(firstName, lastName, salary, departmentId));
    }

    @GetMapping("/find")
    public String findEmployee(@RequestParam("firstName") @NotNull String firstName, @RequestParam("lastName") @NotNull String lastName, @RequestParam("salary") @NotNull int salary,
                               @RequestParam("departmentId") @NotNull int departmentId) {
        employeeService.findEmployee(firstName, lastName, salary, departmentId);
        return gson.toJson(new Employee(firstName, lastName, salary, departmentId));
    }

    @GetMapping("/find")
    public String printAllEmployee() {
        return gson.toJson(employeeService.getEmployeeList());
    }
}
