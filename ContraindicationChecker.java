import java.io.*;
import java.util.Scanner;

class ContraindicationTable {
    private String[][] table;
    private int size;

    public ContraindicationTable(int capacity) {
        table = new String[capacity][2]; // Array-based hash table
        size = capacity;
    }

    // Hash Function 1
    private int hashFunction1(String key) {
        return Math.abs(key.hashCode()) % size;
    }

    // Hash Function 2
    private int hashFunction2(String key) {
        int sum = 0;
        for (char c : key.toCharArray()) {
            sum += c;
        }
        return sum % size;
    }

    public void addContraindication(String drug1, String drug2, boolean useHash2) {
        int index = useHash2 ? hashFunction2(drug1 + drug2) : hashFunction1(drug1 + drug2);
        table[index][0] = drug1;
        table[index][1] = drug2;
    }

    public boolean isContraindicated(String drug1, String drug2, boolean useHash2) {
        int index = useHash2 ? hashFunction2(drug1 + drug2) : hashFunction1(drug1 + drug2);
        return table[index][0] != null && table[index][0].equals(drug1) && table[index][1].equals(drug2);
    }

    public void loadFromFile(String filename, boolean useHash2) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    System.err.println("Skipping invalid entry: " + line);
                    continue;
                }
                addContraindication(parts[0].trim(), parts[1].trim(), useHash2);
            }
        } catch (IOException e) {
            System.err.println("Error loading contraindications: " + e.getMessage());
        }
    }
}

public class ContraindicationChecker {
    public static void main(String[] args) {
        ContraindicationTable table = new ContraindicationTable(100);

        table.loadFromFile("Interactions.csv", false); // Use first hash function

        // User input test
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first drug name: ");
        String drug1 = scanner.nextLine();
        System.out.print("Enter second drug name: ");
        String drug2 = scanner.nextLine();

        if (table.isContraindicated(drug1, drug2, false)) {
            System.out.println("Warning: These drugs are contraindicated!");
        } else {
            System.out.println("No contraindications detected.");
        }
    }
}
