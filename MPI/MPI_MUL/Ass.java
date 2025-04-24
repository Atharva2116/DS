import mpi.*;

public class Ass {
    public static void main(String[] args) {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int unitsize = 5;  // You had unitsize = 1, which was odd given you initialized 20 elements
        int root = 0;

        int[] sendBuffer = new int[unitsize * size];
        int[] receiveBuffer = new int[unitsize];
        int[] gatherBuffer = new int[size];

        if (rank == root) {
            int totalElement = unitsize * size;
            System.out.println("Initializing " + totalElement + " elements: ");
            for (int i = 0; i < totalElement; i++) {
                sendBuffer[i] = i + 1;
                System.out.println("Element " + i + " = " + sendBuffer[i]);
            }
        }

        MPI.COMM_WORLD.Scatter(
            sendBuffer, 0, unitsize, MPI.INT,
            receiveBuffer, 0, unitsize, MPI.INT,
            root
        );

        // Use long to avoid overflow
        long localProduct = 1;
        for (int i = 0; i < unitsize; i++) {
            localProduct *= receiveBuffer[i];
        }

        System.out.println("Intermediate product at process " + rank + " is " + localProduct);

        // Convert long to int (safe if values are still in int range) or handle differently
        int[] localProductInt = new int[] { (int)localProduct }; // Warning: this may overflow during Gather
        MPI.COMM_WORLD.Gather(
            localProductInt, 0, 1, MPI.INT,
            gatherBuffer, 0, 1, MPI.INT,
            root
        );

        if (rank == root) {
            long finalProduct = 1;
            for (int i = 0; i < size; i++) {
                finalProduct *= gatherBuffer[i];
            }
            System.out.println("Final product: " + finalProduct);
        }

        MPI.Finalize();
    }
}
