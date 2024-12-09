// Name: Ali Abdullah Ahmad
// CWID: 20031246

import java.io.*;
import java.util.Scanner;

public class DisplaySelectedCustomersByName {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Last Name to search: ");
        String searchName = scanner.nextLine();

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("customer_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[2].equalsIgnoreCase(searchName)) {
                    System.out.println(line);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No customers with last name " + searchName + " found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
