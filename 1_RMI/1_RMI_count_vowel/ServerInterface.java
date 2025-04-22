import java.rmi.*;
public interface ServerInterface extends Remote{
    int count(String word) throws RemoteException;
}