

var userid, password;

function connectUser() {
    userid = $("#username").val();
    password = $("#password").val();
    if(validate() === false) {
        swal("Access Denied", "Please enter userid/ password!", "error");
        return;
    }
    var data = {
        userid:userid,
        password:password
    };
    $.post("LoginControllerServlet", data, processResponse);
}

function processResponse(responseText) {
    responseText = responseText.trim();
    if(responseText === "error"){
        swal("Access Denied", "Please enter userid/ password!", "error");
        
    }
    else if(responseText.indexOf("jsessionid") !== -1) {
        swal("Success!", "Login Accepted!", "success");
        setTimeout(function() {
            window.location = responseText;
        }, 3000);
    }
    else {
        swal("Access Denied", "Some problem occured.\n Please Try Again Later", "error");        
    }
}

function validate() {
    if(userid ==="" || password === "")
        return false;
    return true;
}
