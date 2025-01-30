package Task;
import java.util.*;

// Main Class
public class ShoppingApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        // Available Products List
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Laptop", 1000.0, "Electronics"));
        productList.add(new Product("Smartphone", 800.0, "Electronics"));
        productList.add(new Product("Book", 20.0, "Stationery"));
        productList.add(new Product("Headphones", 100.0, "Accessories"));

        System.out.println("Welcome to the Shopping App!");

        while (true) {
            System.out.println(" \n--- ---- ---- ---- ---- ---- ---");
            System.out.println("MENU:");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.println(" --- ---- ---- ---- ---- ---- ---");
            System.out.print("Enter your choice: ");

            int choice;

            // Check the input is numeric
            try {
                choice = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                in.next();
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
                    in.nextLine();
                    String addProductName = in.nextLine();
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
                    int quantity = in.nextInt();
                    cart.addItem(selectedProduct, quantity);
                    break;

                case 3: // Remove Product
                    System.out.println("\nEnter the product name to remove:");
                    in.nextLine();
                    String removeProductName = in.nextLine();
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
                    int removeQuantity = in.nextInt();
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

