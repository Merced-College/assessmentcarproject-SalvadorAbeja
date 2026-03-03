
public class Car {
	
	private String id;
	private String brand;
	private String model;
	private int year;
	private String fuelType;
	private String color;
	private double kmpl;

	public Car() {
		id = "Unknown";
		brand = "Unknown";
		model = "Unknown";
		year = 0;
		fuelType = "Unknown";
		color = "Unknown";
		kmpl = 0.0;
	}
	
	public Car(String id, String brand, String model, int year, String fuelType, String color, double kmpl) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.fuelType = fuelType;
		this.color = color;
		this.kmpl = kmpl;
	}
	
	public String getID() {
		return id;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		if (year >= 0) this.year = year;
	}
	
	public String getFuelType() {
		return fuelType;
	}
	
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	
	public String getColor () {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public double getKMPL() {
		return kmpl;
	}
	
	public void setKMPL(double kmpl) {
		this.kmpl = kmpl;
	}
	
	public String toString() {
		return "Car{id = '" + id + "', brand = " + brand + ", model = " + model + ", year = " + year + ", fuelType = " + fuelType + ", color = " + color + ", kmpl = " + kmpl + "'}";
	}
	

}

