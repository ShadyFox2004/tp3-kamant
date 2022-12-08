package a22.sim203.tp3.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WindowAnimationService extends Service<WindowAnimationService.LocationSize[]> {

    private List<LocationSize> target = new ArrayList<>();
    private List<LocationSize> actual = new ArrayList<>();

    private double tailleIncrement = 5;
    private double locationIncrement = 5;

    public void calculateTarget(){
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        for (int i = 0; i < actual.size(); i++) {
            target.add(new LocationSize(
                    i%3 * screenBounds.getMaxX()/3,
                    i/3 * screenBounds.getMaxY()/2,
                    screenBounds.getMaxX()/3,
                    screenBounds.getMaxY()/2));
        }
    }
    public void addActual(LocationSize actual){ this.actual.add(actual);}
    @Override
    protected Task<LocationSize[]> createTask() {
        return new windowAnimationTask();
    }

    private class windowAnimationTask extends Task<LocationSize[]>{
        @Override
        protected LocationSize[] call() throws Exception {
            calculateTarget();
            while (!isCancelled() && !actual.equals(target)) {
                LocationSize[] tailles = new LocationSize[target.size()];
                for (int i = 0; i < tailles.length; i++) {
                    tailles[i] = new LocationSize(incrementValeur(actual.get(i).x, target.get(i).x, locationIncrement), 
                            incrementValeur(actual.get(i).y, target.get(i).y, locationIncrement), 
                            incrementValeur(actual.get(i).width, target.get(i).width, tailleIncrement), 
                            incrementValeur(actual.get(i).height, target.get(i).height, tailleIncrement)).clone();
                }
                actual = new ArrayList<>();
                updateValue(tailles);
                Thread.sleep(30);
            }
            return null;
        }

        /**
         * Adds or substracts an increment to a value until desired value is reached
         * @param actualValue starting value
         * @param targetValue wanted value
         * @param step maximum increment
         * @return the modified value
         */
        private double incrementValeur(double actualValue, double targetValue, double step) {
            double difference = Math.abs(targetValue - actualValue);
            double increment = targetValue - actualValue > 0 ? step : -step;
            if (difference < step)
                increment = targetValue - actualValue;
            return actualValue + increment;
        }

    }
    public static class LocationSize implements Cloneable {
        private double x;
        private double y;
        private double width;
        private double height;

        public LocationSize(double x, double y, double width, double height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        @Override
        public LocationSize clone() throws CloneNotSupportedException {
            return (LocationSize) super.clone();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof LocationSize that)) return false;
            return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0 && Double.compare(that.width, width) == 0 && Double.compare(that.height, height) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, width, height);
        }
    }
}
