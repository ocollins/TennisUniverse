<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="entry_form_div">
    <form action="addMemberServActionServlet" name="" id="select_serv_form" method="get">
        <div id="selectoptions">

            <table>
                <tr>
                    
                </tr>

            </table>
            <p>
                <select name="service" >
                    <c:forEach var="service" items="${serviceList}">
                        <option value="${service.serviceId}">${service.serviceDesc}</option>
                    </c:forEach>
                </select>
            </p>

            <p>Date of service<input type="date" name="service_date" required></p>
            <p>Comments<input type="text" name="notes"></p>

            <input type="submit" value="OK">
        </div>

    </form>

</div>