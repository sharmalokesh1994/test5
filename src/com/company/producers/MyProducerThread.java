package com.company.producers;

import com.company.constants.Constants;
import com.company.message_queue.MessageQueue;
import com.company.model.Message;

public class MyProducerThread implements Runnable {

    private MessageQueue messageQueue;

    public MyProducerThread( MessageQueue messageQueue ) {
        this.messageQueue = messageQueue;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        int messageValue = 1;

        while (true ) {
            Message message = new Message();
            // generate Message
            if(messageValue%2 == 0) {
                message.setRegex(Constants.REGEX_TYPE_1);
                message.setMessage("["+ messageValue +"]");
                messageValue++;
            } else {
                message.setRegex(Constants.REGEX_TYPE_2);
                message.setMessage("["+ messageValue +"]");
                messageValue++;

            }

            for( int retry=0;retry<2;retry++ ) {
                try {
                    messageQueue.addMessages(message);
                    break;
                } catch (Exception ex) {
                    try {
                        // retry after 100 ms
                        System.out.println("will retry after 100 ms");
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
    }

}
