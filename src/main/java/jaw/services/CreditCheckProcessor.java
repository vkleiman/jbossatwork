package jaw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import jaw.entity.CreditCheckRequest;

@Component
public class CreditCheckProcessor {
	
	@Autowired
	CreditVerificationService creditVerificationService;
	
	@JmsListener(destination = "credit_check", containerFactory = "myFactory")
    public void onMessage(CreditCheckRequest creditCheckReq) {
		
		System.out.println("Credit Check:");
		System.out.println("Name = [" + creditCheckReq.getName() + "]");
		System.out.println("SSN = [" + creditCheckReq.getSsn() + "]");
		System.out.println("Email = [" + creditCheckReq.getEmail() + "]");

		System.out.println("Verifying Credit ...");
		String result = creditVerificationService.verifyCredit(creditCheckReq);
		System.out.println("Credit Check Result = [" + result + "]");
	}
}
