package com.stef.MagazineProject.support;

import com.stef.MagazineProject.DAO.DaoCreator;
import com.stef.MagazineProject.DAO.GenericDao;
import com.stef.MagazineProject.domain.Client;
import com.stef.MagazineProject.domain.Employee;
import com.stef.MagazineProject.domain.Stock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;


public class MenuTest {
    @Test
    public void sortMenu() throws Exception {
        /*Scanner in = new Scanner(System.in);
        GenericDao dao = DaoCreator.createMySqlDao("employee");
        ArrayList<Employee> employees = dao.readAll();
        dao = DaoCreator.createMySqlDao("client");
        ArrayList<Client> clients = dao.readAll();
        dao = DaoCreator.createMySqlDao("goods");
        Stock.setGoods(dao.readAll());

        SelectionSort sortProductByName = new SelectionSort(Stock.getGoods());
        Stock.setGoods(sortProductByName.selectionSort("name"));
        System.out.println(Stock.getInformationAboutProducts());

        SelectionSort sortProductByPrice = new SelectionSort(Stock.getGoods());
        Stock.setGoods(sortProductByPrice.selectionSort("price"));
        System.out.println(Stock.getInformationAboutProducts());

        SelectionSort sortEmployeeByName = new SelectionSort(employees);
        employees = sortEmployeeByName.selectionSort("name");
        for (Employee person : employees) {
            System.out.println(person.toString());
        }

        SelectionSort sortEmployeeBySalary = new SelectionSort(employees);
        employees = sortEmployeeBySalary.selectionSort("salary");
        for (Employee person : employees) {
            System.out.println(person.toString());
        }

        SelectionSort sortClientByName = new SelectionSort(clients);
        clients = sortClientByName.selectionSort("name");
        for (Client person : clients) {
            System.out.println(person.toString());

        }*/
    }
}