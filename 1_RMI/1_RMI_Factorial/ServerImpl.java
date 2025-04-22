import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
	public ServerImpl() throws RemoteException {}

	public long factorial(int num) {
		long result = 1;
		for (int i = 2; i <= num; i++) {
			result *= i;
		}
		System.out.println("At server (Thread: " + Thread.currentThread().getName() + "): factorial(" + num + ") = " + result);
		return result;
	}
}
