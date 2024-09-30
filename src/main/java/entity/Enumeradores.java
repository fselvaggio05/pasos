package entity;

public class Enumeradores {
	
	// Definición del enum TipoPractica
	public enum TipoPractica {
	    AMBULATORIA(1), // Valor 1 para Ambulatoria
	    DISCAPACIDAD(2); // Valor 2 para Discapacidad
	    
	    private final int codigo;

	    TipoPractica(int codigo) {
	        this.codigo = codigo;
	    }

	    public int getCodigo() {
	        return codigo;
	    }

	    // Método para convertir una cadena en un enum TipoPractica
	    public static TipoPractica fromString(String str) {
	        if (str.equalsIgnoreCase("Ambulatoria")) {
	            return AMBULATORIA;
	        } else if (str.equalsIgnoreCase("Discapacidad")) {
	            return DISCAPACIDAD;
	        }
	        throw new IllegalArgumentException("Cadena de TipoPractica no válida: " + str);
	    }
	}
	
	public enum TipoUsuario{
		ADMINISTRADOR(1),
		PROFESIONAL(2),
		PACIENTE(3);
		
		private final int codigo;
		
		TipoUsuario(int codigo){
			this.codigo=codigo;
		}
		
		public int getCodigo() {
			return codigo;
		}
		
		// Método para obtener el TipoUsuario a partir de un código
	    public static TipoUsuario fromCodigo(int codigo) {
	        for (TipoUsuario tipo : TipoUsuario.values()) {
	            if (tipo.getCodigo() == codigo) {
	                return tipo;
	            }
	        }
	        throw new IllegalArgumentException("Código de TipoUsuario no válido: " + codigo);
	    }
		
	}
}
