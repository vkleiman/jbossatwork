package jaw.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingRepository extends CrudRepository<AccountingDTO, Long> {

}
