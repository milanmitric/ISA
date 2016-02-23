package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;



@MessageDriven(
	activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "PaymentQueue") 
	}
)
public class PaymentBean implements MessageListener {
	
	@Resource(name="Mail")
	Session session;

	public PaymentBean() {

	}

	public void onMessage(Message message) {
		
	    try {
	        if (message instanceof ObjectMessage) {
	            ObjectMessage obj = (ObjectMessage) message;
	            String info = (String)obj.getObject();
	            
	            // Validate the credit card using web services...
	            
	            sendMessage(info);
	        } else {
	            System.out.println("MESSAGE BEAN: Message of wrong type: " + message.getClass().getName());
	        }
	    } catch (JMSException e) {
	    	System.out.println("USAO U KEC 1");
	        e.printStackTrace();
	    } catch (Throwable te) {
	    	System.out.println("USAO U KEC 2");
	        te.printStackTrace();
	    }
	}

	private void sendMessage(String info) throws AddressException, MessagingException {

		System.out.println("here");
		// Constructs the message 
		String email = null;
		String[] tokens = null;
		String token = null;
		tokens = info.split("korisnickoIme");
		token = new String(tokens[1]);
		tokens = token.split("&");
		token = new String(tokens[0]);
		email = token.substring(1);
		javax.mail.Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("probanjebre@gmail.com"));
		msg.setRecipients(RecipientType.TO, InternetAddress.parse(email));
		msg.setSubject("Test");
		msg.setText(info);
		msg.setSentDate(new Date());
		System.out.println("MEJL " + email);
		System.out.println("TEKST " + info);
		// Sends the message
		Transport.send(msg);

		System.out.println("MESSAGE BEAN: Mail was sent successfully.");
	}
}
