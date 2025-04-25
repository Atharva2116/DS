import UppercaseModule.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;

public class Server {
public static void main(String[] args) {
    try {
        ORB orb =ORB.init(args,null);
        POA rootPOA=POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootPOA.the_POAManager().activate();

        UppercaseImpl upc=new UppercaseImpl();
        org.omg.CORBA.Object ref=rootPOA.servant_to_reference(upc);
        Uppercase h_ref=UppercaseHelper.narrow(ref);

        org.omg.CORBA.Object objRef=orb.resolve_initial_references("NameService");
        NamingContextExt ncref=NamingContextExtHelper.narrow(objRef);

        String name="Uppercase";
        NameComponent path[]=ncref.to_name(name);
        ncref.rebind(path, h_ref);

        System.out.println("Upper case Server reading and waiting.........");
        orb.run();
    } catch (Exception e) {
        e.printStackTrace();        // TODO: handle exception
    }
}    
}
