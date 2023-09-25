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
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PutMapping("/add")
    public String addEmployee(@RequestParam("firstName") @NotNull String firstName, @RequestParam("lastName") @NotNull String lastName) {
        employeeService.addEmployee(firstName, lastName);
        return gson.toJson(new Employee(firstName, lastName));
    }

    @DeleteMapping("/remove")
    public String removeEmployee(@RequestParam("firstName") @NotNull String firstName, @RequestParam("lastName") @NotNull String lastName) {
        employeeService.removeEmployee(firstName, lastName);
        return gson.toJson(new Employee(firstName, lastName));
    }

    @GetMapping("/find")
    public String findEmployee(@RequestParam("firstName") @NotNull String firstName, @RequestParam("lastName") @NotNull String lastName) {
        employeeService.findEmployee(firstName, lastName);
        return gson.toJson(new Employee(firstName, lastName));
    }

    @GetMapping("/find")
    public String printAllEmployee() {
        return gson.toJson(employeeService.getEmployeeList());
    }
}
