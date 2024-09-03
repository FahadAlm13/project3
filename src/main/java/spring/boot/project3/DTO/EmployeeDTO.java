package spring.boot.project3.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.hibernate.validator.constraints.Length;
import spring.boot.project3.Model.User;

@Data
@AllArgsConstructor
public class EmployeeDTO {


    @NotEmpty(message = "Position should not be empty")
    private String position;

    @PositiveOrZero
    @NotNull(message = "Salary should not be null")
    private Double salary;

    @NotEmpty(message = "Username should not be empty")
    @Length(min = 4, max = 30, message = "Username length must be between 4 and 10 characters")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    @Length(min = 6,max = 500, message = "Password length must be at least 6 characters")
    private String password;

    @NotEmpty(message = "Name should not be empty")
    @Length(min = 2, max = 20, message = "Name length must be between 2 and 20 characters")
    private String name;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Must be a valid email format")
    private String email;

    @NotEmpty(message = "Role should not be empty")
    @Pattern(regexp = "^(CUSTOMER|EMPLOYEE|ADMIN)$", message = "Role must be either 'CUSTOMER', 'EMPLOYEE', or 'ADMIN'")
    private String role;

//    private User user;
}
