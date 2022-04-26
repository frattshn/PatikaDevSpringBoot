package com.kodluyoruz.patikadevspringboot;

import com.kodluyoruz.patikadevspringboot.data.entity.EmployeeEntity;
import com.kodluyoruz.patikadevspringboot.data.repository.IEmployeeRepository;
import com.kodluyoruz.patikadevspringboot.test.TestCrud;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatikaDevSpringBootApplicationTests implements TestCrud {

    @Autowired
    private IEmployeeRepository employeeRepository;

    /*
    public PatikaDevSpringBootApplicationTests(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    */

    /*
    @Test
    void contextLoads() {
    }
    */

    @Test
    @Override
    public void testList() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        // Eger sıfırdan buyukse liste vardir.
        assertThat(employeeEntities).size().isGreaterThan(0);

    }

    @Test
    @Override
    public void testFindById() {
        EmployeeEntity employeeEntity = employeeRepository.findById(1L).get();

        // Hamit Test adinda kayit var mi yok mu
        assertEquals("Hamit Test", employeeEntity.getFirstName());

    }

    @Test
    @Override
    public void testCreate() {
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .firstName("Hamit Test")
                .lastName("Mizrak Test")
                .emailId("hamit@test.com")
                .build();
        employeeRepository.save(employeeEntity);

        // Nesne null ise assertionError gonderir
        assertNotNull(employeeRepository.findById(1L).get());

    }

    @Test
    @Override
    public void testUpdate() {
        EmployeeEntity employeeEntity = employeeRepository.findById(1L).get();
        employeeEntity.setFirstName("Hamit 44 Test");
        employeeRepository.save(employeeEntity);

        // Esit degilse hata olmayacak, esit ise exception fırlatır
        assertNotEquals("Hamit Test", employeeRepository.findById(1L).get().getFirstName());

    }

    @Test
    @Override
    public void testDelete() {
        employeeRepository.deleteById(1L);

        //
        assertThat(employeeRepository.existsById(1L)).isFalse();

    }
}
