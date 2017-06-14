package com.stef.MagazineProject.mysql;

import com.stef.MagazineProject.DAO.AbstractDao;
import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.domain.OrderLine;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLOrderLineDao extends AbstractDao<OrderLine,Integer> {

    public MySQLOrderLineDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO orders_goods(order_id,good_id,count_goods,order_line_price) VALUES(?,?,?,?);";
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
    public ArrayList<OrderLine> parseResultSet(ResultSet resultSet) throws DaoException {
        throw new NotImplementedException();
    }

    @Override
    public void statementUpdate(PreparedStatement statement, OrderLine obj) throws DaoException {
        throw new NotImplementedException();
    }

    @Override
    public void statementInsert(PreparedStatement statement, OrderLine obj) throws DaoException {
        try {
            statement.setInt(1, obj.getId());
            statement.setInt(2, obj.getGoods().getId());
            statement.setInt(3, obj.getCount());
            statement.setDouble(4,obj.getPrice());
        } catch (SQLException e) {
            throw new DaoException(e+"error with statement insert");
        }
    }


    @Override
    public OrderLine create() throws DaoException {
        throw new NotImplementedException();
    }

}