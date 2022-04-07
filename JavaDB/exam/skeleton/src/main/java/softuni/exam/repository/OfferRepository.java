package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.dto.OfferExportDTO;
import softuni.exam.models.entity.Offer;

import java.util.List;

// TODO:
@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query("select new softuni.exam.models.dto.OfferExportDTO(o.agent.firstName, o.agent.lastName, o.id, o.apartment.area, o.apartment.town.townName, o.price) from Offer o where o.apartment.apartmentType = 'THREE_ROOMS' order by o.apartment.area desc, o.price asc ")
    List<OfferExportDTO> exportOffers();
}
