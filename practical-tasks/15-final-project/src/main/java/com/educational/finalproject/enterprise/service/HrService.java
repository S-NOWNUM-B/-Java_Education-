package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.DepartmentDTO;
import com.educational.finalproject.enterprise.dto.EmployeeDTO;
import com.educational.finalproject.enterprise.mapper.DepartmentMapper;
import com.educational.finalproject.enterprise.mapper.EmployeeMapper;
import com.educational.finalproject.enterprise.model.Department;
import com.educational.finalproject.enterprise.model.Employee;
import com.educational.finalproject.enterprise.repository.DepartmentRepository;
import com.educational.finalproject.enterprise.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Сервис управления персоналом (HR Service).
 * Содержит бизнес-логику для работы с сотрудниками и департаментами.
 */
@Service
public class HrService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentMapper departmentMapper;

    public HrService(EmployeeRepository employeeRepository, 
                     DepartmentRepository departmentRepository, 
                     EmployeeMapper employeeMapper, 
                     DepartmentMapper departmentMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.employeeMapper = employeeMapper;
        this.departmentMapper = departmentMapper;
    }

    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toDTOList(employees);
    }

    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Сотрудник не найден с id: " + id));
        return employeeMapper.toDTO(employee);
    }

    @Transactional
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee employee = employeeMapper.toEntity(dto);
        
        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Департамент не найден"));
            employee.setDepartment(department);
        }
        
        Employee saved = employeeRepository.save(employee);
        return employeeMapper.toDTO(saved);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Невозможно удалить: сотрудник не найден");
        }
        employeeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<DepartmentDTO> getAllDepartments() {
        return departmentMapper.toDTOList(departmentRepository.findAll());
    }

    @Transactional
    public DepartmentDTO createDepartment(DepartmentDTO dto) {
        Department department = departmentMapper.toEntity(dto);
        Department saved = departmentRepository.save(department);
        return departmentMapper.toDTO(saved);
    }

    /**
     * Бизнес-логика повышения зарплаты.
     */
    @Transactional
    public EmployeeDTO promoteEmployee(Long id, String newPosition, double salaryIncrease) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));
        
        employee.setPosition(newPosition);
        employee.setSalary(employee.getSalary() + salaryIncrease);
        
        Employee updated = employeeRepository.save(employee);
        return employeeMapper.toDTO(updated);
    }

    /**
     * Перевод сотрудника в другой отдел.
     */
    @Transactional
    public EmployeeDTO transferEmployee(Long employeeId, Long departmentId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));
        
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Департамент не найден"));
        
        employee.setDepartment(department);
        Employee updated = employeeRepository.save(employee);
        return employeeMapper.toDTO(updated);
    }
}
