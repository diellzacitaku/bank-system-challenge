package models;

public record Transaction(float amount, String originatingAccountId, String resultingAccountId, String reason) {
}
