package com.stef.MagazineProject.mysql;

import com.stef.MagazineProject.DAO.GenericDao;
import com.stef.MagazineProject.domain.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class MySQLEmployeeDaoTest {
    /*@Test
    public void createInDB() throws Exception {
        Employee employee = new Employee("Vitalik","Mah",1997,11,29,2000);
        employee.setPhoneNumber("0992563583");
        employee.setDate_of_begin(new GregorianCalendar(2017,05,01));
        employee.setPassword("0000");
        employee.setRating(4);
        employee.setCountMarks(1);
        employee.setLogin("vitiksan5");
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Employee.class);
        Employee fieldemployee = (Employee) dao.createInDB(employee);
        System.out.println(fieldemployee.toString());
    }

    @Test
    public void read() throws Exception {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Employee.class);
        Employee findemployee = (Employee) dao.read(2);
        assertNotNull(findemployee);
        System.out.println(findemployee.toString());
    }

    @Test
    public void update() throws Exception {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(),Employee.class);
        Employee employee = (Employee) dao.read(3);
        employee.setName("Vitalik");
        dao.update(employee);
    }

    @Test
    public void delete() throws Exception {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(),  Employee.class);
        Employee findemployee = ( Employee) dao.read(3);
        dao.delete(findemployee);
    }

    @Test
    public void readAll() throws Exception {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Employee.class);
        ArrayList<Employee> employees = dao.readAll();
        assertNotNull(employees);
    }*/

}