import java.io.*;
import java.net.*;

public class MachineB {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000); // Use Machine A's IP instead of localhost if on real machines
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Initially Machine B has the token
        System.out.println("Machine B: Starting with token, entering Critical Section...");
        Thread.sleep(2000); // Simulate critical section
        System.out.println("Machine B: Exiting Critical Section, sending token...");
        out.println("TOKEN");

        while (true) {
            String token = in.readLine();
            if ("TOKEN".equals(token)) {
                System.out.println("Machine B: Got token, entering Critical Section...");
                Thread.sleep(2000);
                System.out.println("Machine B: Exiting Critical Section, sending token...");
                out.println("TOKEN");
            }
        }
    }
}
