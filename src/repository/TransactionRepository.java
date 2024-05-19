package repository;

import models.Account;
import models.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepository {

    public static final Map<Account, List<Transaction>> accountTransactions = new HashMap<>();

}
