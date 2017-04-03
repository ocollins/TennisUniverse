<%--
  Author: Olena Collins
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">


<head>
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
</head>

<title>Adult Tennis</title>
<link href="css/adultTennis.css" rel="stylesheet" type="text/css"/>

<body>
<!--include head element, which is stored in jsp directory-->
<c:import url="head.jsp"/>

<c:import url="menu.jsp"/>
<div id="container2">
    <p>Whether you are new to tennis, getting back into the game or a seasoned competitor, <br />
        The Tennis Universe Tennis Club offers a variety of lessons, clinics, social and competitive <br />
        tennis programs, and events daily designed to challenge all age and ability levels. Not sure how <br />
        to get started?  Our experienced staff can get you headed in the right direction.
    </p>
</div>

<aside>
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
</div>

<c:import url="footer.jsp"/>
</body>

</html>
