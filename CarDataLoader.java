/* 
* Salvador Abeja
* 03/05/2026
* Assessment 1 
*/
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
    * Sorts cars by brand using selection sort algorithm (ascending order, case-insensitive).
    *
    * @param cars the ArrayList of Car objects to sort
    */
   public static void sortByBrand(ArrayList<Car> cars) {
       int n = cars.size();
      
       // Selection sort algorithm by brand (case-insensitive)
       for (int i = 0; i < n - 1; i++) {
           // Find the minimum brand in the remaining unsorted portion
           int minIndex = i;
           for (int j = i + 1; j < n; j++) {
               String brand1 = cars.get(j).getBrand().toLowerCase();
               String brand2 = cars.get(minIndex).getBrand().toLowerCase();
               if (brand1.compareTo(brand2) < 0) {
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
    * Performs binary search on a sorted list of cars by brand (case-insensitive).
    * Returns all cars matching the search brand.
    *
    * @param cars the sorted ArrayList of Car objects to search
    * @param searchBrand the brand to search for (case-insensitive)
    * @return ArrayList of matching Car objects, or empty ArrayList if none found
    */
   public static ArrayList<Car> binarySearchByBrand(ArrayList<Car> cars, String searchBrand) {
       ArrayList<Car> results = new ArrayList<>();
      
       if (cars == null || cars.isEmpty() || searchBrand == null) {
           return results;
       }
      
       String searchKey = searchBrand.toLowerCase();
       int low = 0;
       int high = cars.size() - 1;
       int foundIndex = -1;
      
       // Binary search to find the first occurrence of the brand
       while (low <= high) {
           int mid = low + (high - low) / 2;
           String midBrand = cars.get(mid).getBrand().toLowerCase();
           int comparison = midBrand.compareTo(searchKey);
          
           if (comparison == 0) {
               foundIndex = mid;
               // Move high to find the leftmost occurrence
               high = mid - 1;
           } else if (comparison < 0) {
               low = mid + 1;
           } else {
               high = mid - 1;
           }
       }
      
       // If found, collect all cars with matching brand
       if (foundIndex != -1) {
           // Find all cars with the same brand (could be multiple)
           for (int i = foundIndex; i < cars.size(); i++) {
               if (cars.get(i).getBrand().toLowerCase().equals(searchKey)) {
                   results.add(cars.get(i));
               } else {
                   break;
               }
           }
       }
      
       return results;
   }
  
   /**
    * Prints statistics for the given car list:
    * - Average mileage (KMPL)
    * - Count by fuel type
    *
    * @param cars the ArrayList of Car objects to analyze
    */
   public static void printStats(ArrayList<Car> cars) {
       if (cars == null || cars.isEmpty()) {
           System.out.println("No cars to analyze.");
           return;
       }
      
       // Calculate average mileage
       double totalKmpl = 0;
       for (Car car : cars) {
           totalKmpl += car.getKMPL();
       }
       double averageKmpl = totalKmpl / cars.size();
      
       // Count by fuel type
       Map<String, Integer> fuelTypeCounts = new LinkedHashMap<>();
       for (Car car : cars) {
           String fuelType = car.getFuelType();
           fuelTypeCounts.put(fuelType, fuelTypeCounts.getOrDefault(fuelType, 0) + 1);
       }
      
       // Print results
       System.out.println("\n--- Statistics ---");
       System.out.printf("Average Mileage (KMPL): %.2f\n", averageKmpl);
       System.out.println("\nCount by Fuel Type:");
       for (Map.Entry<String, Integer> entry : fuelTypeCounts.entrySet()) {
           System.out.println("  " + entry.getKey() + ": " + entry.getValue());
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

   public static void printStats1(ArrayList<Car> working) {
    throw new UnsupportedOperationException("Unimplemented method 'printStats'");
   }
}


