package com.barisd.utility;

import com.barisd.repository.entity.Musteri;
import com.sun.xml.bind.v2.model.core.ID;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class MyRepositoryFactory<T,ID> implements ICrud<T, ID>{
    private Session session;
    private Transaction transaction;

    private void openSession() {
        session= HibernateUtility.getSessionFactory().openSession();
        transaction=session.beginTransaction();
    }
    private void closeSession() {
        transaction.commit();
        session.close();
    }

    @Override
    public T save(T entity) {
        openSession();
        session.save(entity);
        closeSession();
        return entity; //geri dönen entity id değerine sahiptir.
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> entities) {
        openSession();
        entities.forEach(entity->{
            session.save(entity);

        });
        closeSession();
        return entities;
    }

    @Override
    public void deleteById(Class<T> clazz,ID id) {
        openSession();
        session.delete(session.get(clazz, (Serializable) id));
        closeSession();
    }

    @Override
    public void delete(T entity) {
        openSession();
        session.delete(entity);
        closeSession();
    }

    @Override
    public T update(T entity) {
        openSession();
        //session.update(entity);
        T guncellenen = (T) session.merge(entity);
        closeSession();
        return guncellenen;
    }

    @Override
    public Optional<T> findById(Class<T> clazz,ID id) {
        openSession();
        Optional<T> obj=Optional.ofNullable(session.get(clazz, (Serializable) id));
        closeSession();
        return obj;
    }

    @Override
    public boolean existsById(ID id) {

        return false;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public List<T> findByEntity(T entity) {
        return null;
    }

    @Override
    public List<T> findByColumnNameAndValue(String columnName, String value) {
        return null;
    }

    @Override
    public List<T> findByColumnNameAndValue(String columnName, Long value) {
        return null;
    }

    @Override
    public List<T> findByColumnNameAndValue(String columnName, BigDecimal value) {
        return null;
    }
}
