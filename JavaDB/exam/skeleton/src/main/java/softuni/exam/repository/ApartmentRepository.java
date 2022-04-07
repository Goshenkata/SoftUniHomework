package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;

import java.util.List;
import java.util.Optional;

// TODO:
@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    @Query("select a from Apartment a where a.town.townName like ?1 and a.area = ?2")
    List<Apartment> findByTownAndArea(String townName, Double area);
}
