package org.example;

import org.apache.activemq.broker.BrokerService;

public class ActiveMQBroker {
    public static void main(String[] args)
    {
        try
        {
            BrokerService brokerService = new BrokerService(); // creation d'un objet BrokerService
            brokerService.addConnector("tcp://0.0.0.0:61616"); //
            brokerService.start(); //demarrage du broker

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}


