package com.educational.finalproject.enterprise.controller;

import com.educational.finalproject.enterprise.dto.DepartmentDTO;
import com.educational.finalproject.enterprise.dto.EmployeeDTO;
import com.educational.finalproject.enterprise.service.HrService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST контроллер для управления персоналом.
 */
@RestController
@RequestMapping("/api/hr")
public class HrController {

    private final HrService hrService;

    public HrController(HrService hrService) {
        this.hrService = hrService;
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees() {
        return hrService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(hrService.getEmployeeById(id));
    }

    @PostMapping("/employees")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO dto) {
        return hrService.createEmployee(dto);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        hrService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/departments")
    public List<DepartmentDTO> getAllDepartments() {
        return hrService.getAllDepartments();
    }

    @PostMapping("/departments")
    public DepartmentDTO createDepartment(@RequestBody DepartmentDTO dto) {
        return hrService.createDepartment(dto);
    }

    @PutMapping("/employees/{id}/promote")
    public EmployeeDTO promote(@PathVariable Long id, 
                              @RequestParam String position, 
                              @RequestParam double increase) {
        return hrService.promoteEmployee(id, position, increase);
    }

    @PutMapping("/employees/{id}/transfer")
    public EmployeeDTO transfer(@PathVariable Long id, @RequestParam Long departmentId) {
        return hrService.transferEmployee(id, departmentId);
    }
}
