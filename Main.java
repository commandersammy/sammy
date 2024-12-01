import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Create a regular Product
        Product product1 = new Product("25", "Laptop", 500.0, 700.0, 10, 5, "Handle with care.");
        product1.displayProductInfo();  // Display product info

        // Create a PerishableProduct with an expiry date
        Date expiryDate = new Date();  // Set current date as expiry date
        PerishableProduct perishableProduct1 = new PerishableProduct("54", "Milk", 0.5, 1.0, 50, 30, "Keep refrigerated.", expiryDate);
        perishableProduct1.displayProductInfo();  // Display perishable product info
    }
}
