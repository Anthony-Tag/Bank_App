package org.menu.service.impl;

import org.menu.dao.CustomerDAO;
import org.menu.dao.EmployeeDAO;
import org.menu.dao.UserDAO;
import org.menu.dao.impl.CustomerDAOImpl;
import org.menu.dao.impl.EmployeeDAOImpl;
import org.menu.dao.impl.UserDAOImpl;
import org.menu.exception.BankException;
import org.menu.model.Customer;
import org.menu.model.Employee;
import org.menu.model.User;
import org.menu.service.BankAppSearch;

public class BankAppSearchImpl implements BankAppSearch {
    private UserDAO userDAO=new UserDAOImpl();
    private CustomerDAO customerDAO=new CustomerDAOImpl();
    private EmployeeDAO employeeDAO=new EmployeeDAOImpl();
    @Override
    public User userExists(String username, String password) throws BankException {
        User user =null;
        user = userDAO.getUser(username, password);
        return user;
    }

    @Override
    public Customer newAccount(User user) {
        return null;
    }
}
