import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextExtPackage.*;
import UppercaseModule.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try {
            ORB orb=ORB.init(args,null);
            org.omg.CORBA.Object objRef=orb.resolve_initial_references("NameService");
            NamingContextExt ncRef=NamingContextExtHelper.narrow(objRef);

            String name="Uppercase";
            Uppercase upcImpl=UppercaseHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Enter string  ");
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String str=br.readLine();

            String result=upcImpl.to_uppercase(str);
            System.out.println(result);
            
        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }
    }
    
}
