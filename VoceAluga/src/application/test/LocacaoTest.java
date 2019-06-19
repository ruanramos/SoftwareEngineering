package application.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import application.controller.*;
import application.model.*;

class LocacaoTest {

	@Test
	void makeSuccessfulRent() throws ControllerException {
		Cliente cliente1;
		HashMap<String,String> clientMap = new HashMap<String,String>();
		ClienteController clientController = new ClienteController();
		
		clientMap.put("cpf","12345678910");
		clientMap.put("endereco","rua oito");
		clientMap.put("nascimento","14/06/2019");
		clientMap.put("nome","Debis Dibris");
		clientMap.put("telefone","22342212");
		clientMap.put("validadecnh","21/11/2020");
		cliente1 = clientController.add(clientMap);
		
		Carro carro1;
		HashMap<String,String> carMap = new HashMap<String,String>();
		CarroController carController = new CarroController();

		carMap.put("placa","LUX-1234");
		carMap.put("quilometragem","8000.8");
		carMap.put("grupo","B");
		carMap.put("ano","11");
		carMap.put("modelo","novo");
		carro1 = carController.add(carMap);
		
		try {
			Locacao locacao1 = new Locacao();
			HashMap<String,String> locacaoMap = new HashMap<String,String>();
			LocacaoController locacaoController = new LocacaoController();
			locacaoMap.put("idcliente","12345678910");
			locacaoMap.put("idcarro","LUX-1234");
			locacaoMap.put("problema","");
			ArrayList<Locacao> list = new ArrayList<Locacao>();
			
			locacaoController.add(locacaoMap);
			
			locacaoController.searchAll(list);
			for (Locacao t : list) if(t.getIdcliente().equals("12345678910")) locacao1 = t;
			assertEquals("12345678910", locacao1.getIdcliente());
			
			locacaoController.remove(locacao1);

			
		} finally {
			clientController.remove(cliente1);
			carController.remove(carro1);
		}
	}

}
