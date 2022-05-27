package com.company;

import com.company.constants.Constants;
import com.company.consumers.MyConsumer;
import com.company.consumers.MyConsumerThread;
import com.company.message_queue.MessageQueue;
import com.company.producers.MyProducerThread;

public class Main {

    public static void main(String[] args) throws Exception {

        MyConsumer myConsumer1 = new MyConsumer(Constants.REGEX_TYPE_1);
        MyConsumer myConsumer2 = new MyConsumer(Constants.REGEX_TYPE_2);
        MyConsumer myConsumer3 = new MyConsumer(Constants.REGEX_TYPE_2);
        MessageQueue messageQueue = new MessageQueue();

        //todo : sequence will decide on this sequence
        messageQueue.addConsumer(myConsumer1);
        messageQueue.addConsumer(myConsumer2);
        messageQueue.addConsumer(myConsumer3);

        new MyConsumerThread(messageQueue);
        new MyProducerThread(messageQueue);

    }

}

