package com.ComplaintMgmt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendMail
 */
@WebServlet("/SendMail")
public class SendMail extends HttpServlet {
	
	@EJB
	private MailSenderBean mailSender;
	
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String toEmail = request.getParameter("email");
		String subject = "Complaint Registration";
		
		String fromEmail = "charu07goel@gmail.com";
		String password = "Charu@25";
		String username = "charu07goel";
		
		try(PrintWriter out = response.getWriter()){
			
			mailSender.sendEmail(fromEmail, username, password, toEmail, subject);
			
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Mail Status</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Mail Status!!!</h1>");
			out.println("<b>Mail Sent Successfully</b><br>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
