package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexionDB.FactoryConnection;
import entity.Equipo;
import entity.Monto_Practica;
import entity.Practica;
import entity.Enumeradores;

public class PracticaRepository {
									ResultSet rs = null;	
									PreparedStatement stmt = null;	
									String respuestaOperacion;
		
									public List<Practica> getAllAmbulatoriasActivas()
									{
										List<Practica> practicas = new ArrayList<>();
										try {
												stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM practica pract INNER JOIN equipo eq ON pract.id_equipo = eq.id_equipo where pract.estado=1 and pract.tipo_practica=1");
												rs = stmt.executeQuery();
												while (rs!=null && rs.next())
												{
													Practica pr = new Practica ();
													Equipo eq = new Equipo();
													pr.setId_practica(rs.getInt("pract.id_practica"));
													// Recuperar el valor del tipo de práctica de la base de datos
										            pr.setTipo_practica(Enumeradores.TipoPractica.AMBULATORIA);
													pr.setDescripcion(rs.getString("pract.descripcion"));
													pr.setDuracion(rs.getInt("pract.duracion"));
													//Armo el Equipo que tiene la Practica
													eq.setId_equipo(rs.getInt("eq.id_equipo"));
													eq.setDescripcion(rs.getString("eq.descripcion"));
													eq.setEstado(rs.getBoolean("eq.estado"));
													if(rs.getDate("eq.fecha_baja")==null) {
														eq.setFecha_baja(null);
													} else {
														eq.setFecha_baja(rs.getDate("eq.fecha_baja").toLocalDate());	
													}													
													pr.setEquipo(eq);													
													practicas.add(pr);
												}
											}
											catch (SQLException e) {
												e.printStackTrace();
											}
											finally {
												        try {
												            if (rs != null) rs.close();
												            if (stmt != null) stmt.close();
												        	} 
												        catch (Exception e) {
												            e.printStackTrace();
												        }								
										        FactoryConnection.getInstancia().releaseConn(); 
										    }											
											return practicas;								
									}
									
									public List<Practica> getAllDiscapacidadActivas() {
									    List<Practica> practicas = new ArrayList<>();
									    PreparedStatement stmt2 = null;
									    ResultSet rs2 = null;

									    try {
									        stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM practica pract where pract.estado=1 and pract.tipo_practica = 2");
									        rs = stmt.executeQuery();
									        while (rs != null && rs.next()) {
									            Practica pr = new Practica();
									            pr.setId_practica(rs.getInt("pract.id_practica"));
									            pr.setTipo_practica(Enumeradores.TipoPractica.DISCAPACIDAD);
									            pr.setDescripcion(rs.getString("pract.descripcion"));
									            pr.setDuracion(rs.getInt("pract.duracion"));
									            pr.setEquipo(null);

									            try {
									                //Consulto el historial de Montos para la Practica
									            	stmt2 = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM monto_practica mp where mp.id_practica=?");
									                stmt2.setInt(1, pr.getId_practica());
									                rs2 = stmt2.executeQuery();
									                List<Monto_Practica> montos = new ArrayList<>();
									                while (rs2 != null && rs2.next()) {
									                    Monto_Practica mp = new Monto_Practica();
									                    mp.setId_monto(rs2.getInt("mp.id_monto")); // Use rs2 en lugar de rs
									                    mp.setFecha_desde(rs2.getDate("mp.fecha_desde").toLocalDate()); // Use rs2 en lugar de rs
									                    mp.setFecha_hasta(rs2.getDate("mp.fecha_hasta").toLocalDate()); // Use rs2 en lugar de rs
									                    mp.setMonto(rs2.getDouble("mp.monto")); // Use rs2 en lugar de rs
									                    montos.add(mp);
									                }
									                pr.setMonto(montos);
									            } catch (SQLException e) {
									                e.printStackTrace(); // Manejo de la excepción SQL
									            } finally {
									                try {
									                    if (rs2 != null) rs2.close();
									                    if (stmt2 != null) stmt2.close();
									                } catch (SQLException e) {
									                    e.printStackTrace(); // Manejo de la excepción SQL al cerrar los recursos
									                }
									            }
									            practicas.add(pr);
									        }
									    } catch (SQLException e) {
									        e.printStackTrace(); // Manejo de la excepción SQL
									    } finally {
									        try {
									            if (rs != null) rs.close();
									            if (stmt != null) stmt.close();
									        } catch (SQLException e) {
									            e.printStackTrace(); // Manejo de la excepción SQL al cerrar los recursos
									        }
									        FactoryConnection.getInstancia().releaseConn();
									    }
									    return practicas;
									}
								
