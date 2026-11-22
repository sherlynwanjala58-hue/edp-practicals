public class RefactoredMain {
    public static void main(String[] args) {
        DataModel model = new DataModel();

        model.addObserver(new LabelDisplay());
        model.addObserver(new BarChartDisplay());
        model.addObserver(new TextLogger());

        model.setValue(10);
        model.setValue(20);
        model.setValue(30);
    }

    private interface Observer {
        void update(int value);
    }

    private static class DataModel {
        private final java.util.List<Observer> observers = new java.util.ArrayList<Observer>();

        void addObserver(Observer observer) {
            observers.add(observer);
        }

        void setValue(int value) {
            for (Observer observer : observers) {
                observer.update(value);
            }
        }
    }

    private static class LabelDisplay implements Observer {
        public void update(int value) {
            System.out.println("Label: " + value);
        }
    }

    private static class BarChartDisplay implements Observer {
        public void update(int value) {
            System.out.println("Bar chart: " + value);
        }
    }

    private static class TextLogger implements Observer {
        public void update(int value) {
            System.out.println("Text logger: " + value);
        }
    }
}