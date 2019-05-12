package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class UpdateUser extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map<String,String> jsonMap = null;
        try {
            // Read the request payload into a String
            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String data = buffer.toString();

            // If the String is not empty, parses the payload into a map
            if (!data.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                    jsonMap = mapper.readValue(data, Map.class);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        Person userToUpdate = getPersonService().getPerson(jsonMap.get("userId"));
        System.out.println(jsonMap.get("firstName"));
        if(userToUpdate != null) {
            userToUpdate.setFirstName(jsonMap.get("firstName"));
            userToUpdate.setLastName(jsonMap.get("lastName"));
            userToUpdate.setStatus(jsonMap.get("status"));
        }


        return "";
    }
}
