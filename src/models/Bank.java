package models;

public final class Bank {

    private final String name;
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

    @Override
    public String toString() {
        return "\nBank name='" + name + '\'' +
                ", transaction flat fee amount=" + transactionFlatFeeAmount + "$" +
                ", transaction percent fee value=" + transactionPercentFeeValue + "%";
    }

}
