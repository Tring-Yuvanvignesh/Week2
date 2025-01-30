
import java.util.*;

// Product Class
class Product {
    private String name;
    private double price;
    private String category;

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

}

class ShoppingCart {
    private Map<Product, Integer> cartItems = new HashMap<>(); // Storing product in cart

    public void addItem(Product product, int quantity) {
        cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity); // 0 + quantity (or) 
        System.out.println(quantity + " x " + product.getName() + " added to cart.");
    }

    public void removeItem(Product product, int quantity) {
        // Check the product is in the cart
        if (cartItems.containsKey(product)) {  
            int currentQty = cartItems.get(product);
            if (currentQty <= quantity) { // If remove quantity is higher then the purchased quantity
                cartItems.remove(product); // remove the item from cart
                System.out.println(product.getName() + " removed from cart.");
            } else {
                cartItems.put(product, currentQty - quantity);  // remove given quantity for item from cart
                System.out.println(quantity + " x " + product.getName() + " removed.");
            }
        } else {
            System.out.println(product.getName() + " not found in cart.");
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue(); // total = total + laptop.getPrice() * 2 => total = total + (1000 * 2)
        }
        return total;
    }

    public void displayCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("\nYour Shopping Cart:");
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(product.getName() + " x " + quantity + " = $" + (product.getPrice() * quantity));
        }
        System.out.println("Total Price: $" + calculateTotal());
    }

    public void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty. Add items before checkout.");
            return;
        }
        System.out.println("\nChecking out...");
        displayCart();
        System.out.println("Thank you for your purchase!");
        cartItems.clear();   // makes the cart empty
    }
}

// Main Class
public class ShoppingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        // Available Products List
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Laptop", 1000.0, "Electronics"));
        productList.add(new Product("Smartphone", 800.0, "Electronics"));
        productList.add(new Product("Book", 20.0, "Stationery"));
        productList.add(new Product("Headphones", 100.0, "Accessories"));

        System.out.println("Welcome to the Shopping App!");

        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;

            // Check the input is numeric
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1: // View Products
                    System.out.println("\nAvailable Products:");
                    for (int i = 0; i < productList.size(); i++) {
                        Product currentProduct = productList.get(i);
                        System.out.println((i + 1) + ". " + currentProduct.getName() + " - $"+currentProduct.getPrice()+" ["+currentProduct.getCategory()+"]");
                    }
                    break;

                case 2: // Add Product
                    System.out.println("\nEnter the product name to add:");
                    scanner.nextLine();
                    String addProductName = scanner.nextLine();
                    Product selectedProduct = null;
                    // Check the product available if available store the object in selectedProduct
                    for (Product p : productList) {  
                        if (p.getName().equalsIgnoreCase(addProductName)) {
                            selectedProduct = p;
                            break;  // break the loop
                        }
                    }
                    // If selectedProduct is null the product is not available
                    if (selectedProduct == null) {
                        System.out.println("Product not found!");
                        break; // break the case 2
                    }
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    cart.addItem(selectedProduct, quantity);
                    break;

                case 3: // Remove Product
                    System.out.println("\nEnter the product name to remove:");
                    scanner.nextLine();
                    String removeProductName = scanner.nextLine();
                    Product removeProduct = null;
                    // Check the product available if available store the object in removeProduct
                    for (Product p : productList) {
                        if (p.getName().equalsIgnoreCase(removeProductName)) {
                            removeProduct = p;
                            break; // break the case 3
                        }
                    }

                    // If removeProduct is null the product is not available in list
                    if (removeProduct == null) {
                        System.out.println("Product not found!");
                        break;
                    }
                    System.out.print("Enter quantity to remove: ");
                    int removeQuantity = scanner.nextInt();
                    cart.removeItem(removeProduct, removeQuantity);
                    break;

                case 4: // View Cart
                    cart.displayCart();
                    break;

                case 5: // Checkout
                    cart.checkout();
                    break;  // for continue Purchase

                case 6: // Exit
                    System.out.println("Thank you for shopping ");
                    return;

                default:
                    System.out.println(" Please enter a number between 1 and 6 ");
            }
        }
    }
}

