package application.controller;

import java.util.List;
import java.util.Map;

import application.dbclass.ReservationDao;
import application.model.Reservation;

public class ReservationController {
private ReservationDao dao;
	
	public ReservationController(ReservationDao dao) {
		this.dao = dao;
	}
	
	public ReservationController() {
		this.dao = new ReservationDao();
	}
	
	public void add(Map<String, String> mapOfFields) throws ControllerException {
		try {
			Form<Reservation> form = new Form<>(Reservation.class);
			form.addInfo(mapOfFields);
			validateReservationFields(form);
			
			Reservation reservation = new Reservation();
			form.fillObjectAttributes(reservation);
			
			dao.insert(reservation);
		}
		catch(RuntimeException e) {
			throw e;
		}
	}
	
	public void remove(Reservation reservation) throws ControllerException {
		dao.delete(reservation);
	}
	
	public void edit(Map<String, String> mapOfFields) throws ControllerException {
		Form<Reservation> form = new Form<>(Reservation.class);
		form.addInfo(mapOfFields);
		validateReservationFields(form);
		
		Reservation reservation = new Reservation();
		form.fillObjectAttributes(reservation);

		dao.insert(reservation);
	}

	public <L extends List<Reservation>> void searchByModelo(L list, String modelo) {
		dao.selectToList(list, "where modelo like '%" + modelo + "%'");
	}

	public <L extends List<Reservation>> void searchByGrupo(L list, String grupo) {
		dao.selectToList(list, "where grupo like '%" + grupo + "%'");
	}
	
	public <L extends List<Reservation>> void searchByPlaca(L list, String placa) {
		dao.selectToList(list, "where placa like '%" + placa + "%'");
	}

	private static void validateReservationFields(Form<Reservation> form) throws ControllerException {
		String errorMessage = "";
		
		if (!isPlateValid(form.getAttribute("placa"))) {
	    	errorMessage += "Placa inv�lida.\n";
	    }
		if (!isCpfValid(form.getAttribute("idcliente"))) {
			errorMessage += "CPF inv�lido.\n";
		}
		if (!isDateValid(form.getAttribute("data"))) {
	    	errorMessage += "Data inv�lida.\n";
	    }
		if (!isGroupValid(form.getAttribute("grupo"))) {
	    	errorMessage += "Grupo inv�lido.\n";
	    }
	    if (!isModelValid(form.getAttribute("modelo"))) {
	    	errorMessage += "Modelo inv�lido.\n";
	    }
	    if (!isDurationValid(form.getAttribute("duracaodias"))) {
	    	errorMessage += "Duracao de dias inv�lida.\n";
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
	private static boolean isDurationValid(String duration) {
		return (duration != null && duration.matches("\\d+"));
	}
	private static boolean isDateValid(String date) {
		return (date != null);
	}
	private static boolean isCpfValid(String cpf) {
		return (cpf != null && cpf.matches("\\d{11}"));
	}
}