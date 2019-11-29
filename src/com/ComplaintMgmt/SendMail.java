package com.ComplaintMgmt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entities.Complaint;

/**
 * Servlet implementation class SendMail
 */
public class SendMail extends HttpServlet {

	@EJB
	private MailSenderBean mailSender = new MailSenderBean();

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Complaint complaint = (Complaint) request.getAttribute("complaint");
		@SuppressWarnings("unchecked")
		ArrayList<String> emails = (ArrayList<String>) request.getAttribute("emails");
		String fromEmail = "project.2020.final.year@gmail.com";
		String password = "Project@20";
		String username = "project.2020.final.year@gmail.com";

		try (PrintWriter out = response.getWriter()) {

			mailSender.sendEmail(fromEmail, username, password, complaint, emails);
			System.out.println("sent");

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Mail Status</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Mail Status!!!</h1>");
			out.println("<b>Mail Sent Successfully</b><br>");
			out.println("<b>Your complaint has been successfully registered. Your compalint number is "
					+ complaint.getComplaint_no() + "</b><br>");
			out.println("<br><br>");
			out.println("Web page redirects after 5 seconds");
			out.println("</body>");
			out.println("</html>");

			response.setHeader("Refresh", "5; HomePage.html");

		}
	}

}
