import java.util.Random;
import mpi.*;
public class Average{
    public static void main(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        

        int unitsize=5;
        int root=0;

        int totalelements=unitsize*size;
        int[] sendBuffer=new int[unitsize*size];
        int[] recieveBuffer=new int [unitsize];
        double[] avgBuffer=new double[size];


        if (root==rank) {
            Random rand=new Random();
            System.out.println("Generated random numbers ");
            for (int i = 0; i < totalelements; i++) {
                sendBuffer[i]=rand.nextInt(100);
                System.out.println(sendBuffer[i]+" ");
            }
            System.out.println();
        }
        MPI.COMM_WORLD.Scatter(
        sendBuffer,0,unitsize,MPI.INT,
        recieveBuffer,0,unitsize,MPI.INT,
        root
        );
        int localsum=0;
        for (int i = 0; i <unitsize; i++) {
            localsum+=recieveBuffer[i];
        }
        double localAvg=(double) localsum/unitsize;
        System.out.println("Process "+rank+" averages "+localAvg);
        MPI.COMM_WORLD.Gather(
            new double[]{localAvg},0,1,MPI.DOUBLE, 
            avgBuffer,0,1,MPI.DOUBLE,0
        );
        if (rank==root) {
            double totalAvg=0.0;
            for (int i = 0; i <size; i++) {
                totalAvg+=avgBuffer[i];
            }
            totalAvg/=size;
            System.out.println("Final average :"+totalAvg);
        }
        MPI.Finalize();
    }
}