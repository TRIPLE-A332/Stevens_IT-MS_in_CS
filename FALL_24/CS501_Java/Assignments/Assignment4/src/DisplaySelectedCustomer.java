// Name: Ali Abdullah Ahmad
// CWID: 20031246

import java.io.*;
import java.util.Scanner;

public class DisplaySelectedCustomer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Customer ID to search: ");
        String searchId = scanner.nextLine();

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("customer_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(searchId)) {
                    System.out.println("Record Found: " + line);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Customer with ID " + searchId + " not found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
