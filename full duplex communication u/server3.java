
import java.io.*;
import java.net.*;
public class server3 {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1000);
            System.out.println("Server Started");
            System.out.println("Waiting for client...");
            Socket s = ss.accept();
            System.out.println("Client Connected");
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            String choice = "";
            int num1, num2,val;
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
                        val=num1 + num2;
                        System.out.println(num1 + " + " + num2 + " = " +val);
                        dos.writeUTF(String.valueOf(val));
                         break;
                    case "2":
                        val= num1 - num2;
                        System.out.println(num1 + " - " + num2 + " = " +val);
                        dos.writeUTF(String.valueOf(val));
                        break;
                    case "3":
                        val=num1 * num2;
                        System.out.println(num1 + " * " + num2 + " = " + val);
                        dos.writeUTF(String.valueOf(val));
                        break;
                    case "4":
                        val=num1 /num2;
                        System.out.println(num1 + " / " + num2 + " = " + val);
                        dos.writeUTF(String.valueOf(val));
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
