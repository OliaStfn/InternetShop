package com.stef.MagazineProject.domain;

import com.stef.MagazineProject.DAO.Identifacator;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class Employee extends Human implements Identifacator<Integer> {
    private int id;
    private GregorianCalendar date_of_begin;
    private double salary;
    private double rating;
    private int countMarks;

    public boolean raiseSalary(int x) {
        if (x > 0) {
            this.salary += x;
            return true;
        }
        return false;
    }

    public Employee() {
        setRating(0);
        setCountMarks(0);
    }

    public Employee(String name, String surname, int year, int month, int day, double salary) {
        setName(name);
        setSurname(surname);
        setBornDate(new GregorianCalendar(year, month, day));
        setSalary(salary);
        setRating(0);
        setDate_of_begin(new GregorianCalendar(2016, 5, 5));
    }

    public String getExperience() {
        GregorianCalendar currentDate = new GregorianCalendar();
        int year, month, date, hour, minutes;

        year = currentDate.get(GregorianCalendar.YEAR)
                - date_of_begin.get(GregorianCalendar.YEAR);
        month = currentDate.get(GregorianCalendar.MONTH)
                - date_of_begin.get(GregorianCalendar.MONTH);
        date = currentDate.get(GregorianCalendar.DATE)
                - date_of_begin.get(GregorianCalendar.DATE);
        hour = currentDate.get(GregorianCalendar.HOUR)
                - date_of_begin.get(GregorianCalendar.HOUR);
        minutes = currentDate.get(GregorianCalendar.MINUTE)
                - date_of_begin.get(GregorianCalendar.MINUTE);

        if (month < 0) {
            month = 0;
        }
        return " year = " + year +
                " month = " + month +
                " date = " + date +
                " hour = " + hour +
                " minutes = " + minutes;
    }

    public int getCountMarks() {
        return countMarks;
    }

    public void setCountMarks(int countMarks) {
        this.countMarks = countMarks;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDate_of_begin(GregorianCalendar date_of_begin) {
        this.date_of_begin = date_of_begin;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public GregorianCalendar getDate_of_begin() {
        return date_of_begin;
    }

    public void addMark() {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Enter your mark for this employee:(from 1 to 5)");
            int newMark = Integer.parseInt(in.next());
            if (newMark < 1 || newMark > 5) throw new Exception("Error");
            countMarks++;
            rating = (rating * countMarks + newMark) / countMarks;
        } catch (Exception e) {
            e.getMessage();
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Name: " + getName() +
                ", Surname: " + getSurname() +
                ", Login: " + getLogin() +
                ", Born Date: " + getBornDate().get(GregorianCalendar.DATE) +
                "." + getBornDate().get(GregorianCalendar.MONTH) +
                "." + getBornDate().get(GregorianCalendar.YEAR) +
                ", Date of begin: " + getDate_of_begin().get(GregorianCalendar.DATE) +
                "." + getDate_of_begin().get(GregorianCalendar.MONTH) +
                "." + getDate_of_begin().get(GregorianCalendar.YEAR) +
                ", Salary: " + salary +
                ", Phone number: " + getPhoneNumber() +
                ", Rating: " + rating +
                '}';
    }

    public void setBonus() {
        salary += salary * 0.1;
    }
}