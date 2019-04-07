package controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetStatus extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person currentUser = (Person)request.getSession().getAttribute("user");
        String status = currentUser.getStatus();
        if (status == null) {
            currentUser.setStatus("Online");
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule statusModule = new SimpleModule();
        statusModule.addSerializer(Person.class, new StatusSerializer());
        mapper.registerModule(statusModule);
        try {
            return mapper.writeValueAsString(currentUser);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private class StatusSerializer extends StdSerializer<Person> {

        public StatusSerializer() {
            this(null);
        }

        public StatusSerializer(Class<Person> t) {
            super(t);
        }

        @Override
        public void serialize(Person person, JsonGenerator jsonGenerator, SerializerProvider serializer) {
            try {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("status", person.getStatus());
                jsonGenerator.writeEndObject();
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
