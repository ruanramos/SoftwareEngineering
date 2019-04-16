package application.dbclass;

import application.model.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDao extends DefaultDao<Car> {
	public CarDao() {
        tableName = "Carro";
        columnNames = "Modelo,Classe,Idade,Quilometragem";
        interrogationMarks = "?,?,?,?"; //mesmo número de interrogações que de colunas em 'columnNames'
        formatForModifyingColumnsWhenUpdatingTableEntries =
                "Modelo=?,Classe=?,Idade=?,Quilometragem=?";
    }

    protected void fillInsertStatement(PreparedStatement statement, Car car) throws SQLException {
        statement.setString(1, car.getModel());
        statement.setString(2, car.getClassification());	//converte char para int
        statement.setInt(3, car.getAge());
        statement.setFloat(4, car.getMileage());
    }

    protected void fillDeleteStatement(PreparedStatement statement, Car car) throws SQLException {
        statement.setInt(1, car.getId());
    }

    protected void fillUpdateStatement(PreparedStatement statement, Car car) throws SQLException {
    	statement.setString(1, car.getModel());
        statement.setString(2, car.getClassification());	//converte char para int
        statement.setInt(3, car.getAge());
        statement.setFloat(4, car.getMileage());
    }

    protected Car getObjectWithDbInformation(ResultSet set) throws SQLException{
        Car car = new Car();

        car.setId(set.getInt("idCarro"));
        car.setModel(set.getString("Modelo"));
        car.setClassification(set.getString("Classe"));
        car.setAge(set.getInt("Idade"));
        car.setMileage(set.getFloat("Quilometragem"));

        return car;
    }
}
