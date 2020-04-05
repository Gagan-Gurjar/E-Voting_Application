<%-- 
    Document   : admindeletecandidate
    Created on : 1 Jan, 2020, 8:20:09 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String userid = (String)session.getAttribute("result");
    if(userid == null) {
        response.sendRedirect("accessdenied.html");
        return;
    }
    String result=(String)request.getAttribute("result");
    if(result == "true") {
        out.println("pass");
        
    }
    else 
        out.println("fail");
    }

%>
