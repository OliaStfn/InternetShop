package com.stef.MagazineProject.mysql;

import com.stef.MagazineProject.DAO.AbstractDao;
import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class MySQLProductDao extends AbstractDao<Product, Integer> {

    public MySQLProductDao(Connection connection) {
        super(connection);
    }

    private class ProductForDB extends Product {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM goods_personal WHERE good_id=";
    }


    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM goods_personal;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO goods_personal(good_name,good_price,good_vendor,good_production_date,good_expiration_date) VALUES(?,?,?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE goods_personal SET good_name=?,good_price=?,good_vendor=?,good_production_date=?,good_expiration_date=? WHERE good_id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM goods_personal WHERE good_id=?;";
    }

    @Override
    public ArrayList<Product> parseResultSet(ResultSet resultSet) throws DaoException {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            while (resultSet.next()) {
                ProductForDB item = new ProductForDB();
                item.setId(resultSet.getInt("good_id"));
                item.setName(resultSet.getString("good_name"));
                item.setPrice(resultSet.getDouble("good_price"));
                item.setVendor(resultSet.getString("good_vendor"));
                item.setProductionDate(convertToGD(resultSet.getDate("good_production_date")));
                item.setExpDate(convertToGD(resultSet.getDate("good_expiration_date")));
                products.add(item);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return products;
    }

    @Override
    public void statementUpdate(PreparedStatement statement, Product obj) throws DaoException {
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
    public void statementInsert(PreparedStatement statement, Product obj) throws DaoException {
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
    public Product create() throws DaoException {
        Product tempProduct = new Product();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name of product: ");
        tempProduct.setName(in.nextLine());
        System.out.println("Enter price of product: ");
        tempProduct.setPrice(in.nextDouble());
        System.out.println("Enter vendor of product: ");
        tempProduct.setVendor(in.nextLine());
        System.out.println("Enter production year of product: ");
        int year = in.nextInt();
        System.out.println("Enter production month of product: ");
        int month = in.nextInt();
        System.out.println("Enter production day of product: ");
        int day = in.nextInt();
        tempProduct.setProductionDate(new GregorianCalendar(year, month, day));
        System.out.println("Enter expiration year of product: ");
        int expyear = in.nextInt();
        System.out.println("Enter expiration month of product: ");
        int expmonth = in.nextInt();
        System.out.println("Enter expiration day of product: ");
        int expday = in.nextInt();
        tempProduct.setExpDate(new GregorianCalendar(expyear, expmonth, expday));
        return createInDB(tempProduct);
    }
}