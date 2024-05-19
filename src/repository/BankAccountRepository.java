package repository;

import models.Account;
import models.Bank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankAccountRepository {

    public static final Map<Bank, List<Account>> bankAccounts = new HashMap<>();

}
