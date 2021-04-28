package org.menu;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.log4j.Logger;
import org.menu.exception.BankException;
import org.menu.model.Customer;
import org.menu.model.Employee;
import org.menu.model.User;
import org.menu.service.BankAppSearch;
import org.menu.service.impl.BankAppSearchImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to Bank App V1.0");
        Logger log=Logger.getLogger(Main.class);
        Scanner scanner=new Scanner(System.in);
        BankAppSearch bankAppSearch= (BankAppSearch) new BankAppSearchImpl();
        String username, password;
        boolean exit =false;
        User user = null;
        System.out.println("Would you like to Login to the Bank App (Yes/No)");
        String logE= scanner.nextLine();
        if (logE.matches("[Yy]")){
            exit = true;
        }
        while(!exit) {

            do {
                System.out.println("Login screen");
                System.out.println("Please enter Username");
                username = scanner.nextLine();
                System.out.println("Please enter Password (case sensitive)");
                password = scanner.nextLine();
                try {
                    user = bankAppSearch.userExists(username, password);
                    if (user != null) {
                        System.out.println("User has logged in");
                        System.out.println(user);
                    }
                } catch (BankException e) {
                    e.printStackTrace();
                }

                if (user.getType() == "employee"){
                    Employee employee=new Employee(user.getUsername(),user.getPassword(), user.getId(), user.getType());
                }else {
                    Customer customer=new Customer(user.getUsername(),user.getPassword(), user.getId(), user.getType());
                }

                System.out.println("Would you like to Logout of the Bank App (Yes/No)");
                logE= scanner.nextLine();
                if (logE.matches("[Yy]")){
                    exit = true;
                }else {
                    exit = false;
                }
            } while (!exit);
        }
        System.out.println("Thank You for using the app");
    }
}
