package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class SendChat extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person author = (Person)request.getSession().getAttribute("user");
        if (author == null) {
            return "";
        }

        Set<Person> participants = new HashSet<>();
        for (String userId : request.getParameterValues("participants[]")) {
            participants.add(getPersonService().getPerson(userId));
        }

        String message = request.getParameter("message");
        if (message != null && !message.isEmpty()) {
            ChatMessage chatMessage = new ChatMessage(author, message);
            getPersonService().getChatSession(participants).addMessage(chatMessage);
        }
        return "";
    }
}
