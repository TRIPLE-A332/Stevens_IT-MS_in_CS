// Name: Ali Abdullah Ahmad
// CWID: 20031246

import java.io.*;
import java.util.Scanner;

public class DisplaySelectedCustomersByBalance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter minimum balance to search: ");
        double minBalance = scanner.nextDouble();

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("customer_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                double balance = Double.parseDouble(data[3]);
                if (balance > minBalance) {
                    System.out.println(line);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No customers with a balance greater than " + minBalance + " found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
