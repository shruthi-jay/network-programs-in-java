import java.net.*;
import java.io.*;
import java.util.*;

public class first {
    public static int PORT_1 = 1111;
    public static int PORT_2 = 2222;
    public static int BUFFER_SIZE = 1024;
    static DatagramSocket ds;
    static DatagramPacket dp;
    static String msg = "";

    public static void main(String[] args) {

        while (!msg.equalsIgnoreCase("exit")) {
            try {
                InetAddress ip = InetAddress.getByName("localhost");
                ds = new DatagramSocket();
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter message :\n");
                msg = scanner.nextLine();
                DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(), ip, PORT_1);
                ds.send(dp);
                ds.disconnect();
                ds.close();
                if (msg.equalsIgnoreCase("exit"))
                    break;

                ds = new DatagramSocket(PORT_2);
                byte[] buffer = new byte[BUFFER_SIZE];
                dp = new DatagramPacket(buffer, BUFFER_SIZE);
                ds.receive(dp);
                msg = new String(dp.getData(), 0, dp.getLength());
                System.out.println("Message Received : ");
                String lower = msg.toLowerCase();
                System.out.println("Received message in Lowercase:  " + lower + "\n");
                ds.disconnect();
                ds.close();
                if (msg.equalsIgnoreCase("exit"))
                    break;

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ds.close();
            }
        }

    }
}
