<%--
  @author O Collins
  Date: 2/10/17
--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<!--
-->
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title></title>
    <link href="css/login.css" rel="stylesheet" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

    <script type="text/javascript" src="js/session.js"></script>
    <script type="text/javascript" src="js/js.cookie.js"></script>
    <script type="text/javascript" src="js/login_script.js"></script>
</head>

<body>

<c:if test="${not empty sendEmailMessage}">
    <h4>${sendEmailMessage}</h4>
</c:if>

<c:if test="${empty validPerson}">
    <h1>Login or register new user</h1>
    <h4>Please enter your user name and password</h4>
    <FORM ACTION="j_security_check" METHOD="POST" id="login_form">
        <TABLE id="logintable">
            <TR><td class="label">User Name:</td><TD class="data"><INPUT TYPE="TEXT" NAME="j_username" id="login_user_name" required/></TD></TR>
            <TR><td class="label">Password:</td><TD class="data"><INPUT TYPE="PASSWORD" NAME="j_password" id="login_password" required/></TD></TR>
            <TR><td class="label"></td><td class="submit_button"><INPUT TYPE="SUBMIT" VALUE="Log In" id="login_button"/></td></TR>
        </TABLE>
    </FORM>


    <h4>Register new user</h4>
    <form action="verifyPersonServlet" method="get">
        <TABLE>
            <p>Enter your member ID</p>
            <TR><td class="label">Member ID:</td><TD class="data"><INPUT TYPE="TEXT" NAME="account_id" required></TD></TR>
            <TR><td class="label"></td><td class="submit_button"><INPUT TYPE="SUBMIT" VALUE="Check Member ID"></td></TR>
        </TABLE>
    </form>

    <h4>Forgot User Name or Password</h4>
    <form action="sendEmailServlet" method="get">
        <TABLE>
            <p>Enter your member ID and Click Email</p>
            <TR><td class="label">Member ID:</td><TD class="data"><INPUT TYPE="TEXT" NAME="person_id" required></TD></TR>
            <TR><td class="label"></td><td class="submit_button"><INPUT TYPE="SUBMIT" VALUE="Email"></td></TR>
        </TABLE>
    </form>
</c:if>

<c:if test="${validPerson}">
    <h1>Register New User</h1>
    <h4>Please enter user name and password</h4>

    <FORM ACTION="registerNewUserServlet" METHOD="POST" id="register_form">
        <TABLE id="register_table">
            <TR><td class="label">User Name:</td><TD class="data"><INPUT TYPE="TEXT" NAME="user_name" id="new_user_name" required></TD></TR>
            <TR><td class="label">Password:</td><TD class="data"><INPUT TYPE="PASSWORD" NAME="password" id="pass_input1" required></TD></TR>
            <TR><td class="label">Confirm Password:</td><TD class="data"><INPUT TYPE="PASSWORD" NAME="password2" id="pass_input2" required></TD></TR>
            <TR><td class="label"></td><td class="submit_button"><INPUT TYPE="SUBMIT" ID="reg_new_user_button" VALUE="Register"></td></TR>
        </TABLE>
    </FORM>

    <div id="pswd_info">
        <h4>Password must meet the following requirements:</h4>
        <ul>
            <li id="capital" class="invalid">
                At least <strong>one capital letter</strong></li>
            <li id="number" class="invalid">
                At least <strong>one number</strong></li>
            <li id="length" class="invalid">
                Be at least <strong>6 characters</strong></li>
        </ul>
    </div>
</c:if>

<c:if test="${not validPerson  && !empty validPerson}">
    <p id="invalididmessage">Invalid member ID!</p
</c:if>

<c:if test="${not empty alreadyRegisteredMessage}">
    <h4>${alreadyRegisteredMessage}</h4>
    <form action="sendEmailServlet" method="get">
        <h4>Forgot user name or password?</h4>
        <input type="hidden" value="${personId}" NAME="person_id"> <br/>
        <p class="submit_button"><INPUT TYPE="SUBMIT" VALUE="Send Reminder Email" ></p>
    </form>
</c:if>


</body>
</html>
