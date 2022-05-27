package com.company.message_queue;

import com.company.consumers.MyConsumer;
import com.company.model.Message;

import java.util.HashMap;
import java.util.LinkedList;

// todo : make it singleton
public class MessageQueue {

    private HashMap<Integer,MyConsumer> myConsumers;
    // todo :  use json object using jackson library for conversion and saving it as json (for conversation purpose)
    private LinkedList<Message> messages;
    private int maxSize;

    public MessageQueue() {
        myConsumers = new HashMap<>();
        messages = new LinkedList<>();
        this.maxSize = 20;
    }

    public void addConsumer( MyConsumer myConsumer ) {
        myConsumers.put(myConsumer.getId(),myConsumer);
    }

    public void deListConsumer(int id) {
        if( myConsumers.containsKey(id) ) {
            myConsumers.remove(id);
        }
    }

    //todo : convert java object to json and then store
    public void consumeMessages() throws InterruptedException {
        while (true) {
            Thread.sleep(100);
            synchronized (this) {
                while (messages.size() == 0) {
                    this.notifyAll();
                    this.wait();
                }

                Message message = messages.removeFirst();

                for( MyConsumer myConsumer : myConsumers.values() ) {
                    if( myConsumer.getRegex().equalsIgnoreCase(message.getRegex()) ) {
                        myConsumer.processData(message);
                    }
                }
            }
        }
    }

    //todo : convert java object to json and then store
    public void addMessages(Message message) {
        synchronized (this) {
            if( messages.size() >= this.maxSize ) {
                // todo: add custom exception
                throw new RuntimeException("size is full");
            }
            messages.addLast(message);
            this.notifyAll();
        }

    }

}
