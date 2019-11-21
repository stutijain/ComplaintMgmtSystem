package com.ComplaintMgmt;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSenderBean {

	public void sendEmail(String fromEmail, String username, String password, String toEmail, String subject,
			String complaint_no) {

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.fallback", "false");
		String message = "Your complaint has been successfully registered. Your complaint number is " + complaint_no
				+ ".\nTo track the staus of your complaint kindly visit the portal.";

		Session mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(true);

		Message mailMessage = new MimeMessage(mailSession);
		try {

			mailMessage.setFrom(new InternetAddress(fromEmail));
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			mailMessage.setText(message);
			mailMessage.setSubject(subject);

			Transport transport = mailSession.getTransport("smtp");
			transport.connect("smtp.gmail.com", username, password);

			transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
