package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import conexionDB.FactoryConnection;
import entity.Consultorio;
import entity.Enumeradores;
import entity.Equipo;
import entity.Horario;
import entity.MontosPractica;
import entity.ObraSocial;
import entity.Paciente;
import entity.Practica;
import entity.Prescripcion;
import entity.Profesional;
import entity.Turno;

public class TurnoRepository {

	PreparedStatement stmt;
	ResultSet rs;
	String respuestaOperacion;

	//Create
	public String abrirAgenda(Turno t) {
		Date fecha_generacion;
		Date fecha_turno;
		Time hora_turno;
		Time hora_hasta;

		fecha_generacion = Date.valueOf(t.getFecha_generacion());
		fecha_turno = Date.valueOf(t.getFecha_t());
		hora_turno = Time.valueOf(t.getHora_t());
		hora_hasta = Time.valueOf(t.getHora_hasta_t());

		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"insert into turno (fecha_generacion, fecha_turno, hora_turno, hora_hasta, estado_t, idHorario, id_consultorio) values(?,?,?,?,?,?,?)");
			stmt.setDate(1, fecha_generacion);
			stmt.setDate(2, fecha_turno);
			stmt.setTime(3, hora_turno);
			stmt.setTime(4, hora_hasta);
			stmt.setString(5, t.getEstado_t());
			stmt.setInt(6, t.getHorario().getId_horario());
			stmt.setInt(7, t.getConsultorio().getId_consultorio());
			stmt.executeUpdate();
			respuestaOperacion = "OK";
		} catch (SQLException e) {
			respuestaOperacion = e.toString();
		} finally {
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		return respuestaOperacion;
	}

	//Read
		//Ultima Generación
		public LocalDate getUltimaFechaGeneracion() {
			LocalDate ult_fecha = null;
	
			try {
				stmt = FactoryConnection.getInstancia().getConn()
						.prepareStatement("select max(fecha_generacion) from turno ");
				rs = stmt.executeQuery();
	
				if (rs != null && rs.next()) {
					if (rs.getDate("max(fecha_generacion)") != null) {
						ult_fecha = rs.getDate("max(fecha_generacion)").toLocalDate();
					}
				}
			} catch (SQLException e) {
				e.toString();
			} finally {
				FactoryConnection.cerrarConexion(rs, stmt);
			}
			return ult_fecha;
		}

		//Feriados
		public List<LocalDate> getFeriados() {
		List<LocalDate> feriados = new ArrayList<LocalDate>();

		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from feriados_argentina");
			rs = stmt.executeQuery();

			while (rs != null && rs.next()) {
				LocalDate dia;
				dia = rs.getDate("fecha_feriado").toLocalDate();
				feriados.add(dia);
			}
		} catch (SQLException e) {
			respuestaOperacion = e.toString();
		} finally {
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		return feriados;
	}
			
		//Turnos por Paciente
		public List<Turno> buscarTurnosAsignadosPaciente(Integer dni) {
			List<Turno> turnos = new ArrayList<Turno>();
	
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from turno t inner join horario h on h.idHorario=t.idHorario inner join practica p on p.id_practica=h.id_practica inner join profesional pf on pf.matricula=h.matricula inner join usuario usPac on usPac.dni=t.dni inner join usuario u on u.dni=pf.dni inner join consultorio c on t.id_consultorio = c.id_consultorio where t.dni=? and fecha_turno >= sysdate() and estado_t='Asignado'");
				stmt.setInt(1, dni);
				rs = stmt.executeQuery();
	
				while (rs != null && rs.next()) {
					Turno unTurno = new Turno();
					Practica unaPractica = new Practica();
					Profesional unProfesional = new Profesional();
					Horario unHorario = new Horario();
					Paciente unPaciente = new Paciente();
					Consultorio unConsultorio = new Consultorio();
	
					unTurno.setId_turno(rs.getInt("idturno"));
					unTurno.setFecha_t(rs.getDate("fecha_turno").toLocalDate());
					unTurno.setHora_t(rs.getTime("hora_turno").toLocalTime());
					unTurno.setEstado_t(rs.getString("estado_t"));
					
	
					unProfesional.setNombre(rs.getString("u.nombre"));
					unProfesional.setApellido(rs.getString("u.apellido"));
	
					unaPractica.setDescripcion(rs.getString("p.descripcion"));
					unaPractica.setId_practica(rs.getInt("p.id_practica"));
	
					unHorario.setId_horario(rs.getInt("h.idHorario"));
	
					unPaciente.setApellido(rs.getString("usPac.apellido"));
					unPaciente.setNombre(rs.getString("usPac.nombre"));
					
					unConsultorio.setId_consultorio(rs.getInt("c.id_consultorio"));
					unConsultorio.setDescripcion(rs.getString("c.descripcion"));
					unConsultorio.setEstado(rs.getBoolean("c.estado"));
	
					unHorario.setPractica(unaPractica);
					unHorario.setProfesional(unProfesional);
					unTurno.setHorario(unHorario);
					unTurno.setPaciente(unPaciente);
					unTurno.setConsultorio(unConsultorio);
	
					turnos.add(unTurno);
					respuestaOperacion = "OK";
				}
			} catch (SQLException e) {
				respuestaOperacion = e.toString();
			}
	
			finally {
				FactoryConnection.cerrarConexion(rs, stmt);
			}
			return turnos;
		}
		
		//Consultorio Disponible
		public Integer validarConsultorioDisponible(LocalDate fecha, LocalTime hora_desde, LocalTime hora_hasta) {
			Time desde = null;
			Time hasta = null;
			desde = Time.valueOf(hora_desde);
			hasta = Time.valueOf(hora_hasta);
			Date fecha_turno = Date.valueOf(fecha);
			Integer cantTurnos = null;
	
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
						"select count(*) from turno where fecha_turno=? and ((hora_turno>=? and hora_hasta<=?) or (hora_turno>? and hora_hasta<=?) or (hora_turno>=? and hora_hasta<?))");
				stmt.setDate(1, fecha_turno);
				stmt.setTime(2, desde);
				stmt.setTime(3, hasta);
				stmt.setTime(4, desde);
				stmt.setTime(5, hasta);
				stmt.setTime(6, desde);
				stmt.setTime(7, hasta);
				rs = stmt.executeQuery();
	
				if (rs != null && rs.next()) {
					cantTurnos = Integer.parseInt(rs.getString(1));
				} else {
					cantTurnos = 0;
				}
			} catch (SQLException e) {
				e.toString();
			} finally {
				FactoryConnection.cerrarConexion(rs, stmt);
			}
			return cantTurnos;
		}

		// Turnos Disponibles por Profesional
		public List<Turno> buscarTurnosDisponibles(Integer matricula, Integer practica) {
			List<Turno> turnosDisponibles = new ArrayList<Turno>();
	
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
						"select * from turno t inner join horario h on h.idHorario=t.idHorario inner join practica pr on pr.id_practica=h.id_practica inner join profesional prof on h.matricula=prof.matricula inner join usuario u on prof.dni=u.dni inner join consultorio c on c.id_consultorio=t.id_consultorio left join equipo eq on eq.id_equipo=pr.id_equipo where t.estado_t='Libre' and h.matricula=? and t.fecha_turno>? and h.id_practica =? order by t.fecha_turno, hora_turno");
				stmt.setInt(1, matricula);
				stmt.setDate(2, Date.valueOf(LocalDate.now()));
				stmt.setInt(3, practica);
				rs = stmt.executeQuery();
	
				while (rs != null && rs.next()) {
					Turno unTurno = new Turno();
					Horario unHorario = new Horario();
					Practica unaPractica = new Practica();
					Equipo unEquipo = new Equipo();
					Profesional unProfesional = new Profesional();
					Consultorio unConsultorio = new Consultorio();
					
					unTurno.setId_turno(rs.getInt("t.idturno"));
					unTurno.setFecha_generacion(rs.getDate("t.fecha_generacion").toLocalDate());
					unTurno.setFecha_t(rs.getDate("fecha_turno").toLocalDate());
					unTurno.setHora_t(rs.getTime("t.hora_turno").toLocalTime());
					unTurno.setHora_hasta_t(rs.getTime("t.hora_hasta").toLocalTime());
					unTurno.setEstado_t(rs.getString("t.estado_t"));
					//Armamos el Horario para el Turno
					unHorario.setId_horario(rs.getInt("idHorario"));
					unHorario.setFecha_alta(rs.getDate("h.fecha_alta").toLocalDate());
					if(rs.getDate("h.fecha_baja")==null) 
					{
						unHorario.setFecha_baja(null);
					}
					else 
					{
						unHorario.setFecha_baja(rs.getDate("h.fecha_baja").toLocalDate());
					}
					unHorario.setHora_desde(rs.getTime("h.hora_desde").toLocalTime());
					unHorario.setHora_hasta(rs.getTime("h.hora_hasta").toLocalTime());
					unHorario.setDia_semana(rs.getInt("h.dia_semana"));
					
					//Armo la Practica para el Horario
					unaPractica.setId_practica(rs.getInt("id_practica"));
					if(rs.getInt("pr.tipo_practica")==1) {
						unaPractica.setTipo_practica(Enumeradores.TipoPractica.AMBULATORIA);
					}
					else {
						unaPractica.setTipo_practica(Enumeradores.TipoPractica.DISCAPACIDAD);
					}
					unaPractica.setDescripcion(rs.getString("pr.descripcion"));
					unaPractica.setEstado(rs.getBoolean("pr.estado"));
					unaPractica.setDuracion(rs.getInt("pr.duracion"));
					if(rs.getDate("pr.fecha_baja")==null) 
					{
						unaPractica.setFecha_baja(null);
					}
					else 
					{
						unaPractica.setFecha_baja(rs.getDate("pr.fecha_baja").toLocalDate());
					}
					
					//Armo el Equipo para la Practica
					unEquipo.setId_equipo(rs.getInt("eq.id_equipo"));
					unEquipo.setTipo_equipo(rs.getString("eq.tipo_equipo"));
					unEquipo.setDescripcion(rs.getString("eq.descripcion"));
					unEquipo.setEstado(rs.getBoolean("eq.estado"));
					if(rs.getDate("eq.fecha_baja")==null) 
					{
						unEquipo.setFecha_baja(null);
					}
					else 
					{
						unEquipo.setFecha_baja(rs.getDate("eq.fecha_baja").toLocalDate());
					}
					//Asigno el Equipo a la Practica
					unaPractica.setEquipo(unEquipo);
					//Asigno Practica a Horario
					unHorario.setPractica(unaPractica);
					
					//Armo el Profesional para el Horario
					unProfesional.setDni(rs.getInt("u.dni"));
					unProfesional.setMatricula(rs.getInt("prof.matricula"));
					unProfesional.setApellido(rs.getString("u.apellido"));
					unProfesional.setNombre(rs.getString("u.nombre"));
					unProfesional.setTelefono(rs.getString("u.telefono"));
					unProfesional.setFecha_nacimiento(rs.getDate("u.fecha_nacimiento").toLocalDate());
					unProfesional.setGenero(rs.getString("u.genero"));
					unProfesional.setEmail(rs.getString("u.email"));
					unProfesional.setClave(rs.getString("u.clave"));
					unProfesional.setTipo_usuario(rs.getInt("u.tipo_usuario"));
					//Asigno el Profesional al Horario
					unHorario.setProfesional(unProfesional);					
					
					unTurno.setHorario(unHorario);
					// si el turno tiene una prescripcion asociada, traigo el id
					if (rs.getInt("id_prescripcion") != 0) {
						Prescripcion unaPrescripcion = new Prescripcion();
						unaPrescripcion.setId_prescripcion(rs.getInt("id_prescripcion")); 
						unTurno.setPrescripcion(unaPrescripcion);
					} else {
						unTurno.setPrescripcion(null);
					}
					
					//Armo el Consultorio para el Turno
					unConsultorio.setId_consultorio(rs.getInt("c.id_consultorio"));
					unConsultorio.setDescripcion(rs.getString("c.descripcion"));
					unConsultorio.setEstado(rs.getBoolean("c.estado"));
					if(rs.getDate("c.fecha_baja")==null) 
					{
						unConsultorio.setFecha_baja(null);
					}
					else 
					{
						unConsultorio.setFecha_baja(rs.getDate("c.fecha_baja").toLocalDate());
					}
					//Asigno el Consultorio al Turno
					unTurno.setConsultorio(unConsultorio);
					//Agrego el Turno a la Lista.
					turnosDisponibles.add(unTurno);
				}
			} catch (SQLException e) {
				respuestaOperacion = e.toString();
			} finally {
				FactoryConnection.cerrarConexion(rs, stmt);
			}
			return turnosDisponibles;
		}

		//Turno por ID
		public Turno buscarTurno(Integer idTurno) {
			Turno unTurno = new Turno();

			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from turno t inner join horario h on h.idHorario=t.idHorario inner join practica pr on pr.id_practica=h.id_practica inner join profesional prof on h.matricula=prof.matricula inner join usuario u on prof.dni=u.dni inner join consultorio c on c.id_consultorio=t.id_consultorio left join equipo eq on eq.id_equipo=pr.id_equipo left join prescripcion presc on presc.id_prescripcion = t.id_prescripcion where idTurno=?");
				stmt.setInt(1, idTurno);
				rs = stmt.executeQuery();

				if (rs != null && rs.next()) {
					Horario unHorario = new Horario();
					Practica unaPractica = new Practica();
					Equipo unEquipo = new Equipo();
					Profesional unProfesional = new Profesional();
					Consultorio unConsultorio = new Consultorio();

					unTurno.setId_turno(idTurno);
					unTurno.setFecha_generacion(rs.getDate("t.fecha_generacion").toLocalDate());
					unTurno.setFecha_t(rs.getDate("fecha_turno").toLocalDate());
					unTurno.setHora_t(rs.getTime("t.hora_turno").toLocalTime());
					unTurno.setHora_hasta_t(rs.getTime("t.hora_hasta").toLocalTime());
					unTurno.setEstado_t(rs.getString("t.estado_t"));
					
					//Armamos el Horario para el Turno
					unHorario.setId_horario(rs.getInt("idHorario"));
					unHorario.setFecha_alta(rs.getDate("h.fecha_alta").toLocalDate());
					if(rs.getDate("h.fecha_baja")==null) 
					{
						unHorario.setFecha_baja(null);
					}
					else 
					{
						unHorario.setFecha_baja(rs.getDate("h.fecha_baja").toLocalDate());
					}
					unHorario.setHora_desde(rs.getTime("h.hora_desde").toLocalTime());
					unHorario.setHora_hasta(rs.getTime("h.hora_hasta").toLocalTime());
					unHorario.setDia_semana(rs.getInt("h.dia_semana"));
					
					//Armo la Practica para el Horario
					unaPractica.setId_practica(rs.getInt("id_practica"));
					if(rs.getInt("pr.tipo_practica")==1) {
						unaPractica.setTipo_practica(Enumeradores.TipoPractica.AMBULATORIA);
					}
					else {
						unaPractica.setTipo_practica(Enumeradores.TipoPractica.DISCAPACIDAD);
					}
					unaPractica.setDescripcion(rs.getString("pr.descripcion"));
					unaPractica.setEstado(rs.getBoolean("pr.estado"));
					unaPractica.setDuracion(rs.getInt("pr.duracion"));
					if(rs.getDate("pr.fecha_baja")==null) 
					{
						unaPractica.setFecha_baja(null);
					}
					else 
					{
						unaPractica.setFecha_baja(rs.getDate("pr.fecha_baja").toLocalDate());
					}
					
					//Armo el Equipo para la Practica
					unEquipo.setId_equipo(rs.getInt("eq.id_equipo"));
					unEquipo.setTipo_equipo(rs.getString("eq.tipo_equipo"));
					unEquipo.setDescripcion(rs.getString("eq.descripcion"));
					unEquipo.setEstado(rs.getBoolean("eq.estado"));
					if(rs.getDate("eq.fecha_baja")==null) 
					{
						unEquipo.setFecha_baja(null);
					}
					else 
					{
						unEquipo.setFecha_baja(rs.getDate("eq.fecha_baja").toLocalDate());
					}
					//Asigno el Equipo a la Practica
					unaPractica.setEquipo(unEquipo);
					//Asigno Practica a Horario
					unHorario.setPractica(unaPractica);
					
					//Armo el Profesional para el Horario
					unProfesional.setDni(rs.getInt("u.dni"));
					unProfesional.setMatricula(rs.getInt("prof.matricula"));
					unProfesional.setApellido(rs.getString("u.apellido"));
					unProfesional.setNombre(rs.getString("u.nombre"));
					unProfesional.setTelefono(rs.getString("u.telefono"));
					unProfesional.setFecha_nacimiento(rs.getDate("u.fecha_nacimiento").toLocalDate());
					unProfesional.setGenero(rs.getString("u.genero"));
					unProfesional.setEmail(rs.getString("u.email"));
					unProfesional.setClave(rs.getString("u.clave"));
					unProfesional.setTipo_usuario(rs.getInt("u.tipo_usuario"));
					//Asigno el Profesional al Horario
					unHorario.setProfesional(unProfesional);					
					
					unTurno.setHorario(unHorario);
					// si el turno tiene una prescripcion asociada, traigo el id
					if (rs.getInt("id_prescripcion") != 0) {
						Prescripcion unaPrescripcion = new Prescripcion();
						unaPrescripcion.setId_prescripcion(rs.getInt("presc.id_prescripcion"));
						unaPrescripcion.setFecha_prescripcion(rs.getDate("presc.fecha_prescripcion").toLocalDate());
						unaPrescripcion.setPractica(unaPractica);
						unaPrescripcion.setCant_sesiones(rs.getInt("presc.cant_sesiones"));
						unaPrescripcion.setSesiones_asistidas(rs.getInt("presc.sesiones_asistidas"));
						unTurno.setPrescripcion(unaPrescripcion);
					} else {
						unTurno.setPrescripcion(null);
					}
					
					//Armo el Consultorio para el Turno
					unConsultorio.setId_consultorio(rs.getInt("c.id_consultorio"));
					unConsultorio.setDescripcion(rs.getString("c.descripcion"));
					unConsultorio.setEstado(rs.getBoolean("c.estado"));
					if(rs.getDate("c.fecha_baja")==null) 
					{
						unConsultorio.setFecha_baja(null);
					}
					else 
					{
						unConsultorio.setFecha_baja(rs.getDate("c.fecha_baja").toLocalDate());
					}
					//Asigno el Consultorio al Turno
					unTurno.setConsultorio(unConsultorio);
				}
			} catch (SQLException e) {
				respuestaOperacion = e.toString();
			} finally {
				FactoryConnection.cerrarConexion(rs, stmt);
			}
			return unTurno;
		}
		
		//Turno por ID
		public Practica buscarPracticaTurno(Integer id_turno) {
			Practica pr = new Practica();

			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
						"select pr.id_practica,descripcion from turno t inner join horario h on h.idHorario=t.idHorario inner join practica pr on pr.id_practica=h.id_practica where idTurno=?");
				stmt.setInt(1, id_turno);
				rs = stmt.executeQuery();

				if (rs != null && rs.next()) {
					pr.setId_practica(rs.getInt("id_practica"));
					pr.setDescripcion(rs.getString("descripcion"));
				}
			}

			catch (SQLException e) {
				respuestaOperacion = e.toString();
			} finally {
				FactoryConnection.cerrarConexion(rs, stmt);
			}
			return pr;
		}
		
		//Recuperar Prescripcion por Turno
		//es correcto que el metodo este aca o puede ir en prescripcion? por ppio de responsabilidad
		public Prescripcion buscarPrescripcion(Turno tur) {
			Prescripcion presc = null;
			try {				
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
						"select * from turno t inner join prescripcion pr on pr.id_prescripcion=t.id_prescripcion where idTurno=?");
				stmt.setInt(1, tur.getId_turno());
				rs = stmt.executeQuery();

				if (rs != null && rs.next()) {
					presc = new Prescripcion();
					presc.setId_prescripcion(rs.getInt("id_ambulatoria"));
					presc.setCant_sesiones(rs.getInt("cant_sesiones"));
					presc.setSesiones_asistidas(rs.getInt("sesiones_asistidas"));
					tur.setPrescripcion(presc);					
				}
			} catch (SQLException e) {
				respuestaOperacion = e.toString();
			} finally {
				FactoryConnection.cerrarConexion(rs, stmt);
			}
			return presc;
		}
		
		//Turnos por Día
		public List<Turno> buscarTurnosDelDia(LocalDate fecha_turno) {
			List<Turno> turnos = new ArrayList<Turno>();
			Date fecha = Date.valueOf(fecha_turno);

			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
						"select * from turno t inner join horario h on h.idhorario=t.idhorario inner join practica pr on h.id_practica=pr.id_practica inner join usuario us on t.dni=us.dni inner join profesional prof on prof.matricula=h.matricula inner join usuario usu on usu.dni=prof.dni where fecha_turno=? and estado_t='Asignado'");
				stmt.setDate(1, fecha);
				rs = stmt.executeQuery();

				while (rs.next() && rs != null) {
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
			} catch (SQLException e) {
				respuestaOperacion = e.toString();
			} finally {
				FactoryConnection.cerrarConexion(rs, stmt);
			}
			return turnos;
		}
		
		//Turnos Asignados por Profesional
		public List<Turno> buscarTurnosAsignadosProfesional(Integer matricula) {
			List<Turno> turnos = new ArrayList<Turno>();

			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from turno t inner join horario h on h.idhorario=t.idhorario inner join practica pract on pract.id_practica=h.id_practica inner join usuario usPac on usPac.dni=t.dni inner join profesional p on p.matricula=h.matricula inner join usuario us on us.dni=p.dni inner join consultorio c on c.id_consultorio = t.id_consultorio where p.matricula=? and estado_t='Asignado' and fecha_turno>=sysdate()");
				stmt.setInt(1, matricula);
				rs = stmt.executeQuery();

				while (rs.next() && rs != null) {
					Turno unTurno = new Turno();
					Practica unaPractica = new Practica();
					Profesional unProfesional = new Profesional();
					Horario unHorario = new Horario();
					Paciente unPaciente = new Paciente();
					Consultorio unConsultorio = new Consultorio();

					unTurno.setId_turno(rs.getInt("idturno"));
					unTurno.setFecha_t(rs.getDate("fecha_turno").toLocalDate());
					unTurno.setHora_t(rs.getTime("hora_turno").toLocalTime());
					unTurno.setEstado_t(rs.getString("t.estado_t"));

					unProfesional.setNombre(rs.getString("us.nombre"));
					unProfesional.setApellido(rs.getString("us.apellido"));

					unaPractica.setDescripcion(rs.getString("pract.descripcion"));
					unaPractica.setId_practica(rs.getInt("pract.id_practica"));

					unHorario.setId_horario(rs.getInt("h.idHorario"));
					unHorario.setPractica(unaPractica);
					unHorario.setProfesional(unProfesional);
					
					unPaciente.setApellido(rs.getString("usPac.apellido"));
					unPaciente.setNombre(rs.getString("usPac.nombre"));

					unConsultorio.setId_consultorio(rs.getInt("c.id_consultorio"));
					unConsultorio.setDescripcion(rs.getString("c.descripcion"));
					unConsultorio.setEstado(rs.getBoolean("c.estado"));

					unTurno.setPaciente(unPaciente);
					unTurno.setHorario(unHorario);
					unTurno.setConsultorio(unConsultorio);
					turnos.add(unTurno);
					respuestaOperacion = "OK";
				}
			} catch (SQLException e) {
				respuestaOperacion = e.toString();
			} finally {
				FactoryConnection.cerrarConexion(rs, stmt);
			}
			return turnos;
		}
		
		//Turnos Ambulatorios Asistidos
		public List<Turno> buscarTurnosAsistidosAmbulatorios(LocalDate fecha_desde, LocalDate fecha_hasta) {
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
		}
		
		//Turnos de Una Prescripcion
		public List<Turno> getTurnosPrescripcion(Integer idPrescripcion) {
			List<Turno> turnosPrescripcion = new ArrayList<Turno>();
			
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
						"select * from turno t inner join horario h on h.idHorario=t.idHorario inner join practica pr on pr.id_practica=h.id_practica inner join profesional prof on h.matricula=prof.matricula inner join usuario u on prof.dni=u.dni inner join usuario u1 on u1.dni = t.dni inner join paciente pac on pac.dni = u1.dni inner join obra_social os on os.id_obra_social = pac.id_obra_social inner join consultorio c on c.id_consultorio=t.id_consultorio inner join equipo eq on eq.id_equipo=pr.id_equipo where t.id_prescripcion = ? order by t.fecha_turno, hora_turno");
				stmt.setInt(1, idPrescripcion);
				rs = stmt.executeQuery();
	
				while (rs != null && rs.next()) {
					Turno unTurno = new Turno();
					Horario unHorario = new Horario();
					Practica unaPractica = new Practica();
					Equipo unEquipo = new Equipo();
					Profesional unProfesional = new Profesional();
					Consultorio unConsultorio = new Consultorio();
					Paciente unPaciente = new Paciente();
					
					unTurno.setId_turno(rs.getInt("t.idturno"));
					unTurno.setFecha_generacion(rs.getDate("t.fecha_generacion").toLocalDate());
					unTurno.setFecha_t(rs.getDate("fecha_turno").toLocalDate());
					unTurno.setHora_t(rs.getTime("t.hora_turno").toLocalTime());
					unTurno.setHora_hasta_t(rs.getTime("t.hora_hasta").toLocalTime());
					unTurno.setEstado_t(rs.getString("t.estado_t"));
					//Armamos el Horario para el Turno
					unHorario.setId_horario(rs.getInt("idHorario"));
					unHorario.setFecha_alta(rs.getDate("h.fecha_alta").toLocalDate());
					if(rs.getDate("h.fecha_baja")==null) 
					{
						unHorario.setFecha_baja(null);
					}
					else 
					{
						unHorario.setFecha_baja(rs.getDate("h.fecha_baja").toLocalDate());
					}
					unHorario.setHora_desde(rs.getTime("h.hora_desde").toLocalTime());
					unHorario.setHora_hasta(rs.getTime("h.hora_hasta").toLocalTime());
					unHorario.setDia_semana(rs.getInt("h.dia_semana"));
					
					//Armo la Practica para el Horario
					unaPractica.setId_practica(rs.getInt("id_practica"));
					if(rs.getInt("pr.tipo_practica")==1) {
						unaPractica.setTipo_practica(Enumeradores.TipoPractica.AMBULATORIA);
					}
					else {
						unaPractica.setTipo_practica(Enumeradores.TipoPractica.DISCAPACIDAD);
					}
					unaPractica.setDescripcion(rs.getString("pr.descripcion"));
					unaPractica.setEstado(rs.getBoolean("pr.estado"));
					unaPractica.setDuracion(rs.getInt("pr.duracion"));
					if(rs.getDate("pr.fecha_baja")==null) 
					{
						unaPractica.setFecha_baja(null);
					}
					else 
					{
						unaPractica.setFecha_baja(rs.getDate("pr.fecha_baja").toLocalDate());
					}
					
					//Armo el Equipo para la Practica
					unEquipo.setId_equipo(rs.getInt("eq.id_equipo"));
					unEquipo.setTipo_equipo(rs.getString("eq.tipo_equipo"));
					unEquipo.setDescripcion(rs.getString("eq.descripcion"));
					unEquipo.setEstado(rs.getBoolean("eq.estado"));
					if(rs.getDate("eq.fecha_baja")==null) 
					{
						unEquipo.setFecha_baja(null);
					}
					else 
					{
						unEquipo.setFecha_baja(rs.getDate("eq.fecha_baja").toLocalDate());
					}
					//Asigno el Equipo a la Practica
					unaPractica.setEquipo(unEquipo);
					//Asigno Practica a Horario
					unHorario.setPractica(unaPractica);
					
					//Armo el Profesional para el Horario
					unProfesional.setDni(rs.getInt("u.dni"));
					unProfesional.setMatricula(rs.getInt("prof.matricula"));
					unProfesional.setApellido(rs.getString("u.apellido"));
					unProfesional.setNombre(rs.getString("u.nombre"));
					unProfesional.setTelefono(rs.getString("u.telefono"));
					unProfesional.setFecha_nacimiento(rs.getDate("u.fecha_nacimiento").toLocalDate());
					unProfesional.setGenero(rs.getString("u.genero"));
					unProfesional.setEmail(rs.getString("u.email"));
					unProfesional.setClave(rs.getString("u.clave"));
					unProfesional.setTipo_usuario(rs.getInt("u.tipo_usuario"));
					//Asigno el Profesional al Horario
					unHorario.setProfesional(unProfesional);
					unTurno.setHorario(unHorario);
					
					//Armo el Paciente para el Turno
					unPaciente.setDni(rs.getInt("u1.dni"));
					unPaciente.setApellido(rs.getString("u1.apellido"));
					unPaciente.setNombre(rs.getString("u1.nombre"));
					unPaciente.setTelefono(rs.getString("u1.telefono"));
					unPaciente.setFecha_nacimiento(rs.getDate("u1.fecha_nacimiento").toLocalDate());
					unPaciente.setGenero(rs.getString("u1.genero"));
					unPaciente.setEmail(rs.getString("u1.email"));
					unPaciente.setClave(rs.getString("u1.clave"));
					unPaciente.setTipo_usuario(rs.getInt("u1.tipo_usuario"));
					//Asigno el Paciente al turno
					unTurno.setPaciente(unPaciente);
					
					// si el turno tiene una prescripcion asociada, traigo el id
					if (rs.getInt("id_prescripcion") != 0) {
						Prescripcion unaPrescripcion = new Prescripcion();
						unaPrescripcion.setId_prescripcion(rs.getInt("id_prescripcion")); 
						unTurno.setPrescripcion(unaPrescripcion);
					} else {
						unTurno.setPrescripcion(null);
					}
					
					//Armo el Consultorio para el Turno
					unConsultorio.setId_consultorio(rs.getInt("c.id_consultorio"));
					unConsultorio.setDescripcion(rs.getString("c.descripcion"));
					unConsultorio.setEstado(rs.getBoolean("c.estado"));
					if(rs.getDate("c.fecha_baja")==null) 
					{
						unConsultorio.setFecha_baja(null);
					}
					else 
					{
						unConsultorio.setFecha_baja(rs.getDate("c.fecha_baja").toLocalDate());
					}
					//Asigno el Consultorio al Turno
					unTurno.setConsultorio(unConsultorio);
					//Agrego el Turno a la Lista.
					turnosPrescripcion.add(unTurno);
				}
			} catch (SQLException e) {
				respuestaOperacion = e.toString();
			} finally {
				FactoryConnection.cerrarConexion(rs, stmt);
			}
			return turnosPrescripcion;
		}
		
		//Antes de confirmar el turno con Prescripción me fijo si no ya no reservó la totalidad de los turnos de la prescripción
		public boolean validarPrescripcionAgotada(Integer idPresc) {
			Boolean respuesta = true;
			
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select presc.cant_sesiones, count(*) from prescripcion presc inner join turno tur on presc.id_prescripcion = tur.id_prescripcion where tur.id_prescripcion=? group by presc.cant_sesiones");
				stmt.setInt(1, idPresc);
				rs = stmt.executeQuery();
	
				if (rs != null && rs.next()) {
					Integer cant_sesiones = rs.getInt("cant_sesiones");
					Integer turnos_reservados = rs.getInt("count(*)");
					if(turnos_reservados<cant_sesiones) {
						respuesta=true;
						}
					else {
						respuesta = false;
					}
					} 
				} catch (SQLException e) {
				respuestaOperacion = e.toString();
			} finally {
				FactoryConnection.cerrarConexion(rs, stmt);
			}
			return respuesta;
		}

	//Update
		//Turno con Prescripcion
		public String registroTurnoConPrescripcion(Paciente pac, Integer id_turno, Integer id_presc) {
			Integer respUpdate = null;
	
			try {
				stmt = FactoryConnection.getInstancia().getConn()
						.prepareStatement("update turno set dni=?, estado_t='Asignado', id_prescripcion=? where idturno=?");
				stmt.setInt(1, pac.getDni());
				stmt.setInt(2, id_presc);
				stmt.setInt(3, id_turno);
				respUpdate = stmt.executeUpdate();
	
				if (respUpdate == 1) {
					respuestaOperacion = "Turno Registrado exitosamente.";
				} else {
					respuestaOperacion = null;
				}
			} catch (SQLException e) {
				respuestaOperacion = e.toString();
			} finally {
				FactoryConnection.cerrarConexion(rs, stmt);
			}
			return respuestaOperacion;
		}

		//Asignar Prescripción al Turno
        public String asignarPrescripcionATurno(Turno tur, Integer id_prescripcion) {

            try {
                stmt = FactoryConnection.getInstancia().getConn()
                        .prepareStatement("update turno set id_prescripcion=? where idturno=?");
                stmt.setInt(1, id_prescripcion);
                stmt.setInt(2, tur.getId_turno());
                Integer resultadoUpdate = stmt.executeUpdate();

                if (resultadoUpdate == 1) {
                    respuestaOperacion = "OK";
                } else {
                    respuestaOperacion = null;
                }
            } catch (SQLException e) {
                respuestaOperacion = e.toString();
            } finally {
                FactoryConnection.cerrarConexion(rs, stmt);
            }
            return respuestaOperacion;
        }
		
		
		
		//Turno Particular
		public String registroTurnoSinPrescripcion(Paciente pac, Integer id_turno) {
			Integer respUpdate = null;
	
			try {
				stmt = FactoryConnection.getInstancia().getConn()
						.prepareStatement("update turno set dni=?, estado_t='Asignado' where idturno=?");
				stmt.setInt(1, pac.getDni());
				stmt.setInt(2, id_turno);
				respUpdate = stmt.executeUpdate();
	
				if (respUpdate == 1) {
					respuestaOperacion = "OK";
				} else {
					respuestaOperacion = null;
				}
			} catch (SQLException e) {
				respuestaOperacion = e.toString();
			} finally {
				FactoryConnection.cerrarConexion(rs, stmt);
			}
			return respuestaOperacion;
		}

		//Registro Asistencia
		public String registrarAsistencia(Paciente pac, Turno tur) {
			try {
				stmt = FactoryConnection.getInstancia().getConn()
						.prepareStatement("update turno set estado_t='Asistido' where idturno=? and dni=?");
				stmt.setInt(1, tur.getId_turno());
				stmt.setInt(2, pac.getDni());
				Integer resultadoUpdate = stmt.executeUpdate();
	
				if (resultadoUpdate == 1) {
					respuestaOperacion = "OK";
				} else {
					respuestaOperacion = null;
				}
			} catch (SQLException e) {
				respuestaOperacion = e.toString();
			} finally {
				FactoryConnection.cerrarConexion(rs, stmt);
			}
			return respuestaOperacion;
		}
	
