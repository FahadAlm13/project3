package spring.boot.project3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import spring.boot.project3.Model.User;


@Data
@AllArgsConstructor
public class CustomerDTO {


    @NotEmpty(message = "Phone number should not be empty")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with '05' and be 10 digits long")
    private String phoneNumber;

    @NotEmpty(message = "Username should not be empty")
    @Length(min = 4, max = 30, message = "Username length must be between 4 and 10 characters")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    @Length(min = 6, message = "Password length must be at least 6 characters")
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
