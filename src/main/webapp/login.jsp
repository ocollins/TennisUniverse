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

<h1>Login or register new user</h1>
<c:if test="${empty validPerson}">
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
        <h4>Please enter your user name and password</h4>
        <FORM ACTION="" METHOD="POST">
            <TABLE id="register_table">
                <TR><td class="label">User Name:</td><TD class="data"><INPUT TYPE="TEXT" NAME="user_name" required></TD></TR>
                <TR><td class="label">Password:</td><TD class="data"><INPUT TYPE="PASSWORD" NAME="password" required></TD></TR>
                <TR><td class="label"></td><td class="submit_button"><INPUT TYPE="SUBMIT" VALUE="Register"></td></TR>
            </TABLE>
        </FORM>
    </c:if>
    <c:if test="${not validPerson  && !empty validPerson}">
        <p id="invalididmessage">Invalid member ID</p
    </c:if>


</body>
</html>
