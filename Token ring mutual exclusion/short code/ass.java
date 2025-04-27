import java.util.*;

class ass {
  public static void main(String args[]) throws Throwable {
    try {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the num of nodes : ");
      int n = sc.nextInt();
      int token = 0;
      for (int i = 0; i < n; i++) {
        System.out.print(" " + i);
      }
      System.out.println(" " + 0);
      while (true) {
        System.out.println("----------------------------------------------");
        System.out.print("Enter sender : ");
        int s = sc.nextInt();
        System.out.print("Enter receiver : ");
        int r = sc.nextInt();
        System.out.print("Enter Data : ");
        String d = sc.next();
        System.out.print("Token passing : ");
        for (int i = token; i != s; i = (i + 1) % n) {
          System.out.print(" " + i + "->");
        }
        System.out.println(" " + s);
        System.out.println("Sender " + s + " sending data: " + d);
        for (int i = s + 1; i != r; i = (i + 1) % n) {
          System.out.println("data " + d + " forwarded by " + i % n);
        }
        System.out.println("Reciever " + r + " recieved data: " + d);
        token = s;
        System.out.println("----------------------------------------------");
      }
    } catch (Exception e) {
      System.out.println("Exception" + e);
    }
  }
}