public List<Turno> buscarTurnosAsistidosAmbulatorios(LocalDate fecha_desde, LocalDate fecha_hasta, int matricula) {
	//Busca de todos los turnos registrados, aquellos que fueron asistidos, de tipo Ambulatorio y están pendientes de cobrar para el rango de fechas ingresado
List<Turno> turnosPendientesACobrar = new ArrayList<Turno>();
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT  t.idturno, t.fecha_turno, t.hora_turno, us.dni, pc.dni, os.id_obra_social, os.nombre_os, h.idhorario, pra.id_practica, pra.descripcion, pra.descripcion, prof.matricula, us.apellido,us.nombre, uspro.dni, uspro.nombre, uspro.apellido, pc.nro_afiliado from turno t inner join usuario us on us.dni = t.dni inner join paciente pc on pc.dni=us.dni  inner join obra_social os on os.id_obra_social=pc.id_obra_social  inner join horario h on h.idhorario = t.idhorario  inner join practica pra on pra.id_practica=h.id_practica  inner join profesional prof on prof.matricula = h.matricula  inner join usuario uspro on uspro.dni =prof.dni inner join prescripcion pres on pres.id_prescripcion=t.id_prescripcion where t.fecha_turno between ? and ? and prof.matricula = ? and t.estado_t='Asistido' and pra.tipo_practica = 1 and t.id_prescripcion is not null GROUP BY t.id_prescripcion, t.idturno, us.dni, pc.dni, os.id_obra_social, h.idhorario, pra.id_practica, prof.matricula, uspro.dni, pres.id_prescripcion ORDER BY t.fecha_turno");
		stmt.setDate(1, Date.valueOf(fecha_desde));
		stmt.setDate(2, Date.valueOf(fecha_hasta));
		stmt.setInt(3, matricula);
		rs = stmt.executeQuery();
		
		while(rs!=null && rs.next())
		{
			Turno tur = new Turno();
			tur.setId_turno(rs.getInt("t.idturno"));
			tur.setFecha_t(rs.getDate("t.fecha_turno").toLocalDate());
			tur.setHora_t(rs.getTime("t.hora_turno").toLocalTime());
			
			Horario horario = new Horario();
			horario.setId_horario(rs.getInt("h.idhorario"));
			
			Practica practica = new Practica();
			practica.setId_practica(rs.getInt("pra.id_practica"));
			practica.setDescripcion(rs.getString("pra.descripcion"));
		
			Paciente paciente = new Paciente();
			paciente.setDni(rs.getInt("pc.dni"));
			paciente.setApellido(rs.getString("us.apellido"));
			paciente.setNombre(rs.getString("us.nombre"));
			paciente.setNro_afiliado(rs.getString("pc.nro_afiliado"));
			
			ObraSocial obraSocial = new ObraSocial();
			obraSocial.setId_obra_social(rs.getInt("os.id_obra_social"));
			obraSocial.setNombre(rs.getString("os.nombre_os"));
	
			Profesional profesional = new Profesional();
			profesional.setMatricula(matricula);
			profesional.setDni(rs.getInt("uspro.dni"));
			profesional.setNombre(rs.getString("uspro.nombre"));
			profesional.setApellido(rs.getString("uspro.apellido"));
			
			
			
			
			horario.setPractica(practica);
			paciente.setObra_social(obraSocial);
			tur.setPaciente(paciente);
			horario.setProfesional(profesional);
			tur.setHorario(horario);
			turnosPendientesACobrar.add(tur);
		}
		
	} catch (SQLException e) {
		respuestaOperacion = e.toString();
	} 
	
	finally
	{
		FactoryConnection.cerrarConexion(rs, stmt);
	}
	
	return turnosPendientesACobrar;
}

