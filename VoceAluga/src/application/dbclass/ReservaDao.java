package application.dbclass;
import application.model.Reserva;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservaDao extends DefaultDao<Reserva> {
	
	public ReservaDao() {
        tableName = "reservas";
        columnNames = "id,idcliente,data,grupo,modelo,duracaodias";
        interrogationMarks = "?,?,?,?,?,?"; //mesmo número de interrogações que de colunas em 'columnNames'
        formatForModifyingColumnsWhenUpdatingTableEntries =
                "id=?,idcliente=?,data=?,grupo=?,modelo=?,duracaodias=?";
    }

    protected void fillInsertStatement(PreparedStatement statement, Reserva reserva) throws SQLException {
        statement.setInt(1, reserva.getId());
        statement.setString(2, reserva.getIdcliente());
        statement.setDate(3, Date.valueOf(reserva.getData()));
        statement.setString(4, reserva.getGrupo());
        statement.setString(5, reserva.getModelo());
        statement.setInt(6, reserva.getDuracaodias());
    }

    protected void fillDeleteStatement(PreparedStatement statement, Reserva reserva) throws SQLException {
    	statement.setString(1, "id");
    	statement.setInt(2, reserva.getId());
    }

    protected void fillUpdateStatement(PreparedStatement statement, Reserva reserva) throws SQLException {
    	fillInsertStatement(statement, reserva);
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
