import java.net.*;
import java.io.*;
import java.util.Scanner;

public class sender2 {
    public static int PORT_NUMBER = 1111;
    static DatagramSocket ds;

    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            ds = new DatagramSocket();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter message :\n");
            String msg = scanner.nextLine();
            DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(), ip, PORT_NUMBER);
            ds.send(dp);
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            ds.close();
        }
    }
}
