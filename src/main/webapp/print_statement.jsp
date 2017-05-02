<%--
  This is a page to produce member monthly statement
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
    <title>Print member statement</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

</head>

<body>

<div id="container">
<!--include head element, which is stored in jsp directory-->
<c:import url="head.jsp"/>

<c:import url="menu.jsp"/>

    <div id="main_container_div">
        <form action="printStatementActionServlet" name="" id="produce_statement_form" method="GET">
            <c:if test="${empty feedbackMessage}">
                <h1 class="title_header">Print Member Statement</h1>
                <h2 class="title_header">Please enter billing period start and end dates and click Produce</h2>
                <table class="add_table" id="add_table">
                    <tr><td>Member ID</td>
                        <td><input type="text" name="memberID" value="${aPerson.personId}" readonly="readonly"></td>
                    </tr>
                    <tr><td>First Name</td>
                        <td><input type="text" name="fname" value="${aPerson.firstName}" required></td>
                    </tr>
                    <tr><td>Last Name</td>
                        <td><input type="text" name="lname" value="${aPerson.lastName}" required></td>
                    </tr>
                    <tr><td>Start Date (YYYY-MM-DD)</td>
                        <td><input type="text" name="startDate" value="" required></td>
                    </tr>
                    <tr><td>End Date (YYYY-MM-DD)</td>
                        <td><input type="text" name="endDate" value="" required></td>
                    </tr>
                    <tr><td><div class="submit_button_div" ><input type="submit" class="submit_button" value="Produce Statement"></div></td>
                    </tr>
                </table>
            </c:if>
        </form>
        <c:if test="${not empty feedbackMessage}">
            <p class="feedback_p">${feedbackMessage}</p>
            <h3 class="feedback_p"><a href="adminLoggedDispServlet">Back to Admin Page</a></h3>
        </c:if>
    </div>
<c:import url="footer.jsp"/>
</div>
</body>

</html>
