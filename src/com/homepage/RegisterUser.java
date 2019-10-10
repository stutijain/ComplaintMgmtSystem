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

import com.entities.Complaint;

@MultipartConfig
public class RegisterUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Complaint complaint = new Complaint();	
		complaint.setCategory(request.getParameter("category"));
		complaint.setName((String) request.getParameter("name"));
		complaint.setEmp_id(request.getParameter("emp_id"));
		complaint.setDesg(request.getParameter("desg"));
		complaint.setDept(request.getParameter("dept"));
		complaint.setEmail(request.getParameter("email"));
		complaint.setContact(request.getParameter("cont_no"));
		complaint.setAddr(request.getParameter("addr"));
		complaint.setPass(request.getParameter("pass"));
				
		
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
					"abcdef");
			String sql = "insert into user_details (category,name,emp_id,designation,department,email,contact_no,address,password)"
					+ " values(?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, complaint.getCategory());
			st.setString(2, complaint.getName());
			st.setString(3, complaint.getEmp_id());
			st.setString(4, complaint.getDesg());
			st.setString(5, complaint.getDept());
			st.setString(6, complaint.getEmail());
			st.setString(7, complaint.getContact());
			st.setString(8, complaint.getAddr());
			st.setString(9, complaint.getPass());			
			int flag = st.executeUpdate();
			if (flag == 1) {				
				out.println("Registered Successfully!");
			} else {
				out.println("Failed!");
			}
			
		} catch (SQLException e) {
			out.println("Email ID already Registered");				
						
		} catch (ClassNotFoundException e) {
			out.println("Failure");			
			
		}
		
		
	}
}
