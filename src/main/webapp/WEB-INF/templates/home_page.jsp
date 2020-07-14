<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <jsp:include page="sections/head.jsp">
        <jsp:param name="titleName" value="Home page"/>
    </jsp:include>
<body>
<div class="container-fluid">
    <jsp:include page="sections/navbar.jsp"/>

    <div class="row m-2">
        <h1 class="text-center">Hotels
        <c:if test="${not empty param.country}">
            in ${param.country}
        </c:if>
        </h1>
    </div>

    <!-- SEARCH COMPONENT START -->
        <div class="row">
            <form action="" class="card card-sm border-light" method="get">
                <div class="card-body row no-gutters align-items-center">
                    <div class="col-auto">
                        <i class="fas fa-search h4 text-body"></i>
                    </div>
                    <div class="col">
                        <label>
                            <select class="form-select" name="country">
                                <c:forEach items="${countries}" var="country">
                                    <option value="${country.name}">${country.name}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>
                    <div class="col-auto">
                        <button class="btn btn-lg btn-success" type="submit">Search</button>
                    </div>
                </div>
            </form>
        </div>
    <!-- SEARCH COMPONENT END -->

    <div class="row">

        <c:choose>
            <c:when test="${empty hotels}">
                <h4>There are no hotels</h4>
            </c:when>
            <c:otherwise>
                <table class="table table-hover table-bordered">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Description</th>
                        <th scope="col">Country</th>
                        <th scope="col">Action</th>
                    </tr>
                    <c:forEach items="${hotels}" var="hotel">
                        <tr>
                            <td>${hotel.id}</td>
                            <td>${hotel.name}</td>
                            <td>${hotel.description}</td>
                            <td>${hotel.country}</td>
                            <td>
                                <a href="<c:url value="/book/${hotel.id}"/>"
                                   class="btn btn-outline-secondary btn-sm">Book room in the hotel</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>