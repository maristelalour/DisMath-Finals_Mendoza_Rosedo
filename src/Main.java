import java.util.Scanner;

/**
 * Main entry point. Builds a small console menu that ties together:
 *   - StudentBST       (Binary Search Tree)        - YOU implement
 *   - CampusGraph      (Dijkstra's shortest path)  - YOU implement
 *   - QuantifierCheck  (∀ / ∃ logical queries)     - YOU implement
 *
 * (You don't need to change this file. Focus on the three classes above.)
 *
 * A few sample students are pre-loaded so the program has data
 * to work with the f1irst time it runs.
 */
public class Main {

    private static final StudentBST bst = new StudentBST();
    private static final CampusGraph campus = new CampusGraph();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        preloadSampleStudents();

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    searchStudent();
                    break;
                case "3":
                    displayStudents();
                    break;
                case "4":
                    findShortestPath();
                    break;
                case "5":
                    runQuantifierCheck();
                    break;
                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 0-5.");
            }
        }
        scanner.close();
    }

    private static void preloadSampleStudents() {
        bst.add(new Student(500,  "Juan Dela Cruz", "BSIT"));
        bst.add(new Student(250,  "Maria Santos",   "BSIT"));
        bst.add(new Student(750,  "Pedro Reyes",    "BSCS"));
        bst.add(new Student(1200, "Ana Garcia",     "BSIT"));
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("===== MINI STUDENT RECORDS & CAMPUS MAP =====");
        System.out.println("[1] Add Student");
        System.out.println("[2] Search Student by ID");
        System.out.println("[3] Display All Students (In-order)");
        System.out.println("[4] Find Shortest Path Between Buildings");
        System.out.println("[5] Run Logical Check (Quantifier)");
        System.out.println("[0] Exit");
        System.out.print("Select option: ");
    }

    // ---------- [1] Add ----------
    private static void addStudent() {
        try {
            System.out.print("Student ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Course: ");
            String course = scanner.nextLine().trim();

            boolean added = bst.add(new Student(id, name, course));
            if (added) {
                System.out.println("Student added successfully.");
            } else {
                System.out.println("A student with ID " + id + " already exists.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }

    // ---------- [2] Search ----------
    private static void searchStudent() {
        if (bst.isEmpty()) {
            System.out.println("No students in the system yet.");
            return;
        }
        try {
            System.out.print("Student ID to search: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            Student s = bst.search(id);
            if (s != null) {
                System.out.println("Found: " + s);
            } else {
                System.out.println("No student found with ID " + id + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }

    // ---------- [3] Display (in-order) ----------
    private static void displayStudents() {
        System.out.println("--- All Students (sorted by ID, in-order traversal) ---");
        bst.displayInOrder();
    }

    // ---------- [4] Shortest Path ----------
    private static void findShortestPath() {
        campus.displayMap();
        try {
            System.out.print("\nEnter source building number: ");
            int src = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter destination building number: ");
            int dst = Integer.parseInt(scanner.nextLine().trim());

            String[] buildings = campus.getBuildings();
            if (buildings == null
                    || src < 0 || src >= buildings.length
                    || dst < 0 || dst >= buildings.length) {
                System.out.println("Invalid building number.");
                return;
            }

            campus.shortestPath(src, dst);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
        }
    }

    // ---------- [5] Quantifier Check ----------
    private static void runQuantifierCheck() {
        System.out.println("[A] Universal (∀) - Are ALL students BSIT?");
        System.out.println("[B] Existential (∃) - Is there a student with ID > 1000?");
        System.out.print("Choose A or B: ");
        String choice = scanner.nextLine().trim().toUpperCase();

        if (choice.equals("A")) {
            QuantifierCheck.checkAllBSIT(bst.getAllStudents());
        } else if (choice.equals("B")) {
            QuantifierCheck.checkAnyIdAbove1000(bst.getAllStudents());
        } else {
            System.out.println("Invalid choice.");
        }
    }
}
