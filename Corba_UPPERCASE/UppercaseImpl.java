import UppercaseModule.UppercasePOA;
public class UppercaseImpl extends UppercasePOA {
    UppercaseImpl(){
        super();
        System.out.println("Uppercase Object Created");
    }
    public String to_uppercase(String name){
        return "Server send "+name.toUpperCase();
    }
    
}