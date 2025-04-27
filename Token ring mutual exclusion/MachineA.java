import java.io.*;
import java.net.*;

public class MachineA {
    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(5000);
        Socket socket = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        while(true){
            String token = in.readLine();
            if("TOKEN".equals(token)){
                System.out.println("Recieved token");
                Thread.sleep(10000);
                System.out.println("Releasing token");
                out.println("TOKEN");
            }
        }
    }
}
