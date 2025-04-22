import java.rmi.server.*;
import java.rmi.*;


public class ServerImpl extends UnicastRemoteObject implements ServerInterface{
    public ServerImpl() throws RemoteException{}
    public double convert(double c){
        double result=(c*9/5)+32;
        System.out.println("At server "+c+" celcius = "+result+" fahrenheit");
        return result;
    }

}
