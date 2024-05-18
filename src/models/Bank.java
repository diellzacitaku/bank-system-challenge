package models;

import java.util.ArrayList;
import java.util.List;

public final class Bank {
    private final String name;
    private final List<Account> accounts = new ArrayList<>();
    private float totalTransactionFeeAmount = 0;
    private float totalTransferAmount = 0;
    private float transactionFlatFeeAmount = 10f;
    private float transactionPercentFeeValue = 5f;

    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

}
