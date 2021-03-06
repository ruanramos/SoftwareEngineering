package application.controller;

import java.util.List;
import java.util.Map;
import application.dbclass.ReservaDao;
import application.model.Reserva;

public class ReservaController {
private ReservaDao dao;
	
	public ReservaController(ReservaDao dao) {
		this.dao = dao;
	}
	
	public ReservaController() {
		this.dao = new ReservaDao();
	}
	
	public void add(Map<String, String> mapOfFields) throws ControllerException {
		try {
			Form<Reserva> form = new Form<>(Reserva.class);
			form.addInfo(mapOfFields);
			validateReservationFields(form);
			
			Reserva reserva = new Reserva();
			form.fillObjectAttributes(reserva);
			
			dao.insert(reserva);
		}
		catch(RuntimeException e) {
			String lowerCasedMessage = e.getMessage().toLowerCase();
			if(lowerCasedMessage.contains("a foreign key constraint fails")) {
				throw new ControllerException("reserva nao encontrada");
			}
			else {
				throw e;
			}
		}
	}
	
	public void remove(Reserva reserva) throws ControllerException {
		dao.delete(reserva);
	}
	
	public Reserva edit(Map<String, String> mapOfFields) throws ControllerException {
		Form<Reserva> form = new Form<>(Reserva.class);
		form.addInfo(mapOfFields);
		validateReservationFields(form);
		
		Reserva reserva = new Reserva();
		form.fillObjectAttributes(reserva);

		dao.update(reserva);
		return reserva;
	}

	public <L extends List<Reserva>> void searchByCpf(L list, String cpf) {
		dao.selectToList(list, "where idcliente like '%" + cpf + "%'");
	}
	public <L extends List<Reserva>> void searchByGrupo(L list, String grupo) {
		dao.selectToList(list, "where grupo like '%" + grupo + "%'");
	}
	public <L extends List<Reserva>> void searchByModelo(L list, String modelo) {
		dao.selectToList(list, "where modelo like '%" + modelo + "%'");
	}
	public <L extends List<Reserva>> void searchAll(L list) {
		dao.selectToList(list);
	}

	private static void validateReservationFields(Form<Reserva> form) throws ControllerException {
		String errorMessage = "";
		
		if (!isCpfValid(form.getAttribute("idcliente"))) {
			errorMessage += "CPF invalido.\n";
		}
		if (!isDateValid(form.getAttribute("data"))) {
	    	errorMessage += "Data invalida.\n";
	    }
		if (!isGroupValid(form.getAttribute("grupo"))) {
	    	errorMessage += "Grupo invalido.\n";
	    }
	    if (!isModelValid(form.getAttribute("modelo"))) {
	    	errorMessage += "Modelo invalido.\n";
	    }
	    if (!isDurationValid(form.getAttribute("duracaodias"))) {
	    	errorMessage += "Duracao de dias invalida.\n";
	    }
	    
	    if (errorMessage.length() != 0) {
	    	throw new ControllerException(errorMessage);
	    }
	    
	}
	
	private static boolean isModelValid(String model) {
		return (model != null);
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