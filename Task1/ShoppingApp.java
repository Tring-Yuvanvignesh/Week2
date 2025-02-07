package Task1;
import java.util.*;

// Main Class
public class ShoppingApp {
    static List<Product> productList = new ArrayList<>();
    static ShoppingCart cart = new ShoppingCart();
    static AdminMode admin = new AdminMode();
    static boolean flag = true;
    static boolean flag2 = true;
 
    public static void main(String[] args) {
 
       Scanner in = new Scanner(System.in);
       if (flag2) {  // for avoiding re initialize the cart after switching from admin mode
         ShoppingCart cart = new ShoppingCart();
         flag2 = false;
      }
       if (flag) { // one time initialize
          admin.initializeProducts();
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
                   catch(IllegalArgumentException e) {
                      System.out.println(e.getMessage());
                   }
                   catch(InputMismatchException e) {
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
                cart.removeItem(removeProductName, removequantity);
                break;
 
             case 4: // View Cart
                cart.displayCart();
                break;
 
             case 5: // Checkout
                boolean flag1 = true;
                for(Map.Entry<Product, Integer> cartProduct: cart.cartItems.entrySet()){
                  for(Product listProduct:productList){
                     flag1=false;
                     if( listProduct.getName().equals(cartProduct.getKey().getName())){
                        flag1 = true;
                        break;
                     }
                  }
                  if(flag1 == false){
                     System.out.println(cartProduct.getKey().getName()+" Not available Right Now Please remove "+cartProduct.getKey().getName()+" from your cart");
                     break;
                  }
                }
                if(flag1){
                  cart.checkout();
                  break;
                }
                else{
                  break;
                }
 
             case 6: // Exit
                System.out.println("Thank you for shopping ");
                return;
 
             case 7:
                System.out.println("Welcome to Admin mode");
                admin.switchMode();
                return;
 
             default:
                System.out.println(" Please enter a number between 1 and 6 ");
          }
       }
    }
 }