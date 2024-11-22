package comunes;

public class Moneda {
	private String nombre;
	private double valor;
	
	/**
	 * Crea una monesa
	 * @param nombre nombre moneda
	 * @param valor valor moneda
	 */
	public Moneda(String nombre, double valor) {
		this.nombre = nombre;
		this.valor = valor;
	}
	
	/**
	 * Ver valor moneda
	 * @return valro moneda
	 */
	public double getValor() {
		return valor;
	}
}