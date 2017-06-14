package com.stef.MagazineProject.mysql;

import com.stef.MagazineProject.DAO.AbstractDao;
import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.domain.Order;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLOrderStatusDao extends AbstractDao<Order, Integer> {

    public MySQLOrderStatusDao(Connection connection) {
        super(connection);
    }

    @Override
    public Order create() throws DaoException {
        throw new NotImplementedException();
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO order_status (order_id,status_id,order_status_date) " +
                "VALUES ((SELECT LAST_INSERT_ID())," +
                "(SELECT status_id FROM status WHERE status=?),NOW());";
    }

    @Override
    public String getSelectQuery() {
        throw new NotImplementedException();
    }

    @Override
    public String getSelectAllQuery() {
        throw new NotImplementedException();
    }

    @Override
    public String getUpdateQuery() {
        throw new NotImplementedException();
    }

    @Override
    public String getDeleteQuery() {
        throw new NotImplementedException();
    }

    @Override
    public ArrayList<Order> parseResultSet(ResultSet resultSet) throws DaoException {
        throw new NotImplementedException();
    }

    @Override
    public void statementUpdate(PreparedStatement statement, Order obj) throws DaoException {
        throw new NotImplementedException();
    }

    @Override
    public void statementInsert(PreparedStatement statement, Order obj) throws DaoException {
        try {
            statement.setString(1, obj.getStatus());
        } catch (SQLException e) {
            throw new DaoException(e+"error with statement insert");
        }
    }
}