public String cancelaTurno(Integer idTurno) {

    try {
        stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
                "update turno set estado_t='Libre',dni=NULL,id_prescripcion=NULL where idturno=?");
        stmt.setInt(1, idTurno);
        Integer resultadoUpdate = stmt.executeUpdate();

        if (resultadoUpdate == 1) {
            respuestaOperacion = "OK";
        } else {
            respuestaOperacion = null;
        }
    } catch (SQLException e) {
        respuestaOperacion = e.toString();
    } finally {
        FactoryConnection.cerrarConexion(rs, stmt);
    }
    return respuestaOperacion;
}



public String facturaTurno(Integer idTurno) {
//Es por cada turno - queda pendiente agregar este método en uno nuevo para el conjunto de todos los turnos
	try {
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"update turno set estado_t='Facturado' where idturno=?");
		stmt.setInt(1, idTurno);
		Integer resultadoUpdate = stmt.executeUpdate();

		if (resultadoUpdate == 1) {
			respuestaOperacion = "OK";
		} else {
			respuestaOperacion = null;
		}
		

		} catch (SQLException e) {
		respuestaOperacion = e.toString();
	} finally {
		FactoryConnection.cerrarConexion(rs, stmt);
	}
	return respuestaOperacion;
}
		
	//Metodo que recupera todos los datos requeridos para mostrar en los tuyrnos que aun se encuentran pendientes de registrar el pago
