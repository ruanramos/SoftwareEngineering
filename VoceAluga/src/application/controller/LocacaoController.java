package application.controller;

import java.util.List;
import java.util.Map;

import application.dbclass.LocacaoDao;
import application.model.Cliente;
import application.model.Locacao;

public class LocacaoController {
private LocacaoDao dao;
	
	public LocacaoController(LocacaoDao dao) {
		this.dao = dao;
	}
	
	public LocacaoController() {
		this.dao = new LocacaoDao();
	}
	
	public void add(Map<String, String> mapOfFields) throws ControllerException {
		try {
			Form<Locacao> form = new Form<>(Locacao.class);
			form.addInfo(mapOfFields);
			validateLocacaotionFields(form);
			
			Locacao locacao = new Locacao();
			form.fillObjectAttributes(locacao);
			
			dao.insert(locacao);
		}
		catch(RuntimeException e) {
			throw e;
		}
	}
	
	public void remove(Locacao locacao) throws ControllerException {
		dao.delete(locacao);
	}
	
	public void edit(Map<String, String> mapOfFields) throws ControllerException {
		Form<Locacao> form = new Form<>(Locacao.class);
		form.addInfo(mapOfFields);
		validateLocacaotionFields(form);
		
		Locacao locacao = new Locacao();
		form.fillObjectAttributes(locacao);

		dao.update(locacao);
	}

	public <L extends List<Locacao>> void searchByModelo(L list, String modelo) {
		dao.selectToList(list, "where modelo like '%" + modelo + "%'");
	}

	public <L extends List<Locacao>> void searchByGrupo(L list, String grupo) {
		dao.selectToList(list, "where grupo like '%" + grupo + "%'");
	}
	
	public <L extends List<Locacao>> void searchByPlaca(L list, String placa) {
		dao.selectToList(list, "where placa like '%" + placa + "%'");
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
		return (plate != null && plate.matches("[A-Z]{3}\\d{4}|[A-Z]{3}\\d[A-Z]\\d{2}"));
	}
	private static boolean isCpfValid(String cpf) {
		return (cpf != null && cpf.matches("\\d{11}"));
	}
	private static boolean isProblemValid(String problem) {
		return (problem != null);
	}
}