package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Horario {
	
	
	private Integer id_horario;

	private LocalTime hora_desde;
	private LocalTime hora_hasta;
	private LocalDate fecha_baja;
	private LocalDate fecha_alta;
	private String dia_semana; //TODO: crear enum para dias de la semana
	private Practica practica;	
	private Profesional profesional;
	



	public Horario(Profesional prof, LocalTime hora_desde, LocalTime hora_hasta, LocalDate fecha_baja, LocalDate fecha_alta,
			String dia_semana) {
		
		
		this.profesional = prof;
		this.hora_desde = hora_desde;
		this.hora_hasta = hora_hasta;
		this.fecha_baja = fecha_baja;
		this.fecha_alta = fecha_alta;
		this.dia_semana = dia_semana;
	}
	
	
	
	public Horario() {
	
	}




	public LocalTime getHora_desde() {		
		
		return hora_desde;
	}
	
	public Practica getPractica() {
		return practica;
	}

	public void setPractica(Practica practica) {
		this.practica = practica;
	}

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}


	public void setHora_desde(LocalTime hora_desde) {
		this.hora_desde = hora_desde;
	}
	public LocalTime getHora_hasta() {
		return hora_hasta;
	}
	public void setHora_hasta(LocalTime hora_hasta) {
		this.hora_hasta = hora_hasta;
	}
	public LocalDate getFecha_baja() {
		return fecha_baja;
	}
	public void setFecha_baja(LocalDate fecha_baja) {
		this.fecha_baja = fecha_baja;
	}
	public LocalDate getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(LocalDate fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	public String getDia_semana() {
		return dia_semana;
	}
	public void setDia_semana(String dia_semana) {
		this.dia_semana = dia_semana;
	}


	public Integer getId_horario() {
		return id_horario;
	}

	public void setId_horario(Integer id_horario) {
		this.id_horario = id_horario;
	}

	public int diaSemanaAsNumber() {
	    switch (this.dia_semana.toLowerCase()) {
	        case "lunes": return 1;
	        case "martes": return 2;
	        case "miércoles": return 3;
	        case "jueves": return 4;
	        case "viernes": return 5;
	        case "sábado": return 6;
	        case "domingo": return 7;
	        default: throw new IllegalArgumentException("Día de la semana inválido: " + this.dia_semana);
	    }
	}
	
	public int getDiaSemanaAsNumber() {
        switch (dia_semana.toLowerCase()) {
            case "lunes":
                return 1;
            case "martes":
                return 2;
            case "miércoles":
            case "miercoles": // Para evitar problemas con acentos
                return 3;
            case "jueves":
                return 4;
            case "viernes":
                return 5;
            case "sábado":
            case "sabado": // Para evitar problemas con acentos
                return 6;
            case "domingo":
                return 7;
            default:
                throw new IllegalArgumentException("Día de la semana inválido: " + dia_semana);
        }
    }

    // Método setter y getter para diaSemana
    public String getDiaSemana() {
        return dia_semana;
    }

    public void setDiaSemana(String diaSemana) {
        this.dia_semana = diaSemana;
    }
	
	
	
	

}
