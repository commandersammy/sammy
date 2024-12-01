public class Product {
    // Attributes
    private String SKU;
    private String productName;
    private double unitCost;
    private double salePrice;
    private int quantityOnHand;
    private int quantityRequired;
    private final String specialInstructions;

    // Default Constructor
    public Product() {
        this.SKU = "00000000";
        this.productName = "Unknown Product";
        this.unitCost = 0.0;
        this.salePrice = 0.0;
        this.quantityOnHand = 0;
        this.quantityRequired = 0;
        this.specialInstructions = "None";
    }

    // Parameterized Constructor
    public Product(String SKU, String productName, double unitCost, double salePrice,
                   int quantityOnHand, int quantityRequired, String specialInstructions) {
        this.SKU = SKU;
        this.productName = productName;
        this.unitCost = unitCost;
        this.salePrice = salePrice;
        this.quantityOnHand = quantityOnHand;
        this.quantityRequired = quantityRequired;
        this.specialInstructions = specialInstructions;
    }

    // Getters and Setters
    public String getSKU() { return SKU; }
    public void setSKU(String SKU) { this.SKU = SKU; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double getUnitCost() { return unitCost; }
    public void setUnitCost(double unitCost) { this.unitCost = unitCost; }

    public double getSalePrice() { return salePrice; }
    public void setSalePrice(double salePrice) { this.salePrice = salePrice; }

    public int getQuantityOnHand() { return quantityOnHand; }
    public void setQuantityOnHand(int quantityOnHand) { this.quantityOnHand = quantityOnHand; }

    public int getQuantityRequired() { return quantityRequired; }
    public void setQuantityRequired(int quantityRequired) { this.quantityRequired = quantityRequired; }

    public String getSpecialInstructions() { return specialInstructions; }

    // Method to display product information
    public void displayProductInfo() {
        System.out.println("Product Information:");
        System.out.println("SKU: " + SKU);
        System.out.println("Product Name: " + productName);
        System.out.println("Unit Cost: $" + unitCost);
        System.out.println("Sale Price: $" + salePrice);
        System.out.println("Quantity on Hand: " + quantityOnHand);
        System.out.println("Quantity Needed: " + quantityRequired);
        System.out.println("Special Instructions: " + specialInstructions);
    }
}
