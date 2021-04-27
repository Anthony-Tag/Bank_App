package org.menu;

import org.menu.exception.BankException;
import org.menu.model.User;
import org.menu.service.BankAppSearch;
import org.menu.service.impl.BankAppSearchImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to Bank App V1.0");
        Scanner scanner=new Scanner(System.in);
        BankAppSearch bankAppSearch= (BankAppSearch) new BankAppSearchImpl();
        String username="jpeters", password="Password1";
        boolean exist =false;
        User user = null;
        do {
            System.out.println("Login screen");
            System.out.println("Please enter Username");
            //username = scanner.nextLine();
            System.out.println("Please enter Password (case sensitive)");
            //password = scanner.nextLine();
            try {
                user=bankAppSearch.userExists(username, password);
                if (user!=null){
                    System.out.println("User has logged in");
                    System.out.println(user);
                    exist = true;
                }
            } catch (BankException e) {
                e.printStackTrace();
            }
            if (exist){
                break;
            }
        }while (!exist);

    }
}
