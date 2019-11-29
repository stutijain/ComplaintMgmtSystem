<%@page import="com.entities.Complaint"%> 
<%@page import="java.util.ArrayList"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


     <table width=25% border=1 style="border:1px solid black;margin-left:auto;margin-right:auto;">
 <tr>
   <th><b>Name</b></th> 
   <th><b>Category</b></th> 
  <th><b>Location</b></th> 
  <th><b>Complaint No</b></th> 
  <th><b>Priority</b></th>
  <th><b>Status</b></th>
 </tr> 
 <tr> 
 <%ArrayList<Complaint>allComplaints=(ArrayList<Complaint>)request.getAttribute("data");
for(Complaint comp:allComplaints){%>
 
     <td><%=comp.getName()%></td> 
     <td><%=comp.getCategory() %></td> 
     <td><%=comp.getLocation() %></td> 
     <td><input type="hidden" name="number" value= <%=comp.getComplaint_no()%> ><%=comp.getComplaint_no() %></td> 
     <td><%=comp.getPriority() %></td> 
     <td><%=comp.getCom_status() %></td> 
  
	 </tr> 
	 <%} %>
	</table>  
</body>
</html>