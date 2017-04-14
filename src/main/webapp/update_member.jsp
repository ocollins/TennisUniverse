<%--
  This is a page to update member information
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
    <title>Update Member</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

</head>

<body>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js" type=""></script>

<div id="container">
<!--include head element, which is stored in jsp directory-->
<c:import url="head.jsp"/>

<c:import url="menu.jsp"/>

    <div id="main_container_div">
        <form action="updateMemberActionServlet" name="" id="update_member_form" method="POST">
            <c:if test="${not empty aPerson.lastName}">
                <h1 class="title_header">Update Member Information</h1>
                <h2 class="title_header">Please update member information below and Submit your changes</h2>
                <c:import url="display_member_info.jsp"/>
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
