package services;

import models.Account;

public class AuthenticationService {

    private static Account currentUser;
    private static String currentBankName;

    public static void login(String bankName, String email, String password) {
        currentUser = AccountService.getAccount(bankName, email, password);
        currentBankName = bankName;
    }

    public static void register(String bankName, String name, String email, String password) {
        AccountService.createAccount(bankName, name, email, password);
    }

    public static void logout() {
        currentUser = null;
        currentBankName = null;
    }

    public static String getCurrentBankName() {
        return currentBankName;
    }

    public static Account getCurrentUser() {
        return currentUser;
    }

}
