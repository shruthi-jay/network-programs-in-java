import java.io.*;
import java.net.*;
public class server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1000);
            System.out.println("Server Started");
            System.out.println("Waiting for client...");
            Socket s = ss.accept();
            System.out.println("Client Connected");
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String choice = "";
            int num1, num2;
            while (!choice.equals("0")) {
                choice = dis.readUTF();
                if (choice .equals("0")) {
                    System.out.println("\n Client Exited");
                    break;
                }
                num1 = Integer.parseInt(dis.readUTF());
                num2 = Integer.parseInt(dis.readUTF());
                switch (choice ) {
                    case "1":
                        System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
                        break;
                    case "2":
                        System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
                        break;
                    case "3":
                        System.out.println(num1 + " * " + num2 + " = " + (num1 * num2));
                        break;
                    case "4":
                        System.out.println(num1 + " / " + num2 + " = " + (float)num1 /(float)num2);
                        break;
                }
            }
            dis.close();
            s.close();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
