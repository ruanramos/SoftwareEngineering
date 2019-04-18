package application.manager;

import java.util.List;
import java.util.Map;

import application.dbclass.CarDao;
import application.model.Car;

public class CarManager {
	public static void add(Map<String, String> mapOfFields) throws ManagerException {
		try {
			Form<Car> form = new Form<>(Car.class);
			form.addInfo(mapOfFields);
			validateCarFields(form);
			
			Car car = new Car();
			form.fillObjectAttributes(car);
			
			CarDao dao = new CarDao();
			dao.insert(car);
		}
		catch(RuntimeException e) {
			String lowerCasedMessage = e.getMessage().toLowerCase();
			if(lowerCasedMessage.contains("duplicate entry") && lowerCasedMessage.contains("cpf")) {
				throw new ManagerException("Já existe um cliente com este CPF");
			}
			else {
				throw e;
			}
		}
	}
	
	public static void remove(Car car) {
		CarDao dao = new CarDao();
		dao.delete(car);
	}
	
	public static void edit(Map<String, String> mapOfFields) throws ManagerException {
		Form<Car> form = new Form<>(Car.class);
		form.addInfo(mapOfFields);
		validateCarFields(form);
		
		Car car = new Car();
		form.fillObjectAttributes(car);
		
		CarDao dao = new CarDao();
		dao.insert(car);
	}
	
	public static <L extends List<Car>> void searchByModel(L list, String modelo) {
		CarDao dao = new CarDao();
		dao.selectToList(list, String.format("Modelo=\"%s\"", modelo));
	}
	
	private static void validateCarFields(Form<Car> form) throws ManagerException {
		String errorMessage = "";

	    if (!isModelValid(form.getAttribute("model"))) {
	    	errorMessage += "Modelo inválido.\n";
	    }
	    if (!isCategoryValid(form.getAttribute("category"))) {
	    	errorMessage += "Categoria inválida.\n";
	    }
	    if (!isAgeValid(form.getAttribute("age"))) {
	    	errorMessage += "Idade inválida.\n";
	    }
	    if (!isMileageValid(form.getAttribute("mileage"))) {
	    	errorMessage += "Quilometragem inválida.\n";
	    }

	    if (errorMessage.length() != 0) {
	    	throw new ManagerException(errorMessage);
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
