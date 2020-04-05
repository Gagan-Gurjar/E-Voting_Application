
function redirectadministratorpage() {
    swal("Admin!", "Redirecting to Admin Page", "success");
    setTimeout(function() {
        window.location = "AdminActions.jsp";
    }, 3000);
}

function managecandidate() {
    swal("Admin!", "Redirecting to Admin Page", "success");
    setTimeout(function() {
        window.location = "ManageCandidate.jsp";
    }, 3000);
    
}

function redirectvotingpage() {
    swal("Admin!", "Redirecting to Admin Page", "success");
    setTimeout(function() {
        window.location = "showcandidate.jsp";
    }, 3000);
}

function redirectadminvotingpage() {
    swal("Admin!", "Redirecting to Admin Page", "success");
    setTimeout(function() {
        window.location = "showadmincandidate.jsp";
    }, 3000);
}

function manageuser() {
    swal("Admin!", "Redirecting to Admin Page", "success");
    setTimeout(function() {
        window.location = "ManageUser.jsp";
    }, 3000);
}


function addcandidate() {
    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);
    var cid = $('#cid').val();
    var cname = $('#cname').val();
    var city = $('#city').val();
    var party = $('#party').val();
    var uid = $('#uid').val();
//    var file = $('file').val();
    data.append("cid", cid);
    data.append("uid", uid);
    data.append("cname", cname);
    data.append("party", party);
    data.append("city", city);
    if(uid === "" || city === "" || party === "" || cname === "" || $('#file')[0].files.length === 0 ) {
        swal("Error!", "All fields are mandatory", "error");
        return false;
    }
    else {
        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            url: 'AddNewCandidatesControllerServlet',
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function(data) {
                str = data + "Record Successfully Added!";
                swal("Admin", str, "success");
                setTimeout(function() {showaddcandidateform();}, 3000);
            },
            error: function(e) {
                swal("Admin", e, "error");
            }
        });
    }
}




function showaddcandidateform() {
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    
    var x = "<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>"
    + "<table>"
    + "<tr><th>Candidate Id: </th><td><input type='text' id='cid'></td></tr>"
    +"<tr><th>User Id: </th><td><input typt='text' id='uid' onkeypress='return getdetails(event)'></td></tr>"
    +"<tr><th>Candidate Name: </th><td><input type='text' id='cname'></td></tr>"
    +"<tr><th>City: </th><td><select id='city'></select></td></tr>"
    +"<tr><th>Party: </th><td><input type='text' id='party'></td></tr>"
    +"<tr><td colspan='2'><input type='file' name='files' value='Select Image' id='file'></td></tr>"
    +"<tr><th><td><input type='button'  value='Add Candidate' onclick='addcandidate()' id='addcnd'></th>"
    +"<th><input type='reset' value='Clear' onclick='showaddcandidateform()'></th></tr>"
    +"</table>"
    +"</form>";
    
    
    newdiv.innerHTML = "<h3>Add New Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML + x ;
    newdiv.innerHTML = newdiv.innerHTML+"<br><span id='addrep'></span>";
    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);
    var data = {id: "getid"};
    $.post("AddCandidateControllerServlet", data, function(responseText){
        $("#cid").val(responseText);
        $('#cid').prop("disabled", true);});
}


function getdetails(e) {
    if(e.keyCode === 13) {
        data = {uid: $("#uid").val()};
        $.post("AddCandidateControllerServlet", data, function(responseText) {
            responseText = responseText.trim();
            var i = responseText.lastIndexOf(",");
            $("#city").empty();
            $("#city").append(responseText.substring(0, i));
            var uname = responseText.substring(i+1, responseText.length);
            if(uname === "wrong")
                swal("Wrong Adhar!", "Adhar no. not found in DB", "error");
            else {
                $('#cname').val(uname);
                $('#cname').prop("disabled", true);
            }
        });
    }
}

function removecandidateForm() {
    var contdiv = document.getElementById("result");
    var formdiv = document.getElementById("candidateform");
    if(formdiv !== null) {
        $("#candidatform").fadeOut("3500");
        contdiv.removeChild(formdiv);
    }
}

function showcandidate() {
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Show Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<div style='color: white; font-weight: bold'>Candidate</div><div>\n\
    <select id='cid'></select></div>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'></span>";
    var addPrd = $("#result")[0];
    addPrd.appendChild(newdiv);
    data = {data: "cid"};
    $.post("ShowCandidateControllerServlet", data, function(responseText) {
        $("#cid").append(responseText);
    });
    $("#cid").change(function() {
        var cid = $(this).children("option:selected").val();
        data = {data: cid};
        $.post("ShowCandidateControllerServlet", data, function(responseText) {
            clearText();
            $("#addresp").append(responseText);
            $("#addresp").append("<th><input type='reset'  value='Clear' onclick='clearText()'></th></tr></table>");
        });
    });
    
}

