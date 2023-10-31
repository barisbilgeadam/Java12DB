package com.barisd.utility;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ICrud<T,ID> {
    T save(T entity);
    Iterable<T> saveAll(Iterable<T> entities);
    void deleteById(Class<T> clazz,ID id);
    void delete(T entity);
    T update(T entity);
    Optional<T> findById(Class<T> clazz,ID id);
    boolean existsById(ID id);
    List<T> findAll();

    /**
     * Buna sonra bakacağız. Reflection kullanacağız.
     * Urun u=new Urun();
     * u.ad="Un";
     * @param entity
     * @return
     */
    List<T> findByEntity(T entity);

    List<T> findByColumnNameAndValue(String columnName, String value);
    List<T> findByColumnNameAndValue(String columnName, Long value);
    List<T> findByColumnNameAndValue(String columnName, BigDecimal value);





}
