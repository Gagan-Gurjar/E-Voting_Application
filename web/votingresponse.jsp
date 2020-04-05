<%-- 
    Document   : votingresponse
    Created on : Dec 28, 2019, 7:18:11 PM
    Author     : Win7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="evoting.dto.CandidateDto,evoting.dao.VoteDao"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="stylesheet/backgroundimage.css" type="text/css" rel="stylesheet">
        <link href="stylesheet/showcandidate.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <title>Voting Response</title>
    </head>
    <body>
        <%
           String userid=(String)session.getAttribute("userid"); 
           System.out.println("\nuserid from voting response is ="+userid);
            if(userid==null) {
                response.sendRedirect("accessdenied.jsp");
            }
            else {
           String cid=VoteDao.getCandidateId(userid);
           StringBuffer displayBlock=new StringBuffer("");
           displayBlock.append("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br> ");
		if(cid == null) {
                    displayBlock.append("<br><div class='subcandidate'>Sorry your Vote is not added!</div>");
                    displayBlock.append("<div class='logout'><a href='logout.html'>logout</a></div></div>");
                    displayBlock.append("<div><h4 id='logout'><a href='LoginControllerServlet?logout=logout.html'>Logout</a></h4></div>");
                    out.println(displayBlock);
                }
                else {
                    CandidateDto candidate=VoteDao.getVote(cid);
                    displayBlock.append("<div class='subcandidate' >Thank you for Voting!!</div><br>");
                    displayBlock.append("<div class='candidateprofile'>Your Vote Added Successfully!!</div>");
                    displayBlock.append("<div class='candidateprofile' id='"+candidate.getCandidateId()+"'>"
                            + "<strong>You are Voted to :</strong><br/><img src='data:image/jpg;base64,"
                        +candidate.getSymbol()+"' style='width:200px;height:200px;'/><br/><div class='candidateprofile'><p>Candidate Id:"
                        +candidate.getCandidateId()+"<br/>"
                        +"Candidate Name:"+candidate.getCandidateName()+"<br/>"
                        + " Party:"+candidate.getParty()+"</div><br/></div>");
                        displayBlock.append("<h2 id='logout'><a href='login.html'>Logout</a></h2>");
                }
                out.println(displayBlock);
            }
        %>
    </body>
</html>
