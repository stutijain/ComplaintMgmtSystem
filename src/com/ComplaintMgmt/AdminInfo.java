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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entities.Complaint;
import com.entities.User;

//@WebServlet("/login")
public class AdminInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		PrintWriter out = response.getWriter();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
					"abcdefgh");

			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt
					.executeQuery("SELECT * FROM complaint_details ");
			if (rs.next()) {
//				out.println("Login Successful");
				
				ArrayList<Complaint> complaints=new ArrayList<>();
				ResultSet allComplaints = stmnt.executeQuery("SELECT * from complaint_details where com_status like 'Pending' ORDER BY complaint_no DESC ;");
				addComplaints(allComplaints,complaints);
				
				ResultSet allComplaintsInP = stmnt.executeQuery("SELECT * from complaint_details where com_status like 'In Progress' ORDER BY complaint_no DESC ;");
				addComplaints(allComplaintsInP,complaints);
				
				ResultSet allComplaintsComp = stmnt.executeQuery("SELECT * from complaint_details where com_status like 'Completed' ORDER BY complaint_no DESC ;");
				addComplaints(allComplaintsComp,complaints);
				
				ResultSet allComplaintsNotComp = stmnt.executeQuery("SELECT * from complaint_details where com_status like 'Not Completed' ORDER BY complaint_no DESC ;");
				addComplaints(allComplaintsNotComp,complaints);
				
				
				request.setAttribute("data", complaints);

				RequestDispatcher rd = request.getRequestDispatcher("AdminView.jsp");
				rd.forward(request, response);
				
				

			} else {
				out.println("Incorrect Username or Password");
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void addComplaints(ResultSet allComplaints,ArrayList<Complaint> complaints) throws NumberFormatException, SQLException{
		while (allComplaints.next()) {
			Complaint complaint = new Complaint();
			complaint.setCategory(allComplaints.getString("category"));
			complaint.setLocation(allComplaints.getString("location"));
			complaint.setSub_location(allComplaints.getString("sub_location"));
			complaint.setDesignation(allComplaints.getString("designation"));
			complaint.setDetails(allComplaints.getString("details"));
			complaint.setPriority(allComplaints.getString("priority"));
			complaint.setName(allComplaints.getString("name"));
			complaint.setEmail(allComplaints.getString("email"));
			complaint.setContact(allComplaints.getString("contact"));
			complaint.setTime(allComplaints.getString("time"));
			complaint.setDate(allComplaints.getString("date"));
			complaint.setComplaint_no(Integer.parseInt(allComplaints.getString("complaint_no")));
			complaint.setCom_status(allComplaints.getString("com_status"));

			complaints.add(complaint);

		}
		
	}

}
