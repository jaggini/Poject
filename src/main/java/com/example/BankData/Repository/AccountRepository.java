package com.example.BankData.Repository;

import com.example.BankData.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {


    //Account findByAccountNumber(Long accountNumber);

}
