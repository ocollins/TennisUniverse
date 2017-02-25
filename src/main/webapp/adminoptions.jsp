<%--
  This is a page for club personnel with admin rights
  Author: Olena Collins
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" ng-app="adminactions">

<head>
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%--Adding libraries for Bootstrap--%>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type=""></script>--%>
    <link href="css/adultTennis.css" rel="stylesheet" type="text/css"/>
    <title>Admin</title>
</head>

<body ng-controller="AdminLoadController" as loadcontroller>
<!--AngularJS-->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js" type=""></script>
<script type="text/javascript" src="js/adminaction.js"></script>
<div id="container">
<!--include head element, which is stored in jsp directory-->
<c:import url="head.jsp"/>

<c:import url="menu.jsp"/>
<h2>This is admin page. It is under construction!</h2>
    <p>{{loadcontroller.product.action_desc}}</p>
    <p></p>

<%--<aside>
    <p><img src="images/people.jpg" alt="Tennis People" id="peopleimage"></img></p>
</aside>

<div id="lessons">
    <h2>Adult Programs</h2>
    <ul id="intro">
        <li><strong>Beginner Clinic</strong>
            This clinic is for the beginner or experienced beginner <br />
            who is looking for instruction in all facets of the game in a relaxed atmosphere.
        </li>
    </ul>
</div>--%>

<c:import url="footer.jsp"/>
</div>
</body>

</html>
