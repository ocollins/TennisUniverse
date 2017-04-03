
<!--Tennis Universe web site
Author: Olena Collins
-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">


<head>
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
</head>

<title>Home</title>
<!--<link href="file:///home/student/EnterpriseRepos/TennisUniverse/src/main/resources/css/club.css" rel="stylesheet" type="text/css"/>-->
<!--<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/club.css" rel="stylesheet" type="text/css"/>-->
<link href="css/club.css" rel="stylesheet" type="text/css">

<body>
<!--include head element, which is stored in jsp directory-->
<c:import url="head.jsp"/>
<c:import url="menu.jsp"/>

<%--<p><a href="adminoptions.jsp">TESTING ADMIN OPTIONS</a></p>--%>
<p><a href="adminDispServlet">TESTING ADMIN OPTIONS</a></p>
<p><a href="adminActionDirectoryServlet">TESTING ADD MEMBER</a></p>

<div id="container">
    <div id="left_box">
        <h2>Year-Round Tennis for the Whole Family</h2>

        <ul id="intro">
            <li>We offer an array of leagues and lessons for all levels of adult players,<br />
                as well as social mixed doubles events and tournament opportunities.
            </li>
            <li>Extensive junior program designed to <br />
                develop a love for the game, from tots to competitive high school athletes. <br />
                Kids can participate in group and private lessons, leagues, and tournaments. <br />
                They truly become part of the Tennis Universe family.
            </li>
            <li>8 Indoor Hard Courts & 4 Outdoor Courts.
            </li>
            <li>Members enjoy our state of the art workout areas, pool, racquetball courts and locker rooms.
            </li>
            <li>Free Childcare.
            </li>
         </ul>
    </div>

    <div id="right_box">
        <p><img src="images/building.jpg" alt="Building" id="buildingimage"></img></p>
        <h2>Court Time</h2>
        <p>
            Monday to Friday 7am - 9:45pm <br/>
            Saturday & Sunday 9am - 6pm <br/>
        </p>
        <h2>Tennis Shop</h2>
        <p>
            <em>Tennis Universe</em> offers the most recent <br/>
            frames and accessories from the <br/>
            top brands. We have highly trained <br/>
            stringing experts who can service <br/>
            any racquet needs.
        </p>
    </div>
</div>




<c:import url="footer.jsp"/>
</body>

</html>