package application.dbclass;
import application.model.Reserva;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservaDao extends DefaultDao<Reserva> {
	
	public ReservaDao() {
        tableName = "reservas";
        columnNames = "idcliente,data,grupo,modelo,duracaodias";
        interrogationMarks = "?,?,?,?,?"; //mesmo número de interrogações que de colunas em 'columnNames'
        formatForModifyingColumnsWhenUpdatingTableEntries =
                "idcliente=?,data=?,grupo=?,modelo=?,duracaodias=?";
        primaryKey = "id";
    }

    protected void fillInsertStatement(PreparedStatement statement, Reserva reserva) throws SQLException {
        statement.setString(1, reserva.getIdcliente());
        statement.setDate(2, Date.valueOf(reserva.getData()));
        statement.setString(3, reserva.getGrupo());
        statement.setString(4, reserva.getModelo());
        statement.setInt(5, reserva.getDuracaodias());
    }

    protected void fillDeleteStatement(PreparedStatement statement, Reserva reserva) throws SQLException {
    	statement.setInt(1, reserva.getId());
    }

    protected void fillUpdateStatement(PreparedStatement statement, Reserva reserva) throws SQLException {
    	statement.setString(1, reserva.getIdcliente());
        statement.setDate(2, Date.valueOf(reserva.getData()));
        statement.setString(3, reserva.getGrupo());
        statement.setString(4, reserva.getModelo());
        statement.setInt(5, reserva.getDuracaodias());
        statement.setInt(6, reserva.getId());
    }

    protected Reserva getObjectWithDbInformation(ResultSet set) throws SQLException{
        Reserva reserva = new Reserva();

        reserva.setId(set.getInt("id"));
        reserva.setIdcliente(set.getString("idcliente"));
        reserva.setData(set.getDate("data").toLocalDate());
        reserva.setGrupo(set.getString("grupo"));
        reserva.setModelo(set.getString("modelo"));
        reserva.setDuracaodias(set.getInt("duracaodias"));
        
        return reserva;
    }
}
