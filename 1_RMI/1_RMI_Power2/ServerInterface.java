import java.rmi.*;
public interface ServerInterface extends Remote{
    double powerof(double num) throws RemoteException;
}