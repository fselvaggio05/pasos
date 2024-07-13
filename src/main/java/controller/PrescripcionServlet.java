package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import entity.Paciente;
import entity.Practica;
import entity.Prescripcion;
import entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.PacienteService;
import service.PracticaService;
import service.PrescripcionService;




@WebServlet("/prescripcion")
public class PrescripcionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PacienteService pacServ;
    private PrescripcionService prescServ;
    private PracticaService practServ;

    public PrescripcionServlet() {      
        this.pacServ = new PacienteService();
        this.prescServ = new PrescripcionService();
        this.practServ = new PracticaService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getAttribute("prescripciones")==null) {
            List<Prescripcion> prescripciones = prescServ.getAll();
            request.setAttribute("prescripciones", prescripciones);
    	}
        request.getRequestDispatcher("registroPrescripcion.jsp").forward(request, response); 
      
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String operacion = request.getParameter("operacion");
        String respuestaOperacion = null;
        Paciente pac = null;
        String buscarPaciente = request.getParameter("buscarPaciente");
    	String agregarPrescripcion = request.getParameter("agregarPrescripcion");
        String dniPacienteParam = request.getParameter("dniPaciente");
        
        switch (operacion) {
            case "buscarPaciente": 
            {            	
            	//Si entró por Buscar Paciente me fijo si hay un dni o recargo la pag completa
            	if(buscarPaciente!=null) 
            	{
            		// Verificar si el parámetro dniPaciente es nulo o está vacío
                    if (dniPacienteParam == null || dniPacienteParam.isEmpty()) {
                        this.doGet(request, response);
                        return;
                    }                    
                    // Convertir el dniPaciente a Integer y buscar al paciente
                    Integer dni = Integer.parseInt(dniPacienteParam);
                    pac = pacServ.buscarPaciente(dni);
                    
                    if (pac != null) 
                    {
                        // Si el paciente existe, buscar las prescripciones que tenga
                        List<Prescripcion> prescripciones = prescServ.getAllPaciente(pac);
                        if (prescripciones == null) 
                        {
                            respuestaOperacion = "No se encontraron prescripciones para ese paciente.";
                        }
                        // Establecer los atributos de sesión
                        request.setAttribute("dniPaciente", pac.getDni());
                        request.setAttribute("prescripciones", prescripciones);
                    } 
                    else 
                    {
                        // Si el paciente no existe, notificar con un mensaje
                        respuestaOperacion = "Paciente no encontrado";
                    }                    
            	}
            	else if (agregarPrescripcion!=null) 
            	{
            		// Verificar si el parámetro dniPaciente es nulo o está vacío
                    if (dniPacienteParam == null || dniPacienteParam.isEmpty()) 
                    {
                        respuestaOperacion="Ingrese el DNI del Paciente para registrar una Prescripción.";
                    }
                    else 
                    {
                    	Integer dni = Integer.parseInt(dniPacienteParam);
                    	//Busco al Paciente
                    	pac=pacServ.buscarPaciente(dni);
                    	if(pac!=null) 
                    	{
                    		List<Practica>practicas=practServ.getAllActivas();
                    		request.setAttribute("paciente", pac);
                    		request.setAttribute("practicas", practicas);
                    		request.setAttribute("accion", agregarPrescripcion);
                    	}
                    	else 
                    	{
                    		respuestaOperacion = "El Paciente no existe.";
                    	}
                    	
                    }
            	}
            	break;
            }
            case "alta":
            {
                Integer dniPaciente = Integer.parseInt(request.getParameter("dniPaciente"));
                Paciente paciente = pacServ.buscarPaciente(dniPaciente);
                LocalDate fechaPrescripcion = LocalDate.parse(request.getParameter("fechaPrescripcion"));
            	Integer id_practica = Integer.parseInt(request.getParameter("id_practica"));
            	Integer cantSesiones = Integer.parseInt(request.getParameter("cantSesiones"));
            	respuestaOperacion=prescServ.insertarPrescripcion(paciente,fechaPrescripcion,id_practica,cantSesiones);
                break;
            }
            
            case "anular":
            {
            	Integer idPrescripcion = Integer.parseInt(request.getParameter("idPrescripcion"));
            	respuestaOperacion = prescServ.anularPrescripcion(idPrescripcion);
            	break;
            }
        }

        if ("OK".equals(respuestaOperacion))
		{																										
			String mensaje = "Operacion realizada correctamente.";
			request.setAttribute("mensaje", mensaje);
			this.doGet(request, response);																										
		}																									
		else 
		{																										
			String mensaje = respuestaOperacion;
			request.setAttribute("mensaje", mensaje);
			this.doGet(request, response);																									
		}
    }
}