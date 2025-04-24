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
        System.out.println("Processes created. Coordinator is P" + coordinator);
    }

    public static void displayProcess() {
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + " is " + (processes[i] ? "UP" : "DOWN"));
        }
        System.out.println("Current coordinator: P" + coordinator);
    }

    public static void bringUp(int id) {
        if (processes[id - 1]) {
            System.out.println("Process P" + id + " is already UP.");
        } else {
            processes[id - 1] = true;
            System.out.println("Process P" + id + " is brought UP.");
        }
    }

    public static void bringDown(int id) {
        if (!processes[id - 1]) {
            System.out.println("Process P" + id + " is already DOWN.");
        } else {
            processes[id - 1] = false;
            System.out.println("Process P" + id + " is brought DOWN.");
        }
    }

    public static void startElection(int id) {
        if (!processes[id - 1]) {
            System.out.println("P" + id + " is DOWN. Cannot start election.");
            return;
        }

        System.out.println("P" + id + " started an election.");

        boolean higherResponded = false;

        for (int i = id; i < n; i++) {
            if (processes[i]) {
                System.out.println("P" + id + " sends ELECTION message to P" + (i + 1));
                System.out.println("P" + (i + 1) + " responds with OK to P" + id);
                higherResponded = true;
            }
        }

        if (!higherResponded) {
            coordinator = id;
            System.out.println("P" + id + " becomes the coordinator.");
            for (int i = 0; i < n; i++) {
                if (i != (id - 1) && processes[i]) {
                    System.out.println("P" + id + " sends COORDINATOR message to P" + (i + 1));
                }
            }
        } else {
            // simulate higher process continuing the election
            for (int i = n - 1; i >= id; i--) {
                if (processes[i]) {
                    startElection(i + 1);
                    break;
                }
            }
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
                    System.out.print("Enter the number of processes: ");
                    int total = sc.nextInt();
                    createProcess(total);
                    break;
                }
                case 2: {
                    displayProcess();
                    break;
                }
                case 3: {
                    System.out.print("Enter the process number to bring UP: ");
                    id = sc.nextInt();
                    if (id >= 1 && id <= n) bringUp(id);
                    else System.out.println("Invalid process number.");
                    break;
                }
                case 4: {
                    System.out.print("Enter the process number to bring DOWN: ");
                    id = sc.nextInt();
                    if (id >= 1 && id <= n) bringDown(id);
                    else System.out.println("Invalid process number.");
                    break;
                }
                case 5: {
                    System.out.print("Enter the process number to start election: ");
                    id = sc.nextInt();
                    if (id >= 1 && id <= n) startElection(id);
                    else System.out.println("Invalid process number.");
                    break;
                }
                case 6: {
                    System.out.println("Exiting...");
                    return;
                }
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
