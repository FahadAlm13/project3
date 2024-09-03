package spring.boot.project3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import spring.boot.project3.API.ApiResponse;
import spring.boot.project3.Model.Account;
import spring.boot.project3.Model.User;
import spring.boot.project3.Service.AccountService;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/getAllAccount")
    public ResponseEntity getAllAccount(@AuthenticationPrincipal User user) { //d
        return ResponseEntity.status(200).body(accountService.getAllAccounts());
    }

    @GetMapping("/getAccountsByCustomerId/{accountId}")
    public ResponseEntity getAccountsByCustomerId(@AuthenticationPrincipal User user, @PathVariable Integer accountId) {
        return ResponseEntity.status(200).body(accountService.getAccountsByCustomerId(user.getId(), accountId));
    }

    // Get account details by account ID
    @GetMapping("/getAccountDetails/{accountId}")
    public ResponseEntity getAccountDetails(@AuthenticationPrincipal User user, @PathVariable Integer accountId) {
        return ResponseEntity.status(200).body(accountService.getAccountDetails(user.getId(), accountId));
    }

    // Create a new account
    @PostMapping("/createAccount")
    public ResponseEntity createAccount(@AuthenticationPrincipal User user, @Valid @RequestBody Account account) {
        accountService.createAccount(user.getId(), account);
        return ResponseEntity.status(200).body(new ApiResponse("Account created successfully"));
    }

    // Update account
    @PutMapping("/updateAccount/{accountId}")
    public ResponseEntity updateAccount(@AuthenticationPrincipal User user, @Valid @RequestBody Account account) {
        accountService.updateAccount(user.getId(), account);
        return ResponseEntity.status(200).body(new ApiResponse("Account updated successfully"));
    }

    // Delete an account by ID
    @DeleteMapping("/deleteAccount/{accountId}")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User user, @PathVariable Integer accountId) {
        accountService.deleteAccount(user.getId(), accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account deleted successfully"));
    }

    // Activate an account by ID
    @PostMapping("/activateAccount/{accountId}") //d
    public ResponseEntity activateAccount(@AuthenticationPrincipal User user, @PathVariable Integer accountId) {
        accountService.activateAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account activated successfully"));
    }

    // Deposit money into an account
    @PostMapping("/deposit/{accountId}")
    public ResponseEntity depositMoney(@AuthenticationPrincipal User user, @PathVariable Integer accountId, @RequestParam Double amount) {
        accountService.depositMoney(user.getId(), accountId, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Amount deposited successfully"));
    }

    // Withdraw money from an account
    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity withdrawMoney(@AuthenticationPrincipal User user, @PathVariable Integer accountId, @RequestParam Double amount) {
        accountService.withdrawMoney(user.getId(), accountId, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Amount withdrawn successfully"));
    }

    // Transfer funds between two accounts
    @PostMapping("/transfer/{fromAccountId}/{toAccountId}")
    public ResponseEntity transferFunds(@AuthenticationPrincipal User user, @PathVariable Integer fromAccountId, @PathVariable Integer toAccountId, @RequestParam Double amount) {
        accountService.transferFunds(user.getId(), fromAccountId, toAccountId, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Amount transferred successfully"));
    }

    // Block an account by ID
    @PostMapping("/blockAccount/{accountId}") //d
    public ResponseEntity blockAccount(@AuthenticationPrincipal User user, @PathVariable Integer accountId) {
        accountService.blockAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account blocked successfully"));
    }
}
