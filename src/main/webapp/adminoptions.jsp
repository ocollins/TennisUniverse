<%--
  Author: Olena Collins
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">


<head>
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<title>Admin</title>
<link href="css/adultTennis.css" rel="stylesheet" type="text/css"/>

<body>
<!--include head element, which is stored in jsp directory-->
<c:import url="head.jsp"/>

<c:import url="menu.jsp"/>
<div id="container2">
    <ul>
        <li>First option</li>
        <li>Second option</li>
    </ul>
</div>

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
</body>

</html>
