/* 
* Salvador Abeja
* 03/05/2026
* Assessment 1 
*/
import java.io.*;
import java.util.*;


public class Main {
  
   private static ArrayList<Car> cars;
   private static ArrayList<Car> working;
   private static ArrayList<Car> foundCars;
   private static Scanner scanner;


   public static void main(String[] args) {
       // Load cars from CSV file
       cars = CarDataLoader.loadCars("Car_Data.csv");
      
       // Create working list with first 2000 cars
       working = new ArrayList<>(cars.subList(0, Math.min(2000, cars.size())));
      
       // Initialize scanner
       scanner = new Scanner(System.in);
       foundCars = new ArrayList<>();
      
       // Display menu
       displayMenu();
      
       scanner.close();
   }
  
   /**
    * Displays the main menu and handles user input.
    */
   private static void displayMenu() {
       boolean exit = false;
      
       while (!exit) {
           System.out.println("\n========== CAR DATABASE MENU ==========");
           System.out.println("1. Search by Brand (case-insensitive)");
           System.out.println("2. Sort by Brand");
           System.out.println("3. Show Statistics");
           System.out.println("4. Show Found Car(s)");
           System.out.println("5. Exit");
           System.out.print("Enter your choice (1-5): ");
          
           String choice = scanner.nextLine().trim();
          
           switch (choice) {
               case "1":
                   searchByBrand();
                   break;
               case "2":
                   sortByBrand();
                   break;
               case "3":
                   showStatistics();
                   break;
               case "4":
                   showFoundCars();
                   break;
               case "5":
                   exit = true;
                   System.out.println("Thank you for using the Car Database. Goodbye!");
                   break;
               default:
                   System.out.println("Invalid choice. Please enter 1-5.");
           }
       }
   }
  
   /**
    * Searches for cars by brand using binary search.
    */
   private static void searchByBrand() {
       System.out.print("\nEnter brand to search for: ");
       String searchBrand = scanner.nextLine().trim();
      
       if (searchBrand.isEmpty()) {
           System.out.println("Brand cannot be empty.");
           return;
       }
      
       // Check if working list is sorted by brand
       System.out.println("Searching in working list (first 2000 cars)...");
       foundCars = CarDataLoader.binarySearchByBrand(working, searchBrand);
      
       if (foundCars.isEmpty()) {
           System.out.println("No cars found with brand: " + searchBrand);
       } else {
           System.out.println("Found " + foundCars.size() + " car(s) with brand: " + searchBrand);
           System.out.println("Type 'Show Found Car(s)' from menu to view details.");
       }
   }
  
   /**
    * Sorts the working list by brand and displays confirmation.
    */
   private static void sortByBrand() {
       System.out.println("\nSorting working list by brand (case-insensitive)...");
       CarDataLoader.sortByBrand(working);
       System.out.println("Working list sorted by brand successfully!");
       System.out.println("Tip: Sort before searching by brand for accurate binary search results.");
   }
  
   /**
    * Shows statistics for the working list.
    */
   private static void showStatistics() {
       System.out.println("\nCalculating statistics for working list (" + working.size() + " cars)...");
       CarDataLoader.printStats(working);
   }
  
   /**
    * Shows the found cars from the last search.
    */
   private static void showFoundCars() {
       if (foundCars.isEmpty()) {
           System.out.println("\nNo cars found yet. Please search first using option 1.");
           return;
       }
      
       System.out.println("\n========== FOUND CARS (" + foundCars.size() + ") ==========");
       for (int i = 0; i < foundCars.size(); i++) {
           System.out.println((i + 1) + ". " + foundCars.get(i));
       }
   }
}

