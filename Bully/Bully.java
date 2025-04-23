import java.io.*;
import java.util.Scanner;

public class Bully {
    static boolean[] processes;
    static int coordinator = -1;
    static int n;

    public static void createProcess(int total) {
        n = total;
        processes = new boolean[n];
        for (int i = 0; i < n; i++) {
            processes[i] = true;
        }
        coordinator = n;
        System.out.println("Processes created . Coordinator is P " + coordinator);
    }

    public static void displayProcess() {
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + " is " + (processes[i] ? "UP" : "Down"));
        }
        System.out.println("Current coordinator :P " + coordinator);

    }

    public static void bringUp(int id) {
        if (processes[id - 1]) {
            System.out.println("Process " + id + " already up");
        } else {
            processes[id] = true;
            System.out.println("P " + id + " is brought up. ");
        }
    }

    public static void bringDown(int id) {
        if (!processes[id - 1]) {
            System.out.println("Process " + id + " already down");
        } else {
            processes[id - 1] = false;
            System.out.println("P " + id + " is brought down.");
        }
    }

    public static void startElection(int id) {
        if (!processes[id]) {
            System.out.println("P" + id + " is down. cannot start election.");
            return;
        }
        System.out.println("P" + id + " started is an election");
        boolean newcoordinator = false;
        for (int i = n - 1; i >= id; i--) {
            if (processes[i]) {
                coordinator = i + 1;
                newcoordinator = true;
                System.out.println("P " + coordinator + " is elected as coordinator");
                break;
            }
        }
        if (!newcoordinator) {
            System.out.println("No active processes found to be coordinator");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice, id;

        while (true) {
            System.out.println("\n--- Bully Algorithm ---");
            System.out.println("1. Create Processes");
            System.out.println("2. Display Status");
            System.out.println("3. Bring Up a Process");
            System.out.println("4. Bring Down a Process");
            System.out.println("5. Start Election");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.println("Enter the number of processes ");
                    int total = sc.nextInt();
                    createProcess(total);
                    break;
                }
                case 2: {
                    displayProcess();
                    break;
                }
                case 3: {
                    System.out.println("Enter the process number to bring up ");
                    id = sc.nextInt();
                    bringUp(id);
                    break;
                }
                case 4: {
                    System.out.println("Enter the process number to bring down ");
                    id = sc.nextInt();
                    bringDown(id);
                    break;
                }
                case 5: {
                    System.out.println("Enter the process number to start election ");
                    id = sc.nextInt();
                    startElection(id);
                    break;
                }
                case 6: {
                    System.out.println("Exiting ");
                    return;
                }
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

}
