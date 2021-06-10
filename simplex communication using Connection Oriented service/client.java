import java.io.*;
import java.net.*;
public class client {
    public static void main(String[] args) {
        int choice=1, num1, num2;
        try {
            Socket s = new Socket("localhost", 1000);
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(System.in));
            DataOutputStream dos = new
                    DataOutputStream(s.getOutputStream());
            while (choice!=0) {
                System.out.println(" 1 - Addition\n 2 - Subtraction\n 3 -Multiplication\n 4 - Division\n 0 - exit\nChoose an operation:");
                choice = Integer.parseInt(br.readLine());
                dos.writeUTF(String.valueOf(choice));
                if (choice==0)
                    break;
                System.out.println("Enter value 1: ");
                num1 = Integer.parseInt(br.readLine());
                System.out.println("Enter value 2 : ");
                num2 = Integer.parseInt(br.readLine());
                dos.writeUTF(String.valueOf(num1));
                dos.writeUTF(String.valueOf(num2));
                dos.flush();
            }
            System.out.println("exited");
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
