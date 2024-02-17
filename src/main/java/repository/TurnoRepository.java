package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.a.LocalDateTimeValueEncoder;
import com.mysql.cj.xdevapi.PreparableStatement.PreparableStatementFinalizer;

import conexionDB.FactoryConnection;

import entity.Horario;
import entity.Turno;

public class TurnoRepository {
	
	PreparedStatement stmt;
	ResultSet rs;
	String respuestaOperacion;

	
	
public String abrirAgenda(Turno t) {
	
		
		
			Date fecha_generacion;
			Date fecha_turno;
			Time hora_turno;
			Time hora_hasta;
			
			fecha_generacion = Date.valueOf(t.getFecha_generacion());
			fecha_turno = Date.valueOf(t.getFecha_t());
			hora_turno = Time.valueOf(t.getHora_t());
			hora_hasta = Time.valueOf(t.getHora_hasta_t());
			
			try
			{
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("insert into turno (fecha_generacion, fecha_turno, hora_turno, hora_hasta, estado_t, idHorario, id_consultorio) values(?,?,?,?,?,?,?)");
				stmt.setDate(1, fecha_generacion);
				stmt.setDate(2, fecha_turno);
				stmt.setTime(3, hora_turno);
				stmt.setTime(4, hora_hasta);				
				stmt.setString(5, t.getEstado_t());
				stmt.setInt(6, t.getId_horario());
				stmt.setInt(7, t.getId_consultorio());
				
				
				stmt.executeUpdate();
				
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
				
				if(rs.getDate("max(fecha_generacion)")!=null)
				{
					ult_fecha = rs.getDate("max(fecha_generacion)").toLocalDate();	
					
				}
				
				
							
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

public List<LocalDate> getFeriados() {
	
	List<LocalDate> feriados = new ArrayList<LocalDate>();
	
	
	try
	{
		
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from feriados_argentina");
		rs= stmt.executeQuery();
		
		while(rs!=null && rs.next())
		{
			LocalDate dia;			
			dia = rs.getDate("fecha_feriado").toLocalDate();
			feriados.add(dia);
			
		}
		
	}
	catch (SQLException e)
	{
		respuestaOperacion = e.toString();
	}
	
	finally
	{
		FactoryConnection.cerrarConexion(rs, stmt);
	}
	
	
	return feriados;
}

public List<Turno> buscarTurnosPendientes(Integer dni) {
	
	List<Turno> turnos = new ArrayList<Turno>();
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from turno t inner join horario h on h.idHorario=t.idHorario inner join practica p on p.id_practica=h.id_practica inner join profesional pf on pf.matricula=h.matricula inner join usuario u on u.dni=pf.dni where t.dni=? and fecha_turno > ?");
		stmt.setInt(1, dni);
		Date fechaHoy = Date.valueOf(LocalDate.now());
		stmt.setDate(2, fechaHoy);
		rs = stmt.executeQuery();
		
		while(rs!=null && rs.next())
		{
			Turno tur = new Turno();
			tur.setFecha_t(rs.getDate("fecha_turno").toLocalDate());
			tur.setHora_t(rs.getTime("hora_turno").toLocalTime());
			tur.setDesc_practica(rs.getString("p.descripcion"));
			tur.setNombre_profesional(rs.getString("nombre"));
			tur.setApellido_profesional(rs.getString("apellido"));
			turnos.add(tur);
			respuestaOperacion = "OK";
			
		}
				
		
	}
	catch (SQLException e)
	{
		respuestaOperacion = e.toString();
	}
	
	finally
	{
		FactoryConnection.cerrarConexion(rs, stmt);
	}
	
	
	return turnos;
}


public Integer validarConsultorioDisponible(LocalDate fecha, LocalTime hora_desde, LocalTime hora_hasta) {
	
	Time desde = null;
	Time hasta = null;
	desde = Time.valueOf(hora_desde);
	hasta = Time.valueOf(hora_hasta);
	Date fecha_turno = Date.valueOf(fecha);
	Integer cantTurnos = null;
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select count(*) from turno where fecha_turno=? and ((hora_turno>=? and hora_hasta<=?) or (hora_turno>? and hora_hasta<=?) or (hora_turno>=? and hora_hasta<?))");
		stmt.setDate(1, fecha_turno);
		stmt.setTime(2, desde);
		stmt.setTime(3, hasta);
		stmt.setTime(4, desde);
		stmt.setTime(5, hasta);
		stmt.setTime(6, desde);
		stmt.setTime(7, hasta);
		rs = stmt.executeQuery();		
		
		if(rs!=null && rs.next())
		{			
			cantTurnos = Integer.parseInt(rs.getString(1));				
		}		
						
		
		else
		{
			cantTurnos = 0;
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
	
	
	return cantTurnos;
	
}
	
}


