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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("category");
		int com_no = Integer.parseInt(request.getParameter("com_number"));
		String cat = request.getParameter("cat");
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
					"abcdefgh");

			
			Statement stmnt = con.createStatement();
			stmnt.executeUpdate(
					"UPDATE complaint_details SET assignTo= '" + userName + "' where complaint_no = " + com_no + ";");
			
			ResultSet rs = stmnt
					.executeQuery("SELECT * FROM complaint_details");
			
			if (rs.next()) {
				ArrayList<Complaint> complaints=new ArrayList<>();					
			
				ResultSet allComplaints = stmnt.executeQuery("SELECT * from complaint_details where com_status like 'Pending' and category = '"+cat+"' ORDER BY complaint_no DESC;");
				addComplaints(allComplaints,complaints);
				
				ResultSet allComplaintsInP = stmnt.executeQuery("SELECT * from complaint_details where com_status like 'In Progress' and category = '"+cat+"' ORDER BY complaint_no DESC;");
				addComplaints(allComplaintsInP,complaints);
				
				ResultSet allComplaintsComp = stmnt.executeQuery("SELECT * from complaint_details where com_status like 'Completed' and category = '"+cat+"' ORDER BY complaint_no DESC;");
				addComplaints(allComplaintsComp,complaints);
				
				ResultSet allComplaintsNotComp = stmnt.executeQuery("SELECT * from complaint_details where com_status like 'Not Completed' and category = '"+cat+"' ORDER BY complaint_no DESC;");
				addComplaints(allComplaintsNotComp,complaints);
				
				request.setAttribute("data", complaints);
				
				ArrayList<String> engineers = new ArrayList<>();
				ResultSet serviceEngineers = stmnt
						.executeQuery("select name from user_details where category like '" + cat
								+ "' and level = 1;");
				
				while (serviceEngineers.next()) {
					engineers.add(serviceEngineers.getString("name"));
					request.setAttribute("level1", engineers);	
				}
				RequestDispatcher rd = request.getRequestDispatcher("UserProfileLevel2.jsp");
				rd.forward(request, response);
			}

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
			complaint.setAssign(allComplaints.getString("assignTo"));

			complaints.add(complaint);

		}

	}

}
