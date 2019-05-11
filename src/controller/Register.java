package controller;

import domain.Person;
import domain.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Register extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter("registerButton") == null) {
            return "register.jsp";
        }

        Person person = new Person();
        ArrayList<String> errors = new ArrayList<>();

        setFirstName(person, request, errors);
        setLastName(person, request, errors);
        setEmail(person, request, errors);
        setPassword(person, request, errors);

        if(errors.size() == 0) {
            try {
                getPersonService().addPerson(person);
            } catch (Exception e) {
                errors.add(e.getMessage());
            } finally {
                if (errors.size() > 0) {
                    request.setAttribute("errors", errors);
                    return "register.jsp";
                } else {
                    return "index.jsp";
                }
            }
        } else {
            request.setAttribute("errors", errors);
            return "register.jsp";
        }
    }

    private void setFirstName(Person person, HttpServletRequest request, ArrayList<String> errors) {
        try {
            String firstName = request.getParameter("firstName");
            if (firstName == null) throw new IllegalArgumentException("First name cannot be empty");
            person.setFirstName(firstName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setLastName(Person person, HttpServletRequest request, ArrayList<String> errors) {
        try {
            String lastName = request.getParameter("lastName");
            if (lastName == null) throw new IllegalArgumentException("Last name cannot be empty");
            person.setLastName(lastName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setEmail(Person person, HttpServletRequest request, ArrayList<String> errors) {
        try {
            String email = request.getParameter("email");
            if (email == null) throw new IllegalArgumentException("Email cannot be empty");
            person.setUserId(email);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setPassword(Person person, HttpServletRequest request, ArrayList<String> errors) {
        try {
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            if (password == null) throw new IllegalArgumentException("Password cannot be empty");
            if (password2 == null) throw new IllegalArgumentException("Password repeat cannot be empty");
            if (!password.equals(password2)) throw new IllegalArgumentException("Passwords do not match");
            person.setHashedPassword(password);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }
}
