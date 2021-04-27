package org.menu.dao;

import org.menu.exception.BankException;
import org.menu.model.User;

public interface UserDAO {
    public User getUser(String username, String password) throws BankException;
}
