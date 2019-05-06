<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="chatHead.jsp">
    <jsp:param name="title" value="Chatroom" />
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Chatroom" />
</jsp:include>
<main>
    <p>
        Participants
    </p>
    <ul id="participants">
        <c:forEach items="${chatSession.participants}" var="person">
            <li id="${person.userId}">
                ${person.firstName} ${person.lastName}
            </li>
        </c:forEach>
    </ul>

    <p>
        Chat
    </p>
        <ul id="messages">
            <c:forEach items="${chatSession.messages}" var="message">
                <li>${message.author.firstName}: ${message.message}</li>
            </c:forEach>
        </ul>
        <input id="messageInput" type="text" name="message" placeholder="Type your message here..."> <button id="sendMessageButton">Send</button>
</main>

<script type="text/javascript" src="js/chat.js"></script>
<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Chat" />
</jsp:include>
</body>
</html>
