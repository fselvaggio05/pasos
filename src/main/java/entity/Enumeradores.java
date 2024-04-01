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
	}

}
