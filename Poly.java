abstract class Animal {
    public abstract void sound();
}

class Dog extends Animal {
    public void sound() {
        System.out.println("Bark");
    }
}

class Cat extends Animal {
    public void sound() {
        System.out.println("Meow");
    }
}

public class Poly {
    public static void main(String[] args) {
        Animal myAnimal = new Dog();
        myAnimal.sound();

        myAnimal = new Cat();
        myAnimal.sound();
    }
}
