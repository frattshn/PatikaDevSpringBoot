package com.kodluyoruz.patikadevspringboot.ui.rest;

import com.kodluyoruz.patikadevspringboot.business.dto.EmployeeDto;
import com.kodluyoruz.patikadevspringboot.business.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    private IEmployeeService employeeService;


    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    // http://localhost:8008/api/v1/employees
    @GetMapping("/employees")
    public List<EmployeeDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    // http://localhost:8080/api/v1/employees/1
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) throws Throwable {
        ResponseEntity<EmployeeDto> employeeDto = employeeService.getEmployeeById(id);
        return employeeDto;
    }

    // http://localhost:8080/api/v1/employees
    @PostMapping("/employees")
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto){
        employeeService.createEmployee(employeeDto);
        return employeeDto;
    }

    // http://localhost:8080/api/v1/employees/1
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) throws Throwable {
        employeeService.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    // http://localhost:8080/api/v1/employees/1
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployeeById(@PathVariable Long id) throws Throwable {
        employeeService.deleteEmployee(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }




}
