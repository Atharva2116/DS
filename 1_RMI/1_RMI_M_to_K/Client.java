import java.rmi.*;

public class Client  {
public static void main(String[] args) {
    try {
        String rmiURL="rmi://localhost/Server";
        ServerInterface serverInterface=(ServerInterface) Naming.lookup(rmiURL);
        double m=Double.parseDouble(args[0]);
        System.out.println("At client convert miles to kilo meter "+m+ " miles "+" = "+serverInterface.convert(m)+" km");
    } catch (Exception e) {
        System.out.println("Exception "+e);
    }
}
    
}