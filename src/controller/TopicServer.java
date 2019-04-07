package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/topics")
public class TopicServer {
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    private static class TopicReply {
        private int topic;
        private String name;
        private int rating;
        private String content;

        private TopicReply() {}

        public int getTopic() { return topic; }
        public String getName() { return name; }
        public String getContent() { return content; }
        public int getRating() { return rating; }

        public void setTopic(int topic) { this.topic = topic; }
        public void setName(String name) { this.name = name; }
        public void setRating(int rating) { this.rating = rating; }
        public void setContent(String content) { this.content = content; }

        String json() {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.writeValueAsString(this);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    @OnOpen
    public void onOpen(Session session){
        System.out.println(session.getId() + " has opened a connection");
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String reply, Session session){
        System.out.println("Reply from " + session.getId() + " - " + reply);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TopicReply topicReply = objectMapper.readValue(reply, TopicReply.class);
            sendTopicToAll(topicReply);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @OnClose
    public void onClose(Session session){
        System.out.println(session.getId() + " has closed a connection");
        sessions.remove(session);
    }

    private void sendTopicToAll(TopicReply topicReply){
        for(Session s : sessions){
            try {
                s.getBasicRemote().sendText(topicReply.json());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}