package com.barisd.repository;

import com.barisd.repository.entity.Department;
import com.barisd.utility.MyRepositoryFactory;

public class DepartmentRepository extends MyRepositoryFactory<Department,Long> {
    public DepartmentRepository() {
        super(Department.class);
    }
}
