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

public class searchByStatus extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String status=request.getParameter("category");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
					"abcdef");

			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM complaint_details where com_status='" + status+"'");
			
			ArrayList<Complaint> allComplaints=new ArrayList<>();
			while(rs.next()){
				Complaint complaint = new Complaint();
				complaint.setCategory(rs.getString("category"));
				complaint.setLocation(rs.getString("location"));
				complaint.setSub_location(rs.getString("sub_location"));
				complaint.setDesignation(rs.getString("designation"));
				complaint.setDetails(rs.getString("details"));
				complaint.setPriority(rs.getString("priority"));
				complaint.setName(rs.getString("name"));
				complaint.setEmail(rs.getString("email"));
				complaint.setContact(rs.getString("contact"));
				complaint.setTime(rs.getString("time"));
				complaint.setDate(rs.getString("date"));
				complaint.setComplaint_no(Integer.parseInt(rs.getString("complaint_no")));
				complaint.setCom_status(rs.getString("com_status"));
				allComplaints.add(complaint);
				

				
//				out.print(complaint.getComplaint_no());
			}
			request.setAttribute("data", allComplaints);
			RequestDispatcher rd = request.getRequestDispatcher("searchDetails.jsp");
			rd.forward(request, response);
			
//			out.print("<table width=25% border=1 align=centre>");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
