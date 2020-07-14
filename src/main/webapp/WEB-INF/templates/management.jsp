<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="sections/head.jsp">
    <jsp:param name="titleName" value="Management"/>
</jsp:include>
<body>
    <div class="container-fluid">
        <jsp:include page="sections/navbar.jsp"/>

        <div class="row m-2">
            <h1 class="text-center">Management</h1>
        </div>

        <div class="row">
                <div class="list-group">
                        <a  href="<c:url value="/hotels"/>"
                            class="list-group-item list-group-item-action">
                            Manage hotels
                        </a>
                        <a  href="<c:url value="/customers"/>"
                            class="list-group-item list-group-item-action">
                            Manage customers
                        </a>
                </div>
        </div>

    </div>
</body>
</html>