import java.io.*;
import java.util.*;

public class CarDataLoader {
    
    /**
     * Loads car data from a CSV file into an ArrayList of Car objects.
     * Skips the header row and handles malformed rows gracefully.
     * 
     * @param filename the path to the CSV file to load
     * @return ArrayList containing the loaded Car objects
     */
    public static ArrayList<Car> loadCars(String filename) {
        ArrayList<Car> cars = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isHeader = true;
            
            while ((line = reader.readLine()) != null) {
                // Skip header row
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                
                try {
                    // Parse CSV line
                    String[] parts = line.split(",");
                    
                    // Validate that we have exactly 7 fields
                    if (parts.length != 7) {
                        System.out.println("Skipping malformed row: " + line);
                        continue;
                    }
                    
                    // Parse fields
                    String id = parts[0].trim();
                    String brand = parts[1].trim();
                    String model = parts[2].trim();
                    int year = Integer.parseInt(parts[3].trim());
                    String fuelType = parts[4].trim();
                    String color = parts[5].trim();
                    double kmpl = Double.parseDouble(parts[6].trim());
                    
                    // Create Car object and add to list
                    Car car = new Car(id, brand, model, year, fuelType, color, kmpl);
                    cars.add(car);
                    
                } catch (NumberFormatException e) {
                    System.out.println("Skipping malformed row (invalid number): " + line);
                } catch (Exception e) {
                    System.out.println("Skipping malformed row (error): " + line);
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + filename);
        } catch (IOException e) {
            System.out.println("Error: IOException while reading file - " + e.getMessage());
        }
        
        // Print total cars loaded
        System.out.println("Total cars loaded: " + cars.size());
        
        return cars;
    }
    
    /**
     * Sorts cars by year using selection sort algorithm (ascending order).
     * 
     * @param cars the ArrayList of Car objects to sort
     */
    public static void selectionSort(ArrayList<Car> cars) {
        int n = cars.size();
        
        // Selection sort algorithm
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum year in the remaining unsorted portion
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (cars.get(j).getYear() < cars.get(minIndex).getYear()) {
                    minIndex = j;
                }
            }
            
            // Swap the minimum element with the current position
            if (minIndex != i) {
                Car temp = cars.get(i);
                cars.set(i, cars.get(minIndex));
                cars.set(minIndex, temp);
            }
        }
    }
    
    /**
     * Prints the first 10 cars from the list.
     * 
     * @param cars the ArrayList of Car objects to print
     */
    public static void printFirstTenCars(ArrayList<Car> cars) {
        int limit = Math.min(10, cars.size());
        System.out.println("\n--- First " + limit + " Cars ---");
        for (int i = 0; i < limit; i++) {
            System.out.println((i + 1) + ". " + cars.get(i));
        }
    }
}
