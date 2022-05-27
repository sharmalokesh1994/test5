package com.company.consumers;

import com.company.model.Message;

public class MyConsumer {

    private int id;
    private String regex;
    private static int ID=0;

    public MyConsumer( String regex ) {
        this.regex = regex;
        setId();
    }

    public int getId() {
        return id;
    }

    private void setId() {
        synchronized(this.getClass()) {
            ID++;
            this.id = ID;
        }
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public void processData(Message message) {
        System.out.println("consumer id : " + getId() + " message id : " + message.getId() + " message : " + message.getMessage());
    }

}
