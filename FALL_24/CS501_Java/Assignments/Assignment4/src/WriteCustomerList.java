// Name: Ali Abdullah Ahmad
// CWID: 20031246

import java.io.*;
import java.util.Scanner;

public class WriteCustomerList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (PrintWriter writer = new PrintWriter(new FileWriter("customer_data.txt", true))) {
            String choice;
            do {
                System.out.print("Enter Customer ID: ");
                String id = scanner.nextLine();

                System.out.print("Enter First Name: ");
                String firstName = scanner.nextLine();

                System.out.print("Enter Last Name: ");
                String lastName = scanner.nextLine();

                System.out.print("Enter Balance Owed: ");
                double balance = scanner.nextDouble();
                scanner.nextLine(); // Consume newline

                writer.println(id + "," + firstName + "," + lastName + "," + balance);

                System.out.print("Do you want to add another record? (yes/no): ");
                choice = scanner.nextLine().toLowerCase();
            } while (choice.equals("yes"));

            System.out.println("Customer records saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
