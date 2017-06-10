package com.stef.MagazineProject.mysql;

import com.stef.MagazineProject.DAO.AbstractDao;
import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.DAO.GenericDao;
import com.stef.MagazineProject.domain.FavouriteList;
import org.apache.commons.lang.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class MySQLFavouriteListDao extends AbstractDao<FavouriteList,Integer> {

    public MySQLFavouriteListDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM favourite_lists WHERE favourite_list_id=";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM favourite_lists;";
    }


    @Override
    public String getCreateQuery() {
        return "INSERT INTO favourite_lists(client_id) VALUES(?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE favourite_lists SET client_id=? WHERE favourite_list_id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM favourite_lists WHERE favourite_list_id=?";
    }

    @Override
    public ArrayList<FavouriteList> parseResultSet(ResultSet resultSet) throws DaoException {
        ArrayList<FavouriteList> clients = new ArrayList<FavouriteList>();
        try {
            while (resultSet.next()) {
                FavouriteList favouriteList = new FavouriteList();
                favouriteList.setId(resultSet.getInt("favourite_list_id"));
                favouriteList.setClientId(resultSet.getInt("client_id"));
                clients.add(favouriteList);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return clients;
    }

    @Override
    public void statementUpdate(PreparedStatement statement, FavouriteList obj) throws DaoException {
        try {
            statement.setInt(1, obj.getClientId());
            statement.setInt(2, obj.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void statementInsert(PreparedStatement statement, FavouriteList obj) throws DaoException {
        try {
            statement.setInt(1, obj.getClientId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public FavouriteList create() throws DaoException {
       throw new NotImplementedException();
    }
}
