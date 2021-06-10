import java.net.*;
import java.io.*;
import java.util.*;

public class second {
    public static int PORT_1 = 1111;
    public static int PORT_2 = 2222;
    public static int BUFFER_SIZE = 1024;
    static DatagramSocket ds;

    public static void main(String[] args) {
        String msg = "";
        try {
            while (!msg.equalsIgnoreCase("exit")) {
                Scanner sc = new Scanner(System.in);
                ds = new DatagramSocket(PORT_1);
                byte[] buffer = new byte[BUFFER_SIZE];
                DatagramPacket dp = new DatagramPacket(buffer, BUFFER_SIZE);
                ds.receive(dp);
                msg = new String(dp.getData(), 0, dp.getLength());
                System.out.println("Message Received : ");
                String upper = msg.toUpperCase();
                System.out.println("Received message in Uppercase:  " + upper + "\n");
                ds.disconnect();
                ds.close();
                if (msg.equalsIgnoreCase("exit"))
                    break;

                ds = new DatagramSocket();
                InetAddress ip = InetAddress.getByName("localhost");
                System.out.print("Enter your message : ");
                msg = sc.nextLine();
                dp = new DatagramPacket(msg.getBytes(), msg.length(), ip, PORT_2);
                ds.send(dp);
                ds.disconnect();
                ds.close();
                if (msg.equalsIgnoreCase("exit"))
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ds.close();
        }
    }
}
