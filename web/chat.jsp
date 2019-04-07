<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="chatHead.jsp">
    <jsp:param name="title" value="Chat" />
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Chat" />
</jsp:include>
<main>
    <p>Welcome, ${user.firstName}!</p>
    <article>
        Your friends
        <table id="friendList">
            <tr>
                <th>Name</th><th>Status</th>
            </tr>

        </table>
        <p>
            Userid: <input type="text" name="status" id="addFriendInput">
            <input type="button" id="addFriendButton" value="Add friend"/>
        </p>

        <p id="statusp">
            Your status:
        </p>
        <input type="button" id="getStatusButton" value="Get status"/>

        <p>
            Change your status to: <input type="text" name="status" id="statusInput">
            <input type="button" id="setStatusButton" value="Update status"/>
        </p>
    </article>
</main>

<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Chat" />
</jsp:include>
</body>
</html>