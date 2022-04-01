package exam.repository;

import exam.model.DTO.BestLaptopDTO;
import exam.model.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//ToDo:
@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    boolean existsByMacAddress(String macAddress);

    @Query("select new exam.model.DTO.BestLaptopDTO(l.macAddress, l.cpuSpeed, l.ram, l.storage, l.price,l.shop.name,l.shop.town.name) from laptops l order by l.cpuSpeed desc, l.ram desc, l.storage desc, l.macAddress")
    List<BestLaptopDTO> getBestLaptops();
}
