<%-- 
    Document   : verifyvote
    Created on : Dec 28, 2019, 7:14:25 PM
    Author     : Win7
--%>

<%
    System.out.println("verifyvote.jsp chla \n\n");
    String userid = (String)session.getAttribute("userid");
    boolean result = (boolean)request.getAttribute("result");
    System.out.println("verify vote jsp ke result me aa riya h "+result +" yee");
    System.out.println("verifyvote jsp me userid aa rhi h " + userid + " ye\nor result aa rha h "+ result + " ye\n");
    if(userid != null && result == true) {
        System.out.println("verifyvote ka if chl rha h\n");
        out.println("success");
    }        
    else {
        System.out.println("verifyvote ka else chl rha h\n");
        out.println("failed");
    }    
%>