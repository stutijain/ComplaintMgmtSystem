package com.ComplaintMgmt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class CancelBooking extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		 
		String bookingNum = request.getParameter("bookNum");	
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
					"abcdef");

			
				Statement stmnt = con.createStatement();
				String sql = "DELETE FROM booking_details where book_id=" + bookingNum+";";

				PreparedStatement st = con.prepareStatement(sql);
				int flag=st.executeUpdate();
				
				if(flag==1){
					out.println("<!DOCTYPE html>");
					out.println("<html>");
					out.println("<body>");
					out.println("<br>");
					out.println("<h3>Booking cancelled Succesfully!</h3>");
					out.println("</body>");
					out.println("</html>");
					response.setHeader("Refresh", "2; CancelBooking.jsp");
					
				}else{
					out.println("<!DOCTYPE html>");
					out.println("<html>");
					out.println("<body>");
					out.println("<br>");
					out.println("<h3>Booking does not exist. Enter valid ID!</h3>");
					out.println("</body>");
					out.println("</html>");
					response.setHeader("Refresh", "2; CancelBooking.jsp");
				}
				
				
				
//				RequestDispatcher rd = request.getRequestDispatcher("CancelBooking.jsp");
//				rd.forward(request, response);
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
