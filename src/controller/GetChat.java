package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class GetChat extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person requester = (Person)request.getSession().getAttribute("user");
        if (requester == null) {
            return "";
        }

        Set<Person> participants = new HashSet<>();
        for (String userId : request.getParameterValues("participants[]")) {
            participants.add(getPersonService().getPerson(userId));
        }

        if(!participants.contains(requester)) {
            return "";
        }
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(getPersonService().getChatSession(participants).getMessages());
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }


    }
}
