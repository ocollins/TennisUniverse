<%--
  @author O Collins
  Date: 2/10/17
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title></title>
    <link href="css/login.css" rel="stylesheet" type="text/css"/>
</head>

<script>
    function displayLogin() {
        document.getElementById("logintable").style.display = "none";
    }

</script>

<body ng-app="">


<c:if test="${empty validPerson}">
    <h1>Login or register new user</h1>
    <h4>Please enter your user name and password</h4>
    <FORM ACTION="j_security_check" METHOD="POST">
        <TABLE id="logintable">
            <TR><td class="label">User Name:</td><TD class="data"><INPUT TYPE="TEXT" NAME="j_username"></TD></TR>
            <TR><td class="label">Password:</td><TD class="data"><INPUT TYPE="PASSWORD" NAME="j_password"></TD></TR>
            <TR><td class="label"></td><td class="submit_button"><INPUT TYPE="SUBMIT" VALUE="Log In"></td></TR>
        </TABLE>
    </FORM>


    <h4>Or</h4>

    <h4>Register new user</h4>
    <form action="verifyPersonServlet" method="get">
        <TABLE>
            <p>Enter your member ID</p>
            <TR><td class="label">Member ID:</td><TD class="data"><INPUT TYPE="TEXT" NAME="account_id"></TD></TR>
            <TR><td class="label"></td><td class="submit_button"><INPUT TYPE="SUBMIT" VALUE="Check Member ID"></td></TR>
        </TABLE>
    </form>
    </c:if>

    <c:if test="${validPerson}">
        <h1>Register New User</h1>
        <h4>Please enter user name and password</h4>
        <FORM ACTION="registerNewUserServlet" METHOD="POST" onsubmit="checkForm(this)">
            <TABLE id="register_table">
                <TR><td class="label">User Name:</td><TD class="data"><INPUT TYPE="TEXT" NAME="user_name" required></TD></TR>
                <TR><td class="label">Password:</td><TD class="data"><INPUT TYPE="PASSWORD" NAME="password" required></TD></TR>
                <TR><td class="label">Confirm Password:</td><TD class="data"><INPUT TYPE="PASSWORD" NAME="password2" required></TD></TR>
                <TR><td class="label"></td><td class="submit_button"><INPUT TYPE="SUBMIT" VALUE="Register"></td></TR>
            </TABLE>
        </FORM>
    </c:if>
    <c:if test="${not validPerson  && !empty validPerson}">
        <p id="invalididmessage">Invalid member ID. Please contact Help Desk</p
    </c:if>

<script type="text/javascript">

    function checkForm(form)
    {
        alert("Testing javascript");
//        if(form.user_name.value == "") {
//            alert("Error: Username cannot be blank!");
//            form.username.focus();
//            return false;
//        }
//        re = /^\w+$/;
//        if(!re.test(form.user_name.value)) {
//            alert("Error: Username must contain only letters, numbers and underscores!");
//            form.username.focus();
//            return false;
//        }

//        if(form.password.value != "" && (form.password.value == form.password2.value)) {
//            if(form.password.value.length < 6) {
//                alert("Error: Password must contain at least six characters!");
//                form.password.focus();
//                return false;
//            }
//            if(form.password.value == form.username.value) {
//                alert("Error: Password must be different from Username!");
//                form.password.focus();
//                return false;
//            }
//            re = /[0-9]/;
//            if(!re.test(form.password.value)) {
//                alert("Error: password must contain at least one number (0-9)!");
//                form.password.focus();
//                return false;
//            }
//            re = /[a-z]/;
//            if(!re.test(form.password.value)) {
//                alert("Error: password must contain at least one lowercase letter (a-z)!");
//                form.password.focus();
//                return false;
//            }
//            re = /[A-Z]/;
//            if(!re.test(form.password.value)) {
//                alert("Error: password must contain at least one uppercase letter (A-Z)!");
//                form.password.focus();
//                return false;
//            }
//        } else {
//            alert("Error: Please check that you've entered and confirmed your password!");
//            form.password.focus();
//            return false;
//        }

        alert("You entered a valid password: " + form.password.value);
        return true;
    }

</script>

</body>
</html>
