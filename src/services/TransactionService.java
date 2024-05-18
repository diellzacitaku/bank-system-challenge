package services;

import models.Account;
import models.Bank;
import models.Transaction;
import models.TransactionFeeType;
import repository.TransactionRepository;

public class TransactionService {
    private final AccountService accountService;
    private final BankService bankService;

    public TransactionService(AccountService accountService, BankService bankService) {
        this.accountService = accountService;
        this.bankService = bankService;
    }

    public Transaction transferFunds(String originatingBankName, String receivingBankName,  String originatingAccountId,  String resultingAccountId, float amount, String reason, TransactionFeeType transactionFeeType) {
        Account originatingAccount = accountService.getAccount(originatingBankName, originatingAccountId);
        Account resultingAccount = accountService.getAccount(receivingBankName, resultingAccountId);

        withdraw(originatingBankName, originatingAccount.getId(), amount, transactionFeeType);
        deposit(receivingBankName, resultingAccount.getId(), amount, transactionFeeType);

        Transaction transaction = new Transaction(originatingAccountId, resultingAccountId, amount, reason);
        TransactionRepository.transactions.add(transaction);

        return transaction;
    }

    public Transaction transferFunds(String bankName, String originatingAccountId, String resultingAccountId, float amount, String reason, TransactionFeeType transactionFeeType) {
        Account originatingAccount = accountService.getAccount(bankName, originatingAccountId);
        Account resultingAccount = accountService.getAccount(bankName, resultingAccountId);

        withdraw(bankName, originatingAccount.getId(), amount, transactionFeeType);
        deposit(bankName, resultingAccount.getId(), amount, transactionFeeType);

        Transaction transaction = new Transaction(originatingAccountId, resultingAccountId, amount, reason);
        TransactionRepository.transactions.add(transaction);

        return transaction;
    }

    public Transaction withdraw(String bankName, String accountId, float amount, TransactionFeeType transactionFeeType) {
        float fee = calculateFee(bankName, amount, transactionFeeType);

        float currentAccountBalance = accountService.getAccountBalance(bankName, accountId);
        accountService.getAccount(bankName, accountId).setBalance(currentAccountBalance - amount - fee);

        Transaction transaction = new Transaction(accountId, null, amount, "withdraw");
        TransactionRepository.transactions.add(transaction);

        return transaction;
    }

    public Transaction deposit(String bankName, String accountId, float amount, TransactionFeeType transactionFeeType) {
        float fee = calculateFee(bankName, amount, transactionFeeType);

        float currentAccountBalance = accountService.getAccountBalance(bankName, accountId);
        accountService.getAccount(bankName, accountId).setBalance(currentAccountBalance + amount - fee);

        Transaction transaction = new Transaction(null, accountId, amount, "deposit");
        TransactionRepository.transactions.add(transaction);

        return transaction;
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
