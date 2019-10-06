package jaw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import jaw.entity.CreditCheckRequest;

@Component
public class JmsProducer {
	
	@Autowired
	private ApplicationContext context;
	
	public void sendMessage(CreditCheckRequest msg) {
		
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        // Send a message with a POJO - the template reuse the message converter
        System.out.println("Sending CreditCheckMsg...");
        jmsTemplate.convertAndSend("credit_check", msg);
    }
}
