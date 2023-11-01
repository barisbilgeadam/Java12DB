package com.barisd.repository;

import com.barisd.repository.entity.Satis;
import com.barisd.repository.entity.Sepet;
import com.barisd.utility.ICrud;
import com.barisd.utility.MyRepositoryFactory;

import java.util.List;

public class SatisRepository extends MyRepositoryFactory<Satis,Long> {


    public SatisRepository() {
        super(Satis.class);
    }
}
