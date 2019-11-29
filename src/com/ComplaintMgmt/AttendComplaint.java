package com.ComplaintMgmt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entities.Complaint;

public class AttendComplaint extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out=response.getWriter();
		int com_no = Integer.parseInt(request.getParameter("number"));
		String new_category=request.getParameter("category").toString();
//		out.println(com_no);
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
					"abcdef");

			Statement stmnt = con.createStatement();
			stmnt.executeUpdate("UPDATE complaint_details SET com_status = '"+new_category+ "' where complaint_no ="+ com_no+";");
//			out.print("Status successfully changed");

//			ResultSet rs = stmnt.executeQuery("SELECT * FROM complaint_details where complaint_no=" + com_no);
			ResultSet rs = stmnt.executeQuery("SELECT * FROM complaint_details where complaint_no=" + com_no);
			while (rs.next())

			{

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
			request.setAttribute("data", complaint);

			RequestDispatcher rd = request.getRequestDispatcher("ComplaintDetails.jsp");
			rd.forward(request, response);
			}
			
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}	

}
