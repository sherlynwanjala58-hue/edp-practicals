public class TextLogger implements DataObserver {
    @Override
    public void update(int newValue) {
        System.out.println("LOG: value changed to " + newValue);
    }
}