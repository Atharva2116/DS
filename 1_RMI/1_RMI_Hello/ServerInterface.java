import java.rmi.*;

public interface ServerInterface extends Remote{

    String echo(String name) throws RemoteException;
}
