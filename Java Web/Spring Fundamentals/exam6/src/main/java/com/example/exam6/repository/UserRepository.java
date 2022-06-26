package com.example.exam6.repository;

import com.example.exam6.model.dto.EmployeeDTO;
import com.example.exam6.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    @Query("select new com.example.exam6.model.dto.EmployeeDTO(concat(e.firstName, ' ', e.lastName) , size(e.orders)) from User e where size(e.orders) >= 1 order by size(e.orders) desc")
    List<EmployeeDTO> findAllEmployees();

}
