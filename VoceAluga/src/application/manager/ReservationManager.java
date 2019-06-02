package application.manager;

import java.util.List;
import java.util.Map;

import application.dbclass.ReservationDao;
import application.dbclass.CustomerDao;
import application.model.Reservation;

public class ReservationManager {
	private ReservationDao dao;
	
	public ReservationManager(ReservationDao dao) {
		this.dao = dao;
	}
	
	public ReservationManager() {
		this.dao = new ReservationDao();
	}
	
	public void add(Map<String, String> mapOfFields) throws ManagerException {
		try {
			Form<Reservation> form = new Form<>(Reservation.class);
			form.addInfo(mapOfFields);
			validateReservationFields(form);
			
			Reservation reservation = new Reservation();
			form.fillObjectAttributes(reservation);
			
			dao.insert(reservation);
		}
		catch(RuntimeException e) {
			String lowerCasedMessage = e.getMessage().toLowerCase();
			if(lowerCasedMessage.contains("duplicate entry") && lowerCasedMessage.contains("cpf")) {
				throw new ManagerException("J� existe um cliente com este CPF");
			}
			else {
				throw e;
			}
		}
	}
	
	public void remove(Reservation reservation) throws ManagerException {
		if (reservation.getId() == 0) {
			throw new ManagerException("Reservation must have an ID to be deleted");
		}
		dao.delete(reservation);
	}
	
	public void edit(Map<String, String> mapOfFields) throws ManagerException {
		Form<Reservation> form = new Form<>(Reservation.class);
		form.addInfo(mapOfFields);
		validateReservationFields(form);
		
		Reservation reservation = new Reservation();
		form.fillObjectAttributes(reservation);

		dao.insert(reservation);
	}

	public <L extends List<Reservation>> void searchByModel(L list, String model) {
		dao.selectToList(list, "where Modelo like '%" + model + "%'");
	}

	public <L extends List<Reservation>> void searchByGroup(L list, String group) {
		dao.selectToList(list, "where Classe like '%" + group + "%'");
	}

	private static void validateReservationFields(Form<Reservation> form) throws ManagerException {
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
