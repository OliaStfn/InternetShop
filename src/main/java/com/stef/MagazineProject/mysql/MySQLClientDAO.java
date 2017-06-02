package com.stef.MagazineProject.mysql;


import com.stef.MagazineProject.DAO.AbstractDao;
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

public class MySQLClientDAO extends AbstractDao<Client, Integer> {

    public MySQLClientDAO(Connection connection) {
        super(connection);
    }

    private class ClientForDB extends Client {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM client_personal";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO client_personal(name,surname,born_date,phone_number,address," +
                "login,password) VALUES(?,?,?,?,?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE client_personal SET name=?,surname=?,born_date=?,phone_number=?," +
                "address=?,login=?,password=? WHERE id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM client_personal WHERE id=?";
    }

    @Override
    public ArrayList<Client> parseResultSet(ResultSet resultSet) throws DaoException {
        ArrayList<Client> clients = new ArrayList<Client>();
        try {
            while (resultSet.next()) {
                MySQLClientDAO.ClientForDB client = new MySQLClientDAO.ClientForDB();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setSurname(resultSet.getString("surname"));
                client.setBornDate(convertToGD(resultSet.getDate("born_date")));
                client.setPhoneNumber(resultSet.getString("phone_number"));
                client.setAddress(resultSet.getString("address"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                clients.add(client);
            }
        } catch (Exception e) {
            throw new DaoException(e);
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
            throw new DaoException(e);
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
            throw new DaoException(e);
        }
    }

    @Override
    public Client create() throws DaoException {
        boolean temp=false;
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Client.class);
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
            ArrayList<Client>clients=dao.readAll();
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