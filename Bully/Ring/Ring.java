import java.util.*;

public class Ring {
    int max_processes, coordinator;
    boolean processes[];
    ArrayList<Integer> pid;

    Ring(int max) {
        coordinator = max;
        max_processes = max;
        pid = new ArrayList<>();
        processes = new boolean[max];

        for (int i = 0; i < max; i++) {
            processes[i] = true;
            System.out.println("P" + (i + 1) + " created");
        }
        System.out.println("P" + coordinator + " is the coordinator");
    }

    void display() {
        for (int i = 0; i < max_processes; i++) {
            if (processes[i]) {
                System.out.println("P" + (i + 1) + " [UP]");
            } else {
                System.out.println("P" + (i + 1) + " [DOWN]");
            }
        }
        System.out.println("P" + coordinator + " is the coordinator ");
    }

    void upProcess(int process_id) {
        if (processes[process_id - 1]) {
            System.out.println("Process P" + process_id + " is already up");
        } else {
            processes[process_id - 1] = true;
            System.out.println("Process P" + process_id + " is up.");
        }
    }

    void downProcess(int process_id) {
        if (!processes[process_id - 1]) {
            System.out.println("Process P" + process_id + " is already down");
        } else {
            processes[process_id - 1] = false;
            System.out.println("Process P" + process_id + " is down.");
        }
    }

    void election(int process_id) {
        if (!processes[process_id - 1]) {
            System.out.println("Process P" + process_id + " is down. Cannot initiate election.");
            return;
        }

        System.out.println("Election initiated by P" + process_id);
        pid.clear();
        int temp = process_id % max_processes;
        pid.add(process_id);

        while (temp != (process_id - 1)) {
            if (processes[temp]) {
                pid.add(temp + 1);
                System.out.println("P" + (temp + 1) + " passes message: " + pid);
            }
            temp = (temp + 1) % max_processes;
        }

        coordinator = Collections.max(pid);
        System.out.println("New Coordinator is P" + coordinator);
    }

    public static void main(String[] args) {
        Ring ring = null;
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n1. Create Processes");
            System.out.println("2. Display Processes");
            System.out.println("3. Up a process");
            System.out.println("4. Down a process");
            System.out.println("5. Run election algorithm");
            System.out.println("6. Exit Program");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the total number of processes: ");
                    int max = sc.nextInt();
                    ring = new Ring(max);
                    break;
                case 2:
                    if (ring != null)
                        ring.display();
                    else
                        System.out.println("Please create processes first.");
                    break;
                case 3:
                    System.out.print("Enter the process to up: ");
                    int upId = sc.nextInt();
                    ring.upProcess(upId);
                    break;
                case 4:
                    System.out.print("Enter the process to down: ");
                    int downId = sc.nextInt();
                    ring.downProcess(downId);
                    break;
                case 5:
                    System.out.print("Enter the process which will initiate election: ");
                    int initiator = sc.nextInt();
                    ring.election(initiator);
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
