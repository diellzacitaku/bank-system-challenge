package services;

import models.Account;
import models.Bank;
import repository.BankAccountRepository;
import repository.TransactionRepository;

import java.util.ArrayList;
import java.util.Objects;

import static java.util.List.of;

public class AccountService {

    public static Account createAccount(String bankName, String accountName, String email, String password) {
        Account account = new Account(accountName, email, password);
        Bank bank = BankService.getBank(bankName);

        BankAccountRepository.bankAccounts.get(bank).add(account);
        TransactionRepository.accountTransactions.put(account, new ArrayList<>());

        return account;
    }

    public static Account getAccount(String bankName, String email, String password) {
        Bank bank = BankService.getBank(bankName);
        return BankAccountRepository.bankAccounts.get(bank)
                .stream()
                .filter(account -> Objects.equals(account.getEmail(), email) &&
                        Objects.equals(account.getPassword(), password))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public static Account getAccount(String bankName, String accountId) {
        Bank bank = BankService.getBank(bankName);
        return BankAccountRepository.bankAccounts.get(bank)
                .stream()
                .filter(account -> Objects.equals(account.getId(), accountId))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public static float getAccountBalance(String bankName, String accountId) {
        return getAccount(bankName, accountId).getBalance();
    }

}
