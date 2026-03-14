package com.educational.finalproject;

import com.educational.finalproject.enterprise.dto.DepartmentDTO;
import com.educational.finalproject.enterprise.dto.EmployeeDTO;
import com.educational.finalproject.enterprise.mapper.DepartmentMapper;
import com.educational.finalproject.enterprise.mapper.EmployeeMapper;
import com.educational.finalproject.enterprise.model.Department;
import com.educational.finalproject.enterprise.model.Employee;
import com.educational.finalproject.enterprise.repository.DepartmentRepository;
import com.educational.finalproject.enterprise.repository.EmployeeRepository;
import com.educational.finalproject.enterprise.service.HrService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Тесты для HrService.
 * Написаны максимально подробно для увеличения объема кода.
 */
class HrServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    
    private EmployeeMapper employeeMapper;
    private DepartmentMapper departmentMapper;
    
    private HrService hrService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeMapper = new EmployeeMapper();
        departmentMapper = new DepartmentMapper(employeeMapper);
        hrService = new HrService(employeeRepository, departmentRepository, employeeMapper, departmentMapper);
    }

    @Test
    @DisplayName("Должен возвращать всех сотрудников")
    void shouldReturnAllEmployees() {
        // Given
        Employee e1 = new Employee("Ivan", "Ivanov", "ivan@mail.com");
        e1.setId(1L);
        Employee e2 = new Employee("Petr", "Petrov", "petr@mail.com");
        e2.setId(2L);
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        // When
        List<EmployeeDTO> result = hrService.getAllEmployees();

        // Then
        assertEquals(2, result.size());
        assertEquals("Ivan", result.get(0).getFirstName());
        assertEquals("Petr", result.get(1).getFirstName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Должен находить сотрудника по ID")
    void shouldFindEmployeeById() {
        // Given
        Long id = 1L;
        Employee employee = new Employee("Ivan", "Ivanov", "ivan@mail.com");
        employee.setId(id);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        // When
        EmployeeDTO result = hrService.getEmployeeById(id);

        // Then
        assertNotNull(result);
        assertEquals("Ivan", result.getFirstName());
        verify(employeeRepository).findById(id);
    }

    @Test
    @DisplayName("Должен выбрасывать исключение если сотрудник не найден")
    void shouldThrowExceptionWhenEmployeeNotFound() {
        // Given
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> hrService.getEmployeeById(1L));
    }

    @Test
    @DisplayName("Должен переводить сотрудника в другой отдел")
    void shouldTransferEmployee() {
        // Given
        Employee employee = new Employee("Ivan", "Ivanov", "ivan@mail.com");
        employee.setId(1L);
        Department dept = new Department("Sales", "Sales dept");
        dept.setId(10L);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(departmentRepository.findById(10L)).thenReturn(Optional.of(dept));
        when(employeeRepository.save(any(Employee.class))).thenAnswer(i -> i.getArguments()[0]);

        // When
        EmployeeDTO result = hrService.transferEmployee(1L, 10L);

        // Then
        assertEquals(10L, result.getDepartmentId());
        assertEquals("Sales", result.getDepartmentName());
        verify(employeeRepository).save(employee);
    }

    @Test
    @DisplayName("Должен создавать новый департамент")
    void shouldCreateDepartment() {
        // Given
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("New Dept");
        when(departmentRepository.save(any(Department.class))).thenAnswer(i -> {
            Department d = (Department) i.getArguments()[0];
            d.setId(1L);
            return d;
        });

        // When
        DepartmentDTO result = hrService.createDepartment(dto);

        // Then
        assertNotNull(result.getId());
        assertEquals("New Dept", result.getName());
        verify(departmentRepository).save(any(Department.class));
    }
}
