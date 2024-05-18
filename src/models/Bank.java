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

    public float getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public void setTotalTransactionFeeAmount(float totalTransactionFeeAmount) {
        this.totalTransactionFeeAmount = totalTransactionFeeAmount;
    }

    public float getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public void setTotalTransferAmount(float totalTransferAmount) {
        this.totalTransferAmount = totalTransferAmount;
    }

    public float getTransactionFlatFeeAmount() {
        return transactionFlatFeeAmount;
    }

    public void setTransactionFlatFeeAmount(float transactionFlatFeeAmount) {
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
    }

    public float getTransactionPercentFeeValue() {
        return transactionPercentFeeValue;
    }

    public void setTransactionPercentFeeValue(float transactionPercentFeeValue) {
        this.transactionPercentFeeValue = transactionPercentFeeValue;
    }

}
