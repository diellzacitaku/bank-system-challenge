package models;

import java.util.List;

public record Bank(String name, List<Account> accounts, float totalTransactionFeeAmount, float totalTransferAmount,
                   float transactionFlatFeeAmount, float transactionPercentFeeValue) {
}
