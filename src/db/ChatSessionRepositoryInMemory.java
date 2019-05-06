package db;

import controller.ChatSession;
import domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChatSessionRepositoryInMemory implements ChatSessionRepository {
    private List<ChatSession> chatSessions;

    public ChatSessionRepositoryInMemory() {
        chatSessions = new ArrayList<>();
    }

    @Override
    public void addChatSession(ChatSession chatSession) {
        chatSessions.add(chatSession);
    }

    @Override
    public ChatSession getChatSession(Set<Person> personList) {
        ChatSession session = new ChatSession(personList);
        int i = chatSessions.indexOf(session);
        if (i == -1) {
            addChatSession(session);
            return session;
        } else {
            return chatSessions.get(i);
        }
    }
}
