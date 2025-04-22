import java.rmi.*;

public interface ServerInterface extends Remote {
	long factorial(int num) throws RemoteException;
}
