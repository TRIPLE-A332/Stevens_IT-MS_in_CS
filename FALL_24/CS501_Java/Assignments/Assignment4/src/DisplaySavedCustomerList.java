// Name: Ali Abdullah Ahmad
// CWID: 20031246

import java.io.*;

public class DisplaySavedCustomerList {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("customer_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
