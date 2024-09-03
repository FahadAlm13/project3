package spring.boot.project3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.boot.project3.API.ApiException;
import spring.boot.project3.DTO.EmployeeDTO;
import spring.boot.project3.Model.Employee;
import spring.boot.project3.Model.User;
import spring.boot.project3.Repository.AuthRepository;
import spring.boot.project3.Repository.EmployeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void registerEmployee(EmployeeDTO employeeDTO) {
        User user = new User();
        user.setUsername(employeeDTO.getUsername());
        String hash = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        user.setPassword(hash);
        user.setName(employeeDTO.getName());
        user.setEmail(employeeDTO.getEmail());
        user.setRole("EMPLOYEE");
        authRepository.save(user);

        Employee employee = new Employee();
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        employee.setUser(user);

        employeeRepository.save(employee);
    }

    public void updateEmployee(Integer employee_id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findEmployeeById(employee_id);
        if(employee == null) {
            throw new ApiException("User not found");
        }
        User user = employee.getUser();
        user.setUsername(employeeDTO.getUsername());
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setName(employeeDTO.getUsername());
        user.setEmail(employeeDTO.getEmail());
        user.setRole("EMPLOYEE");


        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
//        employee.setUser(user);
        authRepository.save(user);
        employeeRepository.save(employee);
    }
    public void deleteEmployee(Integer employee_id) {
        Employee employee = employeeRepository.findEmployeeById(employee_id);
        if(employee == null) {
            throw new ApiException("User not found");
        }
        authRepository.delete(employee.getUser());
        employeeRepository.delete(employee);
    }
}
