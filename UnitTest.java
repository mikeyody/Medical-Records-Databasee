import java.util.Date;
import java.util.Iterator;

public class UnitTest {
    // Entry point for a runtime config to run all unit tests
    public static void main(String[] args) {
        System.out.println("Running all unit tests");
        doUnitTests();
    }

    public static void doUnitTests() {
        int testCount = 0, failCount = 0;

        System.out.println("Running unit tests for PatientSystem");

        // Create Names and Patient Identifiers
        Name name1 = new Name("John", "Doe");
        Name name2 = new Name("Jane", "Smith");

        Date dob1 = new Date(85, 4, 15); // May 15, 1985
        Date dob2 = new Date(90, 7, 20); // August 20, 1990

        PatientIdentity id1 = new PatientIdentity(name1, dob1);
        PatientIdentity id2 = new PatientIdentity(name2, dob2);

        Patient patient1 = new Patient(id1);
        Patient patient2 = new Patient(id2);

        // Testing PatientList
        PatientList patientList = new PatientList();

        // Test adding patients to PatientList
        testCount++;
        if (!patientList.add(patient1)) {
            System.out.println("FAIL: Patient add test failed");
            failCount++;
        }

        testCount++;
        if (!patientList.add(patient2)) {
            System.out.println("FAIL: Patient add test failed");
            failCount++;
        }

        // Test finding patients in PatientList
        testCount++;
        if (patientList.find(id1) == null) {
            System.out.println("FAIL: Patient find test failed");
            failCount++;
        }

        testCount++;
        if (patientList.find(id2) == null) {
            System.out.println("FAIL: Patient find test failed");
            failCount++;
        }

        testCount++;
        if (patientList.find(new PatientIdentity(new Name("Bob", "Smith"), new Date(90, 7, 20))) != null) {
            System.out.println("FAIL: Patient find test failed");
            failCount++;
        }

        // Test iterator for PatientList
        Iterator<Patient> iterator = patientList.iterator();
        testCount++;
        if (!iterator.hasNext() || !iterator.next().equals(patient1)) {
            System.out.println("FAIL: PatientList iterator test failed");
            failCount++;
        }

        testCount++;
        if (!iterator.hasNext() || !iterator.next().equals(patient2)) {
            System.out.println("FAIL: PatientList iterator test failed");
            failCount++;
        }

        testCount++;
        if (iterator.hasNext()) {
            System.out.println("FAIL: PatientList iterator test failed");
            failCount++;
        }

        // Testing BinarySearchTree traversal
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(10);
        tree.add(5);
        tree.add(15);

        testCount++;
        String traversalResult = tree.getInorderTraversal();
        String expectedResult = "5 10 15";
        if (!traversalResult.equals(expectedResult)) {
            System.out.println("FAIL: BinarySearchTree in-order traversal test failed");
            failCount++;
        }

        System.out.printf("%d tests run, %d failed\n", testCount, failCount);
    }

    public class BinarySearchTreeTest {
        public static void main(String[] args) {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>();
            System.out.println("Adding elements...");
            tree.add(10);
            tree.add(5);
            tree.add(15);

            // Test contains
            assert tree.contains(10) : "Test failed: 10 should exist in the tree.";
            assert tree.contains(5) : "Test failed: 5 should exist in the tree.";
            assert !tree.contains(20) : "Test failed: 20 should not exist in the tree.";

            String traversalResult = tree.getInorderTraversal();
            String expectedResult = "5 10 15";
            assert traversalResult.equals(expectedResult)
                    : "Test failed: Expected " + expectedResult + " but got " + traversalResult;

            System.out.println("Traversal:");
            tree.inorderTraversal();

            System.out.println("\nAll tests passed!");
        }
    }
}