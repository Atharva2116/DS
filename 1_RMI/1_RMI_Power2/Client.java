import java.rmi.*;


public class Client {
    public static void main(String[] args) {
    try {
        String rmiURL="rmi://localhost/Server";
        ServerInterface serverInterface=(ServerInterface) Naming.lookup(rmiURL);
        double num=Double.parseDouble(args[0]);
        System.out.println("At client "+num+"^2 = "+serverInterface.powerof(num));
    } catch (Exception e) {
        // TODO: handle exception
        System.out.println("exception "+e);
    }
    }
}
