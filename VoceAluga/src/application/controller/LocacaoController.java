package application.controller;

import java.util.List;
import java.util.Map;
import application.dbclass.LocacaoDao;
import application.model.Locacao;
import application.model.LocacaoManager;
import application.model.Carro;
import application.model.ModelException;

public class LocacaoController {
	
	private LocacaoDao dao;
	private LocacaoManager manager;
	
	public LocacaoController(LocacaoDao dao) {
		this.dao = dao;
		this.manager = new LocacaoManager();
	}
	
	public LocacaoController() {
		this.dao = new LocacaoDao();
		this.manager = new LocacaoManager();
	}
	
	public void add(Map<String, String> mapOfFields) throws ControllerException {
		try {
			Form<Locacao> form = new Form<>(Locacao.class);
			form.addInfo(mapOfFields);
			validateLocacaotionFields(form);
			
			Locacao locacao = new Locacao();
			form.fillObjectAttributes(locacao);
			manager.rentCarOnSpot(locacao.getIdcliente(), locacao.getIdcarro());
		}
		catch(RuntimeException e) {
			if(e.getMessage() == null ) {
				throw e;
			}
			String lowerCasedMessage = e.getMessage().toLowerCase();
			if(lowerCasedMessage.contains("a foreign key constraint fails")) {
				String message = "";
				if(lowerCasedMessage.contains("idcliente")) {
					message += "cliente nao encontrado\n";
				}
				if(lowerCasedMessage.contains("idcarro")) {
					message += "carro nao encontrado\n";
				}
				throw new ControllerException(message);
			}
			else {
				throw e;
			}
		}
		catch(ModelException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	
	public void remove(Locacao locacao) {
		
		dao.delete(locacao);
	}
	
	public Locacao edit(Map<String, String> mapOfFields) throws ControllerException {
		Form<Locacao> form = new Form<>(Locacao.class);
		form.addInfo(mapOfFields);
		validateLocacaotionFields(form);
		
		Locacao locacao = new Locacao();
		form.fillObjectAttributes(locacao);

		dao.update(locacao);
		return locacao;
	}
	
	public <L extends List<Carro>> void getAvailableCars(L list) {
		manager.getAvailableCars(list);
	}
	
	public <L extends List<Locacao>> void searchByCpf(L list, String modelo) {
		dao.selectToList(list, "where idcliente like '%" + modelo + "%'");
	}

	public <L extends List<Locacao>> void searchAll(L list) {
		dao.selectToList(list);
	}
	
	private static void validateLocacaotionFields(Form<Locacao> form) throws ControllerException {
		String errorMessage = "";
		
		if (!isCpfValid(form.getAttribute("idcliente"))) {
			errorMessage += "CPF inv�lido.\n";
		}
		if (!isPlateValid(form.getAttribute("idcarro"))) {
	    	errorMessage += "Placa inv�lida.\n";
	    }
		if (!isProblemValid(form.getAttribute("problema"))) {
	    	errorMessage += "Problema invalido.\n";
	    }
	    
	    if (errorMessage.length() != 0) {
	    	throw new ControllerException(errorMessage);
	    }
	    
	}
	
	private static boolean isPlateValid(String plate) {
		//return (plate != null && plate.matches("[A-Z]{3}\\d{4}|[A-Z]{3}\\d[A-Z]\\d{2}"));
		return plate != null;
	}
	private static boolean isCpfValid(String cpf) {
		return (cpf != null && cpf.matches("\\d{11}"));
	}
	private static boolean isProblemValid(String problem) {
		return (problem != null);
	}
}