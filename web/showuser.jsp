<%-- 
    Document   : showuser
    Created on : 3 Jan, 2020, 8:32:32 PM
    Author     : HP
--%>

<%@page import="evoting.dto.UserDetails, java.util.ArrayList" pageEncoding="UTF-8"%>

<%
    String userid = (String)session.getAttribute("userid");
    if(userid == null) {
        response.sendRedirect("accessdenied.html");
        return;
    }
    String result=(String)request.getAttribute("result");
    StringBuffer displayBlock = new StringBuffer("");
    if(result.equals("userList")) {
        ArrayList<UserDetails> userId = (ArrayList)request.getAttribute("userId");
        displayBlock.append("<table border=2><tr><th>User Id</th><th>User Name</th><th>Address</th><th>City</th>"
                + "<th>Email</th><th>Mobile No.</th></tr>");
        for(UserDetails user : userId) { 
            displayBlock.append("<tr><td>" + user.getUserid() + "</td>" 
                +"<td>" + user.getUsername() + "</td>" 
                +"<td>" + user.getAddress() + "</td>"  
                +"<td>" + user.getCity() + "</td>" 
                +"<td>" + user.getEmail() + "</td>"  
                +"<td>" + user.getMobile() + "</td></tr>");
        }
        displayBlock.append("</table>");
        out.println(displayBlock);
        
    }

%>
