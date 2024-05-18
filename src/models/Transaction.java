package models;

import java.util.Date;
import java.util.Objects;

public final class Transaction {

    private final Date timestamp;
    private final TransactionType transactionType;
    private final String originatingAccountId;
    private final String receivingAccountId;
    private final float amount;
    private final float fee;
    private final String reason;

    public Transaction(TransactionType transactionType,
                       String originatingAccountId,
                       String receivingAccountId,
                       float amount,
                       float fee,
                       String reason) {
        this.timestamp = new Date();
        this.transactionType = transactionType;
        this.originatingAccountId = originatingAccountId;
        this.receivingAccountId = receivingAccountId;
        this.amount = amount;
        this.fee = fee;
        this.reason = reason;
    }

}
