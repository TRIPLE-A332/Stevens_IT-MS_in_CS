package assignment_2;

public class TestWidget {
	
	// The current available widgets
	private Widget w0;
	private Widget w1;
	private Widget w2;
	private Widget w3;
	private Widget w4;
	
	/**
	 * Construct widgets with initial prices.
	 */
	private TestWidget() {
		// Standard widgets
		w0 = new Widget(1.99);
		w1 = new Widget(1.99);
		w2 = new Widget(1.99);
		// Deluxe widgets
		w3 = new Widget(4.99);
		w4 = new Widget(4.99);
	}
	
	private void sellSome() {
		w1.sell();
		w2.sell();
		w4.sell();
	}
	
	private void sellMore() {
		w0.sell();
	}
	
	/**
	 * Apply a discount on the price of unsold widgets.
	 * 
	 * @param percent the percent of the discount
	 */
	private void applyDiscount(int percent) {
		double discount = 1.0 - 0.01 * percent;
		if (!w0.isSold()) {
			w0.setPrice(discount * w0.getPrice());
		}
		if (!w1.isSold()) {
			w1.setPrice(discount * w1.getPrice());
		}
		if (!w2.isSold()) {
			w2.setPrice(discount * w2.getPrice());
		}
		if (!w3.isSold()) {
			w3.setPrice(discount * w3.getPrice());
		}
		if (!w4.isSold()) {
			w4.setPrice(discount * w4.getPrice());
		}
	}
	
	private static void printPrice(double price) {
		System.out.printf("%5.2f\n", price);
	}
	
	private void reportSales() {
		System.out.println("\nStandard Widgets");
		System.out.println("----------------");
		if (w0.isSold()) {
			printPrice(w0.getPrice());
		}
		if (w1.isSold()) {
			printPrice(w1.getPrice());
		}
		if (w2.isSold()) {
			printPrice(w2.getPrice());
		}
		System.out.println("\nDeluxe Widgets");
		System.out.println("----------------");
		if (w3.isSold()) {
			printPrice(w3.getPrice());
		}
		if (w4.isSold()) {
			printPrice(w4.getPrice());
		}
		System.out.println("\nTotal Income");
		System.out.println("----------------");
		printPrice(Widget.getIncome());
	}
	
	public static void main(String[] args) {
		TestWidget test = new TestWidget();
		test.sellSome();
		test.reportSales();
		test.applyDiscount(10);
		System.out.println("\nAfter discount:");
		test.sellMore();
		test.reportSales();
	}

}