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
//import com.sun.glass.ui.Window;

public class AssignComplaint extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("category");
//		out.println(userName);
		int com_no = Integer.parseInt(request.getParameter("com_number"));
		String cat = request.getParameter("cat");
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
					"abcdefgh");

			
			Statement stmnt = con.createStatement();
			stmnt.executeUpdate(
					"UPDATE complaint_details SET assignTo= '" + userName + "' where complaint_no =" + com_no + ";");

//			Window.history.back();
//			ResultSet rs = stmnt.executeQuery("SELECT * FROM complaint_details");
//			// ArrayList<Complaint> complaints=new ArrayList<>();
//			if (rs.next()) {
//				ArrayList<Complaint> complaints = new ArrayList<>();
//				ResultSet allComplaints = stmnt
//						.executeQuery("SELECT * from complaint_details where category ='" + cat + "';");
//				addComplaints(allComplaints, complaints);
//
//				request.setAttribute("data", complaints);
////				response.setHeader("Refresh","1; UserProfileLevel2.jsp");
////				RequestDispatcher rd = request.getRequestDispatcher("UserProfileLevel2.jsp");
////				rd.forward(request, response);
//			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addComplaints(ResultSet allComplaints, ArrayList<Complaint> complaints)
			throws NumberFormatException, SQLException {
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
