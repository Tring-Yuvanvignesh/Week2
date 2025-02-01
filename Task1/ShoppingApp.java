package Task1;
import java.util.*;

// Main Class
public class ShoppingApp {
    private static List<Product> productList = new ArrayList<>();
    private static ShoppingCart cart = new ShoppingCart();
    private static boolean flag = true;
 
    public static void main(String[] args) {
 
       Scanner in = new Scanner(System.in);
       ShoppingCart cart = new ShoppingCart();
       if (flag) { // one time initialize
          initializeProducts();
          flag = false;
       }
       // Available Products List
 
       System.out.println("Welcome to the Shopping App!");
 
       while (true) {
          System.out.println(" \n---  ----  ----  ----  ----  ----  ---");
          System.out.println("MENU:");
          System.out.println("1. View Products");
          System.out.println("2. Add Product to Cart");
          System.out.println("3. Remove Product from Cart");
          System.out.println("4. View Cart");
          System.out.println("5. Checkout");
          System.out.println("6. Exit");
          System.out.println("7. Change mode");
          System.out.println(" ---  ----  ----  ----  ----  ----  ---");
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
                if (productList.isEmpty()) { // if no product
                   System.out.println("No products available.");
                   break;
                }
                System.out.println("\nAvailable Products:");
                for (int i = 0; i < productList.size(); i++) {
                   Product currentProduct = productList.get(i);
                   System.out.println((i + 1) + ". " + currentProduct.getName() + " - RS " + currentProduct.getPrice()
                         + " [" + currentProduct.getCategory() + "]");
                }
                break;
 
             case 2: // Add Product
                if (productList.isEmpty()) { // if no product
                   System.out.println("No products available.");
                   break;
                }
                System.out.println("\nEnter the product name to add:");
                in.nextLine();
                String addProductName = in.nextLine();
                Product selectedProduct = null;
                // Check the product available if available store the object in selectedProduct
                for (Product p : productList) {
                   if (p.getName().equalsIgnoreCase(addProductName)) {
                      selectedProduct = p;
                      break; // break the loop
                   }
                }
                // If selectedProduct is null the product is not available
                if (selectedProduct == null) {
                   System.out.println("Product not found!");
                   break; // break the case 2
                }
                int quantity = 0;
                while (true) {
                   System.out.print("Enter quantity: ");
                   try{
                      quantity = in.nextInt();
                      in.nextLine();
                      if(quantity < 0){
                         throw new IllegalArgumentException("Quantity must be positive");
                      }
                      break;
                   }
                   catch(IllegalArgumentException e){
                      System.out.println(e.getMessage());
                   }
                   catch(InputMismatchException e){
                      System.out.println("Invalid Quantity! Please enter a valid numeric value.");
                      in.nextLine();
                   }
                }
                cart.addItem(selectedProduct, quantity);
                break;
 
             case 3: // Remove Product
                if(cart.cartItems.isEmpty()){ // Check the cart is empty or not
                   System.err.println("Your cart is empty");
                   break;
                }
                System.out.println("\nEnter the product name to remove:");
                in.nextLine();
                String removeProductName = in.nextLine();
                Product removeProduct = null;
                // Check the product available, if available store the object in removeProduct
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
                int removequantity = 0;
                while (true) {
                   System.out.println("Enter quantity to remove: ");
                   try{
                      removequantity = in.nextInt();
                      in.nextLine();
                      if(removequantity < 0){
                         throw new IllegalArgumentException("Quantity must be positive");
                      }
                      break;
                   }
                   catch(IllegalArgumentException e){
                      System.out.println(e.getMessage());
                   }
                   catch(InputMismatchException e){
                      System.out.println("Invalid Quantity! Please enter a valid numeric value.");
                      in.nextLine();
                   }
                }
                cart.removeItem(removeProduct, removequantity);
                break;
 
             case 4: // View Cart
                cart.displayCart();
                break;
 
             case 5: // Checkout
                cart.checkout();
                break; // for continue Purchase
 
             case 6: // Exit
                System.out.println("Thank you for shopping ");
                return;
 
             case 7:
                System.out.println("Welcome to Admin mode");
                switchMode();
                return;
 
             default:
                System.out.println(" Please enter a number between 1 and 6 ");
          }
       }
    }
 
    private static void switchMode() {
       Scanner in = new Scanner(System.in);
       while (true) {
          System.out.println("\n--- Admin Mode ---");
          System.out.println("1. Add Product to Product List");
          System.out.println("2. Remove Product from Product List");
          System.out.println("3. Exit Switch Mode");
          System.out.println("--- --- --- --- --- ---");
          System.out.print("Enter your choice: ");
 
          int choice = in.nextInt();
          in.nextLine();
 
          if (choice == 1) {
             System.out.print("Enter product name: ");
             String name = in.nextLine();
             System.out.print("Enter price: ");
             double price = 0;
             while (true) {
                 try {
                     System.out.print("Enter price: ");
                     price = in.nextDouble();
                     in.nextLine(); 
                     if (price < 0) {
                         throw new IllegalArgumentException("Price cannot be negative.");
                     }
                     break; // Exit loop if valid price
                 } catch (InputMismatchException e) {
                     System.out.println("Invalid price! Please enter a valid numeric value.");
                     in.nextLine(); // Clear the invalid input
                 } catch (IllegalArgumentException e) {
                     System.out.println(e.getMessage());
                 }
             }
             String category = "";
             while(true){
                try{
                   System.out.print("Enter category: ");
                   category = in.nextLine();
                   if(!isValid(category)){
                      throw new IllegalArgumentException("Category can only contain alphabets");
                   }
                   break;
                }
                catch(IllegalArgumentException e){
                   System.out.println(e.getMessage());
                }
             }
             boolean flag = false;
             for(Product currProduct:productList){
                if(currProduct.getName().equalsIgnoreCase(name)){  // Check the product already in the product list
                   currProduct.setPrice(price); // updatating the details
                   currProduct.setCategory(category);
                   System.out.println("Product Altered successfully");
                   flag = true;
                   break;
                }
             }
             if (!flag) {
                productList.add(new Product(name, price, category)); // only execute if the item is new
                System.out.println("Product added successfully!");
             }  
          } else if (choice == 2) {
             System.out.print("Enter product name to remove: ");
             String removeProductName = in.nextLine();
             Product removeProduct = null;
             // Check the product available, if available store the object in removeProduct
             for (Product p : productList) {
                if (p.getName().equalsIgnoreCase(removeProductName)) {
                   removeProduct = p;
                   productList.remove(p);
                   System.out.println("Product successfully removed");
                   break;
                }
             }
             // If removeProduct is null the product is not available in list
             if (removeProduct == null) {
                System.out.println("Product not found!");
             }
          } else {
             main(null);
             break;
          }
       }
    }
 
    private static boolean isValid(String category) {
       for( int i=0;i<category.length();i++) {
          if( (int)category.charAt(i) < (int) 'A' || (int)category.charAt(i) > (int)'z' || ((int)category.charAt(i) > (int)'Z' && (int)category.charAt(i) < (int)'a') ){
             return false;
          }
       }
       return true;
    }
 
    private static void initializeProducts() {
       productList.add(new Product("Laptop", 29999.0, "Electronics"));
       productList.add(new Product("Smart Watch", 1499.0, "Gadgets"));
       productList.add(new Product("Geared Cycle", 8000.0, "Sports"));
       productList.add(new Product("Smartphone", 25000.0, "Electronics"));
    }
 }