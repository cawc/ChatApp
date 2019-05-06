package controller;

import domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChatSession {
    private Set<Person> participants;
    private List<ChatMessage> messages;

    public ChatSession(Set<Person> participants) {
        this.messages = new ArrayList<>();
        setParticipants(participants);
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void addMessage(ChatMessage message) {
        this.messages.add(message);
    }

    private void setParticipants(Set<Person> participants) {
        this.participants = participants;
    }

    private Set<Person> getParticipants() {
        return participants;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ChatSession) {
            return ((ChatSession) other).getParticipants().containsAll(participants);
        }
        return false;
    }
}
