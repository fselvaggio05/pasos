package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement.PreparableStatementFinalizer;

import conexionDB.FactoryConnection;
import entity.Horario;
import entity.Turno;

public class TurnoRepository {
	
	PreparedStatement stmt;
	ResultSet rs;
	String respuestaOperacion;

	
	
public String abrirAgenda(List<Turno> turnos) {
	
		Integer idTurno = 3;
		
		for (Turno t : turnos)
		{
			Date fecha_generacion;
			Date fecha_turno;
			Time hora_turno;
			
			fecha_generacion = Date.valueOf(t.getFecha_generacion());
			fecha_turno = Date.valueOf(t.getFecha_t());
			hora_turno = Time.valueOf(t.getHora_t());
			
			try
			{
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("insert into turno (fecha_generacion, fecha_turno, hora_turno, estado_t, idHorario, idTurno) values(?,?,?,?,?,?)");
				stmt.setDate(1, fecha_generacion);
				stmt.setDate(2, fecha_turno);
				stmt.setTime(3, hora_turno);
				stmt.setString(4, t.getEstado_t());
				stmt.setInt(5, t.getId_horario());
				stmt.setInt(6, idTurno);
				stmt.executeUpdate();
				idTurno++;
				respuestaOperacion = "OK";					
				
			}
			
			catch(SQLException e)
			{
				respuestaOperacion = e.toString();
			}
			
			finally
			{
				FactoryConnection.cerrarConexion(rs, stmt);
			}
		}
		
		
		return respuestaOperacion;
		
		
	}

public LocalDate getUltimaFechaGeneracion() {
		
		LocalDate ult_fecha = null;
		
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select max(fecha_generacion) from turno ");
			rs = stmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				ult_fecha = rs.getDate("max(fecha_generacion)").toLocalDate();				
			}
			
			
			else 
			{
				return null;
			}
		}
		
		catch (SQLException e)
		{
			e.toString();
		}
		
		finally
		{
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		
		
		return ult_fecha;
		
	}

	
	
}
