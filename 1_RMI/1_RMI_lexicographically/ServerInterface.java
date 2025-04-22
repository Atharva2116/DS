import java.rmi.*;
public interface ServerInterface extends Remote {
    String compare(String s1, String s2) throws RemoteException;
    
}
