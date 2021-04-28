package org.menu;

import org.apache.log4j.Logger;
import org.menu.exception.BankException;
import org.menu.model.Account;
import org.menu.model.Transaction;
import org.menu.model.User;
import org.menu.service.BankAppSearch;
import org.menu.service.impl.BankAppSearchImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BankException {

        System.out.println("Welcome to Bank App V1.0");
        Logger log = Logger.getLogger(Main.class);
        Scanner scanner = new Scanner(System.in);
        BankAppSearch bankAppSearch = (BankAppSearch) new BankAppSearchImpl();
        String username, password, logE;
        boolean exit = true, loginE;
        User user = null;
        do {
            System.out.println("Would you like to Login to the Bank App (Yes/No/Create)");
            logE = scanner.nextLine();
            if (logE.equals("yes") || logE.equals("Yes")) {
                exit = false;
                loginE = false;
            } else if (logE.equalsIgnoreCase("create")) {
                System.out.println("Enter a username for the account");
                username = scanner.nextLine();
                System.out.println("Enter a password for the account");
                password = scanner.nextLine();
                user = bankAppSearch.createUser(username, password);
                loginE = true;
            } else {
                loginE = false;
            }
        } while (loginE);
        while (!exit) {
            System.out.println("Login screen");
            System.out.println("Please enter Username or Exit");
            username = scanner.nextLine();
            if (username.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println("Please enter Password (case sensitive)");
            password = scanner.nextLine();
            try {
                user = bankAppSearch.getUser(username, password);
                if (user != null) {
                    System.out.println("User has logged in");
                    System.out.println(user);
                }


                if (user.getType().equals("employee")) {
                    int ch = 0;
                    do {
                        System.out.println("Welcome Employee:");
                        System.out.println("1) Approve or reject account");
                        System.out.println("2) View bank account");
                        System.out.println("3) View a log of transactions from all account");
                        System.out.println("4) Exit back");
                        try {
                            ch = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {

                        }
                        switch (ch) {
                            case 1://approve and reject
                                try {
                                    List<Account> accountList = bankAppSearch.getARAccount();
                                    System.out.println("Finished");
                                } catch (BankException e) {
                                    System.out.println(e);
                                }
                                break;
                            case 2://view bank account
                                System.out.println("Enter customer id");
                                try {
                                    int id = Integer.parseInt(scanner.nextLine());
                                    List<Account> accounts = bankAppSearch.getCustomerAccount(id);
                                    if (accounts != null) {
                                        System.out.println("Accounts found");
                                        System.out.println(accounts);
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Enter integer only");
                                }
                                break;
                            case 3://view log of all transcript
                                try {
                                    List<Transaction> transactionList = bankAppSearch.getTransactions();
                                    if (transactionList != null) {
                                        System.out.println("Accounts found");
                                        System.out.println(transactionList);
                                    }
                                } catch (BankException e) {
                                    System.out.println(e);
                                }
                                break;
                            case 4://exit
                                break;
                            default:
                                System.out.println("Incorrect response");
                        }
                    } while (ch != 4);
                } else {//customer
                    int ch = 0;
                    do {
                        System.out.println("Welcome Customer:");
                        System.out.println("1) Apply for a new bank account");
                        System.out.println("2) View balance of one account");
                        System.out.println("3) Withdrawal or deposit in an account");
                        System.out.println("4) Post money transfer to an other account");
                        System.out.println("5) Accept money transfer from an other account");
                        System.out.println("6) Exit");
                        try {
                            ch = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {

                        }
                        switch (ch) {
                            case 1://new account
                                try {
                                    System.out.println("Enter base balance greater than $50 for your account");

                                    double amount = Double.parseDouble(scanner.nextLine());
                                    bankAppSearch.createAccount(amount, user.getId());
                                }catch (BankException e){
                                    System.out.println(e);
                                }
                                break;
                            case 2://view balance
                                try {
                                    List<Account> accountList = bankAppSearch.getCustomerAccount(user.getId());

                                    for (int i = 0; i < accountList.size(); i++) {
                                        System.out.println(accountList.get(i).getAccount_number());
                                    }
                                    System.out.println("Enter the account number that you want to see the balance of: ");
                                    int account_number = Integer.parseInt(scanner.nextLine());
                                    Account account = bankAppSearch.getAccount(account_number);
                                    System.out.println(account.getBalance());
                                } catch (BankException e) {
                                    System.out.println(e);
                                }
                                break;
                            case 3://withdrawal or deposit
                                try {
                                    List<Account> accounts = bankAppSearch.getCustomerAccount(user.getId());

                                    System.out.println(accounts);
                                    System.out.println("Would you like to withdrawal or deposit");
                                    String ans = scanner.nextLine();
                                    if (ans.equalsIgnoreCase("withdrawal")) {
                                        System.out.println("Enter the amount you would like to withdrawal");
                                        double amt = Double.parseDouble(scanner.nextLine());
                                        System.out.println("Enter the account number you would like to take from");
                                        int acc_num = Integer.parseInt(scanner.nextLine());
                                        bankAppSearch.withdrawalFromAccount(amt, acc_num, user.getId());
                                    } else if (ans.equalsIgnoreCase("deposit")) {
                                        System.out.println("Enter the amount you would like to deposit");
                                        double amt = Double.parseDouble(scanner.nextLine());
                                        System.out.println("Enter the account number you would like to put in");
                                        int acc_num = Integer.parseInt(scanner.nextLine());
                                        bankAppSearch.withdrawalFromAccount(amt, acc_num, user.getId());
                                    }
                                }catch (BankException e){
                                    System.out.println(e);
                                }
                                break;
                            case 4://post money transfer
                                try {
                                    System.out.println("How much money would you like to post");
                                    double post = Double.parseDouble(scanner.nextLine());
                                    System.out.println("What account number would you like to take from");
                                    int account1 = Integer.parseInt(scanner.nextLine());
                                    System.out.println("what account number would you like to put it in");
                                    int account2 = Integer.parseInt(scanner.nextLine());
                                    bankAppSearch.postMoney(post, account1, account2);
                                }catch (NumberFormatException e){
                                    System.out.println(e);
                                }
                                break;
                            case 5://accept money transfer
                                try {
                                    System.out.println("How much money would you like to accept");
                                    double post = Double.parseDouble(scanner.nextLine());
                                    System.out.println("What account number would you like to put it in");
                                    int account1 = Integer.parseInt(scanner.nextLine());
                                    System.out.println("what account number would you like to accept it from");                                  int account2 = Integer.parseInt(scanner.nextLine());
                                    bankAppSearch.acceptMoney(post, account1, account2);
                                }catch (NumberFormatException e){
                                    System.out.println(e);
                                }
                                break;
                            case 6://exit
                                break;
                            default:
                                System.out.println("Incorrect input");
                        }
                    } while (ch != 6);
                }


                System.out.println("Would you like to Logout of the Bank App (Yes/No)");
                logE = scanner.nextLine();
                if (logE.equals("yes") || logE.equals("Yes")) {
                    exit = true;
                } else {
                    exit = false;
                }
            } catch (BankException e) {
            }
        }
        System.out.println("Thank You for using the app");
    }
}
