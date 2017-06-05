package com.stef.MagazineProject.mysql;

import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.DAO.GenericDao;
import com.stef.MagazineProject.domain.Client;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MySQLClientDAOTest {
    @Test
    public void createInDB() throws DaoException {
        Client client = new Client("Olga", "Stefanyshyn", 1999, 03, 22,
                "0932202176", "Vovchynetska 198b/144");
        client.setPassword("0000");
        client.setLogin("kardash11");
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Client.class);
        Client fieldclient = (Client) dao.createInDB(client,0);
        System.out.println(fieldclient.toString());
    }

    @Test
    public void read() throws DaoException {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Client.class);
        Client findclient = (Client) dao.read(9);
        assertNotNull(findclient);
        System.out.println(findclient.toString());
    }

    @Test
    public void update() throws DaoException {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Client.class);
        Client client = (Client) dao.read(14);
        client.setName("Olya");
        dao.update(client,0);
    }

    @Test
    public void delete() throws DaoException {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Client.class);
        Client findclient = (Client) dao.read(19);
        dao.delete(findclient,0);
    }

    @Test
    public void readAll() throws DaoException {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Client.class);
        ArrayList<Client> clients = dao.readAll();
        assertNotNull(clients);
    }
}