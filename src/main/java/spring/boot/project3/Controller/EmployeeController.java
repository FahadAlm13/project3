package spring.boot.project3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import spring.boot.project3.API.ApiResponse;
import spring.boot.project3.DTO.EmployeeDTO;
import spring.boot.project3.Model.Employee;
import spring.boot.project3.Model.User;
import spring.boot.project3.Service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/getAllEmployees")
    public ResponseEntity getAllEmployees(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());
    }
    @PostMapping("/registerEmployee")
    public ResponseEntity registerEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.registerEmployee(employeeDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Employee registered successfully"));
    }
    @PutMapping("/updateEmployee")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal User user, @Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(user.getId(),employeeDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Employee updated successfully"));
    }
    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(200).body(new ApiResponse("Employee deleted successfully"));
    }
}
