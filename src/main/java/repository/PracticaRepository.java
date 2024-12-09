package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexionDB.FactoryConnection;
import entity.Equipo;
import entity.Practica;
import service.MontosPracticaService;
import entity.Enumeradores;

public class PracticaRepository {
									ResultSet rs = null;	
									PreparedStatement stmt = null;	
									String respuestaOperacion;
		
									//Create
									public String insertarAmbulatoria(Integer idPractica, Enumeradores.TipoPractica tipoPractica, String descPractica, Integer duracion, Integer idEquipo) {
										try
										{
											stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into practica (id_practica, tipo_practica, descripcion, duracion, id_equipo) values (?,?,?,?,?)");
											stmt.setInt(1,idPractica);
											stmt.setInt(2, tipoPractica.getCodigo());
											stmt.setString(3, descPractica);
											stmt.setInt(4, duracion);
											if (idEquipo != null) {
									            stmt.setInt(5, idEquipo);
									        } else {
									            stmt.setNull(5, java.sql.Types.INTEGER);
									        }stmt.executeUpdate();
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
									
									public String insertarDiscapacidad(Integer idPractica, Enumeradores.TipoPractica tipoPractica, String descPractica, Integer duracion) {
										try
										{
											stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into practica (id_practica, tipo_practica, descripcion, duracion) values (?,?,?,?)");
											stmt.setInt(1,idPractica);
											stmt.setInt(2, tipoPractica.getCodigo());
											stmt.setString(3, descPractica);
											stmt.setInt(4, duracion);
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
								
									//Read
									public List<Practica> getAllAmbulatoriasActivas()
									{
										List<Practica> practicas = new ArrayList<>();
										try {
												stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM practica pract LEFT JOIN equipo eq ON pract.id_equipo = eq.id_equipo where pract.estado=1 and pract.tipo_practica=1");
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
													pr.setEstado(rs.getBoolean("pract.estado"));
													pr.setFecha_baja(null);
													
													//Armo el Equipo que tiene la Practica
													eq.setId_equipo(rs.getInt("eq.id_equipo"));
													eq.setTipo_equipo(rs.getString("eq.tipo_equipo"));;
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
									    
									    try {
									        stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM practica pract where pract.estado=1 and pract.tipo_practica = 2");
									        rs = stmt.executeQuery();
									        while (rs != null && rs.next()) {
									            Practica pr = new Practica();
									            pr.setId_practica(rs.getInt("pract.id_practica"));	
									            pr.setTipo_practica(Enumeradores.TipoPractica.DISCAPACIDAD);
									            pr.setDescripcion(rs.getString("pract.descripcion"));
									            pr.setDuracion(rs.getInt("pract.duracion"));
									            pr.setEstado(rs.getBoolean("pract.estado"));
									            pr.setEquipo(null);
									            pr.setFecha_baja(null);
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
										List<Practica> discapacidad = this.getAllDiscapacidadActivas();
							            MontosPracticaService mps = new MontosPracticaService();
										for (Practica p: discapacidad) {
											p.setMontos(mps.getMontosPractica(p));
										}
										practicas.addAll(discapacidad);
										return practicas;
									}
								
									public List<Practica> getAllAmbulatoriasInactivas()
									{
										List<Practica> practicas = new ArrayList<>();
										try {
												stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM practica pract LEFT JOIN equipo eq ON pract.id_equipo = eq.id_equipo where pract.estado=0 and pract.tipo_practica=1");
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
													pr.setEstado(rs.getBoolean("pract.estado"));
													pr.setFecha_baja(rs.getDate("pract.fecha_baja").toLocalDate());
													
													//Armo el Equipo que tiene la Practica
													eq.setId_equipo(rs.getInt("eq.id_equipo"));
													eq.setTipo_equipo(rs.getString("eq.tipo_equipo"));;
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
									
									public List<Practica> getAllDiscapacidadInactivas() {
									    List<Practica> practicas = new ArrayList<>();
									    
									    try {
									        stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM practica pract where pract.estado=0 and pract.tipo_practica = 2");
									        rs = stmt.executeQuery();
									        while (rs != null && rs.next()) {
									            Practica pr = new Practica();
									            pr.setId_practica(rs.getInt("pract.id_practica"));	
									            pr.setTipo_practica(Enumeradores.TipoPractica.DISCAPACIDAD);
									            pr.setDescripcion(rs.getString("pract.descripcion"));
									            pr.setDuracion(rs.getInt("pract.duracion"));
									            pr.setEstado(rs.getBoolean("pract.estado"));
									            pr.setEquipo(null);
									            pr.setFecha_baja(rs.getDate("pract.fecha_baja").toLocalDate());
									            //Creo el Service de Monto Practica para buscar los Montos de cada Practica
									            MontosPracticaService mps = new MontosPracticaService();
									            pr.setMontos(mps.getMontosPractica(pr));
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
								
									public List<Practica> getAllInactivas()
									{
										List<Practica> practicas = new ArrayList<>();
										practicas.addAll(this.getAllAmbulatoriasInactivas());
										practicas.addAll(this.getAllDiscapacidadInactivas());
										return practicas;
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
									
									public Practica getPracticaPorID(Integer idPractica) {
										Practica pr = new Practica();
									    
									    try {
									        stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM practica pract where pract.id_practica=?");
									        stmt.setInt(1, idPractica);
									        rs = stmt.executeQuery();
									        while (rs != null && rs.next()) {
									            pr.setId_practica(rs.getInt("pract.id_practica"));	
									            pr.setTipo_practica(Enumeradores.TipoPractica.DISCAPACIDAD);
									            pr.setDescripcion(rs.getString("pract.descripcion"));
									            pr.setDuracion(rs.getInt("pract.duracion"));
									            pr.setEstado(rs.getBoolean("pract.estado"));
									            pr.setEquipo(null);
									            if(rs.getDate("pract.fecha_baja")==null)
									            {
									            	pr.setFecha_baja(null);
									            }
									            else 
									            {
									            	pr.setFecha_baja(rs.getDate("pract.fecha_baja").toLocalDate());
									            }
									            //Creo el Service de Monto Practica para buscar los Montos de cada Practica
									            MontosPracticaService mps = new MontosPracticaService();
									            pr.setMontos(mps.getMontosPractica(pr));
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
									    return pr;
									}
									
									//Update
									public String actualizarAmbulatoria(Integer idPractica, String descPractica, Integer duracion, Integer idEquipo) {														
										try
										{														
											stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update practica set descripcion=?, id_equipo=?, duracion=? where id_practica=?");
											stmt.setString(1, descPractica);
											stmt.setInt(2, idEquipo);
											stmt.setInt(3, duracion);
											stmt.setInt(4, idPractica);
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
									
									public String actualizarDiscapacidad(Integer idPractica, String descPractica, Integer duracion) {														
										try
										{														
											stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update practica set descripcion=?, duracion=? where id_practica=?");
											stmt.setString(1, descPractica);
											stmt.setInt(2, duracion);
											stmt.setInt(3, idPractica);
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
								
									//Delete
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
	}