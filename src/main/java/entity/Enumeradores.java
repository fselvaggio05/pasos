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
}
