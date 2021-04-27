package org.menu.dao.impl;

import org.menu.dao.UserDAO;
import org.menu.dao.dbutil.PostgresSqlConnection;
import org.menu.exception.BankException;
import org.menu.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public User getUser(String username, String password) throws BankException {
        User user=null;
        try(Connection connection= PostgresSqlConnection.getConnection()) {
            String sql = "select u.username, u.\"password\", u.id from bank_app.\"user\" u where u.username = ? and u.\"password\" = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setId(resultSet.getInt("id"));
            }else{
                throw new BankException("No User found with this username and password");
            }

        } catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
            throw new BankException("Internal error occured in getUser... contact SysAdmin");
        }
        return user;
    }
}
