package services;

import models.Account;
import models.Bank;
import repository.BankAccountRepository;
import repository.BankRepository;

import java.util.*;

import static java.util.List.of;

public class BankService {

    public static Bank getBank(String bankName) {
        return BankRepository.banks
                .stream()
                .filter(bank -> Objects.equals(bank.getName(), bankName))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public static List<Bank> getAllBanks() {
        return BankRepository.banks;
    }

    public static Bank createBank(String name) {
        Bank bank = new Bank(name);

        BankRepository.banks.add(bank);
        BankAccountRepository.bankAccounts.put(bank, new ArrayList<>());

        return bank;
    }

}
