package spring.boot.project3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.boot.project3.API.ApiException;
import spring.boot.project3.DTO.CustomerDTO;
import spring.boot.project3.Model.Customer;
import spring.boot.project3.Model.User;
import spring.boot.project3.Repository.AuthRepository;
import spring.boot.project3.Repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    public void registerCustomer(CustomerDTO customerDTO) {
        User user = new User();
        user.setUsername(customerDTO.getUsername());
        String hash = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        user.setPassword(hash);
        user.setName(customerDTO.getName());
        user.setEmail(customerDTO.getEmail());
        user.setRole("CUSTOMER");

        authRepository.save(user);

        Customer customer = new Customer();
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setUser(user);

        customerRepository.save(customer);
    }

    public void updateCustomer(Integer customer_id,CustomerDTO customerDTO) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("User not found");
        }
        User user = customer.getUser();
        user.setUsername(customerDTO.getUsername());
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setName(customerDTO.getUsername());
        user.setEmail(customerDTO.getEmail());
        user.setRole("CUSTOMER");

        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        authRepository.save(user);
        customerRepository.save(customer);
    }

    public void deleteCustomer(Integer customer_id) {
       Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("User not found");
        }
        authRepository.delete(customer.getUser());
        customerRepository.delete(customer);
    }
}

