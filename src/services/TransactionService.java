package services;

import models.*;
import repository.TransactionRepository;

import java.util.List;

public class TransactionService {

    private final AccountService accountService;
    private final BankService bankService;

    public TransactionService(AccountService accountService, BankService bankService) {
        this.accountService = accountService;
        this.bankService = bankService;
    }

    public List<Transaction> getAllTransactions(String bankName, String accountId) {
        Account account = accountService.getAccount(bankName, accountId);
        return TransactionRepository.accountTransactions.get(account);
    }

    public void transferFunds(String originatingBankName,
                              String receivingBankName,
                              String originatingAccountId,
                              String resultingAccountId,
                              float amount,
                              String reason,
                              TransactionFeeType transactionFeeType) {
        Account originatingAccount = accountService.getAccount(originatingBankName, originatingAccountId);
        Account resultingAccount = accountService.getAccount(receivingBankName, resultingAccountId);

        withdraw(originatingBankName, originatingAccount.getId(), amount, transactionFeeType, reason);
        deposit(receivingBankName, resultingAccount.getId(), amount, transactionFeeType, reason);
    }

    public void transferFunds(String bankName,
                              String originatingAccountId,
                              String resultingAccountId,
                              float amount,
                              String reason,
                              TransactionFeeType transactionFeeType) {
        Account originatingAccount = accountService.getAccount(bankName, originatingAccountId);
        Account resultingAccount = accountService.getAccount(bankName, resultingAccountId);

        withdraw(bankName, originatingAccount.getId(), amount, transactionFeeType, reason);
        deposit(bankName, resultingAccount.getId(), amount, transactionFeeType, reason);
    }

    public void withdraw(String bankName,
                         String accountId,
                         float amount,
                         TransactionFeeType transactionFeeType,
                         String reason) {
        float fee = calculateFee(bankName, amount, transactionFeeType);

        Account account = updateAccountBalance(bankName, accountId, -amount - fee);

        Transaction transaction =
                new Transaction(TransactionType.WITHDRAWAL, accountId, null, amount, fee, reason);
        TransactionRepository.accountTransactions.get(account).add(transaction);
    }

    public void deposit(String bankName,
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

    private Account updateAccountBalance(String bankName, String accountId, float amount) {
        float currentAccountBalance = accountService.getAccountBalance(bankName, accountId);
        Account account = accountService.getAccount(bankName, accountId);
        account.setBalance(currentAccountBalance + amount);
        return account;
    }

    private void updateBankTotalTransactionAmount(String bankName, float fee, float amount) {
        Bank bank = bankService.getBank(bankName);

        float bankTotalFeeAmount = bank.getTotalTransactionFeeAmount();
        bank.setTotalTransactionFeeAmount(bankTotalFeeAmount + fee);

        float bankTotalAmount = bank.getTotalTransferAmount();
        bank.setTotalTransferAmount(bankTotalAmount + amount);
    }

    private float calculateFee(String bankName, float amount, TransactionFeeType transactionFeeType) {
        Bank bank = bankService.getBank(bankName);
        validateAmount(amount);

        return switch (transactionFeeType) {
            case FLAT -> bank.getTransactionFlatFeeAmount();
            case PERCENTAGE -> amount * bank.getTransactionPercentFeeValue();
        };
    }

    private void validateAmount(float amount) {
        if (amount < 0) {
            throw new RuntimeException();
        }
    }

}
