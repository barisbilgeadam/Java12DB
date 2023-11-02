package com.barisd.repository;

import com.barisd.repository.entity.Address;
import com.barisd.utility.MyRepositoryFactory;

public class AddressRepository extends MyRepositoryFactory<Address,Long> {
    public AddressRepository() {
        super(Address.class);
    }
}
