package observers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import modelo.Observer;

public class SendEmail implements Observer {

	@Override
	public void actualizar(String valor, String email) {

		// remitente
		String to = email;
		// destinatario
		String from = "EstacionServicioYPZW@example.com";
		// usuario y clave que se obtiene desde Mailtrap
		final String username = "e2d0ab43409951";
		final String password = "49a92c4e24b4c1";

		String host = "smtp.mailtrap.io";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");// it’s optional in Mailtrap
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "2525");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			// Set From: header field
			message.setFrom(new InternetAddress(from));
			// Set To: header field
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			// Set Subject: header field
			message.setSubject("Registro de Venta");
			// Put the content of your message
			message.setText(valor);
			// Send message
			Transport.send(message);
//			System.out.println("Mensaje enviado con éxito...");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
