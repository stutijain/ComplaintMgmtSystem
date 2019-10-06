package com.ComplaintMgmt;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class RegisterComplaint extends HttpServlet {

	private static final long serialVersionUID = 1788L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Complaint complaint = new Complaint();
		complaint.setType(request.getParameter("type"));
		complaint.setCategory(request.getParameter("category"));
		complaint.setLocation((String) request.getParameter("location"));
		complaint.setSub_location(request.getParameter("sub_location"));
		complaint.setSubject(request.getParameter("subject"));
		complaint.setDetails(request.getParameter("details"));
		complaint.setPriority(request.getParameter("priority"));
		complaint.setName(request.getParameter("name"));
		complaint.setEmail(request.getParameter("email"));
		complaint.setContact(request.getParameter("contact"));
		InputStream inputStream = null; // input stream of the upload file

		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("attach");
		if (filePart != null) {
			inputStream = filePart.getInputStream();
		}
		PrintWriter out = response.getWriter();
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
					"abcdef");
			
			String sql = "insert into complaint_details (type,category,location,sub_location,subject,details,priority,name,email,contact,attachment,time,date)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, complaint.getType());
			st.setString(2, complaint.getCategory());
			st.setString(3, complaint.getLocation());
			st.setString(4, complaint.getSub_location());
			st.setString(5, complaint.getSubject());
			st.setString(6, complaint.getDetails());
			st.setString(7, complaint.getPriority());
			st.setString(8, complaint.getName());
			st.setString(9, complaint.getEmail());
			st.setString(10, complaint.getContact());
			st.setBlob(11, inputStream);
			st.setString(12, LocalTime.now().toString());
			st.setString(13, LocalDate.now().toString());
			int flag = st.executeUpdate();
			if (flag == 1) {
				out.println("Added!");
			} else {
				out.println("Failed");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
