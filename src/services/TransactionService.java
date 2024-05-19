package services;

import models.*;
import repository.TransactionRepository;

import java.util.List;

public class TransactionService {

    public static List<Transaction> getAllTransactions(String bankName, String accountId) {
        Account account = AccountService.getAccount(bankName, accountId);
        return TransactionRepository.accountTransactions.get(account);
    }

    public static void transferFunds(String originatingBankName,
                                     String receivingBankName,
                                     String originatingAccountId,
                                     String resultingAccountId,
                                     float amount,
                                     TransactionFeeType transactionFeeType,
                                     String reason) {
        Account originatingAccount = AccountService.getAccount(originatingBankName, originatingAccountId);
        Account resultingAccount = AccountService.getAccount(receivingBankName, resultingAccountId);

        withdraw(originatingBankName, originatingAccount.getId(), amount, transactionFeeType, reason);
        deposit(receivingBankName, resultingAccount.getId(), amount, transactionFeeType, reason);
    }

    public static void transferFunds(String bankName,
                                     String originatingAccountId,
                                     String resultingAccountId,
                                     float amount,
                                     TransactionFeeType transactionFeeType,
                                     String reason) {
        Account originatingAccount = AccountService.getAccount(bankName, originatingAccountId);
        Account resultingAccount = AccountService.getAccount(bankName, resultingAccountId);

        withdraw(bankName, originatingAccount.getId(), amount, transactionFeeType, reason);
        deposit(bankName, resultingAccount.getId(), amount, transactionFeeType, reason);
    }

    public static void withdraw(String bankName,
                                String accountId,
                                float amount,
                                TransactionFeeType transactionFeeType,
                                String reason) {
        float fee = calculateFee(bankName, amount, transactionFeeType);
        Account account = updateAccountBalance(bankName, accountId, -amount - fee);

        updateBankTotalTransactionAmount(bankName, amount, fee);

        Transaction transaction = new Transaction(TransactionType.WITHDRAWAL, accountId, null, amount, fee, reason);
        TransactionRepository.accountTransactions.get(account).add(transaction);
    }

    public static void deposit(String bankName,
                               String accountId,
                               float amount,
                               TransactionFeeType transactionFeeType,
                               String reason) {
        float fee = calculateFee(bankName, amount, transactionFeeType);
        Account account = updateAccountBalance(bankName, accountId, amount - fee);

        updateBankTotalTransactionAmount(bankName, amount, fee);

        Transaction transaction = new Transaction(TransactionType.DEPOSIT, null, accountId, amount, fee, reason);
        TransactionRepository.accountTransactions.get(account).add(transaction);
    }

    private static Account updateAccountBalance(String bankName, String accountId, float amount) {
        float currentAccountBalance = AccountService.getAccountBalance(bankName, accountId);
        Account account = AccountService.getAccount(bankName, accountId);
        account.setBalance(currentAccountBalance + amount);
        return account;
    }

    private static void updateBankTotalTransactionAmount(String bankName, float fee, float amount) {
        Bank bank = BankService.getBank(bankName);

        float bankTotalFeeAmount = bank.getTotalTransactionFeeAmount();
        bank.setTotalTransactionFeeAmount(bankTotalFeeAmount + fee);

        float bankTotalAmount = bank.getTotalTransferAmount();
        bank.setTotalTransferAmount(bankTotalAmount + amount);
    }

    private static float calculateFee(String bankName, float amount, TransactionFeeType transactionFeeType) {
        Bank bank = BankService.getBank(bankName);
        validateAmount(amount);

        return switch (transactionFeeType) {
            case FLAT -> bank.getTransactionFlatFeeAmount();
            case PERCENTAGE -> amount * (bank.getTransactionPercentFeeValue() * 0.01f);
        };
    }

    private static void validateAmount(float amount) {
        if (amount < 0) {
            throw new RuntimeException();
        }
    }

}
