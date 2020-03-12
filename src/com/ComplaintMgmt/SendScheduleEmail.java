package com.ComplaintMgmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.entities.Complaint;

public class SendScheduleEmail implements Job {

	public static final String category = "category";
	public static final String details = "details";
	public static final String priority = "priority";
	public static final String comp_no = "comp_no";
	public static final String level = "level";

	Complaint complaint;
	ArrayList<String> emails;
	String complaint_no;
	String fromEmail;
	String password;
	String username;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String cat = dataMap.getString(category);
		String det = dataMap.getString(details);
		String prio = dataMap.getString(priority);
		String c_no = dataMap.getString(comp_no);
		String lev = dataMap.getString(level);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_system", "root",
					"abcdef");

			Statement stmt = con.createStatement();

			String query = "Select com_status from complaint_details where comp_no = '" + c_no + "';";
			ResultSet rs = stmt.executeQuery(query);
			
			rs = stmt.executeQuery(query);
			String status = new String();
			while(rs.next()) {
				status = rs.getString("com_status");
			}
			if(status.equals("Pending") || status.equals("Not completed")) {
				query = "select email from user_details where level = '" + lev + "' AND category like '" + cat + "';";
				rs = stmt.executeQuery(query);
				ArrayList<String> emails = new ArrayList<>();
				while (rs.next())
					emails.add(rs.getString("email"));
	
				SchdMailSender es = new SchdMailSender();
				es.schdEmail(emails, det, c_no, prio);
				
				
				ScheduleEmail se = new ScheduleEmail();
				se.scdMail(cat, det, prio, c_no, 12, 6, 3, "3");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}