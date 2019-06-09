package application.controller;

import java.util.List;
import java.util.Map;

import application.dbclass.CarroDao;
import application.model.Carro;

public class CarroController {
	private CarroDao dao;

	public CarroController() {
		this.dao = new CarroDao();
	}
	
	public void add(Map<String, String> mapOfFields) throws ControllerException {
		try {
			Form<Carro> form = new Form<>(Carro.class);
			form.addInfo(mapOfFields);
			validateCarFields(form);
			
			Carro carro = new Carro();
			form.fillObjectAttributes(carro);
			
			dao.insert(carro);
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
	
	public void remove(Carro carro) throws ControllerException {
		if (carro.getPlaca() == "") {
			throw new ControllerException("Carro precisa de placa para ser deletado");
		}
		dao.delete(carro);
	}
	
	public void edit(Map<String, String> mapOfFields) throws ControllerException {
		Form<Carro> form = new Form<>(Carro.class);
		form.addInfo(mapOfFields);
		validateCarFields(form);
		
		Carro carro = new Carro();
		form.fillObjectAttributes(carro);

		dao.insert(carro);
	}

	public <L extends List<Carro>> void searchByModelo(L list, String modelo) {
		dao.selectToList(list, "where modelo like '%" + modelo + "%'");
	}

	public <L extends List<Carro>> void searchByGrupo(L list, String grupo) {
		dao.selectToList(list, "where grupo like '%" + grupo + "%'");
	}
	
	public <L extends List<Carro>> void searchByPlaca(L list, String placa) {
		dao.selectToList(list, "where placa like '%" + placa + "%'");
	}

	private static void validateCarFields(Form<Carro> form) throws ControllerException {
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
		return (mileage != null && mileage.matches("\\d+"));
	}
}
