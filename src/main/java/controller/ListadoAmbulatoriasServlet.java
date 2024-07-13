package controller;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;


@WebServlet("/listadoambulatorias")

public class ListadoAmbulatoriasServlet extends HttpServlet {
	
	private TurnoService turServ;
	private PacienteService pacServ;
	private PrescripcionService prescServ;
	private ProfesionalService profServ;
	
	

	public ListadoAmbulatoriasServlet() {
		
		this.turServ = new TurnoService();
		this.pacServ = new PacienteService();
		this.prescServ = new PrescripcionService();
		this.profServ = new ProfesionalService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
		List<Profesional> profesionales = profServ.getAll(); 
		request.setAttribute("profesionales", profesionales);
		request.getRequestDispatcher("ListadoAmbulatorias.jsp").forward(request, response);	

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operacion = request.getParameter("operacion");
		String respuestaOperacion = null;
		String mensaje = null;
		Paciente pac = null;
		HttpSession sesion = request.getSession(); 	
		List<Prescripcion> pres = null ;
		List<Turno> tur = null;
		

		switch (operacion) {
		
		
		case "listado": 
		{   //permite listar las prescripciones ambulatorias pendientes 
			 
			
			Integer matricula = Integer.parseInt(request.getParameter("profesional"));
			Profesional profesional = profServ.getProfesional(matricula);
			LocalDate fecha_desde = LocalDate.parse(request.getParameter("fecha_desde"));
			LocalDate fecha_hasta = LocalDate.parse(request.getParameter("fecha_hasta"));
			 tur= turServ.buscarTurnosAsistidosAmbulatorios(fecha_desde, fecha_hasta, profesional.getMatricula());
			 
			if(tur.size()==0)
			{
				respuestaOperacion="No existen turnos ambulatorios pendientes de cobro para el período ingresado";
				
			}												
			request.setAttribute("turnos", tur);
			request.setAttribute("profesionalSeleccionado",profesional.getMatricula());
			request.setAttribute("fecha_desde", fecha_desde);
			request.setAttribute("fecha_hasta", fecha_hasta);
			request.setAttribute("mensaje", respuestaOperacion);
			this.doGet(request, response);
			break;
		}

		case "exportar": 
		{   
			LocalDate fecha_desde = LocalDate.parse(request.getParameter("fecha_desde"));
			LocalDate fecha_hasta = LocalDate.parse(request.getParameter("fecha_hasta"));
			Integer matricula = Integer.parseInt(request.getParameter("profesional"));
			Profesional profesional = profServ.getProfesional(matricula);
			request.setAttribute("fecha_desde", fecha_desde);
			request.setAttribute("fecha_hasta", fecha_hasta);
			request.setAttribute("turnos", tur);
			request.setAttribute("profesionalSeleccionado",profesional.getMatricula());
			//turServ.facturacionTurnos(tur);
			//permite exportar la lista de prescripciones ambulatorias pendientes, en un documento formato pdf
			Document documento = new Document();

	        try {
	        	PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\Pich\\Documents\\Prescripciones medicas - " + profesional.getApellido()+ ", " + profesional.getNombre() + " - Periodo " + fecha_desde + " - " + fecha_hasta + ".pdf"));
	        	//PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\Pich\\Documents\\prescripciones_medicas.pdf"));
	            documento.open();

	            // Agrega imagen en el encabezado
	            //Queda pendiente generar el archivo incluyendo el nombre del profesional
	            Image imagenClinica = Image.getInstance("C:\\Users\\Pich\\Documents\\pasosClinica.png"); // Ruta a la imagen
	            imagenClinica.scaleToFit(100, 100); // Ajustar tamaño de la imagen
	            imagenClinica.setAlignment(Element.ALIGN_CENTER); // Centrar la imagen
	            documento.add(imagenClinica);

	            // Agrega datos de la clínica
	            Paragraph datosClinica = new Paragraph("Pasos - Kinesiología Integral - Email: contacto@clinicapasos.com", FontFactory.getFont(FontFactory.HELVETICA, 12));
	            datosClinica.setAlignment(Element.ALIGN_CENTER);
	            Paragraph datosClinica2 = new Paragraph("Remito de Prestaciones Fisio - Kinésicas", FontFactory.getFont(FontFactory.HELVETICA, 12));
	            datosClinica2.setAlignment(Element.ALIGN_CENTER);
	            //Queda pendiente agregar un combo, que permita elegir un profesional, y para ese profesional (o todos) buscar sus prescripciones pendientes
	            Paragraph datosClinica3 = new Paragraph("Profesional: " + profesional.getApellido() + ", " + profesional.getNombre() + " - Matrícula: " + profesional.getMatricula(), FontFactory.getFont(FontFactory.HELVETICA, 12));
	            datosClinica3.setAlignment(Element.ALIGN_CENTER); // Centrar los datos de la clínica
	            documento.add(datosClinica);
	            documento.add(datosClinica2);
	            documento.add(datosClinica3);

	            documento.add(new Paragraph("\n")); // Espacio después del encabezado

	           
	            tur= turServ.buscarTurnosAsistidosAmbulatorios(fecha_desde, fecha_hasta, profesional.getMatricula());
                
	            // Ordenar la lista de turnos
	            Collections.sort(tur, new Comparator<Turno>() {
	                @Override
	                public int compare(Turno t1, Turno t2) {
	                    int cmp = t1.getPaciente().getObra_social().getNombre_os().compareTo(t2.getPaciente().getObra_social().getNombre_os());
	                    if (cmp == 0) {
	                        cmp = t1.getHorario().getPractica().getDescripcion().compareTo(t2.getHorario().getPractica().getDescripcion());
	                        if (cmp == 0) {
	                            cmp = t1.getPaciente().getApellido().compareTo(t2.getPaciente().getApellido());
	                            if (cmp == 0) {
	                                cmp = t1.getPaciente().getNombre().compareTo(t2.getPaciente().getNombre());
	                                if (cmp == 0) {
	                                    cmp = t1.getFecha_t().compareTo(t2.getFecha_t());
	                                }
	                            }
	                        }
	                    }
	                    return cmp;
	                }
	            });

	            // Agregar encabezado del reporte
	            documento.add(new Paragraph("Prescripciones Médicas para el período: " + fecha_desde + " - " + fecha_hasta));
	            documento.add(new Paragraph("\n"));

	            // Crear tabla con 5 columnas
	            PdfPTable table = new PdfPTable(5);
	            table.setWidthPercentage(100);
	            table.setSpacingBefore(10f);
	            table.setSpacingAfter(10f);

	            // Agregar encabezados de columna
	            PdfPCell cell;

	            cell = new PdfPCell(new Phrase("Obra Social"));
	            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Práctica"));
	            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Paciente"));
	            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Fecha"));
	            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
	            table.addCell(cell);
	            
	            cell = new PdfPCell(new Phrase("Sesiones asistidas"));
	            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
	            table.addCell(cell);

	            // Variables para agrupar
	            String obraSocialActual = "";
	            String practicaActual = "";
	            String pacienteActual = "";

	            for (int i = 0; i < tur.size(); i++) {
	                Turno t = tur.get(i);
	                String obraSocial = t.getPaciente().getObra_social().getNombre_os();
	                String practica = t.getHorario().getPractica().getDescripcion();
	                String paciente = t.getPaciente().getApellido() + ", " + t.getPaciente().getNombre();
	                String fecha = t.getFecha_t().toString();
	               String cantidadSesiones = String.valueOf(t.getPrescripcion().getSesiones_asistidas());

	                // Agrupación por obra social
	                if (!obraSocial.equals(obraSocialActual)) {
	                    obraSocialActual = obraSocial;
	                    practicaActual = ""; // Reiniciar práctica cuando cambia la obra social
	                    pacienteActual = ""; // Reiniciar paciente cuando cambia la obra social

	                    PdfPCell obraSocialCell = new PdfPCell(new Phrase(obraSocial));
	                    obraSocialCell.setRowspan(getRowSpanForObraSocial(tur, i, obraSocial));
	                    table.addCell(obraSocialCell);
	                }

	                // Agrupación por práctica
	                if (!practica.equals(practicaActual)) {
	                    practicaActual = practica;
	                    pacienteActual = ""; // Reiniciar paciente cuando cambia la práctica

	                    PdfPCell practicaCell = new PdfPCell(new Phrase(practica));
	                    practicaCell.setRowspan(getRowSpanForPractica(tur, i, obraSocial, practica));
	                    table.addCell(practicaCell);
	                }

	                // Agrupación por paciente
	                if (!paciente.equals(pacienteActual)) {
	                    pacienteActual = paciente;

	                    PdfPCell pacienteCell = new PdfPCell(new Phrase(paciente));
	                    pacienteCell.setRowspan(getRowSpanForPaciente(tur, i, obraSocial, practica, paciente));
	                    table.addCell(pacienteCell);
	                }

	                // Fecha y cantidad de sesiones siempre se muestran
	                table.addCell(fecha);
	                table.addCell(cantidadSesiones);
	            }

	            // Añadir la tabla al documento
	            documento.add(table);

	            documento.close();

	            
					respuestaOperacion="Documento exportado exitosamente";
					request.setAttribute("mensaje", respuestaOperacion);
				
	            
				               //Queda pendiente cambiar el estado del turno a Facturado / exportado
							
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
	

// Método para obtener el rowspan para obra social
    private static int getRowSpanForObraSocial(List<Turno> tur, int index, String obraSocial) {
        int count = 0;
        for (int i = index; i < tur.size(); i++) {
            if (tur.get(i).getPaciente().getObra_social().getNombre_os().equals(obraSocial)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    // Método para obtener el rowspan para práctica
    private static int getRowSpanForPractica(List<Turno> tur, int index, String obraSocial, String practica) {
        int count = 0;
        for (int i = index; i < tur.size(); i++) {
            if (tur.get(i).getPaciente().getObra_social().getNombre_os().equals(obraSocial) &&
                tur.get(i).getHorario().getPractica().getDescripcion().equals(practica)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    // Método para obtener el rowspan para paciente
    private static int getRowSpanForPaciente(List<Turno> tur, int index, String obraSocial, String practica, String paciente) {
        int count = 0;
        for (int i = index; i < tur.size(); i++) {
            if (tur.get(i).getPaciente().getObra_social().getNombre_os().equals(obraSocial) &&
                tur.get(i).getHorario().getPractica().getDescripcion().equals(practica) &&
                (tur.get(i).getPaciente().getApellido() + ", " + tur.get(i).getPaciente().getNombre()).equals(paciente)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }}