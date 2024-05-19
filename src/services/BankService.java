package services;

import exception.BankNotFoundException;
import models.Bank;
import repository.BankAccountRepository;
import repository.BankRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankService {

    public static Bank getBank(String bankName) {
        return BankRepository.banks
                .stream()
                .filter(bank -> Objects.equals(bank.getName(), bankName))
                .findFirst()
                .orElseThrow(BankNotFoundException::new);
    }

    public static List<Bank> getAllBanks() {
        return BankRepository.banks;
    }

    public static void createBank(String name) {
        Bank bank = new Bank(name);

        BankRepository.banks.add(bank);
        BankAccountRepository.bankAccounts.put(bank, new ArrayList<>());
    }

}
