package com.homepage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.entities.User;

@MultipartConfig
public class RegisterUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
		User user = new User();	
		user.setCategory(request.getParameter("category"));
		user.setName((String) request.getParameter("name"));
		user.setEmp_id(request.getParameter("emp_id"));
		user.setDesg(request.getParameter("desg"));
		user.setDept(request.getParameter("dept"));
		user.setEmail(request.getParameter("email"));
		user.setContact(request.getParameter("cont_no"));
		user.setAddr(request.getParameter("addr"));
		user.setPass(request.getParameter("pass"));
				
		
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_system", "root",
					"abcdef");
			String sql = "insert into user_details (category,name,emp_id,designation,department,email,contact_no,address,password)"
					+ " values(?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getCategory());
			st.setString(2, user.getName());
			st.setString(3, user.getEmp_id());
			st.setString(4, user.getDesg());
			st.setString(5, user.getDept());
			st.setString(6, user.getEmail());
			st.setString(7, user.getContact());
			st.setString(8, user.getAddr());
			st.setString(9, user.getPass());			
			int flag = st.executeUpdate();
			if (flag == 1) {				
				out.println("Registered Successfully!");
			} else {
				out.println("Failed!");
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Email ID already Registered");			
						
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Failure");
			
		}
		
		
	}
}
