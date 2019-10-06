package jaw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jaw.entity.AccountingDAO;
import jaw.entity.AccountingDTO;
import jaw.entity.CarDAO;
import jaw.entity.CarDTO;

@Service
public class InventoryService {

	@Autowired
	CarDAO carDAO;
	
	@Autowired
	AccountingDAO accountingDAO;
	
	@Autowired
	PlatformTransactionManager ptm;
	
	public List<CarDTO> listAvailableCars() {		
		return carDAO.findByStatus(CarDTO.STATUS_AVAILABLE);
	}
	
	public List<CarDTO> findAll() {		
		return carDAO.findAll();
	}
	
	public CarDTO findCar(Long id) {
		
		CarDTO car;
		if(id==0)
			car = new  CarDTO();
		else
			car = carDAO.findById(id);
		return car;
	}
	
	public void saveCar(CarDTO carDto) {
		Long id = carDto.getId();
		if(id==null || id==0) {
			carDto.setStatus(CarDTO.STATUS_AVAILABLE);
			carDAO.save(carDto);
		}
		else //update
			carDAO.update(carDto);
	}
	
	public void deleteCars(List<Long> ids) {
		if(ids==null)
			return;
		for(Long id: ids)
			carDAO.delete(id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void buyCar(Long carId, Integer price) {
		
		CarDTO car = carDAO.findById(carId);
		car.setStatus(CarDTO.STATUS_SOLD + price);
		carDAO.update(car);
		
		AccountingDTO acct = new AccountingDTO(carId, price);
		try {
			accountingDAO.save(acct);
		}
		catch (Exception e) {
			  System.out.println("buyCar exception " + e);
		      throw new UnexpectedRollbackException("Car no longer available");
		}
	}
}
