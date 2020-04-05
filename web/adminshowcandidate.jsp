<%-- 
    Document   : adminshowcandidate
    Created on : Dec 26, 2019, 8:12:41 PM
    Author     : Win7
--%>

<%@page import="evoting.dto.CandidateDetails, java.util.ArrayList" pageEncoding="UTF-8"%>

<%
    String userid = (String)session.getAttribute("userid");
    if(userid == null) {
        response.sendRedirect("accessdenied.html");
        return;
    }
    String result=(String)request.getAttribute("result");
    StringBuffer displayBlock = new StringBuffer("");
    if(result.equals("candidateList")) {
        ArrayList<String> candidateId = (ArrayList)request.getAttribute("candidateId");
        for(String c : candidateId) 
            displayBlock.append("<option value='" + c + "'>" + c + "</option>");
        out.println(displayBlock);
        
    }
    else if(result.equals("details")) {
        CandidateDetails candidate = (CandidateDetails)request.getAttribute("candidate");
        displayBlock.append("'<table>" +
                "<tr><th>User Id:</th><td>" + candidate.getUserId() + "</td></tr>" 
                +"<tr><th>Candidate Name:</th><td>" + candidate.getCandidateName() + "</td></tr>" 
                +"<tr><th>City:</th><td>" + candidate.getCity() + "</td></tr>" 
                +"<tr><th>Party:</th><td>" + candidate.getParty() + "</td></tr>" 
                +"<tr><th>Symbol:</th><td id='image'><img src='data:image/jpg;base64," + candidate.getSymbol()+ " "
                + "'style='width: 200px; height: 100px;'/></td></tr>");
                
                out.println(displayBlock);
    }

%>