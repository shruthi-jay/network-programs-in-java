import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Client {
    static String msg ="";
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 2000);
        Scanner sc=new Scanner(System.in);
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        while (!msg.equalsIgnoreCase("exit")) {
            System.out.println("\nEnter the message");
            msg = sc.nextLine();
            ArrayList<byte[]> frames = encode(msg);
            int frameSize = frames.size();

            System.out.println("\nYour input string is divided to " + frameSize + " frames ,each of size 5" );
            System.out.println("Total frame to be sent = "+frameSize);
            dos.write(frameSize);
             for (int i = 0; i < frames.size(); i++) {
                System.out.println("Frame - " + (i + 1) + " : " + Arrays.toString((frames.get(i))));
                dos.write(frames.get(i));
                System.out.println("\tFrame - (" + (i + 1) + "/" + frameSize + ") sended.....");
             }
            System.out.println("All Frames have been sent\n--------------------------------");
        }
        dos.close();
        s.close();

    }

    static ArrayList<byte[]> encode(String msg) {
        int index = 0;
        ArrayList<byte[]> frames = new ArrayList<>();
        while(!msg.isEmpty()) {
            byte[] frame = new byte[5];
            for (int i = 0; i < 5; i++) {
                if (msg.isEmpty())
                    break;
                frame[i] = (byte) msg.charAt(0);
                msg = msg.substring(msg.indexOf(frame[i])+1);
            }
            frames.add(index,frame);
            index++;
        }
        return frames;
    }
}
