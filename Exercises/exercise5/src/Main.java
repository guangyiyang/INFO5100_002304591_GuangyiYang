public class Main {
    public static void main(String[] args){

        //Demonstrating the Singleton Pattern
        Singleton singleton = Singleton.getInstance();
        singleton.showMessage();

        //Demonstrating the Factory Pattern
        Product productA = ProductFactory.createProduct("A");
        productA.create();

        Product productB = ProductFactory.createProduct("B");
        productB.create();

        //Demonstrating the Observer Pattern
        ConcreteSubject subject = new ConcreteSubject();
        ConcreteObserver observer1 = new ConcreteObserver();
        ConcreteObserver observer2 = new ConcreteObserver();

        subject.addObserver(observer1);
        subject.addObserver(observer2);

        subject.setState("State 1");
        subject.setState("State 2");

    }
}