public List<Turno> buscarTurnosPendientesCobro(Integer matricula,LocalDate fechaDesde, LocalDate fechaHasta) {
	
	List<Turno> turnosPendientesACobrar = new ArrayList<Turno>();
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from turno t inner join horario h on t.idhorario=h.idhorario inner join practica pr on h.id_practica=pr.id_practica inner join profesional prof on h.matricula=prof.matricula inner join usuario usProf on usProf.dni=prof.dni inner join usuario usPac on t.dni=usPac.dni inner join paciente pac on usPac.dni=pac.dni inner join obra_social obSoc on obSoc.id_obra_social = pac.id_obra_social where t.fecha_turno between ? and ? and t.estado_t='Facturado' and h.matricula=? and id_prescripcion is not null order by t.fecha_turno");
		stmt.setDate(1, Date.valueOf(fechaDesde));
		stmt.setDate(2, Date.valueOf(fechaHasta));
		stmt.setInt(3, matricula);
		rs = stmt.executeQuery();
		
		while(rs!=null && rs.next())
		{
			Practica pract = new Practica();
			pract.setId_practica(rs.getInt("id_practica"));
			pract.setDescripcion(rs.getString("descripcion"));
			
			Profesional prof = new Profesional();
			prof.setMatricula(rs.getInt("matricula"));
			prof.setApellido(rs.getString("usProf.apellido"));
			prof.setNombre(rs.getString("usProf.nombre"));
			
			ObraSocial obSoc = new ObraSocial();
			obSoc.setNombre(rs.getString("nombre_os"));
			
			Paciente pac = new Paciente();
			pac.setApellido(rs.getString("usPac.apellido"));
			pac.setNombre(rs.getString("usPac.nombre"));
			pac.setObra_social(obSoc);
			
			Horario hor = new Horario();
			hor.setId_horario(rs.getInt("idhorario"));
			hor.setPractica(pract);
			hor.setProfesional(prof);			
			
			Turno tur = new Turno();
			tur.setId_turno(rs.getInt("idturno"));
			tur.setFecha_t(rs.getDate("fecha_turno").toLocalDate());
			tur.setHorario(hor);
			tur.setPaciente(pac);
			
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
	
	
}

public String registrarPagoTurno(Turno t) {
	
	String respuestaOperacion = null;
	
	try {
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update turno set estado_t='Abonado' where idturno=?");
		stmt.setInt(1, t.getId_turno());	
		stmt.executeUpdate();
		respuestaOperacion="OK";
	} catch (SQLException e) {
		
		respuestaOperacion = e.toString();
		
	} finally {
		FactoryConnection.cerrarConexion(rs, stmt);
	}
	
	return respuestaOperacion;	
}

public List<Turno> buscarTurnosAsistidosDiscapacidad(LocalDate fecha_desde, LocalDate fecha_hasta, Integer matricula) {
	
List<Turno> turnosDiscapacidadPendientesACobrar = new ArrayList<Turno>();
	
	try
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT t.idturno, t.fecha_turno, t.hora_turno, usPac.dni, usPac.nombre, usPac.apellido, pac.dni, pac.nro_afiliado, obSoc.id_obra_social, obSoc.nombre_os, h.idhorario, pr.id_practica, pr.descripcion, prof.matricula, usProf.apellido, usProf.nombre, usProf.dni, mo.id_monto, mo.monto from turno t inner join horario h on t.idhorario=h.idhorario inner join practica pr on h.id_practica=pr.id_practica inner join profesional prof on h.matricula=prof.matricula inner join usuario usProf on usProf.dni=prof.dni inner join usuario usPac on t.dni=usPac.dni inner join paciente pac on usPac.dni=pac.dni inner join obra_social obSoc on obSoc.id_obra_social = pac.id_obra_social inner join monto_practica mo on mo.id_practica=pr.id_practica where t.fecha_turno between ? and ? and prof.matricula = ? and t.estado_t='Asistido' and pr.tipo_practica = 2 and t.fecha_turno>=mo.fecha_desde and t.fecha_turno<=mo.fecha_hasta and t.id_prescripcion is not null GROUP BY t.idturno, usPac.dni, pac.dni, obSoc.id_obra_social, h.idhorario, h.fecha_alta, h.fecha_baja, h.hora_desde, h.hora_hasta, h.dia_semana, mo.fecha_desde, mo.fecha_hasta, pr.id_practica, prof.matricula, usProf.dni, mo.id_monto, mo.monto ORDER BY t.fecha_turno");
		
		stmt.setDate(1, Date.valueOf(fecha_desde));
		stmt.setDate(2, Date.valueOf(fecha_hasta));
		stmt.setInt(3, matricula);
		rs = stmt.executeQuery();
		
		while(rs!=null && rs.next())
		{
			Practica pract = new Practica();
			pract.setId_practica(rs.getInt("pr.id_practica"));
			pract.setDescripcion(rs.getString("pr.descripcion"));
			
			MontosPractica montoPractica = new MontosPractica();
			montoPractica.setId_monto(rs.getInt("mo.id_monto"));
			montoPractica.setMonto(rs.getDouble("mo.monto"));
			
			List<MontosPractica> montosPractica = new ArrayList<MontosPractica>();
			montosPractica.add(montoPractica);
		    pract.setMontos(montosPractica);
			
			Profesional prof = new Profesional();
			prof.setMatricula(rs.getInt("prof.matricula"));
			prof.setApellido(rs.getString("usProf.apellido"));
			prof.setNombre(rs.getString("usProf.nombre"));

			ObraSocial obSoc = new ObraSocial();
			obSoc.setId_obra_social(rs.getInt("obSoc.id_obra_social"));
			obSoc.setNombre(rs.getString("obSoc.nombre_os"));
			
			Paciente pac = new Paciente();
			pac.setApellido(rs.getString("usPac.apellido"));
			pac.setNombre(rs.getString("usPac.nombre"));
			
			pac.setObra_social(obSoc);
			
			Horario hor = new Horario();
			hor.setId_horario(rs.getInt("h.idhorario"));
			hor.setPractica(pract);
			hor.setProfesional(prof);			
			
			Turno tur = new Turno();
			tur.setId_turno(rs.getInt("t.idturno"));
			tur.setFecha_t(rs.getDate("t.fecha_turno").toLocalDate());
			tur.setHorario(hor);
			tur.setPaciente(pac);
			
			turnosDiscapacidadPendientesACobrar.add(tur);
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
	
	return turnosDiscapacidadPendientesACobrar;
	
}

public int validarHorario(Integer idHorario) {
	Integer turnosPendientes = 0;
	try 
	{
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select count(*) from turno t where t.idhorario =? and t.estado_t='Asignado'");
		stmt.setInt(1, idHorario);	
		rs= stmt.executeQuery();
		if (rs != null && rs.next()) {
			turnosPendientes = Integer.parseInt(rs.getString(1));
		} 
	}		
	catch (SQLException e) {	
		turnosPendientes = null;
	}					
	finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        	} 
        catch (Exception e) {
            e.printStackTrace();
        }
        FactoryConnection.getInstancia().releaseConn(); //es correcta esta forma de cerrar la conexion?
    }
	return turnosPendientes;
}

