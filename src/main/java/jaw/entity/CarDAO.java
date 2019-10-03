package jaw.entity;

import java.util.*;

public interface CarDAO {

	public List<CarDTO> findAll();
	
	public void save(CarDTO car);
	
	public CarDTO findById(Long id);
	
	public void update(CarDTO car);
	
	public void delete(Long id);
	
	public List<CarDTO> findByStatus(String status);
}
