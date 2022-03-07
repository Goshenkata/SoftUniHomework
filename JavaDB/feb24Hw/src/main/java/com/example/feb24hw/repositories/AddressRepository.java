package com.example.feb24hw.repositories;

import com.example.feb24hw.entities.Address;
import com.example.feb24hw.entities.Employee;
import com.example.feb24hw.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAllByTown(Town town);
}
