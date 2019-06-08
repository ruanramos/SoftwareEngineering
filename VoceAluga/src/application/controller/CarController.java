package application.controller;

import java.util.List;
import java.util.Map;

import application.dbclass.CarDao;
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
			if(lowerCasedMessage.contains("duplicate entry") && lowerCasedMessage.contains("placa")) {
				throw new ControllerException("Ja existe um carro com esta placa");
			}
			else {
				throw e;
			}
		}
	}
	
	public void remove(Car car) throws ControllerException {
		if (car.getPlaca() == "") {
			throw new ControllerException("Carro precisa de placa para ser deletado");
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

	public <L extends List<Car>> void searchByModelo(L list, String modelo) {
		dao.selectToList(list, "where modelo like '%" + modelo + "%'");
	}

	public <L extends List<Car>> void searchByGrupo(L list, String grupo) {
		dao.selectToList(list, "where grupo like '%" + grupo + "%'");
	}
	
	public <L extends List<Car>> void searchByPlaca(L list, String placa) {
		dao.selectToList(list, "where placa like '%" + placa + "%'");
	}

	private static void validateCarFields(Form<Car> form) throws ControllerException {
		String errorMessage = "";
		
		if (!isPlateValid(form.getAttribute("placa"))) {
	    	errorMessage += "Placa inv�lida.\n";
	    }
		if (!isMileageValid(form.getAttribute("quilometragem"))) {
	    	errorMessage += "Quilometragem inv�lida.\n";
	    }
		if (!isGroupValid(form.getAttribute("grupo"))) {
	    	errorMessage += "Grupo inv�lido.\n";
	    }
		if (!isYearValid(form.getAttribute("ano"))) {
	    	errorMessage += "Ano inv�lida.\n";
	    }
	    if (!isModelValid(form.getAttribute("modelo"))) {
	    	errorMessage += "Modelo inv�lido.\n";
	    }
	    
	    if (errorMessage.length() != 0) {
	    	throw new ControllerException(errorMessage);
	    }
	}
	
	private static boolean isPlateValid(String plate) {
		return (plate != null && plate.matches("[A-Z]{3}\\d{4}|[A-Z]{3}\\d[A-Z]\\d{2}"));
	}
	
	private static boolean isModelValid(String model) {
		return (model != null && model.length() > 0);
	}
	
	private static boolean isGroupValid(String group) {
		return (group != null && group.length() > 0);
	}
	private static boolean isYearValid(String age) {
		return (age != null && age.matches("\\d+"));
	}
	private static boolean isMileageValid(String mileage) {
		return (mileage != null && mileage.matches("\\d+\\.\\d"));
	}
}
