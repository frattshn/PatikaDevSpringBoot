package com.kodluyoruz.patikadevspringboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// JpaRepository>CrudRepository
@Repository
public interface IEmployeeRepository extends CrudRepository {
}
