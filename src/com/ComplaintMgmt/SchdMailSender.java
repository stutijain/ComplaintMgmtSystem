package com.ComplaintMgmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public void schdEmail(ArrayList<String> emails, String details, String comp_no, String priority) throws ClassNotFoundException, SQLException {

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
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
					"abcdefgh");

			
			Statement stmnt = con.createStatement();
			
			int comp=Integer.parseInt(comp_no);
			
			ResultSet rs = stmnt
					.executeQuery("SELECT * FROM complaint_details where complaint_no="+comp+";");
			
			while(rs.next()){
				String location=rs.getString("location");
			    String sub_location=rs.getString("sub_location");

				userMsg.setFrom(new InternetAddress(fromEmail));
				userMsg.setRecipients(Message.RecipientType.TO, address);
				if(priority.equals("Critical")){
					message="Critical Complaint!!! \nA complaint has been registered in your department with the following details\n Complaint no: " + comp_no + "\nDetails: " + details + "\nLocation: "+location+"\nSub-Location: "+sub_location+"\nPriority: " + priority;
				}else
				message = "A complaint has been registered in your department with the following details\n Complaint no: " + comp_no + "\nDetails: " + details + "\nLocation: "+location+"\nSub-Location: "+sub_location+"\nPriority: " + priority;
				
				String stg="\nPlease resolve the complaint at the earliest. It will proceed on to your superiors after the deadline";
				userMsg.setText(message+stg);
				userMsg.setSubject("New complaint");

				Transport transport = mailSession.getTransport("smtp");
				transport.connect("smtp.gmail.com", username, password);
				transport.sendMessage(userMsg, userMsg.getAllRecipients());
				
			}
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
