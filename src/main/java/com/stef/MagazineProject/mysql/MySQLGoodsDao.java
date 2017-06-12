package com.stef.MagazineProject.mysql;

import com.stef.MagazineProject.DAO.AbstractDao;
import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.domain.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class MySQLGoodsDao extends AbstractDao<Goods, Integer> {

    public MySQLGoodsDao(Connection connection) {
        super(connection);
    }

    private class GoodsForDB extends Goods {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM goods WHERE goods_id=";
    }
    
    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM goods;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO goods(goods_name,goods_price,goods_vendor," +
                "goods_production_date,goods_expiration_date) VALUES(?,?,?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE goods SET goods_name=?,goods_price=?,goods_vendor=?," +
                "goods_production_date=?,goods_expiration_date=? WHERE goods_id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM goods WHERE goods_id=?;";
    }

    @Override
    public ArrayList<Goods> parseResultSet(ResultSet resultSet) throws DaoException {
        ArrayList<Goods> goods = new ArrayList<Goods>();
        try {
            while (resultSet.next()) {
                GoodsForDB item = new GoodsForDB();
                item.setId(resultSet.getInt("goods_id"));
                item.setName(resultSet.getString("goods_name"));
                item.setPrice(resultSet.getDouble("goods_price"));
                item.setVendor(resultSet.getString("goods_vendor"));
                item.setProductionDate(convertToGD(resultSet.getDate("goods_production_date")));
                item.setExpDate(convertToGD(resultSet.getDate("goods_expiration_date")));
                goods.add(item);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return goods;
    }

    @Override
    public void statementUpdate(PreparedStatement statement, Goods obj) throws DaoException {
        try {
            statement.setString(1, obj.getName());
            statement.setDouble(2, obj.getPrice());
            statement.setString(3, obj.getVendor());
            statement.setDate(4, convertToDate(obj.getProductionDate()));
            statement.setDate(5,convertToDate(obj.getExpDate()));
            statement.setInt(6, obj.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void statementInsert(PreparedStatement statement, Goods obj) throws DaoException {
        try {
            statement.setString(1, obj.getName());
            statement.setDouble(2, obj.getPrice());
            statement.setString(3, obj.getVendor());
            statement.setDate(4, convertToDate(obj.getProductionDate()));
            statement.setDate(5,convertToDate(obj.getExpDate()));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public Goods create() throws DaoException {
        Goods tempGoods = new Goods();
        Scanner in = new Scanner(System.in);
        System.out.print("\nEnter name of product: ");
        String name=in.nextLine();
        tempGoods.setName(name);
        System.out.print("Enter price of product: ");
        double price=in.nextDouble();
        tempGoods.setPrice(price);
        System.out.print("Enter vendor of product: ");
        String vendor = in.nextLine();
        tempGoods.setVendor(vendor);
        System.out.print("Enter production year of product: ");
        int year = in.nextInt();
        System.out.print("Enter production month of product: ");
        int month = in.nextInt();
        System.out.print("Enter production day of product: ");
        int day = in.nextInt();
        tempGoods.setProductionDate(new GregorianCalendar(year, month, day));
        System.out.print("Enter expiration year of product: ");
        int expyear = in.nextInt();
        System.out.print("Enter expiration month of product: ");
        int expmonth = in.nextInt();
        System.out.print("Enter expiration day of product: ");
        int expday = in.nextInt();
        tempGoods.setExpDate(new GregorianCalendar(expyear, expmonth, expday));
        return createInDB(tempGoods);
    }
}