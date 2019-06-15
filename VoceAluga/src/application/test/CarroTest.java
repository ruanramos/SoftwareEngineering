package application.test;

import application.model.Carro;
import application.controller.CarroController;
import application.controller.ControllerException;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarroTest {

	@Test
	void addsValidClientAndEditsItAndRemovesIt() throws ControllerException {
		Carro c1 = new Carro();
		HashMap<String,String> map = new HashMap<String,String>();
		CarroController controller = new CarroController();
		ArrayList<Carro> list = new ArrayList<Carro>();

		map.put("placa","LUX-1234");
		map.put("quilometragem","8000.8");
		map.put("grupo","B");
		map.put("ano","11");
		map.put("modelo","novo");
		controller.add(map);
		
		controller.searchAll(list);
		for (Carro c : list) if(c.getPlaca().equals("LUX-1234")) c1 = c;
		assertEquals(11, c1.getAno());
		
		list = new ArrayList<Carro>();
		map.put("ano","12");
		controller.edit(map);
		controller.searchAll(list);
		for (Carro c : list) if(c.getPlaca().equals("LUX-1234")) c1 = c;
		assertEquals(12, c1.getAno());
		
		controller.remove(c1);
	}

}
