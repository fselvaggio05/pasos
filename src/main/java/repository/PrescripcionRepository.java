package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import conexionDB.FactoryConnection;
import entity.Equipo;
import entity.ObraSocial;
import entity.Paciente;
import entity.Practica;
import entity.Prescripcion;

public class PrescripcionRepository {	
	 ResultSet rs = null;
	 PreparedStatement stmt = null;
	 String respuestaOperacion = null;
	 
	//Create	 	
	public String insertarPrescripcion(Paciente paciente,LocalDate fechaPrescripcion,Integer id_practica, Integer cantSesiones)
	{		
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("insert into prescripcion (fecha_prescripcion,nro_afiliado,cod_practica,cant_sesiones,sesiones_asistidas) values (?,?,?,?,?)");
			stmt.setDate(1, Date.valueOf(fechaPrescripcion));
			stmt.setString(2, paciente.getNro_afiliado());
			stmt.setInt(3, id_practica);
			stmt.setInt(4, cantSesiones);
			stmt.setInt(5, 0);
			stmt.executeUpdate();		
			respuestaOperacion ="OK";								
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

	//Read
	public List<Prescripcion> getAll() {
		List<Prescripcion> ambulatorias = new ArrayList<>();
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from prescripcion presc inner join paciente pac on pac.nro_afiliado = presc.nro_afiliado inner join usuario us on us.dni=pac.dni inner join obra_social os on os.id_obra_social=pac.id_obra_social inner join practica pract on pract.id_practica = presc.cod_practica left join equipo eq on eq.id_equipo = pract.id_equipo"); //traigo todas
			rs = stmt.executeQuery();
			
			while(rs!=null && rs.next())
			{
				Prescripcion presc = new Prescripcion();				
				//Armo el Paciente que ompone la Prescripcion
				Paciente pac = new Paciente();
				pac.setDni(rs.getInt("us.dni"));
				pac.setApellido(rs.getString("us.apellido"));
				pac.setNombre(rs.getString("us.nombre"));
				pac.setTelefono(rs.getString("us.telefono"));
				pac.setFecha_nacimiento(rs.getDate("us.fecha_nacimiento").toLocalDate());
				pac.setGenero(rs.getString("us.genero"));
				pac.setEmail(rs.getString("us.email"));
				pac.setClave(rs.getString("us.clave"));
				pac.setNro_afiliado(rs.getString("pac.nro_afiliado"));
				
				//Armo la Obra Social que corresponde al Paciente
				ObraSocial os = new ObraSocial();
				os.setId_obra_social(rs.getInt("os.id_obra_social"));
				os.setNombre(rs.getString("os.nombre_os"));
				os.setEstado(rs.getBoolean("os.estado_os"));
				if(rs.getDate("os.fecha_baja_os")== null) {
					os.setFecha_baja(null);
				} else {
					os.setFecha_baja(rs.getDate("os.fecha_baja_os").toLocalDate());
				}
				//Asigno la OS al Paciente
				pac.setObra_social(os);
				
				//Armo la Practica que compone la Prescripcion
				Practica pract = new Practica();
				pract.setId_practica(rs.getInt("id_practica"));
				pract.setDescripcion(rs.getString("pract.descripcion"));
				pract.setEstado(rs.getBoolean("pract.estado"));
				pract.setDuracion(rs.getInt("pract.duracion"));
				if(rs.getDate("pract.fecha_baja")==null) //Meto el if porque da error si está  nula
				{
					pract.setFecha_baja(null);
				}
				else 
				{
					pract.setFecha_baja(rs.getDate("pract.fecha_baja").toLocalDate());
				}
				//pract.setMonto(rs.getDouble("monto"));
				
				//Armo el Equipo que compone a la Práctica
				Equipo eq = new Equipo();
				eq.setId_equipo(rs.getInt("eq.id_equipo"));
				eq.setTipo_equipo(rs.getString("eq.tipo_equipo"));
				eq.setDescripcion(rs.getString("eq.descripcion"));
				eq.setEstado(rs.getBoolean("eq.estado"));
				if(rs.getDate("eq.fecha_baja")==null) //Meto el if porque da error si está  nula
				{eq.setFecha_baja(null);}
				else {eq.setFecha_baja(rs.getDate("eq.fecha_baja").toLocalDate());}
				//Asigno el Equipo a la Práctica
				pract.setEquipo(eq);				
				
				presc.setId_prescripcion(rs.getInt("presc.id_prescripcion"));
				presc.setFecha_prescripcion(rs.getDate("presc.fecha_prescripcion").toLocalDate());
				presc.setPaciente(pac);
				presc.setPractica(pract);
				presc.setCant_sesiones(rs.getInt("cant_sesiones"));
				presc.setSesiones_asistidas(rs.getInt("sesiones_asistidas"));
				presc.setFecha_alta_prescripcion(rs.getDate("presc.fecha_alta_presc").toLocalDate());
				if(rs.getDate("presc.fecha_baja_presc")==null) {
					presc.setFecha_baja_prescipcion(null);
				} else {
					presc.setFecha_baja_prescipcion(rs.getDate("presc.fecha_baja_presc").toLocalDate());
				}
				presc.setEstado(rs.getBoolean("presc.estado"));
				ambulatorias.add(presc);
			}
		}
		catch (SQLException e)
		{
			respuestaOperacion = e.toString();
		}
		finally {
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		return ambulatorias;
	}
	
	public List<Prescripcion> getAllPaciente(Paciente pac) {
		List<Prescripcion> ambulatorias = new ArrayList<>();
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from prescripcion presc inner join paciente pac on pac.nro_afiliado = presc.nro_afiliado inner join usuario us on us.dni=pac.dni inner join obra_social os on os.id_obra_social=pac.id_obra_social inner join practica pract on pract.id_practica = presc.cod_practica left join equipo eq on eq.id_equipo = pract.id_equipo where presc.nro_afiliado=?");
			stmt.setString(1, pac.getNro_afiliado());

			rs = stmt.executeQuery();
			
			while(rs!=null && rs.next())
			{
				Prescripcion presc = new Prescripcion();
				
				pac.setDni(rs.getInt("us.dni"));
				pac.setApellido(rs.getString("us.apellido"));
				pac.setNombre(rs.getString("us.nombre"));
				pac.setTelefono(rs.getString("us.telefono"));
				pac.setFecha_nacimiento(rs.getDate("us.fecha_nacimiento").toLocalDate());
				pac.setGenero(rs.getString("us.genero"));
				pac.setEmail(rs.getString("us.email"));
				pac.setClave(rs.getString("us.clave"));
				pac.setNro_afiliado(rs.getString("pac.nro_afiliado"));
				
				//Armo la Obra Social que corresponde al Paciente
				ObraSocial os = new ObraSocial();
				os.setId_obra_social(rs.getInt("os.id_obra_social"));
				os.setNombre(rs.getString("os.nombre_os"));
				os.setEstado(rs.getBoolean("os.estado_os"));
				if(rs.getDate("os.fecha_baja_os")== null) {
					os.setFecha_baja(null);
				} else {
					os.setFecha_baja(rs.getDate("os.fecha_baja_os").toLocalDate());
				}
				//Asigno la OS al Paciente
				pac.setObra_social(os);
				
				//Armo la Practica que compone la Prescripcion
				Practica pract = new Practica();
				pract.setId_practica(rs.getInt("id_practica"));
				pract.setDescripcion(rs.getString("pract.descripcion"));
				pract.setEstado(rs.getBoolean("pract.estado"));
				pract.setDuracion(rs.getInt("pract.duracion"));
				if(rs.getDate("pract.fecha_baja")==null) //Meto el if porque da error si está  nula
				{
					pract.setFecha_baja(null);
				}
				else 
				{
					pract.setFecha_baja(rs.getDate("pract.fecha_baja").toLocalDate());
				}
				//pract.setMonto(rs.getDouble("monto"));
				
				//Armo el Equipo que compone a la Práctica
				Equipo eq = new Equipo();
				eq.setId_equipo(rs.getInt("eq.id_equipo"));
				eq.setTipo_equipo(rs.getString("eq.tipo_equipo"));
				eq.setDescripcion(rs.getString("eq.descripcion"));
				eq.setEstado(rs.getBoolean("eq.estado"));
				if(rs.getDate("eq.fecha_baja")==null) //Meto el if porque da error si está  nula
				{eq.setFecha_baja(null);}
				else {eq.setFecha_baja(rs.getDate("eq.fecha_baja").toLocalDate());}
				//Asigno el Equipo a la Práctica
				pract.setEquipo(eq);				
				
				presc.setId_prescripcion(rs.getInt("presc.id_prescripcion"));
				presc.setFecha_prescripcion(rs.getDate("presc.fecha_prescripcion").toLocalDate());
				presc.setPaciente(pac);
				presc.setPractica(pract);
				presc.setCant_sesiones(rs.getInt("cant_sesiones"));
				presc.setSesiones_asistidas(rs.getInt("sesiones_asistidas"));
				presc.setFecha_alta_prescripcion(rs.getDate("presc.fecha_alta_presc").toLocalDate());
				if(rs.getDate("presc.fecha_baja_presc")==null) {
					presc.setFecha_baja_prescipcion(null);
				} else {
					presc.setFecha_baja_prescipcion(rs.getDate("presc.fecha_baja_presc").toLocalDate());
				}
				presc.setEstado(rs.getBoolean("presc.estado"));
				ambulatorias.add(presc);
			}
		}
		catch (SQLException e)
		{
			respuestaOperacion = e.toString();
		}
		finally {
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		return ambulatorias;
	}
	
	public Prescripcion buscarPrescrionesPaciente(Paciente pac, Prescripcion pr) {	
		Prescripcion presc = null;
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from prescripcion presc inner join paciente pac on presc.nro_afiliado = pac.nro_afiliado inner join practica pract on presc.cod_practica = pract.id_practica inner join equipo eq on pract.id_equipo = eq.id_equipo inner join obra_social os on os.id_obra_social = pac.id_obra_social where presc.fecha_prescripcion=? and pac.dni=? and presc.nro_afiliado=? and pract.cod_practica=? and estado=1");
			stmt.setDate(1, Date.valueOf(pr.getFecha_prescripcion()));
			stmt.setInt(2, pac.getDni()); //traigo paciente para buscar tmb por dni y nro de afiliado
			stmt.setString(3, pr.getPaciente().getNro_afiliado());
			stmt.setInt(4, pr.getPractica().getId_practica());
			rs = stmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				presc = new Prescripcion();
				Practica pract = new Practica();
				presc.setCant_sesiones(rs.getInt("cant_sesiones"));
				presc.setFecha_alta_prescripcion(rs.getDate("fecha_alta_presc").toLocalDate());
				presc.setFecha_prescripcion(rs.getDate("fecha_prescripcion").toLocalDate());
				presc.setSesiones_asistidas(rs.getInt("sesiones_asistidas"));
				//Armo la Practica que compone la Prescripcion
				pract.setId_practica(rs.getInt("id_practica"));
				pract.setDescripcion(rs.getString("pract.descripcion"));
				pract.setEstado(rs.getBoolean("pract.estado"));
				pract.setDuracion(rs.getInt("pract.duracion"));
				if(rs.getDate("pract.fecha_baja")==null) //Meto el if porque da error si está  nula
				{
					pract.setFecha_baja(null);
				}
				else 
				{
					pract.setFecha_baja(rs.getDate("pract.fecha_baja").toLocalDate());
				}	
				//Armo el Equipo que compone a la Práctica
				Equipo eq = new Equipo();
				eq.setId_equipo(rs.getInt("eq.id_equipo"));
				eq.setTipo_equipo(rs.getString("eq.tipo_equipo"));
				eq.setDescripcion(rs.getString("eq.descripcion"));
				eq.setEstado(rs.getBoolean("eq.estado"));
				if(rs.getDate("eq.fecha_baja")==null) //Meto el if porque da error si está  nula
				{eq.setFecha_baja(null);}
				else {eq.setFecha_baja(rs.getDate("eq.fecha_baja").toLocalDate());}
				
				//Armo la OS que va al Paciente
				ObraSocial os = new ObraSocial();
				os.setId_obra_social(rs.getInt("os.id_obra_social"));
				os.setNombre(rs.getString("os.nombre_os"));
				os.setEstado(rs.getBoolean("os.estado_os"));
				if(rs.getDate("os.fecha_baja_os")== null) {
					os.setFecha_baja(null);
				} else {
					os.setFecha_baja(rs.getDate("os.fecha_baja_os").toLocalDate());
				}
				
				//Asigno la OS al Paciente que vino por parámetro
				pac.setObra_social(os);
				
				//Asigno el Paciente que vino por parametro a la prescripcion
				presc.setPaciente(pac);
				
				//Asigno el equipo a la Practica
				pract.setEquipo(eq);
				
				//Asigno la Practica a la Prescripcion
				presc.setPractica(pract);		
			}
		}
		catch (SQLException e)
		{respuestaOperacion = e.toString();}
		finally {FactoryConnection.cerrarConexion(rs, stmt);}
		return presc;
	}

	public Prescripcion buscarPrescripcionActiva(Paciente pac, Practica pr) {	
		Prescripcion presc =  null;
		
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from prescripcion pr inner join practica pract on pr.cod_practica = pract.id_practica inner join equipo eq on pract.id_equipo = eq.id_equipo inner join paciente pac on pr.nro_afiliado = pac.nro_afiliado inner join usuario us on pac.dni = us.dni inner join obra_social os on os.id_obra_social = pac.id_obra_social where pr.cod_practica=? and nro_afiliado=? and estado=1");	
			stmt.setInt(1, pr.getId_practica()); 
			stmt.setString(2, pac.getNro_afiliado());
			rs = stmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				presc = new Prescripcion();
				presc.setId_prescripcion(rs.getInt("id_prescripcion"));
				presc.setCant_sesiones(rs.getInt("cant_sesiones"));
				presc.setFecha_alta_prescripcion(rs.getDate("fecha_alta_presc").toLocalDate());
				presc.setFecha_prescripcion(rs.getDate("fecha_prescripcion").toLocalDate());
				presc.setSesiones_asistidas(rs.getInt("sesiones_asistidas"));
				
				//Armo la Practica que compone la Prescripcion
				Practica pract = new Practica();
				pract.setId_practica(rs.getInt("id_practica"));
				pract.setDescripcion(rs.getString("pract.descripcion"));
				pract.setEstado(rs.getBoolean("pract.estado"));
				pract.setDuracion(rs.getInt("pract.duracion"));
				if(rs.getDate("pract.fecha_baja")==null) //Meto el if porque da error si está  nula
				{
					pract.setFecha_baja(null);
				}
				else 
				{
					pract.setFecha_baja(rs.getDate("pract.fecha_baja").toLocalDate());
				}
				
				//Armo el Equipo que compone a la Práctica
				Equipo eq = new Equipo();
				eq.setId_equipo(rs.getInt("eq.id_equipo"));
				eq.setTipo_equipo(rs.getString("eq.tipo_equipo"));
				eq.setDescripcion(rs.getString("eq.descripcion"));
				eq.setEstado(rs.getBoolean("eq.estado"));
				if(rs.getDate("eq.fecha_baja")==null) //Meto el if porque da error si está  nula
				{eq.setFecha_baja(null);}
				else {eq.setFecha_baja(rs.getDate("eq.fecha_baja").toLocalDate());}
				//Asigno el Equipo a la Práctica
				pract.setEquipo(eq);
				//Asigno la Practica a la Prescripcion				
				presc.setPractica(pract);
				
				//Armo la OS que va al Paciente
				ObraSocial os = new ObraSocial();
				os.setId_obra_social(rs.getInt("os.id_obra_social"));
				os.setNombre(rs.getString("os.nombre_os"));
				os.setEstado(rs.getBoolean("os.estado_os"));
				if(rs.getDate("os.fecha_baja_os")== null) {
					os.setFecha_baja(null);
				} else {
					os.setFecha_baja(rs.getDate("os.fecha_baja_os").toLocalDate());
				}				
				//Asigno la OS al Paciente que vino por parámetro
				pac.setObra_social(os);
				//Asigno el Paciente a la Prescripción
				presc.setPaciente(pac);					
			}
		}
		catch (SQLException e)
		{
			respuestaOperacion = e.toString();
		}
		finally {
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		return presc;	
	}
	
	//Update
	public void incrementarSesionesAsistidas(Prescripcion prescripcion) {		
		Integer sesionesAsistidas = prescripcion.getSesiones_asistidas()+1;
		
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update prescripcion set sesiones_asistidas=? where id_ambulatoria=?");
			stmt.setInt(1, sesionesAsistidas);
			stmt.setInt(2,prescripcion.getId_prescripcion());			
			stmt.executeUpdate();	
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

	public void desactivarVigenciaPrescripcion(Prescripcion prescripcion) {	
		Integer sesionesAsistidas = prescripcion.getSesiones_asistidas()+1;
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update prescripcion set sesiones_asistidas=?, estado=0 where id_ambulatoria=?");
			stmt.setInt(1, sesionesAsistidas);
			stmt.setInt(2,prescripcion.getId_prescripcion());			
			stmt.executeUpdate();			
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

	public String anularPrescripcion(Integer idPrescripcion) {
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update prescripcion set estado=0, fecha_baja_presc=current_timestamp() where id_prescripcion=?");
			stmt.setInt(1,idPrescripcion);			
			stmt.executeUpdate();
			respuestaOperacion="OK";
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

	public List<Prescripcion> buscarTodasLasPrescripciones(Paciente paciente) {
		
		List<Prescripcion> prescripciones = new ArrayList<>();
		
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from prescripcion presc inner join paciente pac on pac.nro_afiliado = presc.nro_afiliado inner join usuario us on us.dni=pac.dni inner join obra_social os on os.id_obra_social=pac.id_obra_social inner join practica pract on pract.id_practica = presc.cod_practica where presc.nro_afiliado=?"); //traigo todas
			stmt.setString(1, paciente.getNro_afiliado());
			
			rs = stmt.executeQuery();
			
			while(rs!=null && rs.next())
			{
				Prescripcion presc = new Prescripcion();				
				//Armo el Paciente que ompone la Prescripcion
				Paciente pac = new Paciente();
				pac.setDni(rs.getInt("us.dni"));
				pac.setApellido(rs.getString("us.apellido"));
				pac.setNombre(rs.getString("us.nombre"));
				pac.setTelefono(rs.getString("us.telefono"));
				pac.setFecha_nacimiento(rs.getDate("us.fecha_nacimiento").toLocalDate());
				pac.setGenero(rs.getString("us.genero"));
				pac.setEmail(rs.getString("us.email"));
				pac.setClave(rs.getString("us.clave"));
				pac.setNro_afiliado(rs.getString("pac.nro_afiliado"));
				
				//Armo la Obra Social que corresponde al Paciente
//				ObraSocial os = new ObraSocial();
//				os.setId_obra_social(rs.getInt("os.id_obra_social"));
//				os.setNombre(rs.getString("os.nombre_os"));
//				os.setEstado(rs.getBoolean("os.estado_os"));
//				if(rs.getDate("os.fecha_baja_os")== null) {
//					os.setFecha_baja(null);
//				} else {
//					os.setFecha_baja(rs.getDate("os.fecha_baja_os").toLocalDate());
//				}
//				//Asigno la OS al Paciente
//				pac.setObra_social(os);
				
				//Armo la Practica que compone la Prescripcion
				Practica pract = new Practica();
				pract.setId_practica(rs.getInt("id_practica"));
				pract.setDescripcion(rs.getString("pract.descripcion"));
				pract.setEstado(rs.getBoolean("pract.estado"));
				pract.setDuracion(rs.getInt("pract.duracion"));
				if(rs.getDate("pract.fecha_baja")==null) //Meto el if porque da error si está  nula
				{
					pract.setFecha_baja(null);
				}
				else 
				{
					pract.setFecha_baja(rs.getDate("pract.fecha_baja").toLocalDate());
				}
				//pract.setMonto(rs.getDouble("monto"));
				
//				//Armo el Equipo que compone a la Práctica
//				Equipo eq = new Equipo();
//				eq.setId_equipo(rs.getInt("eq.id_equipo"));
//				eq.setTipo_equipo(rs.getString("eq.tipo_equipo"));
//				eq.setDescripcion(rs.getString("eq.descripcion"));
//				eq.setEstado(rs.getBoolean("eq.estado"));
//				if(rs.getDate("eq.fecha_baja")==null) //Meto el if porque da error si está  nula
//				{eq.setFecha_baja(null);}
//				else {eq.setFecha_baja(rs.getDate("eq.fecha_baja").toLocalDate());}
//				//Asigno el Equipo a la Práctica
//				pract.setEquipo(eq);				
//				
				presc.setId_prescripcion(rs.getInt("presc.id_prescripcion"));
				presc.setFecha_prescripcion(rs.getDate("presc.fecha_prescripcion").toLocalDate());
				presc.setPaciente(pac);
				presc.setPractica(pract);
				presc.setCant_sesiones(rs.getInt("cant_sesiones"));
				presc.setSesiones_asistidas(rs.getInt("sesiones_asistidas"));
				presc.setFecha_alta_prescripcion(rs.getDate("presc.fecha_alta_presc").toLocalDate());
				if(rs.getDate("presc.fecha_baja_presc")==null) {
					presc.setFecha_baja_prescipcion(null);
				} else {
					presc.setFecha_baja_prescipcion(rs.getDate("presc.fecha_baja_presc").toLocalDate());
				}
				presc.setEstado(rs.getBoolean("presc.estado"));
				prescripciones.add(presc);
			}
		}
		catch (SQLException e)
		{
			respuestaOperacion = e.toString();
		}
		finally {
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		return prescripciones;

	}
}
