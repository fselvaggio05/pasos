package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import entity.Paciente;
import entity.Prescripcion;
import entity.Turno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.PacienteService;
import service.PrescripcionService;
import service.TurnoService;

@WebServlet("/prescripcion")
public class PrescripcionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TurnoService turServ;
    private PacienteService pacServ;
    private PrescripcionService prescServ; 

    public PrescripcionServlet() {      
        this.turServ = new TurnoService();
        this.pacServ = new PacienteService();
        this.prescServ = new PrescripcionService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Prescripcion> ambulatorias = prescServ.getAllAmbulatorias();
        List<Prescripcion> discapacidad = prescServ.getAllDiscapacidad();
        request.setAttribute("prescripcionesAmbulatorias", ambulatorias);
        request.setAttribute("prescripcionesDiscapacidad", discapacidad);
        request.getRequestDispatcher("registroPrescripcion.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String operacion = request.getParameter("operacion");
        String respuestaOperacion = null;
        Paciente pac = null;
        HttpSession sesion = request.getSession();    
        
        switch (operacion) {
            case "buscarPaciente": 
            {
                // Verificar si el parámetro dniPaciente es nulo o está vacío
                String dniPacienteParam = request.getParameter("dniPaciente");
                if (dniPacienteParam == null || dniPacienteParam.isEmpty()) {
                    this.doGet(request, response);
                    return;
                }
                
                // Convertir el dniPaciente a Integer y buscar al paciente
                Integer dni = Integer.parseInt(dniPacienteParam);
                pac = pacServ.buscarPaciente(dni);
                
                if (pac != null) {
                    // Si el paciente existe, buscar las prescripciones que tenga
                    List<Prescripcion> ambulatorias = prescServ.getAmbulatoriasPaciente(pac);
                    List<Prescripcion> discapacidad = prescServ.getDiscapacidadPaciente(pac);
                    if (ambulatorias == null && discapacidad == null) {
                        respuestaOperacion = "No se encontraron prescripciones para ese paciente.";
                    }
                    // Establecer los atributos de sesión
                    sesion.setAttribute("dniPaciente", pac.getDni());
                    sesion.setAttribute("prescripcionesAmbulatorias", ambulatorias);
                    sesion.setAttribute("prescripcionesDiscapacidad", discapacidad);
                    // Redireccionar al doGet normal
                    request.getRequestDispatcher("registroPrescripcion.jsp").forward(request, response);
                } else {
                    // Si el paciente no existe, notificar con un mensaje
                    respuestaOperacion = "Paciente no encontrado";
                    // Establecer el mensaje como atributo de la solicitud
                    request.setAttribute("mensaje", respuestaOperacion);
                    // Volver a cargar la página actual (puede necesitar ajustes si se están utilizando frameworks)
                    request.getRequestDispatcher("registroPrescripcion.jsp").forward(request, response);
                }
                break;
            }
            case "alta":
            {
                Prescripcion pr = new Prescripcion();
                pr.setCant_sesiones(Integer.parseInt(request.getParameter("cantSesiones")));      
                pr.setFecha_prescripcion(LocalDate.parse(request.getParameter("fechaPresc")));
                pac = pacServ.buscarPaciente(Integer.parseInt(request.getParameter("dni")));
                pr.setSesiones_asistidas(1); 
                pr.setFecha_alta_prescripcion(LocalDate.now());
                //busco el turno y traigo la practica asociada para setear la practica en la prescripcion
                Integer idTurno = Integer.parseInt(request.getParameter("idTurno"));            
                Turno tur = turServ.buscarTurno(idTurno);
                pr.setPractica(tur.getHorario().getPractica());
                //realizo el registro de asistencia dentro del mismo registro de prescripcion
                pac = (Paciente) sesion.getAttribute("paciente");
                Prescripcion prescAnterior = prescServ.buscarPrescripcionesPaciente(pac,pr);
                if(prescAnterior == null)
                {
                    Integer id_prescripcion = prescServ.insertarPrescripcion(pr);
                    turServ.registrarAsistencia(pac, idTurno);
                    turServ.asignarPrescripcionATurno(tur, id_prescripcion); 
                    respuestaOperacion = "OK";
                }
                else
                {
                    respuestaOperacion = "Existe una prescripcion vigente con los datos ingresados"; //agregar mostrar prescripcion
                }
                break;
            }
        }

        if (respuestaOperacion != null && !respuestaOperacion.isEmpty()) {
            request.setAttribute("mensaje", respuestaOperacion);
        }
    }
}
