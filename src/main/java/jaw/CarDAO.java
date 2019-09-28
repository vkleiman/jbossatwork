package jaw;

import java.util.*;

public class CarDAO 
{
    private List<CarDTO> carList; 
    
    public CarDAO() 
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

 }
