import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
    public ServerImpl() throws RemoteException{}
    public String compare(String s1,String s2) {
        String result=(s1.compareTo(s2)>=0)?s1:s2;
        System.out.println("At server "+s1+" compare "+s2+" = "+result);
        return result;
    }
    
}
