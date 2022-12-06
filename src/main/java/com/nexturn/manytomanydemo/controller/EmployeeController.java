package com.nexturn.manytomanydemo.controller;

import com.nexturn.manytomanydemo.dto.EmployeeDTO;
import com.nexturn.manytomanydemo.model.Employee;
import com.nexturn.manytomanydemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
        List<Employee> employees = employeeService.getAllEmployee();
        List<EmployeeDTO> employeeDTOs = employees.stream().map(EmployeeDTO::from).toList();
        return ResponseEntity.status(HttpStatus.OK).body(employeeDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") final Long id){
        Employee employee = employeeService.getEmployeeById(id);
        EmployeeDTO employeeDTO = EmployeeDTO.from(employee);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody final EmployeeDTO employeeDTO){
        Employee employee = employeeService.saveEmployee(Employee.from(employeeDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(EmployeeDTO.from(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable("id") final Long id){
        Employee employee = employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body(EmployeeDTO.from(employee));
    }

    @PostMapping("/{employeeId}/projects/{projectId}")
    public ResponseEntity<EmployeeDTO> addProjectToEmployee(
            @PathVariable("employeeId") final Long employeeId,
            @PathVariable("projectId") final Long projectId){
        Employee employee = employeeService.addProjectToEmployee(employeeId, projectId);
        return ResponseEntity.status(HttpStatus.OK).body(EmployeeDTO.from(employee));
    }

    @DeleteMapping("/{employeeId}/projects/{projectId}")
    public ResponseEntity<EmployeeDTO> removeProjectFromEmployee(
            @PathVariable("employeeId") final Long employeeId,
            @PathVariable("projectId") final Long projectId){
        Employee employee = employeeService.removeProjectFromEmployee(employeeId, projectId);
        return ResponseEntity.status(HttpStatus.OK).body(EmployeeDTO.from(employee));
    }
}
