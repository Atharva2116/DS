import java.util.*;
import mpi.*;

public class Ass {
    public static void main(String[] args) {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int unitsize = 5;
        int root = 0;

        // Allocate sendbuffer for all processes to avoid NullPointerException
        int[] sendbuffer = new int[unitsize * size]; 
        int[] receivebuffer = new int[unitsize];
        int[] partialSum = new int[size];

        // Only root fills the array with values
        if (rank == root) {
            int totalElements = unitsize * size;
            System.out.println("Enter " + totalElements + " elements ");
            for (int i = 0; i < totalElements; i++) {
                sendbuffer[i] = i + 1;  // Filling values 1 to N
                System.out.println("Element " + i + " = " + sendbuffer[i]);
            }
        }

        //  Scatter the array to all processes
        MPI.COMM_WORLD.Scatter(
            sendbuffer, 0, unitsize, MPI.INT,
            receivebuffer, 0, unitsize, MPI.INT,
            root
        );

        // Calculate sum of chunk in each process
        int localSum = 0;
        for (int i = 0; i < unitsize; i++) {
            localSum += receivebuffer[i];
        }

        System.out.println("Intermediate sum at process " + rank + " is " + localSum);

        // Gather partial sums from all processes to root
        MPI.COMM_WORLD.Gather(
            new int[]{localSum}, 0, 1, MPI.INT,
            partialSum, 0, 1, MPI.INT,
            root
        );

        //  Final aggregation at root
        if (rank == root) {
            int totalSum = 0;
            for (int i = 0; i < size; i++) {
                totalSum += partialSum[i];
            }
            System.out.println("Final sum: " + totalSum);
        }

        MPI.Finalize();
    }
}
