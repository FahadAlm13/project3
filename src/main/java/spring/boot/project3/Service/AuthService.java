package spring.boot.project3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.boot.project3.API.ApiException;
import spring.boot.project3.DTO.CustomerDTO;
import spring.boot.project3.DTO.EmployeeDTO;
import spring.boot.project3.Model.Customer;
import spring.boot.project3.Model.Employee;
import spring.boot.project3.Model.User;
import spring.boot.project3.Repository.AuthRepository;
import spring.boot.project3.Repository.CustomerRepository;
import spring.boot.project3.Repository.EmployeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;





    public List<User> getAllUsers() {
        return authRepository.findAll();
    }

    public void updateUser(User user, Integer id) {
        User user1 = authRepository.findUserById(id);
        if (user1 == null) {
            throw new ApiException("User not found");
        }
        user1.setUsername(user.getUsername());

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user1.setPassword(hash);

        authRepository.save(user1);
    }

    public void deleteUser(Integer id) {
        User user1 = authRepository.findUserById(id);
        if (user1 == null) {
            throw new ApiException("User not found");
        }
        authRepository.delete(user1);
    }

}



