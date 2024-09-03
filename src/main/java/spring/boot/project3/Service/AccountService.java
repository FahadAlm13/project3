package spring.boot.project3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.project3.API.ApiException;
import spring.boot.project3.Model.Account;
import spring.boot.project3.Model.Customer;
import spring.boot.project3.Repository.AccountRepository;
import spring.boot.project3.Repository.CustomerRepository;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;


    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountDetails(Integer customerId ,Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null || !account.getCustomer().getId().equals(customerId)) {
            throw new ApiException("Account not found");
        }
        return account;
    }
    public Account getAccountsByCustomerId(Integer customerId,Integer accountId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        Account account = accountRepository.findAccountById(accountId);
//        if (customer == null ) {
//            throw new ApiException("Customer not found");
//        }
        if (account == null){
            throw new ApiException("Account not found");
        }
        return account;
    }

    public void createAccount(Integer customerId, Account account) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }

        account.setCustomer(customer);
        account.setAccountNumber(generateAccountNumber());

        accountRepository.save(account);
    }
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            String block = String.format("%04d", random.nextInt(10000));
            if (i > 0) {
                accountNumber.append("-");
            }
            accountNumber.append(block);
        }

        return accountNumber.toString();
    }
    public void updateAccount(Integer accountId , Account account) {
        Account existingAccount = accountRepository.findAccountById(accountId);
        if (existingAccount == null) {
            throw new ApiException("Account not found");
        }
        existingAccount.setAccountNumber(account.getAccountNumber());
        existingAccount.setBalance(account.getBalance());
        existingAccount.setIsActive(account.getIsActive());

        accountRepository.save(existingAccount);
    }
    public void deleteAccount(Integer customerId , Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null || !account.getCustomer().getId().equals(customerId)) {
            throw new ApiException("Account not found");
        }
        accountRepository.deleteById(accountId);
    }

    public void activateAccount( Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null ) {
            throw new ApiException("Account not found");
        }
        account.setIsActive(true);
        accountRepository.save(account);
    }
    public void depositMoney(Integer customerId , Integer accountId, Double amount) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null || !account.getCustomer().getId().equals(customerId)) {
            throw new ApiException("Account not found");
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }
    public void withdrawMoney(Integer customerId ,Integer accountId, Double amount) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null || !account.getCustomer().getId().equals(customerId)) {
            throw new ApiException("Account not found");
        }
        if (account.getBalance() < amount) {
            throw new ApiException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    public void transferFunds(Integer customerId , Integer fromAccountId, Integer toAccountId, Double amount) {
        Account fromAccount = accountRepository.findAccountById(fromAccountId);
        Account toAccount = accountRepository.findAccountById(toAccountId);

        if (fromAccount == null || toAccount == null || !fromAccount.getCustomer().getId().equals(customerId)) {
            throw new ApiException("Account not found");
        }
        if (fromAccount.getBalance() < amount) {
            throw new ApiException("Insufficient balance in the source account");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
    public void blockAccount(Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null ) {
            throw new ApiException("Account not found");
        }
        account.setIsActive(false);
        accountRepository.save(account);
    }
}
