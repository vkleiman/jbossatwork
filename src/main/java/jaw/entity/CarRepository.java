package jaw.entity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<CarDTO, Long> {
	List<CarDTO> findByStatus(String status);
}
