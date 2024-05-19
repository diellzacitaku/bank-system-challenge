package services.options;

import exception.InvalidOptionsException;
import models.Account;
import models.TransactionFeeType;
import services.AuthenticationService;
import services.TransactionService;

import java.util.Scanner;

public class BankingOptionsService implements UserOptions {

    private final Scanner scanner;

    public BankingOptionsService() {
        this.scanner = new Scanner(System.in);
    }

    public void showOptions() {
        System.out.println();
        System.out.println(AuthenticationService.getCurrentUser().getName() + ", please choose an option: ");
        System.out.println("(1) Withdraw");
        System.out.println("(2) Deposit");
        System.out.println("(3) Transfer inside the bank");
        System.out.println("(4) Transfer to another bank");
        System.out.println("(5) Check account balance");
        System.out.println("(6) Check transaction history");
        System.out.println("(7) Check account information");
        System.out.println("(8) Log out");
        System.out.print("Your choice: ");

        int input;
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidOptionsException();
        }

        switch (input) {
            case 1 -> withdraw();
            case 2 -> deposit();
            case 3 -> internalTransfer();
            case 4 -> externalTransfer();
            case 5 -> balance();
            case 6 -> transactionHistory();
            case 7 -> accountInformation();
            case 8 -> logout();
            default -> throw new InvalidOptionsException();
        }
    }

    private void withdraw() {
        System.out.println();
        System.out.println("--- Withdraw funds ---");

        float amount = readAmount();
        TransactionFeeType feeType = readTransactionFeeType();

        String currentBankName = AuthenticationService.getCurrentBankName();
        Account account = AuthenticationService.getCurrentUser();

        TransactionService.withdraw(currentBankName, account.getId(), amount, feeType, "ATM");
    }

    private void deposit() {
        System.out.println();
        System.out.println("--- Deposit funds ---");

        float amount = readAmount();
        TransactionFeeType feeType = readTransactionFeeType();

        String currentBankName = AuthenticationService.getCurrentBankName();
        Account account = AuthenticationService.getCurrentUser();

        TransactionService.deposit(currentBankName, account.getId(), amount, feeType, "ATM");
    }

    private void internalTransfer() {
        System.out.println();
        System.out.println("--- Transfer funds internally ---");

        System.out.print("Please enter the ID of the receiving account: ");
        String receivingAccountId = scanner.nextLine();

        System.out.print("What is the reason for the transaction?: ");
        String reason = scanner.nextLine();

        float amount = readAmount();
        TransactionFeeType feeType = readTransactionFeeType();

        String currentBankName = AuthenticationService.getCurrentBankName();
        Account account = AuthenticationService.getCurrentUser();

        TransactionService.transferFunds(currentBankName, account.getId(), receivingAccountId, amount, feeType, reason);
    }

    private void externalTransfer() {
        System.out.println();
        System.out.println("--- Transfer funds externally ---");

        System.out.print("Please enter the ID of the receiving account: ");
        String receivingAccountId = scanner.nextLine();

        System.out.print("Please enter the name of the receiving bank: ");
        String receivingBankName = scanner.nextLine();

        System.out.print("What is the reason for the transaction?: ");
        String reason = scanner.nextLine();

        float amount = readAmount();
        TransactionFeeType feeType = readTransactionFeeType();

        String currentBankName = AuthenticationService.getCurrentBankName();
        Account account = AuthenticationService.getCurrentUser();

        TransactionService.transferFunds(currentBankName, receivingBankName, account.getId(), receivingAccountId,
                amount, feeType, reason);
    }

    private void balance() {
        System.out.println();
        System.out.println("--- Account balance ---");

        Account account = AuthenticationService.getCurrentUser();
        System.out.println("Your current balance: " + account.getBalance());
    }

    private void transactionHistory() {
        System.out.println();
        System.out.println("--- Transaction history ---");

        String currentBankName = AuthenticationService.getCurrentBankName();
        Account currentAccount = AuthenticationService.getCurrentUser();

        System.out.println(TransactionService.getAllTransactions(currentBankName, currentAccount.getId()));
    }

    private void accountInformation() {
        System.out.println();
        System.out.println("--- Account Information ---");

        Account account = AuthenticationService.getCurrentUser();
        System.out.println("Your account Id: " + account.getId());
        System.out.println("Your account name: " + account.getName());
        System.out.println("Your account email: " + account.getEmail());

    }

    private void logout() {
        AuthenticationService.logout();
    }

    private float readAmount() {
        System.out.print("Please enter the amount: ");
        float input;
        try {
            input = Float.parseFloat(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidOptionsException();
        }
        return input;
    }

    private TransactionFeeType readTransactionFeeType() {
        System.out.print("Please enter your preferred fee type (flat or percentage): ");
        String feeType = scanner.nextLine();
        return switch (feeType.toLowerCase()) {
            case "flat" -> TransactionFeeType.FLAT;
            case "percentage" -> TransactionFeeType.PERCENTAGE;
            default -> throw new InvalidOptionsException();
        };
    }

}
