package services;

import models.Account;
import models.Bank;
import repository.BankAccountRepository;

public class AccountService {
    private final BankService bankService;

    public AccountService(BankService bankService) {
        this.bankService = bankService;
    }

    public Account createAccount(String bankName, String accountName) {
        Account account = new Account(accountName);
        Bank bank = bankService.getBank(bankName);

        BankAccountRepository.bankAccounts.get(bank).put(account.getId(), account);

        return account;
    }

    public Account getAccount(String bankName, String accountId) {
        Bank bank = bankService.getBank(bankName);
        Account account = BankAccountRepository.bankAccounts.get(bank).get(accountId);

        if (account == null) {
            throw new RuntimeException();
        }
        return account;
    }

    public float getAccountBalance(String bankName, String accountId) {
        return getAccount(bankName, accountId).getBalance();
    }
}
