<%--
  This is a page to search for member information
  Author: Olena Collins
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link href="css/member_search.css" rel="stylesheet" type="text/css"/>
    <title>Search for a Member</title>
</head>


<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="search_container">
    <form action="memberSearchActionServlet" name="" id="mem_search_form" method="GET">
        <table class="searchtable">
            <tr><td>Search Member by ID</td>
                <td><input type="text" name="searchID" value = ""></td>
            </tr>
            <tr><td colspan="2" class="instruction_td"><strong class="instruction_td">Look up member ID</strong></td>
            </tr>
            <tr><td>Search by Last Name</td>
                <td><input type="text" name="searchLastName" value = ""></td>
            </tr>
            <tr><td class="submit_button" colspan="2"><input type="submit" value="Search"></td></tr>
        </table>
    </form>
</div>


<c:if test="${foundMembers}">
    <div id="members_list_div">
    <table class="members_list_table" >
        <tr>
            <th>Member ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Birth Date</th>
            <th>Street address 1</th>
            <th>Street address 2</th>
            <th>City</th>
        </tr>
        <c:forEach var="member" items="${memberList}">
            <tr>
                <td>${member.personId}</td>
                <td>${member.firstName}</td>
                <td>${member.lastName}</td>
                <td>${member.birthDt}</td>
                <td>${member.addressLine1}</td>
                <td>${member.addressLine2}</td>
                <td>${member.city}</td>
            </tr>
        </c:forEach>
    </table>
    </div>
</c:if>
</body>
</html>