package models;

import utils.IdUtils;

public class Account {

    private final String id;
    private final String email;
    private final String password;
    private final String name;
    private float balance;
    private static final float DEFAULT_BALANCE = 0;

    public Account(String name, String email, String password) {
        this.id = IdUtils.generateId();
        this.email = email;
        this.password = password;
        this.balance = DEFAULT_BALANCE;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        if (balance < 0) {
            throw new RuntimeException();
        }
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

}