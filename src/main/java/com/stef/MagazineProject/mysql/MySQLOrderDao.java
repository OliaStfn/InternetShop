package com.stef.MagazineProject.mysql;

import com.stef.MagazineProject.DAO.AbstractDao;
import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.domain.Order;
import org.apache.commons.lang.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLOrderDao extends AbstractDao<Order, Integer> {

    public MySQLOrderDao(Connection connection) {
        super(connection);
    }
    
    @Override
    public String getSelectQuery() {
        return "SELECT * FROM orders_personal WHERE order_id=?;";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM orders_personal;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO orders_personal(client_id,all_price,order_status,order_date) VALUES(?,?,?,NOW());";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE orders_personal SET client_id=?,all_price=?,order_status=? WHERE order_id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM orders_personal WHERE order_id=?;";
    }

    @Override
    public ArrayList<Order> parseResultSet(ResultSet resultSet) throws DaoException {
        ArrayList<Order> orders = new ArrayList<Order>();
        try {
            while (resultSet.next()) {
                Order item = new Order();
                item.setId(resultSet.getInt("order_id"));
                item.setClientId(resultSet.getInt("client_name"));
                item.setAllPrice(resultSet.getDouble("all_price"));
                item.setStatus(resultSet.getString("order_status"));
                item.setOrderDate(convertToGD(resultSet.getDate("order_date")));
                orders.add(item);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public void statementUpdate(PreparedStatement statement, Order obj) throws DaoException {
        try {
            statement.setInt(1, obj.getClientId());
            statement.setDouble(2, obj.getAllPrice());
            statement.setString(3, obj.getStatus());
            statement.setInt(4, obj.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void statementInsert(PreparedStatement statement, Order obj) throws DaoException {
        try {
            statement.setInt(1, obj.getClientId());
            statement.setDouble(2, obj.getAllPrice());
            statement.setString(3, obj.getStatus());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Order create() throws DaoException {
        throw new NotImplementedException("Not implemented");
    }
}
