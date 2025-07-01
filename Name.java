public class Name implements Comparable<Name> {
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }

    public String fullName() {
        return lastName + ", " + firstName;
    }

    public boolean match(Name other) {
        return this.firstName.equalsIgnoreCase(other.firstName) &&
                this.lastName.equalsIgnoreCase(other.lastName);
    }

    public boolean isLessThan(Name other) {
        int lastCompare = this.lastName.compareToIgnoreCase(other.lastName);
        if (lastCompare != 0)
            return lastCompare < 0;
        return this.firstName.compareToIgnoreCase(other.firstName) < 0;
    }

    @Override
    public String toString() {
        return fullName();
    }

    public static Name fromCSV(String string) {
        String[] parts = string.split(",");
        return new Name(parts[1].trim(), parts[0].trim()); // Assuming CSV format "LastName, FirstName"
    }

    @Override
    public int compareTo(Name other) {
        // Compare by last name first
        int lastNameComparison = this.lastName.compareToIgnoreCase(other.lastName);
        if (lastNameComparison != 0) {
            return lastNameComparison; // If last names are different, return that comparison
        }

        // If last names are the same, compare by first name
        return this.firstName.compareToIgnoreCase(other.firstName);
    }

    public String toCSV() {
        return lastName + ", " + firstName; // CSV format "LastName, FirstName"
    }
}
