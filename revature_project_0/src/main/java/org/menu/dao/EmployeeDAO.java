package org.menu.dao;

import org.menu.exception.BankException;
import org.menu.model.Employee;
import org.menu.model.User;

public interface EmployeeDAO {
    public Employee login(String username, String password) throws BankException;
}
