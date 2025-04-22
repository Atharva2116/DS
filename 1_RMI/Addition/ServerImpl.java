import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface{
    public ServerImpl() throws RemoteException{}
    public double add(double num1,double num2){
        double result =num1+num2;
        System.out.println("At server :add("+num1+num2+") = "+result);
        return result;
    }
}