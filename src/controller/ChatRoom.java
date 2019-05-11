package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class ChatRoom extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("user") == null) {
            return "index.jsp";
        }

        Person user, otherUser; // TODO: refactor to be able to groupchat?
        try {
            user = (Person) request.getSession().getAttribute("user");
            otherUser = getPersonService().getPerson(request.getParameter("uid"));
        } catch (IllegalArgumentException e) {
            return "index.jsp";
        }
        Set<Person> people = new HashSet<>();
        people.add(user);
        people.add(otherUser);

        ChatSession chatSession = getPersonService().getChatSession(people);
        request.setAttribute("chatSession", chatSession);


        return "chatRoom.jsp";
    }
}
