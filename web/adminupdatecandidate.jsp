<%-- 
    Document   : adminupdatecandidate
    Created on : 2 Jan, 2020, 5:58:49 PM
    Author     : HP
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
        displayBlock.append("<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>"
                + "<table>" +
                "<tr><th>User Id:</th><td><input type='text' id='uid' value='" + candidate.getUserId() + "' disabled></td></tr>" 
                +"<tr><th>Candidate Name:</th><td><input type='text' value='" + candidate.getCandidateName() + "' disabled></td></tr>" 
                +"<tr><th>City:</th><td><select id='city'>");
        
        ArrayList <String> city= (ArrayList)request.getAttribute("cities");
        for(String c : city) 
            displayBlock.append("<option value='" + c + "'>" + c + "</option>");
                        
        displayBlock.append("</select></td></tr>" 
                +"<tr><th>Party:</th><td><input type='text' id='party' value='" + candidate.getParty() + "'></td></tr>"
                +"<tr><th>New Symbol:</th><td colspan='2'><input type='file' name='files' value='Select New Image'></td></tr>"
                +"<tr><th><td><input type='button'  value='Update' id='updatebtn'></th>"
                +"<th><input type='reset'  value='Clear' onclick='clearText()'></th></tr>"
                + "</table></form>"
                + "<table><tr><th>Current Symbol:</th><td><img src='data:image/jpg;base64," + candidate.getSymbol()+ " "
                + "'style='width: 200px; height: 100px;'/></td></tr>");
                
                out.println(displayBlock);
    }
    


//<option value='" + candidate.getCity() + "'>"+candidate.getCity()+""
                     //   + "</option>
%>
 
