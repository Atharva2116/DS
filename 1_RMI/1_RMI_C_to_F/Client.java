import java.rmi.*;

public class Client {
    public static void main(String[] args) {
        try {
            String rmiURL="rmi://localhost/Server";
            ServerInterface serverInterface=(ServerInterface) Naming.lookup(rmiURL);
            double c=Double.parseDouble(args[0]);
            System.out.println("At client convert to fahrenheit "+c+" C = "+serverInterface.convert(c));
        } catch (Exception e) {
            System.out.println("exception "+e);
            // TODO: handle exception
        }
    }
}
