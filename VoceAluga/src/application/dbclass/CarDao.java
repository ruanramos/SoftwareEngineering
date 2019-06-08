package application.dbclass;

import application.model.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDao extends DefaultDao<Car> {
	public CarDao() {
        tableName = "carros";
        columnNames = "placa,quilometragem,grupo,ano,modelo";
        interrogationMarks = "?,?,?,?,?"; //mesmo número de interrogações que de colunas em 'columnNames'
        formatForModifyingColumnsWhenUpdatingTableEntries =
                "placa=?,quilometragem=?,grupo=?,ano=?,modelo=?";
    }

    protected void fillInsertStatement(PreparedStatement statement, Car car) throws SQLException {
        statement.setString(1, car.getPlaca());
        statement.setDouble(2, car.getQuilometragem());
        statement.setString(3, car.getGrupo());
        statement.setInt(4, car.getAno());
        statement.setString(5, car.getModelo());
    }

    protected void fillDeleteStatement(PreparedStatement statement, Car car) throws SQLException {
    	statement.setString(1, "placa");
        statement.setString(2, car.getPlaca());
    }

    protected void fillUpdateStatement(PreparedStatement statement, Car car) throws SQLException {
    	fillInsertStatement(statement, car);
    }

    protected Car getObjectWithDbInformation(ResultSet set) throws SQLException{
        Car car = new Car();

        car.setPlaca(set.getString("placa"));
        car.setQuilometragem(set.getDouble("quilometragem"));
        car.setGrupo(set.getString("grupo"));
        car.setAno(set.getInt("ano"));
        car.setModelo(set.getString("modelo"));

        return car;
    }
}
