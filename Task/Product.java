package Task;

// product class
class Product implements ProductDetails {
    private String name;
    private double price;
    private String category;
    private String brandName;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() { 
        return name; 
    }
    public double getPrice() { 
        return price; 
    }
    public String getCategory() {
         return category; 
    }
    public String getBrandName() {
        return brandName;
    }
}
