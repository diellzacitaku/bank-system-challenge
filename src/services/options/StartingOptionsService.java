package services.options;

import exception.InvalidOptionsException;
import services.AuthenticationService;
import services.BankService;

import java.util.Scanner;

public class StartingOptionsService implements UserOptions {

    private final Scanner scanner;

    public StartingOptionsService() {
        this.scanner = new Scanner(System.in);
    }

    public void showOptions() {
        System.out.println("Please choose an option:");
        System.out.println("(1) Login account");
        System.out.println("(2) Register account");
        System.out.println("(3) Create bank");
        System.out.println("(4) View all banks");
        System.out.println("(5) Check bank total transaction fee amount");
        System.out.println("(6) Check bank total transaction amount");

        System.out.print("Your choice: ");

        int input;
        try {
            input = Integer.parseInt(scanner.nextLine());
            System.out.println();
        } catch (NumberFormatException e) {
            throw new InvalidOptionsException();
        }

        switch (input) {
            case 1 -> login();
            case 2 -> register();
            case 3 -> createBank();
            case 4 -> viewAllBanks();
            case 5 -> viewBankTotalTransactionFee();
            case 6 -> viewBankTotalTransfer();
            default -> throw new InvalidOptionsException();
        }
    }

    private void createBank() {
        System.out.println();
        System.out.println("--- Create a bank ---");

        System.out.print("Please enter your bank name: ");
        String bankName = scanner.nextLine();

        BankService.createBank(bankName);
    }

    private void login() {
        System.out.println();
        System.out.println("--- Login to your account ---");

        System.out.print("Please enter your bank name: ");
        String bankName = scanner.nextLine();

        System.out.print("Please enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();

        AuthenticationService.login(bankName, email, password);
    }

    private void register() {
        System.out.println();
        System.out.println("--- Register an account ---");

        System.out.print("Please enter your bank name: ");
        String bankName = scanner.nextLine();

        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Please enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();

        AuthenticationService.register(bankName, name, email, password);
    }

    private void viewAllBanks() {
        System.out.println();
        System.out.println("--- View all banks ---");
        System.out.println(BankService.getAllBanks());
    }

    private void viewBankTotalTransactionFee() {
        System.out.println();
        System.out.println("--- View bank total transaction fee ---");

        System.out.print("Please enter your bank name: ");
        String bankName = scanner.nextLine();

        System.out.println(
                "Total transaction fee amount: " + BankService.getBank(bankName).getTotalTransactionFeeAmount());
    }

    private void viewBankTotalTransfer() {
        System.out.println();
        System.out.println("--- View bank total transfer amount ---");

        System.out.print("Please enter your bank name: ");
        String bankName = scanner.nextLine();

        System.out.println("Total transfer amount: " + BankService.getBank(bankName).getTotalTransferAmount());
    }

}
