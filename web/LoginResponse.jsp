<%-- 
    Document   : LoginResponse
    Created on : Dec 21, 2019, 7:21:36 PM
    Author     : Win7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userid = (String)request.getAttribute("userid");
    String result = (String)request.getAttribute("result");
    if(userid != null && result != null) {
        HttpSession sess = request.getSession();
        sess.setAttribute("userid", userid);
        if(result.equalsIgnoreCase("admin")) {
//          String url = response.encodeURL("AdminControllerServlet");
            String url = "AdminControllerServlet;jsessionid=" + session.getId();
            out.println(url);
        }
        else {
//            String url = response.encodeURL("VotingControllerServlet");
            String url = "VotingControllerServlet;jsessionid=" + session.getId();
            out.println(url);
        }
    }
    else {
        out.println("error");
    }
%>
