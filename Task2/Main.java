package Task2;

abstract class Vehicle{
    abstract void startEngine();
}

class Car extends Vehicle{
    void startEngine(){
        System.out.println("Car engine started.");
    }
}

class Bike extends Vehicle {
    void startEngine() {
        System.out.println("Bike engine started.");
    }
}

public class Main {
    public static void main(String args[]){
        Car car = new Car();
        Bike bike = new Bike();
        car.startEngine();
        bike.startEngine();
    }
}
