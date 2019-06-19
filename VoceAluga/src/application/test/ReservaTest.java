package application.test;

import application.model.Cliente;
import application.model.Reserva;
import application.controller.ReservaController;
import application.controller.ClienteController;
import application.controller.ControllerException;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReservaTest {

	@Test
	void addsReservationAndRemovesIt() throws ControllerException {
		Reserva teste = new Reserva();
		HashMap<String,String> map = new HashMap<String,String>();
		ReservaController controller = new ReservaController();
		ArrayList<Reserva> list = new ArrayList<Reserva>();
		
		//add client
		Cliente c1;
		HashMap<String,String> cMap = new HashMap<String,String>();
		ClienteController cController = new ClienteController();
		
		cMap.put("cpf","12345678910");
		cMap.put("endereco","rua oito");
		cMap.put("nascimento","14/06/2019");
		cMap.put("nome","Debis Dibris");
		cMap.put("telefone","22222134");
		cMap.put("validadecnh","21/11/2020");
		c1 = cController.add(cMap);
		assertEquals("Debis Dibris", c1.getNome());
		//
		
		map.put("idcliente","12345678910");
		map.put("data","14/06/2019");
		map.put("grupo","B");
		map.put("modelo","carro preto");
		map.put("duracaodias","10");
		controller.add(map);
		
		controller.searchAll(list);
		for (Reserva t : list) if(t.getModelo().equals("carro preto")) teste = t;
		assertEquals("carro preto", teste.getModelo());
		
		controller.remove(teste);
		cController.remove(c1);
	}
	
	@Test
	void addsReservationWithInvalidCpf() throws ControllerException {
		HashMap<String,String> map = new HashMap<String,String>();
		ReservaController controller = new ReservaController();

		map.put("idcliente","29380572908");
		map.put("data","14/06/2019");
		map.put("grupo","B");
		map.put("modelo","carro preto");
		map.put("duracaodias","10");
		try {
			controller.add(map);
			fail("expected error");
		}
		catch (ControllerException e) {}
	}

}
