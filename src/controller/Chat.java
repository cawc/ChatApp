package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Chat extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("user") == null) {
            return "index.jsp";
        }
        return "chat.jsp";
    }
}
