import models.Account;
import models.TransactionFeeType;
import services.AccountService;
import services.AuthenticationService;
import services.BankService;
import services.TransactionService;
import services.options.BankingOptionsService;
import services.options.StartingOptionsService;
import services.options.UserOptions;

import java.util.Objects;
import java.util.Scanner;

public class Application {

    public static void populateData() {
        String bktBankName = "BKT";
        BankService.createBank(bktBankName);

        String nlbBankName = "NLB";
        BankService.createBank(nlbBankName);

        Account johnAccount = AccountService.createAccount(bktBankName, "John Smith", "john.smith@gmail.com", "john");
        Account albertAccount = AccountService.createAccount(bktBankName, "Albert Ein", "albert@gmail.com", "albert");
        Account robertAccount =
                AccountService.createAccount(nlbBankName, "Robert Gashi", "robert@gmail.com", "bert123");

        TransactionService.deposit(bktBankName, johnAccount.getId(), 1000.0f, TransactionFeeType.FLAT, "ATM");
        TransactionService.transferFunds(bktBankName, johnAccount.getId(), albertAccount.getId(), 400.0f,
                TransactionFeeType.PERCENTAGE, "Debt");
        TransactionService.transferFunds(bktBankName, nlbBankName, johnAccount.getId(), robertAccount.getId(), 400.0f,
                TransactionFeeType.FLAT, "Friend");
        TransactionService.withdraw(nlbBankName, robertAccount.getId(), 100, TransactionFeeType.PERCENTAGE, "ATM");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Add existing data (yes/no)?: ");
        String populateInput = scanner.nextLine();
        if (Objects.equals(populateInput, "yes")) {
            populateData();
            System.out.println("Successfully added data!");
            System.out.println("--------------------");
        }

        StartingOptionsService userInputService = new StartingOptionsService();
        BankingOptionsService bankingOptionsService = new BankingOptionsService();

        while (true) {
            UserOptions userOptions;
            if (AuthenticationService.getCurrentUser() == null) {
                userOptions = userInputService;
            } else {
                userOptions = bankingOptionsService;
            }
            try {
                userOptions.showOptions();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

}