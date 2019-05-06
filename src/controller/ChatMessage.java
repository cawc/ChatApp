package controller;

import domain.Person;

public class ChatMessage {
    private Person author;
    private String message;

    public ChatMessage(Person author, String message) {
        setAuthor(author);
        setMessage(message);
    }

    public Person getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    private void setAuthor(Person author) {
        this.author = author;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}
