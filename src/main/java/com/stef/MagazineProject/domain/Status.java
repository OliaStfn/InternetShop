package com.stef.MagazineProject.domain;

import com.stef.MagazineProject.DAO.Identifacator;

public class Status implements Identifacator<Integer> {
    private String status;
    private int statusId;

    protected void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int getId() {
        return statusId;
    }
}
