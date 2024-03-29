package jaw.entity;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

//@Repository
public class JPACarDAO implements CarDAO
{   
	@PersistenceContext
	private EntityManager entityManager;
    
    @Autowired
    private CarRepository repository;
 
    public List<CarDTO> findAll()
    {
        return (List<CarDTO>) repository.findAll();
    }
    
    public void save(CarDTO car) {
    		repository.save(car);
    }
    
    public void delete(Long id) {
        	repository.deleteById(id);
    }
    
    @Transactional
    public void update(CarDTO car) {
    		Session session = entityManager.unwrap(Session.class);
    		session.update(car);
    }
    
    public CarDTO findById(Long id) {
		Optional<CarDTO> car = repository.findById(id);
		return car.get();
    }
    
    public List<CarDTO> findByStatus(String status) {
		return repository.findByStatus(status);
    }
}