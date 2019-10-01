package jaw.entity;

import java.util.*;

public class InMemoryCarDAO implements CarDAO
{
    private List<CarDTO> carList; 
    
    public InMemoryCarDAO() 
    {
        carList = new ArrayList<CarDTO>();
        carList.add(new CarDTO("Toyota", "Camry", 2005));
        carList.add(new CarDTO("Toyota", "Corolla", 1999));
        carList.add(new CarDTO("Ford", "Explorer", 2005));
    }
 
    public List<CarDTO> findAll()
    {
        return carList;
    }
    
    public void save(CarDTO car) { }
    
    public CarDTO findById(Long id) {return null; }
    
    public void update(CarDTO car) { }
    
	public void delete(List<Long> ids) { }

}
