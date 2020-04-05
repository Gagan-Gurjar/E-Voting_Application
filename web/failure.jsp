<%-- 
    Document   : failure
    Created on : Dec 24, 2019, 8:35:50 PM
    Author     : Win7
--%>

<%
    String userid = (String)session.getAttribute("userid");
    if(userid == null) {
        response.sendRedirect("accessdenied.html");
        return;
    }
    out.println("failed");
%>
