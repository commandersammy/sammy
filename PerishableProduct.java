import java.util.Date;

public class PerishableProduct extends Product {
    private Date expiryDate;

    // Default Constructor
    public PerishableProduct() {
        super();  // Calls the Product constructor
        this.expiryDate = new Date();  // Default expiry date (current date)
    }

    // Parameterized Constructor
    public PerishableProduct(String SKU, String productName, double unitCost, double salePrice,
                             int quantityOnHand, int quantityRequired, String specialInstructions, Date expiryDate) {
        super(SKU, productName, unitCost, salePrice, quantityOnHand, quantityRequired, specialInstructions);
        this.expiryDate = expiryDate;
    }

    // Getter and Setter for expiryDate
    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }

    // Overriding displayProductInfo to include expiry date
    @Override
    public void displayProductInfo() {
        super.displayProductInfo();  // Call the base class method to display the common info
        System.out.println("Expiry Date: " + expiryDate);
    }
}
