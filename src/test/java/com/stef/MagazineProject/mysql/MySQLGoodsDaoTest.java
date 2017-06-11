package com.stef.MagazineProject.mysql;

import com.stef.MagazineProject.DAO.GenericDao;
import com.stef.MagazineProject.domain.Goods;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class MySQLGoodsDaoTest {
    @Test
    public void create() throws Exception {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Goods.class);
        Goods goods = (Goods) dao.create();
    }

    @Test
    public void createInDB() throws Exception {
        Goods goods = new Goods("salat Caesar", 30.0, "pizza+", new GregorianCalendar(),
                new GregorianCalendar(2018, 6, 5));
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Goods.class);
        Goods fieldproduct = (Goods) dao.createInDB(goods);
        System.out.println(fieldproduct.toString());
    }

    @Test
    public void read() throws Exception {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Goods.class);
        Goods findproduct = (Goods) dao.read(1);
        assertNotNull(findproduct);
        System.out.println(findproduct.toString());
    }

    @Test
    public void update() throws Exception {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Goods.class);
        Goods goods = (Goods) dao.read(1);
        goods.setPrice(8);
        dao.update(goods);
    }

    @Test
    public void delete() throws Exception {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Goods.class);
        Goods goods = (Goods) dao.read(2);
        dao.delete(goods);
    }

    @Test
    public void readAll() throws Exception {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Goods.class);
        ArrayList<Goods> goodss = dao.readAll();
        assertNotNull(goodss);
    }

}