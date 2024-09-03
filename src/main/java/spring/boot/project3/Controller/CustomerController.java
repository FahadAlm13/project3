package spring.boot.project3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import spring.boot.project3.API.ApiResponse;
import spring.boot.project3.DTO.CustomerDTO;
import spring.boot.project3.Model.User;
import spring.boot.project3.Service.CustomerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/getAllCustomers")
    public ResponseEntity getAllCustomers(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    @PostMapping("/registerCustomer")
    public ResponseEntity registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        customerService.registerCustomer(customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Customer registered successfully"));
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user, @Valid @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(user.getId(), customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Customer updated successfully"));
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted successfully"));
    }
}
