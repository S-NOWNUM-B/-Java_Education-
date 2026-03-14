package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByLastName(String lastName);
    List<Employee> findByDepartmentId(Long departmentId);
    Employee findByEmail(String email);
}
