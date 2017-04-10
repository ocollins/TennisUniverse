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

    <%--<script>--%>
        <%--$(document).ready(function(){--%>
            <%--$("#update_member_form").hide();--%>

            <%--});--%>
        <%--});--%>
    <%--</script>--%>
</head>



<body>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js" type=""></script>

<div id="container">
<!--include head element, which is stored in jsp directory-->
<c:import url="head.jsp"/>

<c:import url="menu.jsp"/>



    <div id="main_container_div">
        <h2 id="title_header">Update Member Information</h2>
        <c:import url="member_search.jsp"/>
        <p>${aPerson.lastName}</p>

        <form action="updateMemberActionServlet" name="" id="update_member_form" method="POST">
            <c:if test="${not empty aPerson.lastName}">
                <table class="add_table" id="add_table">
                    <tr><td>First Name</td>
                        <td><input type="text" name="fname" id="fName" value="${aPerson.firstName}" required></td>
                    </tr>
                    <tr><td>Last Name</td>
                        <td><input type="text" name="lname" id="lName" value="${aPerson.lastName}" required></td>
                    </tr>
                    <tr><td>Social Security Number</td>
                        <td><input type="text" name="ssn" id="ssn" value="" required></td>
                    </tr>
                    <tr><td>Birth Date</td>
                        <td><input type="date" name="birth_date" id="birth_date" value="" required></td>
                    </tr>
                    <tr><td>Street address 1</td>
                        <td><input type="text" name="address_line1" id="address_line1" value="" required></td>
                    </tr>
                    <tr><td>Street address 2</td>
                        <td><input type="text" name="address_line2" id="address_line2" value=""></td>
                    </tr>
                    <tr><td>City</td>
                        <td><input type="text" name="city" id="city" value="" required></td>
                    </tr>
                    <tr><td>State</td>
                        <td><input type="text" name="state" id="state" value="" required></td>
                    </tr>
                    <tr><td>Zip Code</td>
                        <td><input type="text" name="zip" id="zip" value="" required></td>
                    </tr>

                    <tr><td>Email Address</td>
                        <td><input type="text" name="email" id="email" value="" required></td>
                    </tr>
                    <tr><td>Phone Number (no dashes - 9999999999)</td>
                        <td><input type="text" name="phone" id="phone_input" value="" required></td>
                    </tr>
                    <tr class="submit_button"><td colspan="2" id="submit_button"><input type="submit" value="Submit"></td>
                    </tr>
                </table>
            </c:if>
        </form>
        <c:if test="${not empty feedbackMessage}">
            <p id="feedback_p">Member was updated successfully</p>
        </c:if>
    </div>
    <script type="">
        $(document).ready(function() {
            $('#add_table').DataTable();
        } );
    </script>
<c:import url="footer.jsp"/>
</div>
</body>

</html>
