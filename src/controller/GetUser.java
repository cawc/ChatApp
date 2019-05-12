package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUser extends AsyncRequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        if (userId == null || userId.isEmpty()) throw new IllegalArgumentException("Empty id");
        ObjectMapper objectMapper = new ObjectMapper();

        String result;
        try {
            result = objectMapper.writeValueAsString(getPersonService().getPerson(userId));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }

        return result;
    }
}
