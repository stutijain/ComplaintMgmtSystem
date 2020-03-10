package com.homepage;

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

public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		PrintWriter out = response.getWriter();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
					"abcdef");

			Statement stmnt = con.createStatement();
			
	
			ResultSet rs = stmnt
					.executeQuery("SELECT * FROM user_details where email='" + email + "' and password='" + pass + "'");

			if (rs.next()) {
				// out.println("Login Successful");

				// admin level=9
				String level=rs.getString("level");
				if (level.equals("9")) {
					ArrayList<Complaint> complaints = new ArrayList<>();
					ResultSet allComplaints = stmnt
							.executeQuery("SELECT * from complaint_details where com_status like 'Pending';");
					addComplaints(allComplaints, complaints);

					ResultSet allComplaintsInP = stmnt
							.executeQuery("SELECT * from complaint_details where com_status like 'In Progress';");
					addComplaints(allComplaintsInP, complaints);

					ResultSet allComplaintsComp = stmnt
							.executeQuery("SELECT * from complaint_details where com_status like 'Completed';");
					addComplaints(allComplaintsComp, complaints);

					ResultSet allComplaintsNotComp = stmnt
							.executeQuery("SELECT * from complaint_details where com_status like 'Not Completed';");
					addComplaints(allComplaintsNotComp, complaints);

					request.setAttribute("data", complaints);

					RequestDispatcher rd = request.getRequestDispatcher("AdminView.jsp");
					rd.forward(request, response);

				} else {
					User user = new User();
					user.setCategory(rs.getString("category"));

					ArrayList<Complaint> complaints = new ArrayList<>();
					ResultSet allComplaints = stmnt.executeQuery("SELECT * from complaint_details where category like '"
							+ user.getCategory() + "' and com_status like 'Pending';");
					addComplaints(allComplaints, complaints);

					ResultSet allComplaintsInP = stmnt
							.executeQuery("SELECT * from complaint_details where category like '" + user.getCategory()
									+ "' and com_status like 'In Progress';");
					addComplaints(allComplaintsInP, complaints);

					ResultSet allComplaintsComp = stmnt
							.executeQuery("SELECT * from complaint_details where category like '" + user.getCategory()
									+ "' and com_status like 'Completed';");
					addComplaints(allComplaintsComp, complaints);

					ResultSet allComplaintsNotComp = stmnt
							.executeQuery("SELECT * from complaint_details where category like '" + user.getCategory()
									+ "' and com_status like 'Not Completed';");
					addComplaints(allComplaintsNotComp, complaints);

					request.setAttribute("data", complaints);
					if (level.equals("1")) {
						RequestDispatcher rd = request.getRequestDispatcher("UserProfile.jsp");
						rd.forward(request, response);
					} else {
						ArrayList<String> engineers = new ArrayList<>();
						ResultSet serviceEngineers = stmnt
								.executeQuery("select name from user_details where category like '" + user.getCategory()
										+ "' and level = 1;");
						while (serviceEngineers.next()) {
							engineers.add(serviceEngineers.getString("name"));
							request.setAttribute("level1", engineers);
						}
						RequestDispatcher rd = request.getRequestDispatcher("UserProfileLevel2.jsp");
						rd.forward(request, response);
					}

				}

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