package com.kodluyoruz.patikadevspringboot.business.service.impl;

import com.kodluyoruz.patikadevspringboot.business.dto.EmployeeDto;
import com.kodluyoruz.patikadevspringboot.business.service.IEmployeeService;
import com.kodluyoruz.patikadevspringboot.data.entity.EmployeeEntity;
import com.kodluyoruz.patikadevspringboot.data.repository.IEmployeeRepository;
import com.kodluyoruz.patikadevspringboot.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements IEmployeeService {


    private IEmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public EmployeeServiceImpl(IEmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }



    // http://localhost:8080/api/v1/employees
    @GetMapping("/employees")
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employees = new ArrayList<>();
        Iterable<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        for(EmployeeEntity e : employeeEntities){
            EmployeeDto employeeDto = entityToDto(e);
            employees.add(employeeDto);
        }
        return employees;
    }

    // http://localhost:8080/api/v1/employees
    @PostMapping("/employees")
    @Override
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = dtoToEntity(employeeDto);
        employeeRepository.save(employeeEntity);
        return employeeDto;
    }

    // http://localhost:8080/api/v1/employees/1
    @GetMapping("/employees/{id}")
    @Override
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) throws Throwable {
        EmployeeEntity employeeEntity = (EmployeeEntity) employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Exist"));
        EmployeeDto employeeDto = entityToDto(employeeEntity);
        return ResponseEntity.ok(employeeDto);
    }

    // http://localhost:8080/api/v1/employees/1
    @PutMapping("/employees/{id}")
    @Override
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) throws Throwable {
        EmployeeEntity employeeEntity = dtoToEntity(employeeDto);
        EmployeeEntity employee = (EmployeeEntity) employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Exist"));
        employee.setFirstName(employeeEntity.getFirstName());
        employee.setLastName(employeeEntity.getLastName());
        employee.setEmailId(employeeEntity.getEmailId());
        EmployeeEntity updatedEmployee = (EmployeeEntity) employeeRepository.save(employee); //Burayı araştır
        EmployeeDto updatedDto = entityToDto(updatedEmployee);
        return ResponseEntity.ok(updatedDto);
    }

    // http://localhost:8080/api/v1/employee/1
    @DeleteMapping("/employees/{id}")
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) throws Throwable {
        EmployeeEntity employeeEntity = (EmployeeEntity) employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
        employeeRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    //Model Mapper

    @Override
    public EmployeeDto entityToDto(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = modelMapper.map(employeeEntity, EmployeeDto.class);
        return employeeDto;
    }

    @Override
    public EmployeeEntity dtoToEntity(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        return employeeEntity;
    }
}
