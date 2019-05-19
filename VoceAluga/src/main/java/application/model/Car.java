package application.model;

import javafx.beans.property.*;

public class Car {
	private IntegerProperty id;
	private StringProperty model;
	private StringProperty category;
	private IntegerProperty age;
	private DoubleProperty mileage;

	public Car() {
		this.id = new SimpleIntegerProperty();
		this.model = new SimpleStringProperty("");
		this.category = new SimpleStringProperty("");
		this.age = new SimpleIntegerProperty(0);
		this.mileage = new SimpleDoubleProperty(0.0);
	}

	public int getId() {
		return id.get();
	}

	public IntegerProperty idProperty() {
		return id;
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public String getModel() {
		return model.get();
	}

	public StringProperty modelProperty() {
		return model;
	}

	public void setModel(String model) {
		this.model.set(model);
	}

	public String getCategory() {
		return category.get();
	}

	public StringProperty categoryProperty() {
		return category;
	}

	public void setCategory(String category) {
		this.category.set(category);
	}

	public int getAge() {
		return age.get();
	}

	public IntegerProperty ageProperty() {
		return age;
	}

	public void setAge(int age) {
		this.age.set(age);
	}

	public double getMileage() {
		return mileage.get();
	}

	public DoubleProperty mileageProperty() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage.set(mileage);
	}
}
