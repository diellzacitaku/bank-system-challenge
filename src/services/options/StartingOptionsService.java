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
        System.out.println("(1) Create bank");
        System.out.println("(2) Login account");
        System.out.println("(3) Register account");
        System.out.print("Your choice: ");

        int input;
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidOptionsException();
        }
        System.out.println();

        switch (input) {
            case 1 -> createBank();
            case 2 -> login();
            case 3 -> register();
            default -> throw new InvalidOptionsException();
        }
    }

    private void createBank() {
        System.out.println("--- Create a bank ---");

        System.out.print("Please enter your bank name: ");
        String bankName = scanner.nextLine();

        BankService.createBank(bankName);
    }

    private void login() {
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

}
