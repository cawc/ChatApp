package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddFriend extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = (String)request.getParameter("userid");
        if (userid == null || userid.isEmpty()) {
            return "Empty/null";
        }

        Person friend = getPersonService().getPerson(userid);
        if (friend == null) {
            return "User not found";
        }

        Person user = (Person)request.getSession().getAttribute("user");
        user.addFriend(friend);
        return "Added";
    }
}
