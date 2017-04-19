<%--
  This is a page to delete member information
  Author: Olena Collins
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link href="css/update_member.css" rel="stylesheet" type="text/css"/>
    <title>Delete Member</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

</head>

<body>
<script>
    function displayAlert() {
        alert("Are you sure you want to delete this member?");
    }
</script>

<div id="container">
<!--include head element, which is stored in jsp directory-->
<c:import url="head.jsp"/>

<c:import url="menu.jsp"/>

    <div id="main_container_div">
        <form action="deleteMemberActionServlet" name="" id="delete_member_form" method="POST">
            <c:if test="${not empty aPerson.lastName}">
                <h1 class="title_header">Delete Member Information</h1>
                <h2 class="title_header">Please verify this is the member you want to delete and click Submit</h2>
                <c:import url="display_member_info.jsp"/>
                <div class="submit_button_div" ><input type="submit" value="Submit" class= "submit_button" onclick="displayAlert()"></div>
            </c:if>
        </form>
        <c:if test="${not empty resultMessage}">
            <p id="feedback_p">${resultMessage}</p>
        </c:if>
    </div>
<c:import url="footer.jsp"/>
</div>
</body>

</html>
