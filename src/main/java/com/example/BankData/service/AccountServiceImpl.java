package com.example.BankData.service;

import com.example.BankData.Repository.AccountRepository;
import com.example.BankData.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements  AccountService{
    @Autowired
    AccountRepository repo;
    @Override
    public Account createAccount(Account account) {

        return repo.save(account);
    }

    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
      Optional<Account> account=repo.findById(accountNumber);
      if(account.isEmpty())
      {
          throw new RuntimeException("Account is not prsent");
      }
      Account account_found=account.get();
        return account_found;
    }

    @Override
    public List<Account> getAllAccountDetails() {
     List<Account>   listOfAccounts =repo.findAll();

        return listOfAccounts;
    }

    @Override
    public Account depositAmount(Long accountNumber, Double amount) {
       Optional<Account> account=repo.findById(accountNumber);
        if(account.isEmpty())
        {
            throw new RuntimeException("Account is not present");
        }
        Account accountPrsent=account.get();
        Double totalbalance = accountPrsent.getAccount_balance() + amount;
        accountPrsent.setAccount_balance(totalbalance);
        repo.save(accountPrsent);
        return accountPrsent;

    }

    @Override
    public Account withdrawAmount(Long accountNumber, Double amount) {
        Optional<Account> account=repo.findById(accountNumber);
        if(account.isEmpty())
        {
            throw new RuntimeException("Account is not present");
        }
        Account accountPrsent=account.get();
         Double accountBalance=  accountPrsent.getAccount_balance()-amount;
        accountPrsent.setAccount_balance(accountBalance);
        repo.save(accountPrsent);
        return accountPrsent;
    }

    @Override
    public void closeAccount(Long accountNumber) {
        getAccountDetailsByAccountNumber(accountNumber);
        repo.deleteById(accountNumber);

    }
}
