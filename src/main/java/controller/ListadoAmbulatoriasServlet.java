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
		{   //permite listar las prescripciones ambulatorias pendientes 
			LocalDate fecha_desde = LocalDate.parse(request.getParameter("fecha_desde"));
			LocalDate fecha_hasta = LocalDate.parse(request.getParameter("fecha_hasta"));
			 pres = prescServ.buscarPrescripcionesAmbulatorias(fecha_desde, fecha_hasta);
			if(pres.size()==0)
			{
				respuestaOperacion="No existen prescripciones ambulatorias pendientes de cobro para el período ingresado";
			}												
			request.setAttribute("prescripcion", pres);	
			request.setAttribute("fecha_desde", fecha_desde);
			request.setAttribute("fecha_hasta", fecha_hasta);
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

	            // Agrega imagen en el encabezado
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
	            Paragraph datosClinica3 = new Paragraph("Profesional: " + "Matrícula: ", FontFactory.getFont(FontFactory.HELVETICA, 12));
	            datosClinica3.setAlignment(Element.ALIGN_CENTER); // Centrar los datos de la clínica
	            documento.add(datosClinica);
	            documento.add(datosClinica2);
	            documento.add(datosClinica3);

	            documento.add(new Paragraph("\n")); // Espacio después del encabezado

	            LocalDate fecha_desde = LocalDate.parse(request.getParameter("fecha_desde"));
	            LocalDate fecha_hasta = LocalDate.parse(request.getParameter("fecha_hasta"));
	            List<Prescripcion> presc = prescServ.buscarPrescripcionesAmbulatorias(fecha_desde, fecha_hasta);

	            // Ordenar la lista de prescripciones
	            Collections.sort(presc, new Comparator<Prescripcion>() {
	                @Override
	                public int compare(Prescripcion p1, Prescripcion p2) {
	                    int cmp = p1.getPaciente().getObra_social().getNombre_os().compareTo(p2.getPaciente().getObra_social().getNombre_os());
	                    if (cmp == 0) {
	                        cmp = p1.getPractica().getDescripcion().compareTo(p2.getPractica().getDescripcion());
	                        if (cmp == 0) {
	                            cmp = p1.getPaciente().getApellido().compareTo(p2.getPaciente().getApellido());
	                            if (cmp == 0) {
	                                cmp = p1.getPaciente().getNombre().compareTo(p2.getPaciente().getNombre());
	                                if (cmp == 0) {
	                                    cmp = p1.getFecha_prescripcion().compareTo(p2.getFecha_prescripcion());
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
	            
	            cell = new PdfPCell(new Phrase("Cantidad de Sesiones"));
	            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
	            table.addCell(cell);

	            // Variables para agrupar
	            String obraSocialActual = "";
	            String practicaActual = "";
	            String pacienteActual = "";

	            for (int i = 0; i < presc.size(); i++) {
	                Prescripcion prescripcion = presc.get(i);
	                String obraSocial = prescripcion.getPaciente().getObra_social().getNombre_os();
	                String practica = prescripcion.getPractica().getDescripcion();
	                String paciente = prescripcion.getPaciente().getApellido() + ", " + prescripcion.getPaciente().getNombre();
	                String fecha = prescripcion.getFecha_prescripcion().toString();
	                String cantidadSesiones = String.valueOf(prescripcion.getCant_sesiones());

	                // Agrupación por obra social
	                if (!obraSocial.equals(obraSocialActual)) {
	                    obraSocialActual = obraSocial;
	                    practicaActual = ""; // Reiniciar práctica cuando cambia la obra social
	                    pacienteActual = ""; // Reiniciar paciente cuando cambia la obra social

	                    PdfPCell obraSocialCell = new PdfPCell(new Phrase(obraSocial));
	                    obraSocialCell.setRowspan(getRowSpanForObraSocial(presc, i, obraSocial));
	                    table.addCell(obraSocialCell);
	                }

	                // Agrupación por práctica
	                if (!practica.equals(practicaActual)) {
	                    practicaActual = practica;
	                    pacienteActual = ""; // Reiniciar paciente cuando cambia la práctica

	                    PdfPCell practicaCell = new PdfPCell(new Phrase(practica));
	                    practicaCell.setRowspan(getRowSpanForPractica(presc, i, obraSocial, practica));
	                    table.addCell(practicaCell);
	                }

	                // Agrupación por paciente
	                if (!paciente.equals(pacienteActual)) {
	                    pacienteActual = paciente;

	                    PdfPCell pacienteCell = new PdfPCell(new Phrase(paciente));
	                    pacienteCell.setRowspan(getRowSpanForPaciente(presc, i, obraSocial, practica, paciente));
	                    table.addCell(pacienteCell);
	                }

	                // Fecha y cantidad de sesiones siempre se muestran
	                table.addCell(fecha);
	                table.addCell(cantidadSesiones);
	            }

	            // Añadir la tabla al documento
	            documento.add(table);

	            documento.close();

				               //Queda pendiente cambiar el estado de la prescipcion a Facturada / exportada
							
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
    private static int getRowSpanForObraSocial(List<Prescripcion> pres, int index, String obraSocial) {
        int count = 0;
        for (int i = index; i < pres.size(); i++) {
            if (pres.get(i).getPaciente().getObra_social().getNombre_os().equals(obraSocial)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    // Método para obtener el rowspan para práctica
    private static int getRowSpanForPractica(List<Prescripcion> pres, int index, String obraSocial, String practica) {
        int count = 0;
        for (int i = index; i < pres.size(); i++) {
            if (pres.get(i).getPaciente().getObra_social().getNombre_os().equals(obraSocial) &&
                pres.get(i).getPractica().getDescripcion().equals(practica)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    // Método para obtener el rowspan para paciente
    private static int getRowSpanForPaciente(List<Prescripcion> pres, int index, String obraSocial, String practica, String paciente) {
        int count = 0;
        for (int i = index; i < pres.size(); i++) {
            if (pres.get(i).getPaciente().getObra_social().getNombre_os().equals(obraSocial) &&
                pres.get(i).getPractica().getDescripcion().equals(practica) &&
                (pres.get(i).getPaciente().getApellido() + ", " + pres.get(i).getPaciente().getNombre()).equals(paciente)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }}