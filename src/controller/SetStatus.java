package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetStatus extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String status = (String)request.getParameter("status");
        if (status == null || status.isEmpty()) {
            status = "Nothing";
        }
        Person user = (Person)request.getSession().getAttribute("user");
        user.setStatus(status);
        return "";
    }
}
