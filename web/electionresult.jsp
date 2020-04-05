<%-- 
    Document   : electionresult
    Created on : 6 Jan, 2020, 7:55:48 PM
    Author     : HP
--%>

<%@page import="evoting.dao.CandidateDao,evoting.dto.CandidateDetails, java.util.*" pageEncoding="UTF-8"%>

<%
    String userid = (String)session.getAttribute("userid");
    if(userid == null) {
        response.sendRedirect("accessdenied.html");
        return;
    }
    int voteCount = (int)request.getAttribute("votecount");
    LinkedHashMap<CandidateDetails, Integer> resultDetails = (LinkedHashMap)request.getAttribute("result");
    StringBuffer displayBlock = new StringBuffer("");
    displayBlock.append("<table border=2><tr><th>Candidate Id</th><th>Candidate Name</th><th>Symbol</th><th>Party</th>"
                + "<th>City</th><th>Vote</th><th>Vote %</th></tr>");
    Set s = resultDetails.entrySet();
    Iterator it = s.iterator();
    while(it.hasNext()) {
        Map.Entry<CandidateDetails, Integer> e = (Map.Entry)it.next();
        CandidateDetails c = e.getKey();
        displayBlock.append("<tr><td>" + c.getCandidateId() + "</td>" 
        +"<td>" + c.getCandidateName() + "</td>" 
        +"<td><img src='data:image/jpg;base64,"+c.getSymbol()+"' style='width:200px;height:100px;'/></td>"  
        +"<td>" + c.getParty() + "</td>" 
        +"<td>" + c.getCity() + "</td>"
        +"<td>" + e.getValue( )+ "</td>"
        +"<td>" + (float)e.getValue() / voteCount * 100 + "</td></tr>");
    }
    displayBlock.append("</table>");
    out.println(displayBlock);
%>
