package application.controller;

import java.util.List;
import java.util.Map;

import application.dbclass.CarDao;
import application.dbclass.CustomerDao;
import application.model.Car;

public class CarController {
	private CarDao dao;
	
	public CarController(CarDao dao) {
		this.dao = dao;
	}
	
	public CarController() {
		this.dao = new CarDao();
	}
	
	public void add(Map<String, String> mapOfFields) throws ControllerException {
		try {
			Form<Car> form = new Form<>(Car.class);
			form.addInfo(mapOfFields);
			validateCarFields(form);
			
			Car car = new Car();
			form.fillObjectAttributes(car);
			
			dao.insert(car);
		}
		catch(RuntimeException e) {
			String lowerCasedMessage = e.getMessage().toLowerCase();
			if(lowerCasedMessage.contains("duplicate entry") && lowerCasedMessage.contains("cpf")) {
				throw new ControllerException("J� existe um cliente com este CPF");
			}
			else {
				throw e;
			}
		}
	}
	
	public void remove(Car car) throws ControllerException {
		if (car.getId() == 0) {
			throw new ControllerException("Car must have an ID to be deleted");
		}
		dao.delete(car);
	}
	
	public void edit(Map<String, String> mapOfFields) throws ControllerException {
		Form<Car> form = new Form<>(Car.class);
		form.addInfo(mapOfFields);
		validateCarFields(form);
		
		Car car = new Car();
		form.fillObjectAttributes(car);

		dao.insert(car);
	}

	public <L extends List<Car>> void searchByModel(L list, String model) {
		dao.selectToList(list, "where Modelo like '%" + model + "%'");
	}

	public <L extends List<Car>> void searchByGroup(L list, String group) {
		dao.selectToList(list, "where Classe like '%" + group + "%'");
	}

	private static void validateCarFields(Form<Car> form) throws ControllerException {
		String errorMessage = "";

	    if (!isModelValid(form.getAttribute("model"))) {
	    	errorMessage += "Modelo inv�lido.\n";
	    }
	    if (!isCategoryValid(form.getAttribute("category"))) {
	    	errorMessage += "Categoria inv�lida.\n";
	    }
	    if (!isAgeValid(form.getAttribute("age"))) {
	    	errorMessage += "Idade inv�lida.\n";
	    }
	    if (!isMileageValid(form.getAttribute("mileage"))) {
	    	errorMessage += "Quilometragem inv�lida.\n";
	    }

	    if (errorMessage.length() != 0) {
	    	throw new ControllerException(errorMessage);
	    }
	}
	
	private static boolean isModelValid(String model) {
		return (model != null && model.length() > 0);
	}
	
	private static boolean isCategoryValid(String category) {
		return (category != null && category.length() > 0);
	}
	private static boolean isAgeValid(String age) {
		return (age != null && age.matches("\\d+"));
	}
	private static boolean isMileageValid(String mileage) {
		return (mileage != null && mileage.matches("\\d+\\.\\d"));
	}
}
