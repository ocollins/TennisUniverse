<%--
  This is a page for club member to book services
  Author: Olena Collins
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link href="css/add_member_serv.css" rel="stylesheet" type="text/css"/>
    <title>Book Service</title>
</head>


<body>

<div id="container">
<!--include head element, which is stored in jsp directory-->
<c:import url="head.jsp"/>

<c:import url="menu.jsp"/>

    <h2 class="instruction">Please select service from the list below</h2>

    <c:import url="select_service.jsp"/>

    <h3 class="feedback_p"><a href="adminLoggedDispServlet">Back to Admin Page</a></h3>

<c:import url="footer.jsp"/>
</div>
</body>

</html>
