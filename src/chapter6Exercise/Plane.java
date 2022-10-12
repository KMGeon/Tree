package chapter6Exercise;

public class Plane {
	
	//필드
	
	private String manufacture;
	private String model;
	private int maxNumberOfPassengers;
	private static int numberOfPlanes;
	
	//생성자
	
	public Plane(){numberOfPlanes++;}
	public Plane(String manufacture, String model, int maxNumberOfPassengers){
		this.manufacture =manufacture;
		this.model =model;
		this.maxNumberOfPassengers =maxNumberOfPassengers;
		numberOfPlanes++;
	}
	
	//메소드
	
	public String getManufacture() {
		return manufacture;
	}
	
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public int getMaxNumberOfPassengers() {
		return maxNumberOfPassengers;
	}
	
	public void setMaxNumberOfPassengers(int maxNumberOfPassengers) {
		if(maxNumberOfPassengers>0) {
			this.maxNumberOfPassengers = maxNumberOfPassengers;			
		}
	}
	public static int getNumberOfPlanes() {
		
		return numberOfPlanes;
	}

	
	
	
}
