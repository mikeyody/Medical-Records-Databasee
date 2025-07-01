import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class PatientIdentity implements Comparable<PatientIdentity> {
    private Name name;
    private Date dateOfBirth;

    // Constructor
    public PatientIdentity(Name name, Date dateOfBirth) {
        if (name == null || dateOfBirth == null) {
            throw new IllegalArgumentException("Name and Date of Birth cannot be null");
        }
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    // Matches another PatientIdentity
    public boolean match(PatientIdentity other) {
        if (other == null) {
            return false;
        }
        return this.name.match(other.name) && this.dateOfBirth.equals(other.dateOfBirth);
    }

    // Implement the compareTo method
    @Override
    public int compareTo(PatientIdentity other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot compare to a null PatientIdentity");
        }

        // Compare by name first
        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }

        // If names are equal, compare by date of birth
        return this.dateOfBirth.compareTo(other.dateOfBirth);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        PatientIdentity other = (PatientIdentity) obj;
        return name.equals(other.name) && dateOfBirth.equals(other.dateOfBirth);
    }

    public boolean isLessThan(PatientIdentity other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot compare to a null PatientIdentity");
        }

        // Compare names first
        if (this.name.isLessThan(other.name)) {
            return true;
        } else if (this.name.match(other.name)) {
            // If names are equal, compare dates of birth
            return this.dateOfBirth.before(other.dateOfBirth);
        }

        // If the name is greater, return false
        return false;
    }

    // Convert to a readable string
    @Override
    public String toString() {
        return "Name: " + name.toString() + ", Date of Birth: " + dateOfBirth.toString();
    }

    // Convert to CSV format
    public String toCSV() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return name.toCSV() + "," + sdf.format(dateOfBirth);
    }

    // Factory method to create a PatientIdentity from CSV
    public static PatientIdentity fromCSV(String csvLine) throws ParseException {
        String[] parts = csvLine.split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid CSV line for PatientIdentity");
        }
        Name name = Name.fromCSV(parts[0]); // Assuming Name has fromCSV method
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = sdf.parse(parts[1]);
        return new PatientIdentity(name, dateOfBirth);
    }
}