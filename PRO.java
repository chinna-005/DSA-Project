package K22UG;
import java.util.*;

// Define the ProcessEntity class
class ProcessEntity {
    int processID;
    String processName;
    int executionTime;

    public ProcessEntity(int processID, String processName, int executionTime) {
        this.processID = processID;
        this.processName = processName;
        this.executionTime = executionTime;
    }
}

// Define a class for the BST node
class TreeNode {
    ProcessEntity data;
    TreeNode left;
    TreeNode right;

    public TreeNode(ProcessEntity data) {
        this.data = data;
        left = null;
        right = null;
    }
}

// Define a Priority Queue based on Binary Search Tree
class SJFQueue {
    private TreeNode root;

    // Insert a process into the BST
    public void insert(ProcessEntity process) {
        root = insertRec(root, process);
    }

    // Helper function to insert a process recursively
    private TreeNode insertRec(TreeNode root, ProcessEntity process) {
        if (root == null) {
            return new TreeNode(process);
        }

        if (process.executionTime < root.data.executionTime) {
            root.left = insertRec(root.left, process);
        } else if (process.executionTime > root.data.executionTime) {
            root.right = insertRec(root.right, process);
        }

        return root;
    }

    // Perform an inorder traversal to get processes in SJF order
    public void inorder() {
        inorderRec(root);
    }

    // Helper function to perform inorder traversal recursively
    private void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println("Process ID: " + root.data.processID + ", Process Name: " + root.data.processName + ", Execution Time: " + root.data.executionTime);
            inorderRec(root.right);
        }
    }
}

public class PRO {
    public static void main(String[] args) {
        // Create a SJFQueue
        SJFQueue sjfQueue = new SJFQueue();
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the number of processes
        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        // Prompt the user to enter processes
        for (int i = 1; i <= numProcesses; i++) {
            System.out.print("Enter Process Name for Process " + i + ": ");
            String processName = scanner.nextLine();

            System.out.print("Enter Execution Time for Process " + i + ": ");
            int executionTime = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            // Generate a unique process ID (you can modify this logic if needed)
            int processID = new Random().nextInt(1000);

            // Create a new ProcessEntity and add it to the SJFQueue
            ProcessEntity process = new ProcessEntity(processID, processName, executionTime);
            sjfQueue.insert(process);
        }

        // Print processes in SJF order
        System.out.println("Shortest Job First (SJF) Scheduling Order:");
        sjfQueue.inorder();
        scanner.close();
    }
}
