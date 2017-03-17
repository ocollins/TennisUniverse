<%--
  This is a page to add a new member information
  Author: Olena Collins
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="css/add_member_serv.css" rel="stylesheet" type="text/css"/>
    <title>Add Member</title>
</head>


<body>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js" type=""></script>

<div id="container">
<!--include head element, which is stored in jsp directory-->
<c:import url="head.jsp"/>

<c:import url="menu.jsp"/>

    <div id="search_div">
        <h2 id="title_header">Add Member Services</h2>
        <form action="/addMemberServActionServlet" name="" id="member_search_form" method="get">
            <c:import url="member_search.jsp"/>

        </form>
    </div>

    <h2 id="meber_found_no">Member not found</h2>

    <div>
        <form action="/addMemberServActionServlet" name="" id="select_serv_form" method="get">
            <div id="selectoptions">
                <p id="instruction">Please select service from the list below</p>

                <select name="service" form="display_form">
                    <c:forEach var="service" items="${serviceList}">
                        <option value="${service.serviceId}">${service.serviceDesc}</option>
                    </c:forEach>
                </select>

                <input type="submit" value="OK">
            </div>

        </form>

    </div>

<c:import url="footer.jsp"/>
</div>
</body>

</html>
