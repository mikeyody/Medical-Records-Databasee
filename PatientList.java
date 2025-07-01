import java.util.Iterator;

public class PatientList implements Iterable<Patient> {
    private BinarySearchTree<PatientIdentity> patientTree;

    public PatientList() {
        this.patientTree = new BinarySearchTree<>();
    }

    // Add a patient
    public boolean add(Patient patient) {
        if (patient == null)
            return false;
        return patientTree.add(patient.getPatientIdentity());
    }

    // Find a patient
    public Patient find(PatientIdentity identity) {
        if (identity == null)
            return null;
        PatientIdentity foundIdentity = patientTree.find(identity);
        return foundIdentity == null ? null : new Patient(foundIdentity);
    }

    public boolean contains(PatientIdentity identity) {
        return patientTree.contains(identity);
    }

    // Implement Iterator
    @Override
    public Iterator<Patient> iterator() {
        Iterator<PatientIdentity> treeIterator = patientTree.iterator();
        return new Iterator<Patient>() {
            @Override
            public boolean hasNext() {
                return treeIterator.hasNext();
            }

            @Override
            public Patient next() {
                return new Patient(treeIterator.next());
            }
        };
    }
}
