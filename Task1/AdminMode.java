package Task1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMode extends ShoppingApp {

    ShoppingApp app = new ShoppingApp();

    public void switchMode() {
       Scanner in = new Scanner(System.in);
       while (true) {
          System.out.println("\n--- Admin Mode ---");
          System.out.println("1. Add Product to Product List");
          System.out.println("2. Remove Product from Product List");
          System.out.println("3. Exit Switch Mode");
          System.out.println("--- --- --- --- --- ---");
          System.out.print("Enter your choice: ");
        
          int choice;
          try {
             choice = in.nextInt();
          } catch (InputMismatchException e) {
             System.out.println("Please enter a valid number.");
             in.next();
             continue;
          }
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
             if(productList.isEmpty()){  // If no product available in productList
                System.out.println("No products available");
                continue;
             }
             System.out.println("Enter product name to remove: ");
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
          } else if(choice == 3) {
             app.main(null);
             break;
          } else{
            System.out.println(" Please enter a number between 1 and 3 ");
          }
       }
    }

    public boolean isValid(String category) {
        for( int i=0;i<category.length();i++) {
           if( (int)category.charAt(i) < (int) 'A' || (int)category.charAt(i) > (int)'z' || ((int)category.charAt(i) > (int)'Z' && (int)category.charAt(i) < (int)'a') ){
              return false;
           }
        }
        return true;
     }

     public void initializeProducts() {
        productList.add(new Product("Laptop", 29999.0, "Electronics"));
        productList.add(new Product("Smart Watch", 1499.0, "Gadgets"));
        productList.add(new Product("Geared Cycle", 8000.0, "Sports"));
        productList.add(new Product("Smartphone", 25000.0, "Electronics"));
     }
}
