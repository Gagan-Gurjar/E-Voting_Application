<%-- 
    Document   : success
    Created on : Dec 24, 2019, 8:32:07 PM
    Author     : Win7
--%>

<%
    String userid = (String)session.getAttribute("userid");
    if(userid == null) {
        response.sendRedirect("accessdenied.html");
        return;
    }
    out.println("success");
%>
