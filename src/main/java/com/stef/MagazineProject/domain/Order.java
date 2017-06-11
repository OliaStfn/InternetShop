package com.stef.MagazineProject.domain;

import com.stef.MagazineProject.DAO.DaoCreator;
import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.DAO.GenericDao;
import com.stef.MagazineProject.DAO.Identifacator;
import com.stef.MagazineProject.mysql.MySQLDaoFactory;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Order implements Identifacator<Integer>{
    private int clientId;
    private int orderId;
    private Status status;
    private GregorianCalendar changeStatusDate;;
    private ArrayList<OrderLine> lines;

    public Order() {
    }

    public Order(int clientId) {
        this.clientId = clientId;
        lines = new ArrayList<>();
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getId() {
        return orderId;
    }

    public void setId(int orderId) {
        this.orderId = orderId;
    }

    public double allPrice() {
        double temp=0;
        for (OrderLine line: lines){
            temp+=line.getPrice();
        }
        return temp;
    }

    public void addNewLine(int count,Goods goods){
        lines.add(new OrderLine(goods,count,orderId));
    }

    public void addNewLine(OrderLine line){
        lines.add(line);
    }

    public void setStatus(String status) throws DaoException {
        GenericDao dao = DaoCreator.createMySqlDao("status");
        this.status = (Status) dao.createInDB(new Status(status));
        dao = DaoCreator.createMySqlDao("status order");
        dao.createInDB(this);
    }

    public void setStatus() throws DaoException {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Status.class);
        status =(Status) dao.create();
    }

    public void setChangeStatusDate(GregorianCalendar changeStatusDate) {
        this.changeStatusDate = changeStatusDate;
    }

    public String getStatus() {
        return status.getStatus();
    }
}
