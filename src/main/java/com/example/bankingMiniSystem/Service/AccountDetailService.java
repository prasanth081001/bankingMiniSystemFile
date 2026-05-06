package com.example.bankingMiniSystem.Service;

import com.example.bankingMiniSystem.Entity.AccountDetails;
import com.example.bankingMiniSystem.Entity.TransactionDetails;
import com.example.bankingMiniSystem.Repository.AccountRepository;
import com.example.bankingMiniSystem.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountDetailService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountDetails createAccount(AccountDetails accountDetails){
        accountDetails.setBalance(1.0);
        return accountRepository.save(accountDetails);
    }

    public String deposit(String accNo,Double amount){
        if (amount<=0) throw new RuntimeException("invalid amount");
        AccountDetails accountDetails=accountRepository.findByAccountNumber(accNo).orElseThrow(()->new RuntimeException("Account not found"));
        accountDetails.setBalance(accountDetails.getBalance()+amount);
        accountRepository.save(accountDetails);
        saveTransaction("Deposit",amount,accNo,null);
        return "Deposited successfully";
    }
    public String withdraw(String accNo,Double amount){
        AccountDetails accountDetails=accountRepository.findByAccountNumber(accNo).orElseThrow(()->new RuntimeException("Account not found"));
        if (accountDetails.getBalance()<amount) throw new RuntimeException("Insufficient balance");
        accountDetails.setBalance(accountDetails.getBalance()-amount);
        accountRepository.save(accountDetails);
        saveTransaction("Withdraw",amount,accNo,null);
        return "withdraw successful";
    }
    @Transactional
    public String transfer(String from,String to,Double amount){
        if (from.equals(to)) throw new RuntimeException("Same account transfer not allowed ");
        AccountDetails source=accountRepository.findByAccountNumber(from).orElseThrow(()-> new RuntimeException("Source not found"));
        AccountDetails target=accountRepository.findByAccountNumber(to).orElseThrow(()->new RuntimeException("Target not found"));
        if (source.getBalance()<amount) throw new RuntimeException("Insufficient balance");
        source.setBalance(source.getBalance()-amount);
        target.setBalance(target.getBalance()+amount);
        accountRepository.save(source);
        accountRepository.save(target);
        saveTransaction("Transfer",amount,from,to);
        return "Transfer successful";
    }
    private void saveTransaction(String type,Double amount,String source,String target){
        TransactionDetails transactionDetails=new TransactionDetails();
        transactionDetails.setType(type);
        transactionDetails.setAmount(amount);
        transactionDetails.setSourceAccount(source);
        transactionDetails.setTargetAccount(target);
        transactionRepository.save(transactionDetails);
    }

}
