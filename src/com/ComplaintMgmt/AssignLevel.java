package com.ComplaintMgmt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entities.Complaint;
import com.entities.User;

//@WebServlet("/login")
public class AssignLevel extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		 
		String email = request.getParameter("email");
		int level = Integer.parseInt(request.getParameter("level"));
		System.out.println(level + " " + email);
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
					"abcdefgh");

			Statement stmnt = con.createStatement();
			stmnt.executeUpdate("UPDATE user_details SET level = '"+level+ "' where email ='"+email+"';");
			
			ResultSet rs = stmnt.executeQuery("SELECT * FROM user_details");
			
			if (rs.next()) {
//				out.println("Login Successful");
				
				ArrayList<User> complaints=new ArrayList<>();
				ResultSet allComplaints = stmnt.executeQuery("SELECT * from user_details ");
				addComplaints(allComplaints,complaints);
				
				request.setAttribute("data", complaints);

//				RequestDispatcher rd = request.getRequestDispatcher("EngineerDetails.jsp");
//				rd.forward(request, response);

			}

			
			RequestDispatcher rd = request.getRequestDispatcher("EngineerDetails.jsp");
			rd.forward(request, response);

			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addComplaints(ResultSet allComplaints,ArrayList<User> complaints) throws NumberFormatException, SQLException{
		while (allComplaints.next()) {
			User complaint = new User();
			complaint.setCategory(allComplaints.getString("category"));
//			complaint.setLocation(allComplaints.getString("location"));
//			complaint.setSub_location(allComplaints.getString("sub_location"));
//			complaint.setDesignation(allComplaints.getString("designation"));
//			complaint.setDetails(allComplaints.getString("details"));
//			complaint.setPriority(allComplaints.getString("priority"));
			complaint.setName(allComplaints.getString("name"));
			complaint.setEmail(allComplaints.getString("email"));
			complaint.setContact(allComplaints.getString("contact_no"));
			complaint.setDob(allComplaints.getString("dob"));
			complaint.setLevel(allComplaints.getString("level"));
//			complaint.setComplaint_no(Integer.parseInt(allComplaints.getString("complaint_no")));
//			complaint.setCom_status(allComplaints.getString("com_status"));

			complaints.add(complaint);

		}
		
	}
}
