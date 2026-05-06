package com.example.bankingMiniSystem.Controller;

import com.example.bankingMiniSystem.Entity.AccountDetails;
import com.example.bankingMiniSystem.Entity.Customer;
import com.example.bankingMiniSystem.Entity.TransactionDetails;
import com.example.bankingMiniSystem.Repository.CustomerRepository;
import com.example.bankingMiniSystem.Repository.TransactionRepository;
import com.example.bankingMiniSystem.Service.AccountDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BankMiniController {
    private final AccountDetailService detailService;
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;


   @PostMapping("/addcustomers")
   public Customer createCustomer(@RequestBody Customer customer){
       return customerRepository.save(customer);
   }
    @PostMapping("/addaccounts")
    public AccountDetails create(@RequestBody AccountDetails accountDetails){
        return detailService.createAccount(accountDetails);
    }
    @PostMapping("/depositamount")
    public String deposit(@RequestParam String accNo,@RequestParam Double amount){
        return detailService.deposit(accNo,amount);
    }
    @PostMapping("/withdrawal")
    public String withdraw(@RequestParam String accNo,@RequestParam Double amount){
        return detailService.withdraw(accNo, amount);
    }
    @PostMapping("/transferamount")
    public String transfer(@RequestParam String from,@RequestParam String to,@RequestParam Double amount){
        return detailService.transfer(from, to, amount);
    }
    @GetMapping("/accountStatement/{accNo}")
    public List<TransactionDetails> statement(@PathVariable String accNo){
        return transactionRepository.findBySourceAccountOrTargetAccount(accNo,accNo);
    }
}
