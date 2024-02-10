package controller;

import entity.Paciente;
import entity.Profesional;
import entity.Usuario;
import service.PacienteService;
import service.ProfesionalService;
import service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/usuarios")

public class UsuarioServlet extends HttpServlet {

    public UsuarioService usServ;

    public PacienteService pacServ;

    public ProfesionalService profServ;

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    		request.getRequestDispatcher("usuarios").forward(request,response);
    }

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UsuarioService usServ = new UsuarioService();
        Integer opcUs = Integer.parseInt(request.getParameter("tipoUsuario"));
        String respuestaOperacion = null;
        String mensaje = null;

        //TODO AGREGAR ENVIO DE MAIL LUEGO DE LA CREACION DEL PACIENTE Y PROFESIONAL

        switch(opcUs) {
            case 1: {
               
            	
            		Integer dni = Integer.parseInt(request.getParameter("dni"));
                    Usuario us;
					try {
						us = new Usuario(dni, request.getParameter("nombre"), request.getParameter("apellido"), request.getParameter("email"), request.getParameter("fechaNac"), request.getParameter("telefono"), request.getParameter("clave"), request.getParameter("genero"));
					    respuestaOperacion = usServ.insertarUsuario(us);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                 
                break;	                
              
            }

            case 2: { //TODO REVISAR MATRICULA QUE LLEGA EN NULL
               
                    Integer matricula = Integer.parseInt(request.getParameter("matricula"));
                    Profesional prof;
					try {
						prof = new Profesional(Integer.parseInt(request.getParameter("dni")), request.getParameter("nombre"), request.getParameter("apellido"), request.getParameter("email"), request.getParameter("fechaNac"), request.getParameter("telefono"), request.getParameter("clave"), request.getParameter("genero") , matricula);
						ProfesionalService profServ = new ProfesionalService();
		                respuestaOperacion = profServ.insertarProfesional(prof);

					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					break;	     
                 
               
            }

            case 3:{
               
                    Integer obraSocial = Integer.parseInt(request.getParameter("obraSocial"));
                    Paciente pac;
					try {
						pac = new Paciente(Integer.parseInt(request.getParameter("dni")), request.getParameter("nombre"), request.getParameter("apellido"), request.getParameter("email"), request.getParameter("fechaNac"), request.getParameter("telefono"), request.getParameter("clave"), request.getParameter("genero"), obraSocial , request.getParameter("nroAfiliado"));
				        PacienteService pacServ = new PacienteService();
	                    respuestaOperacion = pacServ.insertarPaciente(pac);
	                    
					} catch (NumberFormatException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            
					break;	     
               
            }
            

        }
        
        if (respuestaOperacion == "OK")
        {
        	mensaje = "Operacion realizada correctamente";
        	request.setAttribute("mensaje", mensaje);
        	
        }
        
        else 
        {
        	mensaje = respuestaOperacion;
        	request.setAttribute("mensaje", mensaje);
        }
        
        
        // esta linea muestra error cuado se quiere redireccionar la pagina desde alli 
       //this.doGet(request, response);
        
        request.getRequestDispatcher("altaUsuario_admin.jsp").forward(request,response);
        
         
      
        
    }

}