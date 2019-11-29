package com.ComplaintMgmt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class ForgotPassword
 */
//@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		try {

			String email = request.getParameter("email");
			String dob = request.getParameter("dob");
			String pass = request.getParameter("pass");
			String cpass = request.getParameter("cpass");

			if (pass.equals("")) {

				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<body>");
				out.println("<br>");
				out.println("<h3>Password cannot be null!!</h3>");
				out.println("</body>");
				out.println("</html>");
				response.setHeader("Refresh", "1; forgotPass.html");

			} else if (!cpass.equals(pass)) {

				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<body>");
				out.println("<br>");
				out.println("<h3>Password donot match!!</h3>");
				out.println("</body>");
				out.println("</html>");
				response.setHeader("Refresh", "1; forgotPass.html");

			} else {

				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
						"abcdef");

				Statement stmnt = con.createStatement();
				ResultSet rs = stmnt
						.executeQuery("SELECT * FROM user_details where email='" + email + "' and dob= '" + dob + "'");
				if (rs.next()) {
					String sql = "update user_details set password=? where email=?";
					PreparedStatement st = con.prepareStatement(sql);
					st.setString(2, email);
					st.setString(1, pass);

					int flag = st.executeUpdate();

					if (flag == 1) {

						out.println("<!DOCTYPE html>");
						out.println("<html>");
						out.println("<body>");
						out.println("<h1>Password Updated Successfully</h1>");
						out.println("<br>Web page redirects in 3 seconds");
						out.println("</body>");
						out.println("</html>");

						response.setHeader("Refresh", "3; HomePage.html");

					} else {
						out.println("<!DOCTYPE html>");
						out.println("<html>");
						out.println("<body>");
						out.println("<h1>Cannot Update Paasword</h1>");
						out.println("<br>Web page redirects in 3 seconds");
						out.println("</body>");
						out.println("</html>");

						response.setHeader("Refresh", "3; HomePage.html");
					}
				} else {

					out.println("<!DOCTYPE html>");
					out.println("<html>");
					out.println("<body>");
					out.println("<h1>Check Email ID and Date of Birth</h1>");
					out.println("</body>");
					out.println("</html>");

					response.setHeader("Refresh", "1; forgotPass.html");
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
