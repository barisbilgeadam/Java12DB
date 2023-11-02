package com.barisd.utility;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyRepositoryFactory<T,ID> implements ICrud<T, ID>{
    private Session session;
    private Transaction transaction;
    private CriteriaBuilder criteriaBuilder;
    Class<T> clazz;
    public MyRepositoryFactory(Class<T> clazz) {
        this.clazz=clazz;
    }

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
            session.save(entity); //jpada persist
        });
        closeSession();
        return entities;
    }

    @Override
    public void deleteById(ID id) {
        openSession();
        session.delete(session.get(clazz, (Serializable) id));
        closeSession();
    }

    @Override
    public void delete(T entity) {
        openSession();
        session.delete(entity); //jpada remove
        closeSession();
    }

    @Override
    public T update(T entity) {
        openSession();
        //session.update(entity); //jpada merge()
        T guncellenen = (T) session.merge(entity);
        closeSession();
        return guncellenen;
    }

    @Override
    public Optional<T> findById(ID id) {
        openSession();
        Optional<T> obj=Optional.ofNullable(session.get(clazz, (Serializable) id)); //jpa da find()
        closeSession();
        return obj;
    }

    @Override
    public boolean existsById(ID id) {
        return findById(id).isPresent();
    }

    @Override
    public List<T> findAll() {
        openSession();
        //session.createNativeQuery() bu metod içine direkt SQL yazılabilir.
        List<T> resultList = session.createQuery("FROM " + clazz.getSimpleName(), clazz).getResultList();
        closeSession();
        return resultList;
    }

    /**
     * select * from tbl_ where columnname=value
     * @param columnName
     * @param value
     * @return
     */
    @Override
    public <E> List<T> findByColumnNameAndValue(String columnName, E value) {
        openSession();
        List<T> resultList = session.createQuery("FROM " + clazz.getSimpleName() + " WHERE " + columnName + " = :xyz", clazz)
                .setParameter("xyz", value).getResultList();
        closeSession();
        return resultList;
    }

    /**
     * findByEntity metodu ile yapılmak istenen:  Bir sınıf içindeki alanların metod tarafından
     * okunulması ve her bir alanın içindeki değerlerin kontrol edilerek
     * null olmayanları sorguya dahil etmesidir.
     * Böylece esnek sorgulama sistemi kazandırılmaya çalışılacaktır.
     * Bu işlemin adına REVERSE ENGINEERING denir.
     * Burada REFLECTION API kullanılabilir.
     */
    @Override
    public List<T> findByEntity(T entity) {
        openSession();
        criteriaBuilder= session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);

        List<Predicate> predicates=new ArrayList<>(); //sorguda kullanacağımız kriterleri tutacağımız liste.
        // Sınıf içindeki tüm fieldları dizi olarak döner.
        Field[] fields = clazz.getDeclaredFields(); //id,ad,soyad...
        for (Field field:fields) {
            field.setAccessible(true); // fieldı Erişime açmak için
            try {
                Object value = field.get(entity);
                if(value!=null && !field.getName().equals("id")){
                    if(field.getType().isAssignableFrom(String.class)){
                        predicates.add(criteriaBuilder.like(root.get(field.getName()), (String) value));
                    }
                    if(field.getType().isAssignableFrom(Number.class)) {
                        predicates.add(criteriaBuilder.equal(root.get(field.getName()), value));
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));
        List<T> resultList = session.createQuery(criteriaQuery).getResultList();
        closeSession();
        return resultList;
    }






}
