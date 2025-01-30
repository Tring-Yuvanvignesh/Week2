import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {

        Queue<Integer> ticketQueue = new LinkedList<>();

        ticketQueue.add(1);     
        ticketQueue.add(2);
        ticketQueue.add(3);
        ticketQueue.add(4);

        System.out.println("Current Queue: " + ticketQueue);

        while (!ticketQueue.isEmpty()) {
            int Person = ticketQueue.poll();
            System.out.println("Person Number "+ Person + " got their ticket.");
            System.out.println("Updated Queue: " + ticketQueue);
        }

        System.out.println("All Person have their Ticket!");
    }
}
