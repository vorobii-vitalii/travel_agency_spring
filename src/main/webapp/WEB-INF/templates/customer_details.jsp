<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="sections/head.jsp">
    <jsp:param name="titleName" value="Customer details"/>
</jsp:include>
<body>
<div class="container-fluid">

    <jsp:include page="sections/navbar.jsp"/>

    <div class="row m-2">
        <h1 class="text-center">Orders of ${customer.name}</h1>
    </div>

    <div class="row">
        <c:choose>
            <c:when test="${empty orders}">
                <h4>User has no orders</h4>
            </c:when>
            <c:otherwise>
                <table class="table table-hover table-bordered">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Room number</th>
                        <th scope="col">Hotel</th>
                        <th scope="col">Date from</th>
                        <th scope="col">Date till</th>
                    </tr>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td>${order.id}</td>
                            <td>
                                    ${order.room.roomNumber}
                            </td>
                            <td>
                                <i>${order.room.hotel.name}</i> &nbsp;
                                <a href="/rooms/${order.room.hotelId}"
                                   class="btn btn-sm btn-outline-info">Hotel rooms
                                </a>
                            </td>
                            <td>${order.dateFrom}</td>
                            <td>${order.dateTill}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

</div>
</body>
</html>