import java.util.List;

/**
 * ===== TOPIC: QUANTIFIERS =====
 *
 * Two checks are provided. Only checkAllBSIT (∀) is fully implemented
 * for this project, as required by the brief.
 *
 *   checkAllBSIT          -> Universal Quantifier   (∀ "for all")
 *   checkAnyIdAbove1000   -> Existential Quantifier (∃ "there exists")
 *
 * Reminder:
 *   - A ∀ check loops through the list and returns FALSE the moment
 *     it finds ONE student that does NOT satisfy the condition
 *     (that student is the COUNTER-EXAMPLE).
 *   - A ∃ check loops through the list and returns TRUE the moment
 *     it finds ONE student that DOES satisfy the condition
 *     (that student is the EXAMPLE).
 */
public class QuantifierCheck {

    // Universal Quantifier (∀) — "for all"
    public static void checkAllBSIT(List<Student> students) {

        // Step 1: Print the symbolic statement and its English translation.
        System.out.println("Symbolic : \u2200x (Course(x) = \"BSIT\")");
        System.out.println("English  : For ALL students, the course is BSIT.");

        // Step 2: Handle the edge case — if there are no students, it is
        //         vacuously TRUE (there is no one to violate the condition).
        if (students.isEmpty()) {
            System.out.println("Result   : TRUE (vacuously — no students to check)");
            return;
        }

        // Step 3: Loop through every student.
        //         A ∀ check fails the moment ONE student does NOT satisfy the condition.
        for (Student s : students) {
            if (!s.getCourse().equalsIgnoreCase("BSIT")) {
                // Found a counter-example — the statement is FALSE.
                System.out.println("Result   : FALSE");
                System.out.println("Counter-example: " + s);
                return;   // stop immediately after finding the first violation
            }
        }

        // Step 4: The loop finished with no violations — every student is BSIT.
        System.out.println("Result   : TRUE");
        System.out.println("Every student in the system is enrolled as BSIT");
    }

    // Existential Quantifier (∃) — "there exists"
    public static void checkAnyIdAbove1000(List<Student> students) {

        // Step 1: Print the symbolic statement and its English translation.
        System.out.println("Symbolic : \u2203x (ID(x) > 1000)");
        System.out.println("English  : THERE EXISTS a student whose ID is greater than 1000.");

        // Step 2: Loop through every student.
        //         A ∃ check succeeds the moment ONE student satisfies the condition.
        for (Student s : students) {
            if (s.getId() > 1000) {
                // Found a matching example — the statement is TRUE.
                System.out.println("Result   : TRUE");
                System.out.println("Example  : " + s);
                return;   // stop immediately after finding the first match
            }
        }

        // Step 3: The loop finished with no match — no such student exists.
        System.out.println("Result   : FALSE");
    }
}