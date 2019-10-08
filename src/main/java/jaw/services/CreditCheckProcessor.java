package jaw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

import jaw.entity.CreditCheckRequest;

@Component
public class CreditCheckProcessor {
	
	@Value("${jaw.mailprovider:sendgrid}")
	private String mailProvider;
	
	static final String SENDGRID_API_KEY = "YOUR-SENDGRID-API-KEY";
    static final String SENDGRID_SENDER = "support@jawmotors.com";
    static final String TO_EMAIL = "TO_EMAIL";
    
	@Autowired
	CreditVerificationService creditVerificationService;
	@Autowired
	MailSender mailSender;
	
	@JmsListener(destination = "credit_check", containerFactory = "myFactory")
    public void onMessage(CreditCheckRequest creditCheckReq) {
		
		System.out.println("Credit Check:");
		System.out.println("Name = [" + creditCheckReq.getName() + "]");
		System.out.println("SSN = [" + creditCheckReq.getSsn() + "]");
		System.out.println("Email = [" + creditCheckReq.getEmail() + "]");

		System.out.println("Verifying Credit ...");
		String result = creditVerificationService.verifyCredit(creditCheckReq);
		System.out.println("Credit Check Result = [" + result + "]");
		
        if(mailProvider.equals("sendgrid")) {
	        SendGrid sendgrid = new SendGrid(SENDGRID_API_KEY);
	        SendGrid.Email email = new SendGrid.Email();
	        email.addTo(creditCheckReq.getEmail());
	        email.setFrom(SENDGRID_SENDER);
	        email.setSubject("Credit Check");
	        email.setText("Dear " + creditCheckReq.getName()
	        			      + ", your credit check result is "
	        			      + result);
	        try {
	        	SendGrid.Response response = sendgrid.send(email);
	        	if (response.getCode() != 200) {
	        		System.out.print(String.format("An error occurred: %s", response.getMessage()));
	        		return;
	        	}
	        } catch (SendGridException e) {
	        	System.out.println("SendGridException: " + e);
	        	return;
	        }
	        System.out.println("Email sent.");
        }
        else { //send mail using Spring
	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(creditCheckReq.getEmail());
	        msg.setFrom("support@support.com");
	        msg.setSubject("Credit Check");
	        msg.setText(
	            "Dear " + creditCheckReq.getName()
	                + ", your credit check result is "
	                + result);
	        try{
	            this.mailSender.send(msg);
	        }
	        catch (MailException ex) {
	            // simply log it and go on...
	            System.out.println(ex.getMessage());
	        }
        }
	}
}
