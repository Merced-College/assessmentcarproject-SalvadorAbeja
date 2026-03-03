import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Load cars from CSV file
        ArrayList<Car> cars = CarDataLoader.loadCars("Car_Data.csv");
        
        // Sort cars by year using selection sort
        CarDataLoader.selectionSort(cars);
        
        // Create working list with first 2000 cars
        ArrayList<Car> working = new ArrayList<>(cars.subList(0, Math.min(2000, cars.size())));
        
        // Print the first 10 cars
        CarDataLoader.printFirstTenCars(cars);
    }
}
