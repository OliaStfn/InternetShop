package com.stef.MagazineProject.mysql;

import com.stef.MagazineProject.DAO.AbstractDao;
import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.domain.FavouriteList;
import com.stef.MagazineProject.domain.FavouriteListLine;
import com.stef.MagazineProject.domain.Goods;
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

    private class GoodsForDB extends Goods {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO favourite_list_goods(favourite_list_id,goods_id) VALUES(?,?);";
    }

    @Override
    public String getSelectQuery() {
       return "SELECT f.*,g.* FROM favourite_list_goods f NATURAL JOIN goods g " +
               "WHERE f.goods_id=g.goods_id " +
               "AND list_id=";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT f.*,g.* FROM favourite_list_goods f NATURAL JOIN goods g " +
                "WHERE f.goods_id=g.goods_id;";    }

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
        ArrayList<FavouriteListLine> lines = new ArrayList<>();
        try {
            while (resultSet.next()) {
                GoodsForDB item = new GoodsForDB();
                item.setId(resultSet.getInt("goods_id"));
                item.setName(resultSet.getString("goods_name"));
                item.setPrice(resultSet.getDouble("goods_price"));
                item.setVendor(resultSet.getString("goods_vendor"));
                item.setProductionDate(convertToGD(resultSet.getDate("goods_production_date")));
                item.setExpDate(convertToGD(resultSet.getDate("goods_expiration_date")));
                FavouriteListLine line = new FavouriteListLine(item,
                        resultSet.getInt("favourite_list_id"));
                line.setId(resultSet.getInt("list_id"));
                lines.add(line);
            }
        } catch (Exception e) {
            throw new DaoException(e+"error with pars result set");
        }
        return lines;
    }

    @Override
    public void statementUpdate(PreparedStatement statement, FavouriteListLine obj) throws DaoException {
        throw new NotImplementedException();
    }

    @Override
    public void statementInsert(PreparedStatement statement, FavouriteListLine obj) throws DaoException {
        try {
            statement.setInt(1, obj.getFavoriteListId());
            statement.setInt(2, obj.getGoods().getId());
        } catch (SQLException e) {
            throw new DaoException(e+"error with statement insert");
        }
    }

    @Override
    public FavouriteListLine create() throws DaoException {
        throw new NotImplementedException();
    }
}