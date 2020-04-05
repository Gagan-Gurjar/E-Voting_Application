<%-- 
    Document   : showcandidate
    Created on : Dec 27, 2019, 8:11:00 PM
    Author     : Win7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,evoting.dto.CandidateDto"%>
<html>
    <head>
        <link href="stylesheet/showcandidate.css" type="text/css" rel="stylesheet">
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <script src="jsscript/vote.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <title>show candidate</title>
    </head>
    <body>
     <%
        String userid=(String)session.getAttribute("userid");
        if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
          System.out.println("show candidate jsp");
          StringBuffer displayBlock=new StringBuffer("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div> "
                  + "<br><div class='subcandidate'>Whom do you want to vote ?</div>"
                  +"<div class='logout'><a href='login.html'>logout</a></div>" 
                  +"</div></div><div class='buttons'>");
          ArrayList <CandidateDto> candidate=(ArrayList)request.getAttribute("candidateList");
          for(CandidateDto c: candidate) {
          displayBlock.append("<input id='"+c.getCandidateId()+"' value='"+c.getCandidateId()+"' name='flat' type='radio' onclick='addvote()' />");
          displayBlock.append("<label for='"+c.getCandidateId()+"'><img src='data:image/jpg;base64,"+c.getSymbol()+"' style='width:300px;height:200px;'/></label>"
                + "<br/><div class='candidateprofile'><p>Candidate Id:"+c.getCandidateId()+"<br/>"
                +"Candidate Name:"+c.getCandidateName()+"<br/>"
                + "Party:"+c.getParty()+"</label><br/></div>");
          }
          out.println(displayBlock);
      %>
  
 </body>
</html>
