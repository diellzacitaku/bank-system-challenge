package services;

import models.Account;
import models.Bank;
import repository.AccountRepository;
import repository.BankRepository;

import java.util.List;
import java.util.Objects;

public class BankService {
    private final AccountService accountService;

    public BankService(AccountService accountService) {
        this.accountService = accountService;
    }

    public Bank getBank(String bankName) {
        return BankRepository.banks
                .stream()
                .filter(bank -> Objects.equals(bank.getName(), bankName))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public List<Bank> getAllBanks() {
        return BankRepository.banks;
    }

    public Bank createBank(String name) {
        Bank bank = new Bank(name);
        BankRepository.banks.add(bank);
        return bank;
    }

    public Bank addAccount(String accountId, String bankName) {
        Account account = accountService.getAccount(accountId);

        Bank bank = getBank(bankName);
        bank.addAccount(account);

        return bank;
    }

}
