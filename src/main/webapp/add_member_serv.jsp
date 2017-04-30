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
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link href="css/add_member_serv.css" rel="stylesheet" type="text/css"/>
    <title>Request Service</title>
</head>


<body>

<div id="container">
<!--include head element, which is stored in jsp directory-->
<c:import url="head.jsp"/>

<c:import url="menu.jsp"/>

    <div>
        <form action="addMemberServActionServlet" name="" id="select_serv_form" method="get">
            <div id="selectoptions">
                <h2 class="instruction">You are adding services for &nbsp;${aPerson.firstName}&nbsp;${aPerson.lastName}</h2>
                <h2 class="instruction">Please select service from the list below</h2>

                <select name="service" form="display_form">
                    <c:forEach var="service" items="${serviceList}">
                        <option value="${service.serviceId}">${service.serviceDesc}</option>
                    </c:forEach>
                </select>
                <input type="date" name="service_date" required>
                <input type="text" name="notes">

                <input type="submit" value="OK">
            </div>

        </form>

    </div>

<c:import url="footer.jsp"/>
</div>
</body>

</html>
