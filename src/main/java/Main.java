

import com.solacesystems.jcsmp.*;





public class Main {

    public static void main(String... args) throws JCSMPException {


        final JCSMPProperties properties = new JCSMPProperties();
        properties.setProperty(JCSMPProperties.HOST, args[0]);  // msg-backbone-ip:port
        properties.setProperty(JCSMPProperties.VPN_NAME, args[1]); // message-vpn
        properties.setProperty(JCSMPProperties.USERNAME, args[2]);
        properties.setProperty(JCSMPProperties.PASSWORD, args[3]);
        String topic  = args[4];
        final JCSMPSession session = JCSMPFactory.onlyInstance().createSession(properties);
        session.connect();

        System.out.printf("Publishing to %s",topic);
        Publish pub = new Publish();
        pub.connect(session, topic);
    }
}
