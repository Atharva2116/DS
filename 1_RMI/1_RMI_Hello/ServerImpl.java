import java.rmi.*;
import java.rmi.server.*;
import java.rmi.RemoteException;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface{
    public ServerImpl() throws RemoteException{}

    public String echo(String name){
        String m="Hello "+name;
        System.out.println("At server "+name+" :echo  "+name+" = "+m);
        return m;
    }
}
