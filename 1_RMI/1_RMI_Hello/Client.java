import java.rmi.*;

public class Client {
    public static void main(String[] args) {
        try {
            String rmiURL="rmi://localhost/Server";
            ServerInterface serverinterface=(ServerInterface) Naming.lookup(rmiURL);
            String name=args[0];
            System.out.println("At client echo "+name +" = "+serverinterface.echo(name));
        } catch (Exception e) {
            System.out.println("Exception "+e);
        }
    }
    
}