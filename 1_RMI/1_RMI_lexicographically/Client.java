import java.rmi.*;

public class Client {
    public static void main(String[] args) {
        try {
            String rmiURL="rmi://localhost/Server";
            ServerInterface serverInterface=(ServerInterface) Naming.lookup(rmiURL);
            String s1=args[0];
            String s2=args[1];
            System.out.println("At client compare string "+s1+" and "+s2+ " = "+serverInterface.compare(s1, s2));
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("exception"+e);
        }
    }
}
