package org.menu.model;

public class Customer extends User{

    private int user_id;
    private int[] account_number;

    public Customer(String username, String password, int id, String type, int user_id, int[] account_number) {
        super(username, password, id, type);
        this.user_id = user_id;
        this.account_number = account_number;
    }

    public Customer(){}

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int[] getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int[] account_number) {
        this.account_number = account_number;
    }
}
