package jaw.entity;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountingDAO
{   
	@PersistenceContext
	private EntityManager entityManager;
    
    @Autowired
    private AccountingRepository repository;
 
    public List<AccountingDTO> findAll()
    {
        return (List<AccountingDTO>) repository.findAll();
    }
    
	@Transactional(propagation=Propagation.REQUIRED)
    public void save(AccountingDTO acct) {
		//Session session = entityManager.unwrap(Session.class);
		//session.update(acct);
		repository.save(acct);
    }
}