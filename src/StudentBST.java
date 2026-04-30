import java.util.ArrayList;
import java.util.List;

/**
 * ===== TOPIC: TREES (Binary Search Tree) =====
 *
 * A simple Binary Search Tree that stores Student records
 * using the Student ID as the key.
 *
 * BST rule: for every node,
 *   - all IDs in the LEFT subtree are SMALLER than the node's ID
 *   - all IDs in the RIGHT subtree are LARGER than the node's ID
 */
// Binary Search Tree
public class StudentBST {

    // Each Node holds a Student plus references to left and right children.
    private static class Node {
        Student student;
        Node left;
        Node right;

        Node(Student student) {
            this.student = student;
        }
    }

    private Node root;

    // ---------- ADD ----------
    public boolean add(Student s) {
        // If the tree is empty, make this student the root.
        if (root == null) {
            root = new Node(s);
            return true;
        }
        // Otherwise, use the recursive helper to find the right spot.
        return addRecursive(root, s);
    }

    // Recursive helper: walk the tree and insert at the correct empty spot.
    private boolean addRecursive(Node current, Student s) {
        if (s.getId() == current.student.getId()) {
            // Duplicate ID — do not insert.
            return false;
        } else if (s.getId() < current.student.getId()) {
            // New ID is smaller -> go LEFT.
            if (current.left == null) {
                current.left = new Node(s);
                return true;
            } else {
                return addRecursive(current.left, s);
            }
        } else {
            // New ID is larger -> go RIGHT.
            if (current.right == null) {
                current.right = new Node(s);
                return true;
            } else {
                return addRecursive(current.right, s);
            }
        }
    }

    // ---------- SEARCH ----------
    public Student search(int id) {
        return searchRecursive(root, id);
    }

    // Recursive helper: follow BST rule until we find the ID or hit null.
    private Student searchRecursive(Node current, int id) {
        if (current == null) {
            // Reached an empty spot — student not found.
            return null;
        }
        if (id == current.student.getId()) {
            return current.student;
        } else if (id < current.student.getId()) {
            return searchRecursive(current.left, id);
        } else {
            return searchRecursive(current.right, id);
        }
    }

    // ---------- IN-ORDER DISPLAY ----------
    public void displayInOrder() {
        if (root == null) {
            System.out.println("(no students yet)");
            return;
        }
        inOrderRecursive(root);
    }

    // Recursive helper: LEFT -> current -> RIGHT (prints sorted by ID).
    private void inOrderRecursive(Node current) {
        if (current == null) return;
        inOrderRecursive(current.left);          // visit left subtree first
        System.out.println(current.student);     // print current node
        inOrderRecursive(current.right);         // visit right subtree last
    }

    // ---------- HELPER for the Quantifier module ----------
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        collectInOrder(root, list);
        return list;
    }

    // Same traversal as displayInOrder but adds to a list instead of printing.
    private void collectInOrder(Node current, List<Student> list) {
        if (current == null) return;
        collectInOrder(current.left, list);
        list.add(current.student);
        collectInOrder(current.right, list);
    }

    public boolean isEmpty() {
        return root == null;
    }
}