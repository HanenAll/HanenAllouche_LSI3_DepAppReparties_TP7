package org.example;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
public class Consumer {
    public static void main(String[] args) {
        try {
            // il faut que le consumer se connecte sur la meme chause que le producer est connecté avec.
            ConnectionFactory connexionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            // creation de connexion sur le broker
            //tcp c le protocole localhost c'est l@ IP de la machine qui a le broker autrement c le serveur et 61616 c le port
            Connection connection = connexionFactory.createConnection();//etablir une connexion vers le broker
            connection.start(); // je lance la connexion avant le send de message
            Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            //true pour dire que le message sera envoyer que lorsque on fait  la commit de session
            // false permet d'envoyer le message automatiquement apres chaque send
            Destination destination = session.createTopic("MyTopic.topic");//si elle a trouver une file d'attente
            // qui porte ce nom elle va connecter sur lui.
            // La destination est l'endroit où les messages seront échangés entre les producers et consumers
            MessageConsumer consumer = session.createConsumer(destination); //le consumer va recevoir les message
            // publiés sur le topic specifié
            consumer.setMessageListener(new MessageListener(){
                //on a mis un JMS listner pour la reception des messages. En fait, ce listner permet au consumer
                // d'agir de maniere reactive en traitant les messages dès qu'ils sont disponibles dans la file d'attente.
                @Override
                public void onMessage(Message message) {
                    if (message instanceof TextMessage)
                    {
                        TextMessage textMessage =(TextMessage) message;
                        try{
                            System.out.println("Message reçu :"+textMessage);


                        }catch(Exception e){e.printStackTrace();}
                    }
                }
            });
        }catch (Exception e )
        {
            e.printStackTrace();
        }

    }
}
