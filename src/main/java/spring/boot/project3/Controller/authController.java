package spring.boot.project3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.project3.Model.User;
import spring.boot.project3.Service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class authController {
    private final AuthService authService;

    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity updateUser(@Valid @RequestBody User user, @PathVariable Integer id) {
        authService.updateUser(user, id);
        return ResponseEntity.status(200).body("User updated successfully");
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        authService.deleteUser(id);
        return ResponseEntity.status(200).body("User deleted successfully");
    }
}
