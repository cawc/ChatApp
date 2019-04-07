<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="chatHead.jsp">
    <jsp:param name="title" value="Blog" />
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Blog" />
</jsp:include>
<main>
    <article>
        <h2>Topics</h2>
        <h3>Was het een interessante projectweek?</h3>
        <p id="topic1"></p>
        <h3>Wat ben je van plan om te doen vandaag?</h3>
        <p id="topic2"></p>
        <h3>Naar welke muziek ben je momenteel aan het luisteren?</h3>
        <p id="topic3"></p>
        <h3>Wat zijn de examenvragen voor het vak Web4?</h3>
        <p id="topic4"></p>
        <h3>Welk nieuw en hip javascript-library gebruik je momenteel?</h3>
        <p id="topic5"></p>
        <p>
        <ul>
            <li>Rating <input type="number" min="0" max="10" step="1" id="rating"/></li>
            <li>Name <input type="text" id="name"></li>
            <li>Comment <input type="text" id="content"></li>
            <li>Topic <select id="topic">
                <option value="1">Projectweek</option>
                <option value="2">Plannen vandaag</option>
                <option value="3">Muziek</option>
                <option value="4">Examenvragen</option>
                <option value="5">cool_library.js</option>
            </select>
            </li>
            <li><button id="submitTopic" onclick="submitComment()">Submit</button></li>
        </ul>
        </p>
    </article>
    <script src="js/topicSockets.js" type="text/javascript"></script>
</main>

<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Blog" />
</jsp:include>
</body>
</html>