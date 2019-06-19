package application.model;

import java.util.ArrayList;
import java.util.List;

import application.dbclass.*;

public class LocacaoManager {
	
	private final int numberOfGroups = 3;
	private final char lesserGroup = 'A';
	
	private ArrayList<Carro> carroList;
	private ArrayList<Locacao> locacaoList;
	
	public <L extends List<Carro>> void getAvailableCars(L list) {
		resetLists();
		for(Carro carro : carroList) {
			if(isAvailable(carro)) {
				list.add(carro);
			}
		}
	}
	
	public void rentCarOnSpot(String idCliente, String idCarro) throws ModelException {
		LocacaoDao locacaoDao = new LocacaoDao();
		Locacao locacao;
		
		resetLists();
		locacaoDao.selectToList(locacaoList);
		if(clientRentedACarAlready(idCliente)) {
			throw new ModelException("O cliente de cpf " + idCliente + " ja possui um carro alugado em seu nome");
		}
		if( !isAvailable(idCarro) ) {
			throw new ModelException("Veiculo nao disponivel");
		}
		locacao = new Locacao();
		locacao.setIdcarro(idCarro);
		locacao.setIdcliente(idCliente);
		locacao.setProblema("");
		locacaoDao.insert(locacao);
	}
	
	public void rentCarFromReservation(Reserva reserva) throws ModelException {
		LocacaoDao locacaoDao = new LocacaoDao();
		Carro reservedCar;
		Locacao locacao;
		
		resetLists();
		if(clientRentedACarAlready(reserva.getIdcliente())) {
			throw new ModelException("O cliente de cpf " + reserva.getIdcliente() + " ja possui um carro alugado em seu nome");
		}
		reservedCar = choosesCar(reserva.getGrupo(), reserva.getModelo());
		if(reservedCar == null) {
			throw new ModelException("Nao foi possivel encontrar um veiculo para a reserva do cliente de cpf " + reserva.getIdcliente());
		}
		locacao = new Locacao();
		locacao.setIdcarro(reservedCar.getPlaca());
		locacao.setIdcliente(reserva.getIdcliente());
		locacao.setProblema("");
		locacaoDao.insert(locacao);
	}
	
	private Carro choosesCar(String groupString, String model) {
		char group = groupString.toUpperCase().charAt(0);
		
		for(char currentGroup = group; currentGroup < lesserGroup + numberOfGroups; currentGroup++) {
			Carro availableCar = selectCarOfGivenGroup(group, model);
			if (availableCar != null ) return availableCar;
		}
		for(char currentGroup = (char)(group - 1); currentGroup >= lesserGroup; currentGroup--) {
			Carro availableCar = selectCarOfGivenGroup(group, model);
			if (availableCar != null ) return availableCar;
		}	
		return null;
	}
	
	private Carro selectCarOfGivenGroup(char group, String optionalModel) {
		for(Carro carro : carroList) {
			char currentCarGroup = carro.getGrupo().toUpperCase().charAt(0);
			if( (currentCarGroup == group) && (optionalModel.equals(carro.getModelo())) && isAvailable(carro) ) {
				return carro;
			}
		}
		for(Carro carro : carroList) {
			char currentCarGroup = carro.getGrupo().toUpperCase().charAt(0);
			if((currentCarGroup == group) && isAvailable(carro)) {
				return carro;
			}
		}
		
		return null;
	}
	
	private boolean isAvailable(Carro carro) {
		return isAvailable(carro.getPlaca());
	}
	
	private boolean isAvailable(String idCarro) {
		for(Locacao locacao : locacaoList) {
			if(locacao.getIdcarro().equals(idCarro)) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean clientRentedACarAlready(String idCliente) {
		for(Locacao locacao : locacaoList) {
			if(locacao.getIdcliente().equals(idCliente)) {
				return true;
			}
		}
		return false;
	}
	
	private void resetLists() {
		CarroDao carroDao = new CarroDao();
		LocacaoDao locacaoDao = new LocacaoDao();
		
		carroList = new ArrayList<Carro>();
		locacaoList = new ArrayList<Locacao>();	
		carroDao.selectToList(carroList);
		locacaoDao.selectToList(locacaoList);
	}
}
