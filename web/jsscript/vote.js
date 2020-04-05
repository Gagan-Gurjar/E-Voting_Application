/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function addvote() {
    var id = $('input[name=flat]:checked').val();
   // alert(id );
    data = {candidateid: id};
    $.post('AddVoteControllerServlet', data, processresponse);
}

function processresponse(responseText) {
    responseText = responseText.trim();
  //  alert(responseText+'=ye aaya h');
    if(responseText === "success") 
        window.location = "votingresponse.jsp";
    else {
        alert("vote js ka else");
        window.location = "accessdenied.html";
    }
	
}