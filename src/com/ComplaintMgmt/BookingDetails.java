package com.ComplaintMgmt;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
//import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;

import com.entities.Booking;
//import com.entities.Complaint;

public class BookingDetails extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		
		String category = request.getParameter("category");
		String date = request.getParameter("date");
		String time_from=request.getParameter("time_from");
		String time_to=request.getParameter("time_to");
//		out.println("hey");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root","abcdef");

			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM booking_details where category='" + category + "' and date='" + date + "' and time_to > '" + time_from + "' ;");
//			out.println(rs);
			
		    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		    java.util.Date d1 = sdf.parse(time_from);
		    java.util.Date d2 = sdf.parse(time_to);
		    
			if(d1.getTime()>=d2.getTime()){
				out.println("Please choose valid timings");
			}
			else if (rs.next()) {
				String thisTimeFrom=rs.getString("time_from");
				String thisTimeTo=rs.getString("time_to");
				String thisDate=rs.getString("date");
				out.println("The place is occupied from "+ thisTimeFrom+" to "+thisTimeTo+" on "+ thisDate+" . Please book some other timings.");	
			}else {
				Booking book=new Booking();
				book.setCategory(request.getParameter("category"));
				book.setDetails(request.getParameter("details"));
				book.setName(request.getParameter("name"));
				book.setEmail(request.getParameter("email"));
				book.setContact(request.getParameter("contact"));
				book.setDate(request.getParameter("date"));
				book.setTimeFrom(request.getParameter("time_from"));
				book.setTimeTo(request.getParameter("time_to"));
				book.setStatus(true);
				
				String sql = "insert into booking_details (category,details,name,email,contact,date,time_from,time_to,occupiedStatus)"
				+ " values(?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement st = con.prepareStatement(sql);
				
				st.setString(1, book.getCategory());
				st.setString(2, book.getDetails());
				st.setString(3, book.getName());
				st.setString(4, book.getEmail());
				st.setString(5, book.getContact());
				st.setString(6, book.getDate());
				st.setString(7, book.getTimeFrom());
				st.setString(8, book.getTimeTo());
				st.setBoolean(9, book.getStatus());
//				st.setInt(10, book.getBooking_no());

				int flag = st.executeUpdate();
				if(flag==1){
					out.print("<script>window.alert(\"Booked Successfully!\");window.location.replace(\"HomePage.html\");</script>");
				}else{
					out.print("<script>window.alert(\"Booking failed!\");window.location.replace(\"Booking.jsp\");</script>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
