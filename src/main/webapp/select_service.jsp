<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="entry_form_div">
    <form action="addMemberServActionServlet" name="" id="select_serv_form" method="get">
        <div id="selectoptions">

            <table id="add_member_service_table">
                <tr>
                    <td>
                        Service:
                    </td>
                    <td>
                        <select name="service" >
                            <c:forEach var="service" items="${serviceList}">
                                <option value="${service.serviceId}">${service.serviceDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Date of service:
                    </td>
                    <td>
                        <input type="date" name="service_date" required>
                    </td>
                </tr>
                <tr>
                    <td>
                        Comments:
                    </td>
                    <td>
                        <input type="text" name="notes" required>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" id="submit_button"><input type="submit" value="OK"></td>
                </tr>

            </table>

        </div>

    </form>

</div>