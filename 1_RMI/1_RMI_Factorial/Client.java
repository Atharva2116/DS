import java.rmi.*;

public class Client {
	public static void main(String args[]) {
		try {
			String rmiURL = "rmi://localhost/Server";
			ServerInterface serverInterface = (ServerInterface) Naming.lookup(rmiURL);
			int number = Integer.parseInt(args[0]);
			System.out.println("At Client: factorial(" + number + ") = " + serverInterface.factorial(number));
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
}
