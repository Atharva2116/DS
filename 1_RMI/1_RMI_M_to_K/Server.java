import java.rmi.*;


public class Server {
    public static void main(String[] args) {
        try {
            ServerImpl serverImpl=new ServerImpl();
            Naming.rebind("Server", serverImpl);
            System.out.println("server started...........");
        } catch (Exception e) {
            System.out.println("Exception "+e);
            // TODO: handle exception
        }
    }    
}
