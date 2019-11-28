package com.ComplaintMgmt;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.entities.Complaint;

public class MailSenderBean {

	public void sendEmail(String fromEmail, String username, String password, Complaint complaint,
			ArrayList<String> emails) {

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.fallback", "false");

		String subject = "";
		String message = "";

		Session mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(true);

		Message mailMessage = new MimeMessage(mailSession);
		try {

			mailMessage.setFrom(new InternetAddress(fromEmail));
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(complaint.getEmail()));
			subject = "Complaint Registration";
			message = "Your complaint has been successfully registered. Your complaint number is "
					+ complaint.getComplaint_no() + ".\nTo track the status of your complaint kindly visit the portal.";
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

		if (emails.size() > 0) {
			InternetAddress[] address = new InternetAddress[emails.size()];
			for (int i = 0; i < emails.size(); i++) {
				try {
					address[i] = new InternetAddress(emails.get(i));
				} catch (AddressException e) {
					e.printStackTrace();
				}
			}

			Message userMsg = new MimeMessage(mailSession);
			try {

				userMsg.setFrom(new InternetAddress(fromEmail));
				userMsg.setRecipients(Message.RecipientType.TO, address);
				message = "complaint no: " + complaint.getComplaint_no() + "\nDetails: " + complaint.getDetails()
						+ "\nPriority: " + complaint.getPriority();
				userMsg.setText(message);
				userMsg.setSubject("New complaint");

				Transport transport = mailSession.getTransport("smtp");
				transport.connect("smtp.gmail.com", username, password);
				transport.sendMessage(userMsg, userMsg.getAllRecipients());
			} catch (MessagingException e) {
				e.printStackTrace();
			}

		}
	}
}
