package com.stef.MagazineProject.mysql;

import com.stef.MagazineProject.DAO.AbstractDao;
import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.DAO.GenericDao;
import com.stef.MagazineProject.domain.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class MySQLStatusDao extends AbstractDao<Status,Integer>{

    public MySQLStatusDao(Connection connection) {
        super(connection);
    }

    private class StatusForDB extends Status {
        public StatusForDB() {
            super();
        }

        public void setId(int id) {
            super.setStatusId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM status WHERE status_id=";
    }


    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM status;";
    }


    @Override
    public String getCreateQuery() {
        return "INSERT INTO status(status) VALUES(?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE status SET status=? WHERE status_id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM status WHERE status_id=?";
    }

    @Override
    public ArrayList<Status> parseResultSet(ResultSet resultSet) throws DaoException {
        ArrayList<Status> statuses = new ArrayList<Status>();
        try {
            while (resultSet.next()) {
                MySQLStatusDao.StatusForDB status = new MySQLStatusDao.StatusForDB();
                status.setId(resultSet.getInt("status_id"));
                status.setStatus(resultSet.getString("status"));
                statuses.add(status);
            }
        } catch (Exception e) {
            throw new DaoException(e+"error with pars result set");
        }
        return statuses;
    }

    @Override
    public void statementUpdate(PreparedStatement statement, Status obj) throws DaoException {
        try {
            statement.setString(1, obj.getStatus());
            statement.setInt(2, obj.getId());
        } catch (SQLException e) {
            throw new DaoException(e+"error with statement update");
        }
    }

    @Override
    public void statementInsert(PreparedStatement statement, Status obj) throws DaoException {
        try {
            statement.setString(1, obj.getStatus());
        } catch (SQLException e) {
            throw new DaoException(e+"error with statement insert");
        }
    }


    @Override
    public Status create() throws DaoException {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Status.class);
        Status tempStatus = new Status();
        Scanner in = new Scanner(System.in);

        System.out.println("Enter status: ");
        tempStatus.setStatus(in.nextLine());

        ArrayList<Status>statuses=dao.readAll();
            for(Status status: statuses){
                if(tempStatus.getStatus().equals(status.getStatus())){
                    return status;
                }
            }
        return createInDB(tempStatus);
    }
}