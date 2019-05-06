package db;

import controller.ChatSession;
import domain.Person;

import java.util.Set;

public interface ChatSessionRepository {
    void addChatSession(ChatSession chatSession);
    ChatSession getChatSession(Set<Person> personList);
}
