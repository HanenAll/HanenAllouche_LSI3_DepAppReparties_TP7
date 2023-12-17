package org.example;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
public class Producer {
    public static void main(String[] args) {
        try {
          ConnectionFactory connexionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
          // creation de connexion sur le broker
          //tcp c le protocole localhost c'est l@ IP de la machine qui a le broker autrement c le serveur et 61616 c le port
          //connexion sur l'URL
            Connection connection=connexionFactory.createConnection(); //etablir une connexion vers le broker
            connection.start(); // je lance la connexion avant le send de message
            Session session= connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            //true pour dire que le message sera envoyer que lorsque on fait  la commit de session
            // false permet d'envoyer le message automatiquement apres chaque send
            Destination destination=session.createTopic("myTopic.topic"); // définir la destination du message grace
            // a createTopic
            MessageProducer producer=session.createProducer(destination);
            TextMessage textMessage=session.createTextMessage();
            textMessage.setText("HelloWOrld");
            producer.send(textMessage); //
            session.commit(); // j'ai mis le  message dans la file d'attente et on a fait commit car on a mis true
            // dans la create session si on a mis false on l'ecrit pas
            session.close(); //fermeture de session
            connection.close(); // fermeture de connexion
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
