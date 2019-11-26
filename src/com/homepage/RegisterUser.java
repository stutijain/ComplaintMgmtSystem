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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		User user = new User();	
		user.setCategory(request.getParameter("category"));
		user.setName((String) request.getParameter("name"));
		
//		this is level basically
		user.setEmp_id(Integer.parseInt(request.getParameter("emp_id")));
		
		user.setDesg(request.getParameter("desg"));
		user.setDept(request.getParameter("dept"));
		user.setEmail(request.getParameter("email"));
		user.setContact(request.getParameter("cont_no"));
		user.setAddr(request.getParameter("addr"));
		user.setPass(request.getParameter("pass"));
		String email = request.getParameter("email");		
		
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
					"abcdef");
			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM user_details where email='"+email+"'");
			if(rs.next()){
				out.println("Already Registered!!!");
//				JOptionPane.showMessageDialog(null, "Already Registered");				
			}else{
				String sql = "insert into user_details (category,name,level,designation,department,email,contact_no,address,password)"
						+ " values(?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, user.getCategory());
				st.setString(2, user.getName());
				st.setInt(3, user.getEmp_id());
				st.setString(4, user.getDesg());
				st.setString(5, user.getDept());
				st.setString(6, user.getEmail());
				st.setString(7, user.getContact());
				st.setString(8, user.getAddr());
				st.setString(9, user.getPass());
				int flag = st.executeUpdate();
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
				}
			}
							
		} catch (SQLException e) {
			out.println("Email ID already Registered");				
						
		} catch (ClassNotFoundException e) {
			out.println("Failure");			
			
		}
		
		
	}
}
