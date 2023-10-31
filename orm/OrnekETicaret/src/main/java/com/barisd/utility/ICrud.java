package com.barisd.utility;

import com.barisd.repository.entity.Musteri;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ICrud<T> {
    void save(T entity);
    void update(T entity);
    void delete(Long id);
    List<T> findAll();
    T findById(Long id);


}
