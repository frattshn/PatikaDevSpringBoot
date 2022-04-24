package com.kodluyoruz.patikadevspringboot.data.repository;

import com.kodluyoruz.patikadevspringboot.data.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository>CrudRepository
@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
