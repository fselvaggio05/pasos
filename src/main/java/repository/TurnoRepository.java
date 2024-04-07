
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
import entity.Consultorio;
import entity.Horario;
import entity.Paciente;
import entity.Practica;
import entity.Prescripcion;
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

public List<Turno> buscarTurnosAsignadosPaciente(Integer dni) {
	
	List<Turno> turnos = new ArrayList<Turno>();
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from turno t inner join horario h on h.idHorario=t.idHorario inner join practica p on p.id_practica=h.id_practica inner join profesional pf on pf.matricula=h.matricula inner join usuario usPac on usPac.dni=t.dni inner join usuario u on u.dni=pf.dni where t.dni=? and fecha_turno >= ? and estado_t='Asignado'");
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
			Paciente pac = new Paciente();
			
			tur.setId_turno(rs.getInt("idturno"));
			tur.setFecha_t(rs.getDate("fecha_turno").toLocalDate());
			tur.setHora_t(rs.getTime("hora_turno").toLocalTime());
			
			prof.setNombre(rs.getString("u.nombre"));
			prof.setApellido(rs.getString("u.apellido"));	
			
			pr.setDescripcion(rs.getString("p.descripcion")); 
			pr.setId_practica(rs.getInt("p.id_practica"));
			
			hor.setId_horario(rs.getInt("h.idHorario"));
			
			pac.setApellido(rs.getString("usPac.apellido"));
			pac.setNombre(rs.getString("usPac.nombre"));
			
			hor.setPractica(pr);
			hor.setProfesional(prof);
			tur.setHorario(hor);
			tur.setPaciente(pac);
					
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
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from turno t inner join horario h on h.idHorario=t.idHorario where t.estado_t='Libre' and h.matricula=? and t.fecha_turno>? order by t.fecha_turno, hora_turno");
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

public String registroTurnoConPrescripcion(Paciente pac, Integer id_turno, Integer id_presc) {
	
	Integer respUpdate = null;
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update turno set dni=?, estado_t='Asignado', id_prescripcion=? where idturno=?");
		stmt.setInt(1, pac.getDni());
		stmt.setInt(2,id_presc); 		
		stmt.setInt(3, id_turno);
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


public String registroTurnoSinPrescripcion(Paciente pac, Integer id_turno) {
	
	Integer respUpdate = null;
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update turno set dni=?, estado_t='Asignado' where idturno=?");
		stmt.setInt(1, pac.getDni());		
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


public String registrarAsistencia(Paciente pac, Turno tur) {
	
	
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update turno set estado_t='Asistido' where idturno=? and dni=?");		
		stmt.setInt(1, tur.getId_turno());
		stmt.setInt(2, pac.getDni());
		Integer resultadoUpdate  = stmt.executeUpdate();
		
		if(resultadoUpdate==1)
		{
			respuestaOperacion = "OK";
		}		
		else
		{
			respuestaOperacion=null;
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
	return respuestaOperacion;
}

public Turno buscarTurno(Integer idTurno) {
	
	Turno tur = new Turno();
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from turno t inner join horario h on h.idHorario=t.idHorario inner join practica pr on pr.id_practica=h.id_practica where idTurno=?");
		stmt.setInt(1, idTurno);
		rs = stmt.executeQuery();
		
		if(rs!=null && rs.next())
		{			
			
			Practica pr = new Practica();
			Horario h = new Horario();
			
			tur.setFecha_t(rs.getDate("fecha_turno").toLocalDate());
			tur.setId_turno(idTurno);
			h.setId_horario(rs.getInt("idHorario"));
			pr.setId_practica(rs.getInt("id_practica"));
			
			h.setPractica(pr);
			tur.setHorario(h);
			
			//si el turno tiene una prescripcion asociada, traigo el id
			if(rs.getInt("id_prescripcion")!=0)
			{
				Prescripcion presc = new Prescripcion();
				presc.setId_Prescripcion(rs.getInt("id_prescripcion")); //campo en tabla que refiere a prescripcion id_prescripcion				
				tur.setPrescripcion(presc);	
			}
			
			else
			{
				tur.setPrescripcion(null);
			}
			
					
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
	
	return tur;
}

public Practica buscarPracticaTurno(Integer id_turno) {
	
	
	Practica pr = new Practica();
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select pr.id_practica,descripcion from turno t inner join horario h on h.idHorario=t.idHorario inner join practica pr on pr.id_practica=h.id_practica where idTurno=?");
		stmt.setInt(1, id_turno);
		rs = stmt.executeQuery();
		
		if(rs!=null && rs.next())
		{			
			pr.setId_practica(rs.getInt("id_practica"));
			pr.setDescripcion(rs.getString("descripcion"));
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
	
	return pr;
}


//es correcto que el metodo este aca o puede ir en prescripcion? por ppio de responsabilidad
public void buscarPrescripcion(Turno tur) {
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from turno t inner join prescripcion pr on pr.id_ambulatoria=t.id_prescripcion where idTurno=?");
		stmt.setInt(1, tur.getId_turno());
		rs = stmt.executeQuery();
		
		if(rs!=null && rs.next())
		{			
			Prescripcion presc = new Prescripcion();	
			presc.setId_Prescripcion(rs.getInt("id_ambulatoria"));
			presc.setCant_sesiones(rs.getInt("cant_sesiones"));
			presc.setSesiones_asistidas(rs.getInt("sesiones_asistidas"));
			tur.setPrescripcion(presc);
			
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
	
	
	
}

public String asignarPrescripcionATurno(Turno tur, Integer id_prescripcion) {
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update turno set id_prescripcion=? where idturno=?");
		stmt.setInt(1, id_prescripcion);
		stmt.setInt(2, tur.getId_turno());
		Integer resultadoUpdate  = stmt.executeUpdate();
		
		if(resultadoUpdate==1)
		{
			respuestaOperacion = "OK";
		}		
		else
		{
			respuestaOperacion=null;
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

public String cancelaTurno(Integer idTurno) {
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update turno set estado_t='Libre',dni=NULL,id_prescripcion=NULL where idturno=?");
		stmt.setInt(1, idTurno);		
		Integer resultadoUpdate  = stmt.executeUpdate();
		
		if(resultadoUpdate==1)
		{
			respuestaOperacion = "OK";
		}		
		else
		{
			respuestaOperacion=null;
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

public List<Turno> buscarTurnosDelDia(LocalDate fecha_turno) {
	
	List<Turno> turnos = new ArrayList<Turno>();
	Date fecha = Date.valueOf(fecha_turno);	
	
	try 
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from turno t inner join horario h on h.idhorario=t.idhorario inner join practica pr on h.id_practica=pr.id_practica inner join usuario us on t.dni=us.dni inner join profesional prof on prof.matricula=h.matricula inner join usuario usu on usu.dni=prof.dni where fecha_turno=? and estado_t='Asignado'");
		stmt.setDate(1, fecha);
		rs = stmt.executeQuery();
		
		while(rs.next() && rs!=null)
		{
			Turno tur = new Turno();
			tur.setId_turno(rs.getInt("idturno"));
			tur.setFecha_t(rs.getDate("fecha_turno").toLocalDate());
			tur.setHora_t(rs.getTime("hora_turno").toLocalTime());
			tur.setEstado_t(rs.getString("estado_t"));
						
			Practica pr = new Practica();
			pr.setId_practica(rs.getInt("id_practica"));
			pr.setDescripcion(rs.getString("descripcion"));
			
			Profesional prof = new Profesional();
			prof.setMatricula(rs.getInt("prof.matricula"));
			prof.setApellido(rs.getString("usu.apellido"));
			prof.setNombre(rs.getString("usu.nombre"));
			
			Horario hor = new Horario();
			hor.setId_horario(rs.getInt("idhorario"));			

			Paciente pac = new Paciente();
			pac.setDni(rs.getInt("dni"));
			pac.setApellido(rs.getString("us.apellido"));
			pac.setNombre(rs.getString("nombre"));
			
			Consultorio cons = new Consultorio();
			cons.setId_consultorio(rs.getInt("id_consultorio"));
			
			hor.setPractica(pr);
			hor.setProfesional(prof);
			tur.setHorario(hor);
			tur.setPaciente(pac);	
			tur.setConsultorio(cons);
			turnos.add(tur);
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
	
	
	return turnos;
}

public List<Turno> buscarTurnosAsignadosProfesional(Integer matricula) {
	
	List<Turno> turnos = new ArrayList<Turno>();
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from turno t inner join horario h on h.idhorario=t.idhorario inner join practica pract on pract.id_practica=h.id_practica inner join usuario usPac on usPac.dni=t.dni inner join profesional p on p.matricula=h.matricula inner join usuario us on us.dni=p.dni where p.matricula=? and estado_t='Asignado' and fecha_turno>=?"); //agregamos filtro por fecha tambien?
		stmt.setInt(1, matricula);
		stmt.setDate(2, Date.valueOf(LocalDate.now()));
		rs = stmt.executeQuery();
		
		while(rs.next() && rs!=null)
		{
			Turno tur = new Turno();
			Practica pr = new Practica();
			Profesional prof = new Profesional();
			Horario hor = new Horario();
			Paciente pac = new Paciente();
			Consultorio cons = new Consultorio();
			
			tur.setId_turno(rs.getInt("idturno"));
			tur.setFecha_t(rs.getDate("fecha_turno").toLocalDate());
			tur.setHora_t(rs.getTime("hora_turno").toLocalTime());
			
			prof.setNombre(rs.getString("us.nombre"));
			prof.setApellido(rs.getString("us.apellido"));	
			
			pr.setDescripcion(rs.getString("pract.descripcion")); 
			pr.setId_practica(rs.getInt("pract.id_practica"));
			
			hor.setId_horario(rs.getInt("h.idHorario"));
			
			
			pac.setApellido(rs.getString("usPac.apellido"));
			pac.setNombre(rs.getString("usPac.nombre"));

			cons.setId_consultorio(rs.getInt("id_consultorio"));
			
						
			tur.setHorario(hor);
			tur.setPaciente(pac);
			hor.setPractica(pr);
			hor.setProfesional(prof);
			tur.setConsultorio(cons);
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

