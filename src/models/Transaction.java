package models;

public record Transaction(String originatingAccountId, String resultingAccountId, float amount, String reason) {
}
