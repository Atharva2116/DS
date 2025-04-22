
import java.rmi.*;
public interface ServerInterface extends Remote {

    double convert(double c) throws RemoteException;

}
