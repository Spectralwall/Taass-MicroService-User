package com.example.microServiceUser.RabbitMQ;

import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;

/*
 *  Legge dalla coda di messaggi  e stampa il messaggio da terminale
 */

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}