import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface{
    public ServerImpl() throws RemoteException {}

    public int count(String word){
        int cnt=0;
        for(char ch:word.toLowerCase().toCharArray()){
            if("aeiou".indexOf(ch)!=-1){
                cnt++;
            }
        }
        System.out.println("At server "+word+" count vowels "+ cnt);
        return cnt;
    }
     
    
}