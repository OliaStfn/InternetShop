package com.stef.MagazineProject.DAO;

public interface DaoFactory<T> {

    public interface DaoCreator<T> {
        public GenericDao create(T connection);
    }

    public T getConnection() throws DaoException, DaoException;

    public GenericDao getDao(T connection, Class daoClass) throws DaoException;
}