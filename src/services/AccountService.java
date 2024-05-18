package services;

import models.Account;
import repository.AccountRepository;

public class AccountService {

    public Account getAccount(String accountId) {
        Account account = AccountRepository.accounts.get(accountId);
        if (account == null) {
            throw new RuntimeException();
        }
        return account;
    }

    public float getAccountBalance(String accountId) {
        return getAccount(accountId).getBalance();
    }

    public Account createAccount(String name) {
        Account account = new Account(name);
        AccountRepository.accounts.put(account.getId(), account);
        return account;
    }

    public float withdraw(String accountId, float amount) {
        validateAmount(amount);

        float currentAccountBalance = getAccountBalance(accountId);
        getAccount(accountId).setBalance(currentAccountBalance - amount);

        return getAccountBalance(accountId);
    }

    public float deposit(String accountId, float amount) {
        validateAmount(amount);

        float currentAccountBalance = getAccountBalance(accountId);
        getAccount(accountId).setBalance(currentAccountBalance + amount);

        return getAccountBalance(accountId);
    }

    private void validateAmount(float amount) {
        if (amount < 0) {
            throw new RuntimeException();
        }
    }

}
