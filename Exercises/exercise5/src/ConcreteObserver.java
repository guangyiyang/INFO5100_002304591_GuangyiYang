public class ConcreteObserver implements Observer{
    private String observerState;

    @Override
    public void update(String message) {
        this.observerState = message;
        System.out.println("Observer state updated: " + observerState);
    }
}
