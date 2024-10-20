package assignment_2;

/**
 * Widget class that models a sellable widget.
 * Author: [Your Name]
 * CWID: [Your CWID]
 */
public class Widget {
    // Data fields
    private double price;
    private boolean sold;
    private static double income = 0.0;

    // Constructor
    public Widget(double price) {
        this.price = price;
        this.sold = false;  // Widgets are unsold initially
    }

    // Sell the widget if it hasn't been sold yet
    public void sell() {
        if (!this.sold) {
            this.sold = true;
            income += this.price;  // Add the price to total income
        }
    }

    // Getter for price
    public double getPrice() {
        return this.price;
    }

    // Setter for price (only if the widget hasn't been sold)
    public void setPrice(double price) {
        if (!this.sold) {
            this.price = price;
        }
    }

    // Getter for sold (boolean convention: isSold)
    public boolean isSold() {
        return this.sold;
    }

    // Getter for total income (static method)
    public static double getIncome() {
        return income;
    }
}
