package service;

import java.util.Properties;

import entity.Usuario;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EnvioMailService {
	
	private final Properties properties = new Properties();
	
	private static final String mailFrom = "pasosclinica@gmail.com";
	private static final String password = "omtb wekw bogr kyaq";

	

	private static Properties getGmailProperties() {
		
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");		
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port","587");		
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		return prop;	
//		//properties.put("mail.smtp.mail.sender","emisor@gmail.com");
//		properties.put("mail.smtp.user", mailFrom);
//		properties.put("mail.smtp.ssl.protocols", "TSLv1.2");		
		
	}
	
	
	
	private static Session getEmailSession()
	{
		return Session.getInstance(getGmailProperties(), new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(mailFrom, password);
			}		
		});
	}
	
	

	public void enviarEmail(Usuario us) {
		
		
		try{
			Message msj = new MimeMessage(getEmailSession());
			msj.setFrom(new InternetAddress(mailFrom));
			msj.setRecipients(Message.RecipientType.TO, InternetAddress.parse(us.getEmail()));
			msj.setSubject("Su contraseña ha cambiado");
			msj.setText("Su nueva clase es: " + us.getClave());
			Transport.send(msj);
			
		}
		
		catch (MessagingException me){
			//Manejar la excepcion o arrojarla?
			
			return;
		}
		
	}
}
