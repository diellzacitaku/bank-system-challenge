package services;

import models.Account;
import models.Bank;
import repository.BankAccountRepository;
import repository.BankRepository;

import java.util.*;

public class BankService {

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
        BankAccountRepository.bankAccounts.put(bank, Map.of());

        return bank;
    }

}
