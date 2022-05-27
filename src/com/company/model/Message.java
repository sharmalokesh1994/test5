package com.company.model;

public class Message {

    private int id;
    private static int ID;

    private String regex;
    private String message;

    public Message() {
        setId();
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        synchronized(Message.class ) {
            ID = ID+1;
            this.id = ID;
        }
    }
}
