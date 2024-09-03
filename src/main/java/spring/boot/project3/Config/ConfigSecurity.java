package spring.boot.project3.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigSecurity {
    private final UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("api/v1/employee/registerEmployee", "api/v1/customer/registerCustomer").permitAll()
                .requestMatchers("/api/v1/auth/getAllUsers", "/api/v1/auth/updateUser/**",
                        "/api/v1/auth/deleteUser/**", "/api/v1/customer/getAllCustomers",
                        "/api/v1/employee/getAllEmployees", "/api/v1/customer/deleteCustomer/**",
                        "/api/v1/employee/deleteEmployee/**", "/api/v1/account/getAllAccount", "/api/v1/account/blockAccount/**",
                        "/api/v1/account/activateAccount/**", "/api/v1/account/deleteAccount/**"
                        , "/api/v1/account/getAccountsByCustomerId/**").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/employee/updateEmployee"
                ).hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/customer/updateCustomer", "/api/v1/account/deposit/**", "/api/v1/account/createAccount",
                        "/api/v1/account/transfer/**", "/api/v1/account/withdraw/**",
                        "/api/v1/account/getAccountDetails/**", "/api/v1/account/updateAccount/**").hasAuthority("CUSTOMER")
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();

        return http.build();
    }
}
