package com.ComplaintMgmt;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.entities.Complaint;

@MultipartConfig

public class RegisterComplaint extends HttpServlet {

	SendMail sendmail = new SendMail();
	// MailSenderBean mailSender = new MailSenderBean() ;

	private static final long serialVersionUID = 1788L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Complaint complaint = new Complaint();
		complaint.setCategory(request.getParameter("category"));
		complaint.setLocation((String) request.getParameter("location"));
		complaint.setSub_location(request.getParameter("sub_location"));
		complaint.setDesignation(request.getParameter("designation"));
		complaint.setDetails(request.getParameter("details"));
		complaint.setPriority(request.getParameter("priority"));
		complaint.setName(request.getParameter("name"));
		complaint.setEmail(request.getParameter("email"));
		complaint.setContact(request.getParameter("contact"));
		complaint.setDate(LocalTime.now().toString());
		complaint.setTime(LocalDate.now().toString());
		complaint.setCom_status("Pending");
		complaint.setAssign("None");
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

			String sql = "insert into complaint_details (category,location,sub_location,details,priority,name,email,contact,attachment,time,date,designation,com_status,assignTo)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, complaint.getCategory());
			st.setString(2, complaint.getLocation());
			st.setString(3, complaint.getSub_location());
			st.setString(4, complaint.getDetails());
			st.setString(5, complaint.getPriority());
			st.setString(6, complaint.getName());
			st.setString(7, complaint.getEmail());
			st.setString(8, complaint.getContact());
			st.setBlob(9, inputStream);
			st.setString(10, complaint.getTime());
			st.setString(11, complaint.getDate());
			st.setString(12, complaint.getDesignation());
			st.setString(13, complaint.getCom_status());
			st.setString(14, complaint.getAssign());

			int flag = st.executeUpdate();
			if (flag == 1) {
				Statement stmt = con.createStatement();

				// retrieves data for the latest complaint
				String query = "SELECT * FROM complaint_details where complaint_no=(select max(complaint_no) from complaint_details);";
				ResultSet rs = stmt.executeQuery(query);
				int comp_no = 0;
				while (rs.next()) {
					// Retrieve by column name
					comp_no = rs.getInt("complaint_no");
				}

				query = "select email from user_details where level=1 AND category like '" + complaint.getCategory()
						+ "';";
				rs = stmt.executeQuery(query);
				ArrayList<String> emails = new ArrayList<>();
				while (rs.next())
					emails.add(rs.getString("email"));
				complaint.setComplaint_no(comp_no);
				request.setAttribute("complaint", complaint);
				request.setAttribute("emails", emails);
				sendmail.doPost(request, response);

				ScheduleEmail se = new ScheduleEmail();
				se.scdMail(complaint.getCategory(), complaint.getDetails(), complaint.getPriority(),
						Integer.toString(comp_no));

			} else {
				out.println("Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