public String cancelarTurnos(Integer idHorario) {
	String respuestaOperacion = null;
	
	try {
		stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update turno set estado_t='Cancelado' where idhorario=?");
		stmt.setInt(1, idHorario);	
		stmt.executeUpdate();
		respuestaOperacion="OK";
	} catch (SQLException e) {
		
		respuestaOperacion = e.toString();
		
	} finally {
		FactoryConnection.cerrarConexion(rs, stmt);
	}
	
	return respuestaOperacion;	
}

public String registrarAsistenciaYAnularPrescrescripcion(Turno tur) {
	String respuestaOperacion = null;
    Connection conexion = null;
    PreparedStatement stmt1 = null;
    PreparedStatement stmt2 = null;
    
    try {
    	conexion = FactoryConnection.getInstancia().getConn();
        conexion.setAutoCommit(false);
        
        //Primer Update
        stmt1 = conexion.prepareStatement("update turno set estado_t='Asistido' where idturno=?");
		stmt1.setInt(1, tur.getId_turno());	
		stmt1.executeUpdate();
		
		//Segundo Update
		stmt2 = conexion.prepareStatement("update prescripcion set sesiones_asistidas=cant_sesiones, estado=0, fecha_baja_presc=current_timestamp() where id_prescripcion=?");
		stmt2.setInt(1, tur.getPrescripcion().getId_prescripcion());
		stmt2.executeUpdate();
		
		//Committeo los dos update
		conexion.commit();
        respuestaOperacion = "OK";

	} 
    catch (SQLException e) 
					    	{		
								if (conexion!= null) 
								{
						            try 
						            {
						                conexion.rollback();
						            } 
						            catch (SQLException rollbackEx) 
						            {
						                respuestaOperacion=e.toString();
						            }
								}
					    	} 
    finally 
    {
		FactoryConnection.cerrarConexion(rs, stmt);
	}
    return respuestaOperacion;
}

