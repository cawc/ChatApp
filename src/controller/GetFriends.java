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
import java.util.ArrayList;
import java.util.List;

public class GetFriends extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Person> friends = ((Person)request.getSession().getAttribute("user")).getFriends();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule friendModule = new SimpleModule();
        friendModule.addSerializer(Person.class, new FriendSerializer());
        mapper.registerModule(friendModule);

        String result;

        try {
            result = mapper.writeValueAsString(friends);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }

        return result;
    }

    private class FriendSerializer extends StdSerializer<Person> {

        public FriendSerializer() {
            this(null);
        }

        public FriendSerializer(Class<Person> t) {
            super(t);
        }

        @Override
        public void serialize(Person person, JsonGenerator jsonGenerator, SerializerProvider serializer) {
            try {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("status", person.getStatus());
                jsonGenerator.writeStringField("name", person.getFirstName());
                jsonGenerator.writeStringField("userId", person.getUserId());
                jsonGenerator.writeEndObject();
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
