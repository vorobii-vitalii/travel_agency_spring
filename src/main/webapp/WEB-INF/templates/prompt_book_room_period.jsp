<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<jsp:include page="sections/head.jsp">
    <jsp:param name="titleName" value="Enter the period"/>
</jsp:include>
<body>
    <div class="container-fluid">

        <jsp:include page="sections/navbar.jsp"/>

        <div class="row m-2">
            <h1 class="text-center">Enter the period</h1>
        </div>

        <div class="row">
            <h3 class="text-left text-info">${message}</h3>
            <form method="get" action="">
                <div class="mb-3">
                    <label for="startDate" class="form-label">Start date</label>
                    <input name="start"
                           type="date"
                           class="form-control"
                           id="startDate"
                    />
                </div>
                <div class="mb-3">
                    <label for="endDate" class="form-label">End date</label>
                    <input name="end"
                           type="date"
                           class="form-control"
                           id="endDate"
                    />
                </div>
                <div class="mb-3">
                    <button class="btn btn-success" type="submit">Submit</button>
                </div>
            </form>
        </div>

    </div>
</body>
</html>
