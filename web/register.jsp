<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Register" />
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Register" />
</jsp:include>
<main>
    <c:if test="${errors.size()>0 }">
        <div class="danger">
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li>${error }</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <form method="post" action="Controller?action=Register">
        <p>
            <label for="firstName">First name</label>
            <input type="text" id="firstName" name="firstName">
        </p>
        <p>
            <label for="lastName">Last name</label>
            <input type="text" id="lastName" name="lastName">
        </p>
        <p>
            <label for="email">Email</label>
            <input type="text" id="email" name="email">
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password">
        </p>
        <p>
            <label for="password2">Repeat password</label>
            <input type="password2" id="password2" name="password2">
        </p>
        <p>
            <input type="submit" id="registerButton" name="registerButton" value="Register">
        </p>
    </form>
</main>

<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home" />
</jsp:include>
</body>
</html>
