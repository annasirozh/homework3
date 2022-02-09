package com.useraccountproject.model;

public class Transaction {

    private int transactinId;
    private int accountId;
    private int amount;

    public Transaction(int transactinId, int accountId, int amount) {
        this.transactinId = transactinId;
        this.accountId = accountId;
        this.amount = amount;
    }

    public int getTransactinId() {
        return transactinId;
    }
    public void setTransactinId(int transactinId) {
        this.transactinId = transactinId;
    }
    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
