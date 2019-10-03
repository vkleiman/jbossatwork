package jaw;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jaw.entity.CarDAO;
import jaw.entity.InMemoryCarDAO;
import jaw.entity.JDBCCarDAO;
import jaw.entity.JPACarDAO;

@EnableTransactionManagement
@EnableAutoConfiguration
@Configuration
public class AppConfig {
	
	@Value("${jaw.cardao:jpa}")
	private String carDao;
	
	@Bean
	public CarDAO createDAO() {
		
		System.out.println("cardao = " + carDao);
		if(carDao.equals("memory"))
			return new InMemoryCarDAO();
		else if(carDao.equals("jdbc"))
			return new JDBCCarDAO();
		return new JPACarDAO();
	}
	
}
