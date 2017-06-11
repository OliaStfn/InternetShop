package com.stef.MagazineProject.mysql;

import com.stef.MagazineProject.DAO.AbstractDao;
import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.domain.Goods;
import com.stef.MagazineProject.domain.Order;
import com.stef.MagazineProject.domain.OrderLine;
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

    private class GoodsForDB extends Goods {
        public void setId(int id) {
            super.setId(id);
        }
    }


    @Override
    public String getSelectQuery() {
        return "SELECT o.*,s.status,os.order_status_date,g.*,og.count_goods,og.order_line_price " +
                "FROM orders o NATURAL JOIN status s " +
                "NATURAL JOIN order_status os " +
                "NATURAL JOIN goods g "+
                "NATURAL JOIN orders_goods og "+
                "WHERE os.order_id=o.order_id " +
                "AND os.status_id=s.status_id " +
                "AND og.order_id=o.order_id " +
                "AND og.good_id=g.goods_id " +
                "AND o.order_id=";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT o.*,s.status,os.order_status_date,g.*,og.count_goods,og.order_line_price " +
                "FROM orders o NATURAL JOIN status s " +
                "NATURAL JOIN order_status os " +
                "NATURAL JOIN goods g "+
                "NATURAL JOIN orders_goods og "+
                "WHERE os.order_id=o.order_id " +
                "AND os.status_id=s.status_id " +
                "AND og.order_id=o.order_id " +
                "AND og.good_id=g.goods_id;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO orders(client_id) VALUES(?);";
    }

    @Override
    public String getUpdateQuery() {
        return "INSERT INTO order_status (order_id,status_id,order_status_date) " +
                "VALUES(?,(SELECT status_id FROM status WHERE status=?),NOW());";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM orders WHERE order_id=?;";
    }

    @Override
    public ArrayList<Order> parseResultSet(ResultSet resultSet) throws DaoException {
        ArrayList<Order> orders = new ArrayList<Order>();
        try {
            while (resultSet.next()) {
                Order order = new Order();
                OrderLine line = new OrderLine();
                GoodsForDB item = new GoodsForDB();
                order.setId(resultSet.getInt("orders.order_id"));
                order.setClientId(resultSet.getInt("orders.client_name"));
                order.setChangeStatusDate(convertToGD(resultSet.getDate("order_status.order_status_date")));
                order.setStatus(resultSet.getString("status.status"));
                line.setCount(resultSet.getInt("orders_goods.count_goods"));
                line.setPrice(resultSet.getDouble("orders_goods.order_line_price"));
                item.setId(resultSet.getInt("goods.goods_id"));
                item.setName(resultSet.getString("goods.goods_name"));
                item.setPrice(resultSet.getDouble("goods.goods_price"));
                item.setVendor(resultSet.getString("goods.goods_vendor"));
                item.setProductionDate(convertToGD(resultSet.getDate("goods.goods_production_date")));
                item.setExpDate(convertToGD(resultSet.getDate("goods.goods_expiration_date")));
                line.setGoods(item);
                if (order.getId()==orders.get(orders.size()-1).getId()){
                    orders.get(orders.size()-1).addNewLine(line);
                }else{
                    order.addNewLine(line);
                    orders.add(order);
                }
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public void statementUpdate(PreparedStatement statement, Order obj) throws DaoException {
        try {
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getStatus());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void statementInsert(PreparedStatement statement, Order obj) throws DaoException {
        try {
            statement.setInt(1, obj.getClientId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Order create() throws DaoException {
        throw new NotImplementedException("Not implemented");
    }
}