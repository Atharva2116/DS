import mpi.*;
import java.util.*;

public class Reciprocal{
    public static void main(String[] args) {
        MPI.Init(args);
        int rank=MPI.COMM_WORLD.Rank();
        int size=MPI.COMM_WORLD.Size();

        int root=0,unitsize=5;

        double[] sendbuffer=new double[size];
        double[] receivebuffer=new double[1];
        double[] resultbuffer=new double[size];

        if(rank==root){
            System.out.println("Root initialized array: ");
            for (int i = 0; i <size; i++) {
                sendbuffer[i]=i+1;
                System.out.println("Element "+i+" = "+sendbuffer[i]);
            }
        }

        MPI.COMM_WORLD.Scatter(
            sendbuffer,0,1,MPI.DOUBLE,
            receivebuffer,0,1,MPI.DOUBLE,
            root
        );

        receivebuffer[0]=1.0/receivebuffer[0];
        System.out.println("Process "+rank+" calculated reciprocal "+receivebuffer[0]);

        MPI.COMM_WORLD.Gather(
            receivebuffer,0,1,MPI.DOUBLE,
            resultbuffer,0,1,MPI.DOUBLE,
            root
        );
        if(rank==root){
            System.out.println("Resultant array with reciprocals ");
            for (int i = 0; i <size; i++) {
                System.out.println("Reciprocal at index "+i+" = "+resultbuffer[i]);
            }
        }

        MPI.Finalize();
    }
}