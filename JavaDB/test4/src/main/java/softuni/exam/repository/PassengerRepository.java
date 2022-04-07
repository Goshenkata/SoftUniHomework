package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.Passenger;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    @Query("select p from Passenger p order by p.tickets.size desc, p.email")
    List<Passenger> findAllByOrderByTicketsCountThenByEmail();

    boolean existsByEmail(String email);

    Passenger getByEmail(String passangerEmail);
}
