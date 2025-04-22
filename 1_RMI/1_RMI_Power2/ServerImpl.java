import java.rmi.*;
import java.rmi.server.*;
public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
    public ServerImpl() throws RemoteException{}
    public double powerof(double num){
        double result=Math.pow(2,num);
        System.out.println("At server "+num+"^2 = "+result);
        return result;
    }
    
}