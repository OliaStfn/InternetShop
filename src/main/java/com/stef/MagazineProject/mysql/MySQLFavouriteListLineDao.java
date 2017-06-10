package com.stef.MagazineProject.mysql;

import com.stef.MagazineProject.DAO.AbstractDao;
import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.domain.FavouriteListLine;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLFavouriteListLineDao extends AbstractDao<FavouriteListLine,Integer> {

    public MySQLFavouriteListLineDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO orders_goods(favourite_list_id,goods_id) VALUES(?,?);";
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
    public ArrayList<FavouriteListLine> parseResultSet(ResultSet resultSet) throws DaoException {
        throw new NotImplementedException();
    }

    @Override
    public void statementUpdate(PreparedStatement statement, FavouriteListLine obj) throws DaoException {
        throw new NotImplementedException();
    }

    @Override
    public void statementInsert(PreparedStatement statement, FavouriteListLine obj) throws DaoException {
        try {
            statement.setInt(1, obj.getId());
            statement.setInt(2, obj.getGoods().getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public FavouriteListLine create() throws DaoException {
        throw new NotImplementedException();
    }
}
