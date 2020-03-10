package com.homepage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.entities.User;

@MultipartConfig
public class RegisterUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = new User();
		user.setCategory(request.getParameter("category"));
		user.setName((String) request.getParameter("name"));
		user.setEmail(request.getParameter("email"));
		user.setContact(request.getParameter("cont_no"));
		
		// this is level basically
//		user.setEmp_id(Integer.parseInt(request.getParameter("emp_id")));
		user.setLevel(request.getParameter("level"));

		user.setDesg(request.getParameter("desg"));
		user.setDept(request.getParameter("dept"));
		
		
		user.setAddr(request.getParameter("addr"));
		user.setPass(request.getParameter("pass"));
		user.setDob((String) request.getParameter("dob"));
		
		String email = request.getParameter("email");

		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
					"abcdef");
			Statement stmnt = con.createStatement();
			
			String query = "SELECT * FROM user_details where user_id=(select max(user_id) from user_details);";
			ResultSet rs = stmnt.executeQuery(query);
			int user_id = 0;
			while (rs.next()) {
				user_id = rs.getInt("user_id");
			}
			user.setEmp_id(user_id);
			
			
			 rs = stmnt.executeQuery("SELECT * FROM user_details where email='" + email + "'");

			if (rs.next()) {
				out.println("Already Registered!!!");
				response.setHeader("Refresh", "2; Registration.html");

			} else {

				String sql = "insert into user_details (category,name,email,contact_no,user_id,designation,department,address,password,dob,level)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, user.getCategory());
				st.setString(2, user.getName());
				st.setString(3, user.getEmail());
				st.setString(4, user.getContact());
				st.setInt(5, user.getEmp_id());
				st.setString(6, user.getDesg());
				st.setString(7, user.getDept());
				st.setString(8, user.getAddr());
				st.setString(9, user.getPass());
				st.setString(10, user.getDob());
				st.setString(11, user.getLevel());
//				out.println("hey");
				int flag = st.executeUpdate();
//				out.println("hey");
				if (flag == 1) {
					out.println("<!DOCTYPE html>");
					out.println("<html>");
					out.println("<body>");
					out.println("<h1>Registered Successfully!</h1>");
					out.println("<br><br>");
					out.println("Web page redirects after 5 seconds");
					out.println("</body>");
					out.println("</html>");

					response.setHeader("Refresh", "5; HomePage.html");
				} else {
					out.println("Failed!");
					response.setHeader("Refresh", "2; HomePage.html");
				}
			}

		} catch (SQLException e) {
			out.println("Try Again");
			response.setHeader("Refresh", "1; Registration.html");

		} catch (ClassNotFoundException e) {
			out.println("Failure");

		}

	}
}
