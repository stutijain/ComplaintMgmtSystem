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

public class SchdMailSender {
	
	public void schdEmail(ArrayList<String> emails, String details, String comp_no, String priority){
		
		String fromEmail = "project.2020.final.year@gmail.com";
		String password = "Project@20";
		String username = "project.2020.final.year@gmail.com";
		
		
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.fallback", "false");

		String message = "";

		Session mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(true);
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
			message="complaint no: "+comp_no+"\nDetails: "+details+"\nPriority: "+priority;
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
