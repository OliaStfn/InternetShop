package com.stef.MagazineProject.mysql;


import com.stef.MagazineProject.DAO.AbstractDao;
import com.stef.MagazineProject.DAO.DaoCreator;
import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.DAO.GenericDao;
import com.stef.MagazineProject.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class MySQLClientDao extends AbstractDao<Client, Integer> {

    public MySQLClientDao(Connection connection) {
        super(connection);
    }

    private class ClientForDB extends Client {
        public ClientForDB() throws DaoException {
        }

        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM clients WHERE client_id=";
    }


    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM clients;";
    }


    @Override
    public String getCreateQuery() {
        return "INSERT INTO clients(client_name,client_surname,client_born_date,client_phone_number,client_address," +
                "client_login,client_password) VALUES(?,?,?,?,?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE clients SET client_name=?,client_surname=?,client_born_date=?,client_phone_number=?," +
                "client_address=?,client_login=?,client_password=? WHERE client_id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM clients WHERE client_id=?";
    }

    @Override
    public ArrayList<Client> parseResultSet(ResultSet resultSet) throws DaoException {
        ArrayList<Client> clients = new ArrayList<Client>();
        try {
            while (resultSet.next()) {
                MySQLClientDao.ClientForDB client = new MySQLClientDao.ClientForDB();
                client.setId(resultSet.getInt("client_id"));
                client.setName(resultSet.getString("client_name"));
                client.setSurname(resultSet.getString("client_surname"));
                client.setBornDate(convertToGD(resultSet.getDate("client_born_date")));
                client.setPhoneNumber(resultSet.getString("client_phone_number"));
                client.setAddress(resultSet.getString("client_address"));
                client.setLogin(resultSet.getString("client_login"));
                client.setPassword(resultSet.getString("client_password"));
                clients.add(client);
            }
        } catch (Exception e) {
            throw new DaoException(e+"error with pars result set");
        }
        return clients;
    }

    @Override
    public void statementUpdate(PreparedStatement statement, Client obj) throws DaoException {
        try {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getSurname());
            statement.setDate(3,convertToDate(obj.getBornDate()));
            statement.setString(4,obj.getPhoneNumber());
            statement.setString(5,obj.getAddress());
            statement.setString(6,obj.getLogin());
            statement.setString(7,obj.getPassword());
            statement.setInt(8, obj.getId());
        } catch (SQLException e) {
            throw new DaoException(e+"error with statement update");
        }
    }

    @Override
    public void statementInsert(PreparedStatement statement, Client obj) throws DaoException {
        try {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getSurname());
            statement.setDate(3,convertToDate(obj.getBornDate()));
            statement.setString(4,obj.getPhoneNumber());
            statement.setString(5,obj.getAddress());
            statement.setString(6,obj.getLogin());
            statement.setString(7,obj.getPassword());
        } catch (SQLException e) {
            throw new DaoException(e+"error with statement insert");
        }
    }


    @Override
    public Client create() throws DaoException {
        boolean temp=false;
        Client tempClient = new Client();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name: ");
        tempClient.setName(in.nextLine());
        System.out.println("Enter surname: ");
        tempClient.setSurname(in.nextLine());
        System.out.println("Enter year of birth: ");
        int year = in.nextInt();
        System.out.println("Enter month of birth: ");
        int month = in.nextInt();
        System.out.println("Enter day of birth: ");
        int day = in.nextInt();
        tempClient.setBornDate(new GregorianCalendar(year, month, day));
        System.out.println("Enter phone number: ");
        tempClient.setPhoneNumber(in.nextLine());
        System.out.println("Enter address: ");
        tempClient.setAddress(in.nextLine());
        do {
            System.out.println("Enter your login: ");
            tempClient.setLogin(in.nextLine());
            ArrayList<Client>clients=DaoCreator.readClientFromDB();
            for(Client client: clients){
                if(tempClient.getLogin().equals(client.getLogin())){
                    System.out.println("Username is exist");
                    temp=true;
                    break;
                }
            }
        } while (temp);
        System.out.println("Enter your password: ");
        tempClient.setPassword(in.nextLine());

        return createInDB(tempClient);
    }
}