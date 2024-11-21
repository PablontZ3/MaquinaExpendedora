package comunes;

public class Producto {
	private String nombre;
	private double precio;
	
	/**
	 * Creador producto
	 * @param nombre nombre del producto
	 * @param precio precio del producto
	 */
	public Producto(String nombre,double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}
	/**
	 * Ver nombre producto
	 * @return nombre del producto
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Ver precio producto
	 * @return precio del producto
	 */
	public double getPrecio() {
		return precio;
	}
}
