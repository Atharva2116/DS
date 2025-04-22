import java.rmi.server.*;
import java.rmi.*;


public class ServerImpl extends UnicastRemoteObject implements  ServerInterface {
    public ServerImpl() throws RemoteException{}
    public double convert(double m){
        double k=m*1.60934;
        System.out.println("At server "+m+" miles to "+k+" km");
        return k;
    }
    
}
