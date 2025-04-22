import java.rmi.*;
import java.rmi.server.*;


public class Server {
public static void main(String[] args) {
    try {
        ServerImpl serverImpl=new ServerImpl();
        Naming.rebind("Server",serverImpl);
        System.out.println("Server started....");
    } catch (Exception e) {
        System.out.println("exception "+e);
        // TODO: handle exception
    }
}    
}
