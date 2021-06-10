import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Server6 {
    static String msg = "";
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(2000);
        System.out.println("Server Initiated");
        System.out.println("Waiting for client...");
        Socket s = ss.accept();
        System.out.println("Client Connected\n-----------------------------\n");
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        while (!msg.equalsIgnoreCase("exit")) {
            int frameSize = dis.read();
            if (frameSize <= 0)
                break;
            System.out.println("\nTotal frames to be received = " + frameSize);
            ArrayList<byte[]> frames = new ArrayList<>();
            for (int i = 0; i < frameSize; i++) {
                byte[] frame = new byte[5];
                dis.read(frame);
                frames.add(frame);
                System.out.println("Receiving Frame (" + (i + 1) + "/" + frameSize + ")......");
                dos.write(1);
                System.out.println("\tFrame " + (i + 1) + " : " + Arrays.toString((frames.get(i))));

            }

            msg = new String(decode(frames));
            System.out.println("All Frames have been received");
            System.out.println("\nMessage  : " + msg +"\n-----------------------------\n");
        }
        dis.close();
        s.close();
        ss.close();
    }

    static char[] decode(ArrayList<byte[]> frames) {
        int index = 0;
        char[] msg = new char[1025];
        for (byte[] frame : frames) {
            for (int j = 0; j < 5; j++) {
                msg[index] = (char) frame[j];
                index++;
            }
        }
        return msg;
    }

}
