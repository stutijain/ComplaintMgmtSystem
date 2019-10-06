package com.ComplaintMgmt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchComplaint")
public class SearchComplaint extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchComplaint() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int com_no = Integer.parseInt(request.getParameter("number"));
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
					"abcdef");

			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM complaint_details where complaint_no=" + com_no);
			out.println("Complaint:");
			while (rs.next()) {
				out.println(rs.getString(1) + ", " + rs.getString(2) + " , " + rs.getString(3) + ", " + rs.getString(4)
						+ rs.getString(5) + ", " + rs.getString(6) + " , " + rs.getString(7) + ", " + rs.getString(8)
						+ rs.getString(9) + ", " + rs.getString(10) + " , " + rs.getString(11) + ", " + rs.getString(12)
						+ ", " + rs.getString(13));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
