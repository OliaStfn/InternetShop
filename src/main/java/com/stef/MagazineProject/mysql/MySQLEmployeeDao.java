package com.stef.MagazineProject.mysql;

import com.stef.MagazineProject.DAO.AbstractDao;
import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.domain.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class MySQLEmployeeDao extends AbstractDao<Employee, Integer> {

    public MySQLEmployeeDao(Connection connection) {
        super(connection);
    }

    private class EmployeeForDB extends Employee {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM employees WHERE employee_id=";
    }


    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM employees;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO employees(employee_name,employee_surname,employee_born_date," +
                "employee_phone_number,employee_date_of_begin," +
                "employee_salary,employee_rating,employee_count_marks,employee_login,employee_password)" +
                " VALUES(?,?,?,?,?,?,?,?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE employees SET employee_name=?,employee_surname=?,employee_born_date=?," +
                "employee_phone_number=?,employee_date_of_begin=?," +
                "employee_salary=?, employee_rating=?, employee_count_marks=?," +
                "employee_login=?,employee_password=? WHERE employee_id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM employees WHERE employee_id=?";
    }

    @Override
    public ArrayList<Employee> parseResultSet(ResultSet resultSet) throws DaoException {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try {
            while (resultSet.next()) {
                MySQLEmployeeDao.EmployeeForDB employee = new MySQLEmployeeDao.EmployeeForDB();
                employee.setId(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("employee_name"));
                employee.setSurname(resultSet.getString("employee_surname"));
                employee.setBornDate(convertToGD(resultSet.getDate("employee_born_date")));
                employee.setPhoneNumber(resultSet.getString("employee_phone_number"));
                employee.setDate_of_begin(convertToGD(resultSet.getDate("employee_date_of_begin")));
                employee.setSalary(resultSet.getDouble("employee_salary"));
                employee.setRating(resultSet.getDouble("employee_rating"));
                employee.setCountMarks(resultSet.getInt("employee_count_marks"));
                employee.setLogin(resultSet.getString("employee_login"));
                employee.setPassword(resultSet.getString("employee_password"));
                employees.add(employee);
            }
        } catch (Exception e) {
            throw new DaoException(e+"error with pars result set");
        }
        return employees;
    }

    @Override
    public void statementUpdate(PreparedStatement statement, Employee obj) throws DaoException {
        try {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getSurname());
            statement.setDate(3,convertToDate(obj.getBornDate()));
            statement.setString(4,obj.getPhoneNumber());
            statement.setDate(5,convertToDate(obj.getDate_of_begin()));
            statement.setDouble(6,obj.getSalary());
            statement.setDouble(7,obj.getRating());
            statement.setInt(8,obj.getCountMarks());
            statement.setString(9,obj.getLogin());
            statement.setString(10,obj.getPassword());
            statement.setInt(11, obj.getId());
        } catch (SQLException e) {
            throw new DaoException(e+"error with statement update");
        }
    }

    @Override
    public void statementInsert(PreparedStatement statement,Employee obj) throws DaoException {
        try {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getSurname());
            statement.setDate(3,convertToDate(obj.getBornDate()));
            statement.setString(4,obj.getPhoneNumber());
            statement.setDate(5,convertToDate(obj.getDate_of_begin()));
            statement.setDouble(6,obj.getSalary());
            statement.setDouble(7,obj.getRating());
            statement.setInt(8,obj.getCountMarks());
            statement.setString(9,obj.getLogin());
            statement.setString(10,obj.getPassword());
        } catch (SQLException e) {
            throw new DaoException(e+"error with statement insert");
        }
    }

    @Override
    public Employee create() throws DaoException {
        Employee tempEmployee = new Employee();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name: ");
        tempEmployee.setName(in.nextLine());
        System.out.println("Enter surname: ");
        tempEmployee.setSurname(in.nextLine());
        System.out.println("Enter year of birth: ");
        int year = in.nextInt();
        System.out.println("Enter month of birth: ");
        int month = in.nextInt();
        System.out.println("Enter day of birth: ");
        int day = in.nextInt();
        tempEmployee.setBornDate(new GregorianCalendar(year, month, day));
        System.out.println("Enter phone number: ");
        tempEmployee.setPhoneNumber(in.nextLine());
        System.out.println("Enter the beginning of the year: ");
        int yearBegin = in.nextInt();
        System.out.println("Enter the beginning of the month: ");
        int monthBegin = in.nextInt();
        System.out.println("Enter the beginning of the day: ");
        int dayBegin = in.nextInt();
        tempEmployee.setBornDate(new GregorianCalendar(yearBegin, monthBegin, dayBegin));
        System.out.println("Enter your salary: ");
        tempEmployee.setSalary(in.nextDouble());
        System.out.println("Enter your login: ");
        tempEmployee.setLogin(in.next());
        System.out.println("Enter your password: ");
        tempEmployee.setPassword(in.next());

        return createInDB(tempEmployee);
    }
}