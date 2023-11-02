package com.barisd.repository;

import com.barisd.repository.entity.Personel;
import com.barisd.utility.MyRepositoryFactory;

public class PersonelRepository extends MyRepositoryFactory<Personel,Long> {
    public PersonelRepository() {
        super(Personel.class);
    }
}
