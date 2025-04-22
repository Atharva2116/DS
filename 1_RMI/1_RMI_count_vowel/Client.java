import java.rmi.*;

public class Client {
    public static void main(String[] args) {
        try {
            String rmiURL="rmi://localhost/Server";
            ServerInterface serverInterface=(ServerInterface) Naming.lookup(rmiURL);
            String word=args[0];
            System.out.println("At client for word "+word +" the vowels count is "+serverInterface.count(word));
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception "+e);
        }
    }
}
