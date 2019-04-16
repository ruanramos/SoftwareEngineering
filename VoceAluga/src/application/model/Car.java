package application.model;

public class Car {
	private int id;
	private String model;
	private String classification;
	private int age;
	private float mileage;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getMileage() {
		return mileage;
	}
	public void setMileage(float mileage) {
		this.mileage = mileage;
	} 
}
