package com.stef.MagazineProject.mysql;

import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.DAO.DaoFactory;
import com.stef.MagazineProject.DAO.GenericDao;
import com.stef.MagazineProject.domain.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class MySQLDaoFactory implements DaoFactory<Connection> {
    private static final Logger log = Logger.getLogger(MySQLDaoFactory.class);
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://servlab.mysql.ukraine.com.ua/servlab_devolga?useSSL=false";
    private static String USERNAME = "servlab_devolga";
    private static String PASSWORD = "942lucxf";
    private Map<Class, DaoCreator> allDao;

    @Override
    public Connection getConnection() throws DaoException {
        Connection connection = null;

        try {
            Class.forName(driverName); // Завантажуємо клас драйвера
        } catch (ClassNotFoundException e) {
            log.error("Driver JDBC has NOT get");
            log.error(e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("The successful connection for DB");
        } catch (SQLException e) {
            log.error("Failed connection for DB");
            log.error(e.getMessage());
        }
        return connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class daoClass) throws DaoException {
        DaoCreator creator = allDao.get(daoClass);
        if (creator == null){
            throw new DaoException("DAO for class "+daoClass+" not found");
        }
        return creator.create(connection);
    }


    public MySQLDaoFactory() {
        allDao = new HashMap<Class, DaoCreator>();

        allDao.put(Product.class, new DaoFactory.DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySQLProductDao(connection);
            }
        });

        allDao.put(Client.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySQLClientDao(connection);
            }
        });

        allDao.put(Employee.class, new DaoFactory.DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySQLEmployeeDao(connection);
            }
        });

        allDao.put(Order.class, new DaoFactory.DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySQLOrderDao(connection);
            }
        });

        allDao.put(FavouriteList.class, new DaoFactory.DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySQLFavouriteListDao(connection);
            }
        });
    }
}