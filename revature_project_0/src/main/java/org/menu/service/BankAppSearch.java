package org.menu.service;

import org.menu.exception.BankException;
import org.menu.model.Customer;
import org.menu.model.User;

public interface BankAppSearch {
    public User userExists(String username, String password) throws BankException;
    public Customer newAccount(User user);

}
