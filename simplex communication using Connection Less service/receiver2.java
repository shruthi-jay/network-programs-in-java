import java.net.*;
import java.io.*;

public class receiver2 {
  public static int PORT_NUMBER = 1111;
  public static int BUFFER_SIZE = 1024;
  static DatagramSocket ds;
  public static void main(String[] args)  {
    try {
      ds = new DatagramSocket(PORT_NUMBER);
      byte[] buffer = new byte[BUFFER_SIZE];
      DatagramPacket dp = new DatagramPacket(buffer, BUFFER_SIZE);
      ds.receive(dp);
      String msg = new String(dp.getData(), 0, dp.getLength());
      System.out.println("Message Received\n\n");
      String upper = msg.toUpperCase();
      System.out.println("Received message in Uppercase:  " + upper + "\n");
      String lower = msg.toLowerCase();
      System.out.println("Received message in Lowercase:  " + lower + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      ds.close();
    }
  }
}
