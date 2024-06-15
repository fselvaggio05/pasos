package controller;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entity.Horario;
import entity.Paciente;
import entity.Practica;
import entity.Prescripcion;
import entity.Profesional;
import entity.Turno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.HorarioService;
import service.PacienteService;
import service.PracticaService;
import service.PrescripcionService;
import service.ProfesionalService;
import service.TurnoService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


@WebServlet("/listadoambulatorias")

public class ListadoAmbulatoriasServlet extends HttpServlet {
	
	private TurnoService turServ;
	private PacienteService pacServ;
	private PrescripcionService prescServ;
	
	

	public ListadoAmbulatoriasServlet() {
		
		this.turServ = new TurnoService();
		this.pacServ = new PacienteService();
		this.prescServ = new PrescripcionService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		request.getRequestDispatcher("ListadoAmbulatorias.jsp").forward(request, response);		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operacion = request.getParameter("operacion");
		String respuestaOperacion = null;
		String mensaje = null;
		Paciente pac = null;
		HttpSession sesion = request.getSession(); 	
		List<Prescripcion> pres = null ;

		switch (operacion) {
		
		
		case "listado": 
		{
			
			LocalDate fecha_desde = LocalDate.parse(request.getParameter("fecha_desde"));
			LocalDate fecha_hasta = LocalDate.parse(request.getParameter("fecha_hasta"));
			 pres = prescServ.buscarPrescripcionesAmbulatorias(fecha_desde, fecha_hasta);
			
			if(pres.size()==0)
			{
				respuestaOperacion="No existen prescripciones ambulatorias pendientes de cobro para el período ingresado";
			}												
			request.setAttribute("prescripcion", pres);	
			this.doGet(request, response);
			
			
			
			break;
			
			
			
			
			
			
			
		}

		case "exportar": 
		{
			
			
			//permite exportar la lista de prescripciones ambulatorias pendientes, en un documento formato pdf
			            Document documento = new Document();
			      
			            try {
			            	PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\Pich\\Documents\\prescripciones_medicas.pdf"));
			                documento.open();
				            LocalDate fecha_desde = LocalDate.parse(request.getParameter("fecha_desde"));
							LocalDate fecha_hasta = LocalDate.parse(request.getParameter("fecha_hasta"));
							pres = prescServ.buscarPrescripcionesAmbulatorias(fecha_desde, fecha_hasta);
				            documento.add(new Paragraph("Prescripciones Médicas para el período: " + fecha_desde + " - " + fecha_hasta));
				            documento.add(new Paragraph("\n"));
				         
				            
				            int i = 0;
				            while (i < pres.size()) {
				                Prescripcion prescripcion = pres.get(i);
				                //validar si solo mostramos el nombre de la obra social o el id y el nombre de la obra social o solo el id
				                documento.add(new Paragraph("Obra Social: " + prescripcion.getPaciente().getObra_social().getId_obra_social() + " - " + prescripcion.getPaciente().getObra_social().getNombre_os()));
				                documento.add(new Paragraph("Paciente: " + prescripcion.getPaciente().getNro_afiliado() + " " + prescripcion.getPaciente().getApellido() + ", " + prescripcion.getPaciente().getNombre()));
				                documento.add(new Paragraph("Practica: " + prescripcion.getPractica().getDescripcion()));
				                documento.add(new Paragraph("Fecha: " + prescripcion.getFecha_prescripcion()));
				                documento.add(new Paragraph("Cantidad de Sesiones: " + prescripcion.getCant_sesiones()));
				                documento.add(new Paragraph("\n"));
				                i++;       
				                                    }
				                documento.close(); 
				                
				               
							
				                this.doGet(request, response);
				                
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (DocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			          
			    break;
				
		}
			
		
		}
	}
	
}