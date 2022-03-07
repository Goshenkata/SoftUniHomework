package com.example.feb24hw.repositories;

import com.example.feb24hw.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
   boolean existsAllByFirstNameAndLastName(String firstName, String lastName);
   List<Employee> findAllBySalaryGreaterThan(BigDecimal n);
   List<Employee> findAllByDepartmentNameOrderBySalaryAscIdAsc(String departmentName);
   Optional<Employee> findByLastName(String lastName);

}
