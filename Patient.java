public class Patient {
    private final PatientIdentity patientIdentity;

    public Patient(PatientIdentity patientIdentity) {
        if (patientIdentity == null) {
            throw new IllegalArgumentException("PatientIdentity cannot be null");
        }
        this.patientIdentity = patientIdentity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Patient other = (Patient) obj;
        return patientIdentity.equals(other.patientIdentity); // Delegate equality to PatientIdentity
    }

    public PatientIdentity getPatientIdentity() {
        return patientIdentity;
    }

    public String toCSV() {
        return patientIdentity.toCSV(); // Assume PatientIdentity has a toCSV method
    }

    public PatientIdentity getIdentity() {
        return this.getPatientIdentity();
    }

    public static Patient fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        if (csvLine == null || csvLine.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid CSV line");
        }

        try {
            PatientIdentity identity = PatientIdentity.fromCSV(csvLine);
            return new Patient(identity);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsiing CSV line: " + csvLine, e);
        }
    }

}