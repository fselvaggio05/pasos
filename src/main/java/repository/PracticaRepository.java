package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexionDB.FactoryConnection;
import entity.Equipo;
import entity.Practica;

public class PracticaRepository {
									ResultSet rs = null;	
									PreparedStatement stmt = null;	
									String respuestaOperacion;
		
									public List<Practica> getAllActivas()
									{
										List<Practica> practicas = new ArrayList<>();
										try {
												stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM practica p INNER JOIN equipo e ON p.id_equipo = e.id_equipo where p.estado=1");
												rs = stmt.executeQuery();
												while (rs!=null && rs.next())
												{
													Practica pr = new Practica ();
													Equipo eq = new Equipo();
													pr.setId_practica(rs.getInt("id_practica"));
													pr.setDescripcion(rs.getString("descripcion"));
													pr.setDuracion(rs.getInt("duracion"));
													eq.setId_equipo(rs.getInt("id_equipo"));
													eq.setDescripcion(rs.getString("e.descripcion"));
													eq.setEstado(rs.getBoolean("e.estado"));
													if(rs.getDate("e.fecha_baja")==null) {
														eq.setFecha_baja(null);
													} else {
														eq.setFecha_baja(rs.getDate("e.fecha_baja").toLocalDate());	
													}													
													pr.setEquipo(eq);
													pr.setMonto(rs.getDouble("monto"));
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
													pr.setFecha_baja(rs.getDate("fecha_baja"));
													eq.setId_equipo(rs.getInt("id_equipo"));
													eq.setDescripcion(rs.getString("e.descripcion"));
													eq.setEstado(rs.getBoolean("e.estado"));
													if(rs.getDate("e.fecha_baja")==null) {
														eq.setFecha_baja(null);
													} else {
														eq.setFecha_baja(rs.getDate("e.fecha_baja").toLocalDate());	
													}													
													pr.setEquipo(eq);
													pr.setMonto(rs.getDouble("monto"));
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
								
									public String insertarPractica(Integer idPractica, String descPractica, Integer duracion, Integer idEquipo,Double monto) {													
										try
										{
											stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into practica (id_practica, descripcion, duracion, id_equipo, monto) values (?,?,?,?,?)");
											stmt.setInt(1,idPractica);
											stmt.setString(2, descPractica);
											stmt.setInt(3, duracion);
											stmt.setInt(4, idEquipo);
											stmt.setDouble(5, monto);
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