function deletecandidate() {
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Remove Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<div style='color: white; font-weight: bold'>Candidate</div><div>\n\
    <select id='cid'></select></div>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'></span>";
    var addPrd = $("#result")[0];
    addPrd.appendChild(newdiv);
    data = {data: "cid"};
    $.post("ShowCandidateControllerServlet", data, function(responseText) {
        $("#cid").append(responseText);
    });
    $("#cid").change(function() {
        var cid = $(this).children("option:selected").val();
        data = {data: cid};
        $.post("ShowCandidateControllerServlet", data, function(responseText) {
            clearText();
            $("#addresp").append(responseText);
            $("#addresp").append("<table><tr><th><td><input value='Remove' type='button'  id='deletebtn'>\n\
                        </th><th><input type='reset'  value='Clear' onclick='clearText()'></th></tr>");
            $("#deletebtn").click(function() {
                data = {data: cid};
                $.post("DeleteCandidateControllerServlet", data, function(responseText) {
                    clearText();
                    responseText = responseText.trim();
                    if(responseText === "success") {
                        swal("Admin!", "Candidate successfully removed!", "success");
                        setTimeout(function() {
                            window.location = "ManageCandidate.jsp";
                        }, 3000);
                    } 
                    else {
                        swal("Error", "Some error occured, Please try again later!", "error");
                        setTimeout(function() {
                            window.location = "ManageCandidate.jsp";
                        }, 3000);
                    }
                });
            });
            
        });
    });
    
    
}

function updatecandidate() {
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Update Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<div style='color: white; font-weight: bold'>Candidate</div><div>\n\
    <select id='cid'></select></div>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><div id='addresp'></span>";
    var addPrd = $("#result")[0];
    addPrd.appendChild(newdiv);
    data = {data: "cid"};
    $.post("UpdateCandidateControllerServlet", data, function(responseText) {
        $("#cid").append(responseText);
    });
    $("#cid").change(function() {
        var cid = $(this).children("option:selected").val();
        data = {data: cid};
        $.post("UpdateCandidateControllerServlet", data, function(responseText) {
            clearText();            
            $("#addresp").append(responseText);
            $("#updatebtn").click(function() {
                
                var form = $('#fileUploadForm')[0];
                var data = new FormData(form);
                var uid = $('#uid').val();
                var city = $('#city').val();
                var party = $('#party').val();
                data.append("uid", uid)
                data.append("party", party);
                data.append("city", city);
                $.ajax({
                    type: 'POST',
                    enctype: 'multipart/form-data',
                    url: 'UpdateSingleCandidateControllerServlet',
                    data: data,
                    processData: false,
                    contentType: false,
                    cache: false,
                    timeout: 600000,
                    success: function(data) {
                        str = data + "Record Successfully Updated!";
                        swal("Admin", str, "success");
//                        setTimeout(function() {showaddcandidateform();}, 3000);
                    },
                    error: function(e) {
                        swal("Admin", e, "error");
                    }
                });
            });
            
        });
    });
}

function showuser() {
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Show User</h3>";   
    newdiv.innerHTML = newdiv.innerHTML + "<br><div id='addresp'></span>";
    var addPrd = $("#result")[0];
    addPrd.appendChild(newdiv);
    data = {data: 'user'};
    $.post("ShowUsersControllerServlet", data, function(responseText) {
        clearText();
        $("#addresp").append(responseText);
    });
    
}

function removeuser() {
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Remove User</h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<div style='color: white; font-weight: bold'>User</div><div>\n\
    <select id='uid'></select></div>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'></span>";
    var addPrd = $("#result")[0];
    addPrd.appendChild(newdiv);
    data = {data: "uid"};
    $.post("RemoveUserControllerServlet", data, function(responseText) {
        $("#uid").append(responseText);
    });
    $("#uid").change(function() {
        var uid = $(this).children("option:selected").val();
        data = {data: uid};
        $.post("RemoveUserControllerServlet", data, function(responseText) {
            clearText();
            $("#addresp").append(responseText);
            $("#addresp").append("<br><input value='Remove' type='button'  id='deletebtn'>");
            $("#deletebtn").click(function() {
                data = {data:  ('r'+uid+'r')};                  
                $.post("RemoveUserControllerServlet", data, function(responseText) {
                    clearText();
                    responseText = responseText.trim();
                    if(responseText === "success") {
                        swal("Admin!", "User successfully removed!", "success");
                        setTimeout(function() {
                            window.location = "ManageUser.jsp";
                        }, 3000);
                    } 
                    else {
                        swal("Error", "Some error occured, Please try again later!", "error");
                        setTimeout(function() {
                            window.location = "ManageUser.jsp";
                        }, 3000);
                    }
                });
            });
            
        });
    });
}



function clearText() {
    $("#addresp").html("");
}
function electionresult() {
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");    
    newdiv.innerHTML = "<h3>Result</h3>";   
    newdiv.innerHTML = newdiv.innerHTML + "<br><div id='addresp'></span>";
    var addPrd = $("#result")[0];
    addPrd.appendChild(newdiv);
    var data = {data: "result"};
    $.post("ElectionResultControllerServlet", data, function(responseText) {
        clearText();
        $("#addresp").append(responseText);
    });
    
}