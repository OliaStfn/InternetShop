package com.stef.MagazineProject.domain;

import com.stef.MagazineProject.DAO.Identifacator;
import com.stef.MagazineProject.support.ISort;

import java.util.GregorianCalendar;

public class Client extends Human implements Identifacator<Integer>, ISort {
    private FavouriteList list;
    private int id;
    private String phoneNumber;
    private String address;
    private String login;
    private String password;


    public Client() {
        list = new FavouriteList();
        setName("name");
        setSurname("surname");
        setBornDate(new GregorianCalendar(1990, 01, 01));
        phoneNumber = "none";
        address = "none";
    }

    public Client(String name, String surname,
                  int year, int month, int day,
                  String phoneNumber, String address) {
        list = new FavouriteList();
        setName(name);
        setSurname(surname);
        setBornDate(new GregorianCalendar(year, month, day));
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addProductToFavouriteList() {
        list.addProduct();
    }

    public void deleteProductFromFavouriteList() {
        list.deleteProduct();
    }

    @Override
    public String toString() {
        String tabulation = "\n________________________________________________";
        return "Client:" + tabulation +
                "\n |\tName: \t\t\t|\t" + getName() +
                "\n |\tSurname: \t\t|\t" + getSurname() +
                "\n |\tLogin: \t\t\t|\t" + getLogin() +
                "\n |\tBorn Date: \t\t|\t" + getBornDate().get(GregorianCalendar.DATE) +
                "." + getBornDate().get(GregorianCalendar.MONTH) +
                "." + getBornDate().get(GregorianCalendar.YEAR) +
                "\n |\tPhoneNumber: \t|\t" + phoneNumber +
                "\n |\tAddress: \t\t|\t" + address + tabulation;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getWordForSort() {
        return getSurname() + getName();
    }
}