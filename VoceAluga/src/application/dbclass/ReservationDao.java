package application.dbclass;
import application.model.Reservation;
import application.model.Reservation;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDao extends DefaultDao<Reservation> {
	
	public ReservationDao() {
        tableName = "reservas";
        columnNames = "id,idcliente,data,grupo,modelo,duracaodias";
        interrogationMarks = "?,?,?,?,?,?"; //mesmo número de interrogações que de colunas em 'columnNames'
        formatForModifyingColumnsWhenUpdatingTableEntries =
                "id=?,idcliente=?,data=?,grupo=?,modelo=?,duracaodias=?";
    }

    protected void fillInsertStatement(PreparedStatement statement, Reservation reservation) throws SQLException {
        statement.setInt(1, reservation.getId());
        statement.setString(2, reservation.getIdcliente());
        statement.setDate(3, Date.valueOf(reservation.getData()));
        statement.setString(4, reservation.getGrupo());
        statement.setString(5, reservation.getModelo());
        statement.setInt(6, reservation.getDuracaodias());
    }

    protected void fillDeleteStatement(PreparedStatement statement, Reservation reservation) throws SQLException {
    	statement.setString(1, "id");
    	statement.setInt(2, reservation.getId());
    }

    protected void fillUpdateStatement(PreparedStatement statement, Reservation reservation) throws SQLException {
    	fillInsertStatement(statement, reservation);
    }

    protected Reservation getObjectWithDbInformation(ResultSet set) throws SQLException{
        Reservation reservation = new Reservation();

        reservation.setId(set.getInt("id"));
        reservation.setIdcliente(set.getString("idcliente"));
        reservation.setData(set.getDate("data").toLocalDate());
        reservation.setGrupo(set.getString("grupo"));
        reservation.setModelo(set.getString("modelo"));
        reservation.setDuracaodias(set.getInt("duracaodias"));
        
        return reservation;
    }
}
