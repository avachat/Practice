package avachat.learn.netty;

public class TransportReceiver {

/* NOT thread safe */

    private static TransportReceiver TheInstance = null;

    private Transport transport = null; // underlying channel

    public static TransportReceiver init(Transport t) {
        TheInstance = new TransportReceiver(t);
        return TheInstance;
    }

    public static TransportReceiver getInstance() {
        return TheInstance;
    }

    private TransportReceiver(Transport t) {

        transport = t;

    } //


    /**
     * This methid will be called on both the client and server side Server receives messages sent by client :
     * MessageType1 or 2 Cleint receives echo-back from server
     */
    public void receiveMessage(Object msg) {

        if (msg == null) {
            System.out.println("Received a NULL object");
            return;
        } //

        if (msg instanceof MessageType1) {
            System.out.println("Server will acknowledge message type 1");
            transport.sendObject(new Integer(1), null); // echo back
            return;
        } //

        if (msg instanceof MessageType2) {
            System.out.println("Server will acknowledge message type 2");
            transport.sendObject(new Integer(2), null); // echo back
            return;
        } //

        if (msg instanceof Number) {
            int received_echo = ((Number) msg).intValue();
            boolean is_match = (received_echo == DataTransmitter.LastMessageType);
            System.out.println("Client received echo back = " + received_echo);
            System.out.println("Received echo macthes with what was sent ? " + (is_match ? "Yes" : "No"));
            return;
        } //

        System.out.println("Unknown message type");

    }

}

