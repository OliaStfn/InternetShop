package com.stef.MagazineProject.DAO;

import java.io.Serializable;
import java.util.ArrayList;

public interface GenericDao<T, PK extends Serializable> {
    public T create() throws DaoException;
    public T createInDB(T object, int key) throws DaoException;
    public T read(PK key)throws DaoException;
    public void update(T obj,int key)throws DaoException;
    public void delete(T obj, int key)throws DaoException;
    public ArrayList<T> readAll()throws DaoException;

}