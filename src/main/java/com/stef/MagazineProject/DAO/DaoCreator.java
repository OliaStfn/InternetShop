package com.stef.MagazineProject.DAO;

import com.stef.MagazineProject.domain.*;
import com.stef.MagazineProject.mysql.MySQLDaoFactory;

public class DaoCreator {

    public static GenericDao createMySqlDao(String nameDao) throws DaoException {
        DaoFactory factory = new MySQLDaoFactory();
        if (nameDao.toLowerCase().equalsIgnoreCase("goods"))
            return factory.getDao(factory.getConnection(),Goods.class);
        else if(nameDao.toLowerCase().equalsIgnoreCase("client"))
            return factory.getDao(factory.getConnection(),Client.class);
        else if (nameDao.toLowerCase().equalsIgnoreCase("employee"))
            return factory.getDao(factory.getConnection(),Employee.class);
        else if(nameDao.toLowerCase().equalsIgnoreCase("order line"))
            return factory.getDao(factory.getConnection(),OrderLine.class);
        else if (nameDao.toLowerCase().equalsIgnoreCase("order"))
            return factory.getDao(factory.getConnection(),Order.class);
        else if (nameDao.toLowerCase().equalsIgnoreCase("status"))
            //return factory.getDao(factory.getConnection() .class);
            System.out.println();
        else if (nameDao.toLowerCase().equalsIgnoreCase("favorite"))
            return factory.getDao(factory.getConnection(),FavouriteList.class);
        else
            System.out.println("Not found same class");
        return null;
    }
}
