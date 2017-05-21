package com.stef.MagazineProject.domain;

import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;


public class EmployeeTest {
    @Test
    public void main() {
        Employee employee = new Employee();
        employee.setPassword("2");
        employee.setSalary(2);
        employee.setLogin("f");
        employee.setDate_of_begin(new GregorianCalendar());
        employee.setBornDate(new GregorianCalendar());
        employee.setName("f");
        employee.setSurname("gf");
        employee.setPhoneNumber("42");
        System.out.println(employee.toString());
    }
}