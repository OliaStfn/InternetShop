package com.stef.MagazineProject.DAO;

import com.stef.MagazineProject.domain.*;
import com.stef.MagazineProject.mysql.MySQLDaoFactory;
import com.stef.MagazineProject.support.OrderStTableMarker;

import java.util.ArrayList;

public class DaoCreator {

    public static GenericDao createMySqlDao(String nameDao) throws DaoException {
        DaoFactory factory = new MySQLDaoFactory();
        if (nameDao.toLowerCase().equalsIgnoreCase("goods"))
            return factory.getDao(factory.getConnection(), Goods.class);
        else if (nameDao.toLowerCase().equalsIgnoreCase("client"))
            return factory.getDao(factory.getConnection(), Client.class);
        else if (nameDao.toLowerCase().equalsIgnoreCase("employee"))
            return factory.getDao(factory.getConnection(), Employee.class);
        else if (nameDao.toLowerCase().equalsIgnoreCase("order line"))
            return factory.getDao(factory.getConnection(), OrderLine.class);
        else if (nameDao.toLowerCase().equalsIgnoreCase("order"))
            return factory.getDao(factory.getConnection(), Order.class);
        else if (nameDao.toLowerCase().equalsIgnoreCase("status"))
            return factory.getDao(factory.getConnection(), Status.class);
        else if (nameDao.toLowerCase().equalsIgnoreCase("favorite line"))
            return factory.getDao(factory.getConnection(), FavouriteListLine.class);
        else if (nameDao.toLowerCase().equalsIgnoreCase("favorite"))
            return factory.getDao(factory.getConnection(), FavouriteList.class);
        else if (nameDao.toLowerCase().equalsIgnoreCase("order status"))
            return factory.getDao(factory.getConnection(), OrderStTableMarker.class);
        else
            System.out.println("Not found same class");
        return null;
    }

    public static ArrayList<Client> readClientFromDB() throws DaoException {
        GenericDao dao = createMySqlDao("client");
        ArrayList<Client> clients = dao.readAll();
        dao = createMySqlDao("order");
        for (Client client : clients) {
            client.setOrders(dao.readAll());
        }
        return clients;
    }
}