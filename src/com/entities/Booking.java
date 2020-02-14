package com.entities;

public class Booking {
	String category;
	
	String details;

	String name;

	String email;
	String contact;

	int booking_no;
	String date;
	boolean occupiedStatus;
	String time_from;
	String time_to;
	
	public String getTimeFrom(){
		return time_from;
	}
	public void setTimeFrom(String time_from){
		this.time_from=time_from;
	}
	
	public String getTimeTo(){
		return time_to;
	}
	public void setTimeTo(String time_to){
		this.time_to=time_to;
	}
	
	public boolean getStatus(){
		return occupiedStatus;
	}
	public void setStatus(boolean occupiedStatus){
		this.occupiedStatus=occupiedStatus;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getBooking_no() {
		return booking_no;
	}

	public void setBooking_no(int booking_no) {
		this.booking_no = booking_no;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


}