									public List<Practica> getAllActivas()
									{
										List<Practica> practicas = new ArrayList<>();
										practicas.addAll(this.getAllAmbulatoriasActivas());
										practicas.addAll(this.getAllDiscapacidadActivas());
										return practicas;
									}
									public List<Practica> getAllInactivas() {											
										   List<Practica> practicas = new ArrayList<>();
											
											try {
												stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM practica p INNER JOIN equipo e ON p.id_equipo = e.id_equipo where p.estado=0");
												rs = stmt.executeQuery();
												while (rs!=null && rs.next())
												{
													Practica pr = new Practica ();
													Equipo eq = new Equipo();
													pr.setId_practica(rs.getInt("id_practica"));
													pr.setDescripcion(rs.getString("p.descripcion"));
													pr.setDuracion(rs.getInt("duracion"));
													pr.setFecha_baja(rs.getDate("fecha_baja").toLocalDate());
													eq.setId_equipo(rs.getInt("id_equipo"));
													eq.setDescripcion(rs.getString("e.descripcion"));
													eq.setEstado(rs.getBoolean("e.estado"));
													if(rs.getDate("e.fecha_baja")==null) {
														eq.setFecha_baja(null);
													} else {
														eq.setFecha_baja(rs.getDate("e.fecha_baja").toLocalDate());	
													}													
													pr.setEquipo(eq);
													practicas.add(pr);
												}
											} 
											catch (SQLException e) {
												e.printStackTrace();
											}
											finally {
												        try {
												            if (rs != null) rs.close();
												            if (stmt != null) stmt.close();
												        	} 
												        catch (Exception e) {
												            e.printStackTrace();
												        }								
										        FactoryConnection.getInstancia().releaseConn(); 
										    }
											return practicas;								
									}
								
									public String insertarPractica(Integer idPractica, Enumeradores.TipoPractica tipoPractica, String descPractica, Integer duracion, Integer idEquipo) {													
										try
										{
											stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into practica (id_practica, tipo_practica, descripcion, duracion, id_equipo) values (?,?,?,?,?,?)");
											stmt.setInt(1,idPractica);
											stmt.setInt(2, tipoPractica.getCodigo());
											stmt.setString(3, descPractica);
											stmt.setInt(4, duracion);
											stmt.setInt(5, idEquipo);
											stmt.executeUpdate();
											respuestaOperacion = "OK";
										}
										catch (SQLException e) {														
											respuestaOperacion = e.toString();
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
										return respuestaOperacion;
									}
								
									public String actualizarPractica(Integer idPractica, String descPractica, Integer duracion, Integer idEquipo, Double monto) {														
										try
										{														
											stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update practica set descripcion=?, id_equipo=?, duracion=?, monto=? where id_practica=?");
											stmt.setString(1, descPractica);
											stmt.setInt(2, idEquipo);
											stmt.setInt(3, duracion);
											stmt.setDouble(4, monto);
											stmt.setInt(5, idPractica);
											stmt.executeUpdate();
											respuestaOperacion= "OK";													
										}
										catch (SQLException e) {	
											respuestaOperacion = e.toString();
										}										
										finally {								
											        try {
											            if (rs != null) rs.close();
											            if (stmt != null) stmt.close();
											        	} 
											        catch (Exception e) {
											            e.printStackTrace();
											        }
											        FactoryConnection.getInstancia().releaseConn(); 
									    		}										
										return respuestaOperacion;
									}								
								
									public String eliminarPractica(Integer idPractica) {
										String respuestaOperacion;
										
										try
										{
											stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update practica set estado=0, fecha_baja=current_timestamp() where id_practica=?");
											stmt.setInt(1, idPractica);	
											stmt.executeUpdate();
											respuestaOperacion= "OK";
										}										
										catch (SQLException e) {											
											respuestaOperacion = e.toString();
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
										return respuestaOperacion;
										}
									
									public String revivirPractica(Integer id_practica)
									{
										String respuestaOperacion;
										
										try
										{					
											stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update practica set estado=1, fecha_baja=null where id_practica=?");
											stmt.setInt(1, id_practica);
											stmt.executeUpdate();
											respuestaOperacion= "OK";														
										}										
										catch (SQLException e) {											
											respuestaOperacion = e.toString();
										}										
										finally {
											        try {
											            if (rs != null) rs.close();
											            if (stmt != null) stmt.close();
											        	} 
											        catch (Exception e) {
											            e.printStackTrace();
											        }								
											        FactoryConnection.getInstancia().releaseConn(); 
									    		}										
										return respuestaOperacion;
									}
								
									public Integer getDuracionPractica(Practica pr) {
										Integer duracionPractica = null;
										
										try 
										{
											stmt= FactoryConnection.getInstancia().getConn().prepareStatement("select duracion from practica where id_practica=?");
											stmt.setInt(1, pr.getId_practica());
											rs = stmt.executeQuery();	
											
											if(rs.next() && rs!=null )
																		{
																		duracionPractica = rs.getInt("duracion");
																		respuestaOperacion = "OK";
																		}
										}
										catch (SQLException e)
										{
											respuestaOperacion = e.toString();
										}
										finally 
										{			
											   try {
										            if (rs != null) rs.close();
										            if (stmt != null) stmt.close();
										        	} 
										        catch (Exception e) {
										            e.printStackTrace();
										        }
								
										        FactoryConnection.getInstancia().releaseConn();			
										}
										
										return duracionPractica;
									}
	}