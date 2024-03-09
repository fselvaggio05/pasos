
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
import entity.Practica;
import entity.Profesional;
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
				stmt.setInt(6, t.getHorario().getId_horario());
				stmt.setInt(7, t.getConsultorio().getId_consultorio());
				
				
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

public List<Turno> buscarTurnosAsignados(Integer dni) {
	
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
			Practica pr = new Practica();
			Profesional prof = new Profesional();
			Horario hor = new Horario();
			
			tur.setId_turno(rs.getInt("idturno"));
			tur.setFecha_t(rs.getDate("fecha_turno").toLocalDate());
			tur.setHora_t(rs.getTime("hora_turno").toLocalTime());
			
			prof.setNombre(rs.getString("nombre"));
			prof.setApellido(rs.getString("apellido"));	
			
			pr.setDescripcion(rs.getString("p.descripcion")); 
			pr.setId_practica(rs.getInt("p.id_practica"));
			
			hor.setId_horario(rs.getInt("h.idHorario"));
			hor.setPractica(pr);
			hor.setProfesional(prof);
			
			tur.setHorario(hor);
					
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

public List<Turno> buscarTurnosDisponibles(Integer matricula) {
	
	List<Turno> turnosDisponibles = new ArrayList<Turno>();
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from turno t inner join horario h on h.idHorario=t.idHorario where t.estado_t='Libre' and h.matricula=? and t.fecha_turno>? order by t.fecha_turno");
		stmt.setInt(1, matricula);
		stmt.setDate(2, Date.valueOf(LocalDate.now()));
		rs = stmt.executeQuery();
		
		while(rs!=null && rs.next())
		{
			Turno tur = new Turno();
			tur.setId_turno(rs.getInt("idturno"));
			tur.setFecha_t(rs.getDate("fecha_turno").toLocalDate());
			tur.setHora_t(rs.getTime("hora_turno").toLocalTime());
			turnosDisponibles.add(tur);
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
	
	return turnosDisponibles;
}

public String registroTurno(Integer dni, Integer id_turno) {
	
	Integer respUpdate = null;
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update turno set dni=?, estado_t='Asignado' where idturno=?");
		stmt.setInt(1, dni);
		stmt.setInt(2, id_turno);
		respUpdate = stmt.executeUpdate();
		
		if(respUpdate==1)
		{
			respuestaOperacion = "OK";
		}
		
		else
		{
			respuestaOperacion  = null;
		}
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
	
public List<Turno> buscarTurnosAsistidosAmbulatorios(LocalDate fecha_desde, LocalDate fecha_hasta) {
	//Busca de todos los turnos registrados, aquellos que fueron asistidos, de tipo Ambulatorio y están pendientes de cobrar para el rango de fechas ingresado
List<Turno> turnosPendientesACobrar = new ArrayList<Turno>();
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from turno t where t.fecha_turno>=? and t.fecha_turno<=? and t.tipo='Ambulatorio' and t.estado_t='Asistido' order by t.fecha_turno");
		stmt.setDate(1, Date.valueOf(fecha_desde));
		stmt.setDate(2, Date.valueOf(fecha_hasta));
		rs = stmt.executeQuery();
		
		while(rs!=null && rs.next())
		{
			Turno tur = new Turno();
			tur.setId_turno(rs.getInt("idturno"));
			tur.setFecha_t(rs.getDate("fecha_turno").toLocalDate());
			tur.setHora_t(rs.getTime("hora_turno").toLocalTime());
			// tur.setHorario(rs.getInt("idhorario"));
			turnosPendientesACobrar.add(tur);
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
	
	return turnosPendientesACobrar;
	

} } 

