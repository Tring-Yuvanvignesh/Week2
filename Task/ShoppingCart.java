package Task;
import java.util.*;;

// ShoppingCart
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
