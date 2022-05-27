package com.company.consumers;

import com.company.message_queue.MessageQueue;

public class MyConsumerThread implements Runnable {

    private MessageQueue messageQueue;

    public MyConsumerThread(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            messageQueue.consumeMessages();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
