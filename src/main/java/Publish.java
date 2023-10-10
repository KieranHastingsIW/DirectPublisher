import Entity.DeathNotice;
import com.google.gson.Gson;
import com.solacesystems.jcsmp.*;

import java.io.IOException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class Publish {


    public void connect(JCSMPSession session, String topic) throws JCSMPException, InterruptedException, IOException {
        XMLMessageProducer prod = session.getMessageProducer(new JCSMPStreamingPublishEventHandler() {

            @Override
            public void responseReceived(String messageID) {
                System.out.println("Producer received response for msg: " + messageID);
            }

            @Override
            public void handleError(String messageID, JCSMPException e, long timestamp) {
                System.out.printf("Producer received error for msg: %s@%s - %s%n",
                        messageID, timestamp, e);
            }
        });
        final Topic setTopic = JCSMPFactory.onlyInstance().createTopic(topic);
        TextMessage msg = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text here to Mock Events, every new line will be a new Event!");

        while(System.in.available() == 0){
           try {
               String input = scanner.nextLine();
               if (Objects.equals(input, "death")) {
                   Gson gson = new Gson();
                   DeathNotice deathNotice = new DeathNotice(input, new Date());
                   String jsonInString = gson.toJson(deathNotice);
                   msg.setText(jsonInString);
                   prod.send(msg, setTopic);
               }
           }catch (NoSuchElementException e){
               System.out.println("Application terminated");
               return;
               }

        }




    }
}