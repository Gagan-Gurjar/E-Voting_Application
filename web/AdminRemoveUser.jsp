<%-- 
    Document   : AdminRemoveUser
    Created on : 3 Jan, 2020, 11:14:05 PM
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
        ArrayList <String> userId = (ArrayList)request.getAttribute("userId");
        for(String c : userId) 
            displayBlock.append("<option value='" + c + "'>" + c + "</option>");
        out.println(displayBlock);
        
    }
    else if(result.equals("details")) {
        UserDetails user = (UserDetails)request.getAttribute("user");
        displayBlock.append("<table>" +
                "<tr><th>User Id:</th><td>" + user.getUserid() + "</td></tr>" 
                +"<tr><th>User Name:</th><td>" + user.getUsername() + "</td></tr>" 
                +"<tr><th>Address:</th><td>" + user.getAddress() + "</td></tr>" 
                +"<tr><th>City:</th><td>" + user.getCity() + "</td></tr>" 
                +"<tr><th>Email:</th><td>" + user.getEmail() + "</td></tr>" 
                +"<tr><th>Mobile No.:</th><td>" + user.getMobile () + "</td></tr>" 
                +"</table>");
                
                out.println(displayBlock);
    }
    else if(result.equals("success")) {
        out.println("success");
    }

%>