public String registrarAsistenciaYAumentarSesionesAsistidas(Turno tur) {
	String respuestaOperacion = null;
    Connection conexion = null;
    PreparedStatement stmt1 = null;
    PreparedStatement stmt2 = null;
    
    try {
    	conexion = FactoryConnection.getInstancia().getConn();
        conexion.setAutoCommit(false);
        
        //Primer Update
        stmt1 = conexion.prepareStatement("update turno set estado_t='Asistido' where idturno=?");
		stmt1.setInt(1, tur.getId_turno());	
		stmt1.executeUpdate();
		
		//Segundo Update
		stmt2 = conexion.prepareStatement("update prescripcion set sesiones_asistidas=? where id_prescripcion=?");
		stmt2.setInt(1, tur.getPrescripcion().getSesiones_asistidas()+1);
		stmt2.setInt(2, tur.getPrescripcion().getId_prescripcion());
		stmt2.executeUpdate();
		
		//Committeo los dos update
		conexion.commit();
        respuestaOperacion = "OK";

	} 
    catch (SQLException e) 
					    	{		
								if (conexion!= null) 
								{
						            try 
						            {
						                conexion.rollback();
						            } 
						            catch (SQLException rollbackEx) 
						            {
						                respuestaOperacion=e.toString();
						            }
								}
					    	} 
    finally 
    {
		FactoryConnection.cerrarConexion(rs, stmt);
	}
    return respuestaOperacion;
}



}

