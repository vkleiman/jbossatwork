package jaw;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import javax.jms.ConnectionFactory;

import jaw.entity.CarDAO;
import jaw.entity.InMemoryCarDAO;
import jaw.entity.JDBCCarDAO;
import jaw.entity.JPACarDAO;

@EnableTransactionManagement
@EnableAutoConfiguration
@EnableJms
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
	
    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
