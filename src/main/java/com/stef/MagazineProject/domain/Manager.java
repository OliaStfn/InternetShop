package com.stef.MagazineProject.domain;

public class Manager extends Employee {

    private String phone;
    private String department;

    public Manager() {
        super();
        this.phone = phone;
        this.department = department;
    }

    public Manager(String name, String surname, int year, int month, int day, double salary, String phone, String department) {
        super(name, surname, year, month, day, salary);
        this.phone = phone;
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void raiseSalary() {
        setSalary(getSalary() + getSalary() * 0.15);
    }

    @Override
    public void setBonus() {
        setSalary(getSalary() + getSalary() * 0.25);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "Phone: " + phone +
                ", Department: " + department +
                super.toString() +
                '}';
    }
}