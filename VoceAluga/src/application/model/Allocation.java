package application.model;

import javafx.beans.property.*;


public class Allocation {
	private IntegerProperty id;
	private Customer customer;
	private Car car;
	private SimpleIntegerProperty carid;
	private boolean finished;
	public Allocation() {
		this.id = new SimpleIntegerProperty(0);
		this.carid = new SimpleIntegerProperty(0); 
		// chave que serah usada no bd, pois cada carro soh pode ser alocado de cada vez
		this.customer = new Customer();
		this.car = new Car();
		this.setFinished(false);
		// se jah devolveram o carro ou nao, 
		// ter uma maneira de manter a alocacao mesmo que ela ainda nao esteja ativa
		// por exemplo, carro alocado -> usado -> devolvido -> finished == true
	}
	public int getId() {
		return id.get();
	}
	public IntegerProperty idProperty() {
		return id;
	}
	public void setId(int id) {
		this.id.set(id);
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
		this.carid = (SimpleIntegerProperty) car.idProperty();
		// ao selecionar o carro automaticamente pegamos a chave dele para ser a
		// carid da classe allocation
	}
	public IntegerProperty caridProperty() {
		return id;
		// esse eh o get, ja que o set jah esta definido acima
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	
	
	

}
