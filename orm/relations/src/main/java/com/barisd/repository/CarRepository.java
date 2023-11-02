package com.barisd.repository;

import com.barisd.repository.entity.Car;
import com.barisd.utility.MyRepositoryFactory;

public class CarRepository extends MyRepositoryFactory<Car,Long> {
    public CarRepository() {
        super(Car.class);
    }
}
