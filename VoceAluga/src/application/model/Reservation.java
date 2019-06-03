package application.model;

import java.time.LocalDate;

import javafx.beans.property.*;


public class Reservation {
	private IntegerProperty id;
	private SimpleObjectProperty<Customer> customer;
	private SimpleObjectProperty<Car> car;
	private SimpleIntegerProperty carid;
	
	private SimpleObjectProperty<LocalDate> begin; // quando inicia a reserva
	private SimpleObjectProperty<LocalDate> end;  // quando o cliente entregou o carro
	private SimpleObjectProperty<LocalDate> validity; // validade da reserva
	private boolean finished;
	
	
	public Reservation() {
		this.id = new SimpleIntegerProperty(0);
		this.carid = new SimpleIntegerProperty(0); 
		// chave que serah usada no bd, pois cada carro soh pode ser alocado de cada vez
		this.customer = new SimpleObjectProperty<Customer>();
		this.car = new SimpleObjectProperty<Car>();
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
		return customer.get();
	}
	public ObjectProperty<Customer> customerProperty(){
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer.set(customer);
	}
	public Car getCar() {
		return car.get();
	}
	public ObjectProperty<Car> carProperty() {
		return car;
	}
	public void setCar(Car car) {
		this.car.set(car);
		this.carid = (SimpleIntegerProperty) car.idProperty();
		// ao selecionar o carro automaticamente pegamos a chave dele para ser a
		// carid da classe allocation
	}
	public IntegerProperty caridProperty() {
		return id;
		// esse eh o get, ja que o set jah esta definido acima
	}
	public Integer getCarid() {
		return null;
		
	}
	public boolean isFinished() {
		return finished;
	}
	
	
		
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	
	public LocalDate getBegin() {
		return begin.get();
	}
	public ObjectProperty<LocalDate> beginProperty(){
		return begin;
	}
	public void setBegin(LocalDate begin) {
		this.begin.set(begin);
	}
	
	public LocalDate getEnd() {
		return end.get();
	}
	public ObjectProperty<LocalDate> endProperty(){
		return end;
	}
	
	public void setEnd(LocalDate end) {
		this.end.set(end);
	}
	
	public LocalDate getValidity() {
		return validity.get();
	}
	public ObjectProperty<LocalDate> validityProperty(){
		return validity;
	}
	
	public void setValidity(LocalDate validity) {
		this.validity.set(validity);
	}


}
