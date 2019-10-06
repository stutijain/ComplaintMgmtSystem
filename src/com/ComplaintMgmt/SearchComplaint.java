package com.ComplaintMgmt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
			
			out.print("<table width=25% border=1>");
			out.print("<center><h1>Result:</h1></center>");

			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next())

			{
				out.print("<tr>");

				/* Printing column names */
				for (int i = 1; i < rsmd.getColumnCount(); i++) {
					if (rsmd.getColumnName(i).equals("attachment"))
						continue;
					out.print("<td>" + rsmd.getColumnName(i).toUpperCase() + "</td>");

					out.print("<td>" + rs.getString(i) + "</td></tr>");
				}
			}

			out.print("</table>");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
