<%--
  This is a page for club personnel with admin rights
  Author: Olena Collins
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html xmlns="http://www.w3.org/1999/xhtml" ng-app="">

<head>
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%--Adding libraries for Bootstrap--%>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type=""></script>--%>
    <link href="css/adminoptions.css" rel="stylesheet" type="text/css"/>
    <title>Admin</title>
</head>


<body>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js" type=""></script>

<script type="text/javascript" src="js/adminaction.js"></script>
<div id="container">
<!--include head element, which is stored in jsp directory-->
<c:import url="head.jsp"/>

<c:import url="menu.jsp"/>

    <jsp:include page="/adminDispServlet"/>

<form action="/adminActionDirectoryServlet" method="get">
    <div id="selectoptions">
        <p id="instruction">Please select option from the list below</p>

        <select>
            <c:forEach var="option" items="${adminActionsList}">
                <option value="${option.adminActionId}">${option.actionDesc}</option>
            </c:forEach>
        </select>

        <input type="submit" value="OK">
    </div>
</form>


<c:import url="footer.jsp"/>
</div>
</body>

</html>
