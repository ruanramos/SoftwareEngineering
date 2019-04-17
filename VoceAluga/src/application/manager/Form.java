package application.manager;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;

/*
 * Classe usada para inserir um novo cliente/carro/etc com as informações do formulário.
 * As chaves dos maps passados devem ser exatamente iguais aos nomes dos atributos da classe
 * que se deseja instanciar.
 * 
 * Exemplo de um map usado para a classe Customer, o map deve ser do tipo map<string,string>:
 * 		 {firstName = Osvaldo, cpf = 11111111111, }
 */
public class Form<T extends Object> {
	
	private Map<String,String> attributeMap = new TreeMap<>();
	private final Class<T> type;
	
	public Form(Class<T> type) {
		
		this.type = type;
		
		for(Field field : type.getDeclaredFields()) {
			field.setAccessible(true);
			attributeMap.put(field.getName(), "");
		}
	}
	
	public void addInfo(Map<String,String> map) {
		for(Map.Entry<String, String> entry : map.entrySet()) {
			attributeMap.replace(entry.getKey(), entry.getValue());
		}
	}
	
	public void fillObjectAttributes(T object) {
		try {
			for(Map.Entry<String, String> entry : attributeMap.entrySet()) {
				if( !entry.getValue().equals("") ) {
					Field field = type.getDeclaredField(entry.getKey());
					Class<?> fieldType = field.getType();
					field.setAccessible(true);
					field.set(object, convertString(entry.getValue(), fieldType));
				}
			}
		}
		catch( NoSuchFieldException e ) {
			throw new RuntimeException(e);
		}
		catch( SecurityException e ) {
			throw new RuntimeException(e);
		}
		catch( IllegalArgumentException e ) {
			throw new RuntimeException(e);
		}
		catch( IllegalAccessException e ) {
			throw new RuntimeException(e);
		}
	}
	
	private Object convertString(String string, Class<?> typeToConvert) {
		if(typeToConvert.equals(String.class)) {
			return string;
		}
		if(typeToConvert.equals(StringProperty.class)) {
			return new SimpleStringProperty(string);
		}
		if(typeToConvert.equals(IntegerProperty.class)) {
			return new SimpleIntegerProperty(Integer.parseInt(string));
		}
		if(typeToConvert.equals(int.class)) {
			return new SimpleIntegerProperty(Integer.parseInt(string));
		}
		if(typeToConvert.equals(float.class)) {
			return Float.parseFloat(string);
		}
		
		
		throw new RuntimeException("Tipo de parâmetro não definido");
	}
	
	public String getAttribute(String attributeName) {
		if(attributeMap.containsKey(attributeName)) {
			return attributeMap.get(attributeName);
		}
		
		throw new RuntimeException(new NoSuchFieldException());
	}
} 
