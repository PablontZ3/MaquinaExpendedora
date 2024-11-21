package comunes;

public class PlantillaPersona extends Thread {
	String nombre;
	Cartera cartera;
	Maquina maquinaExp;
	int productoInteres;
	
	/**
	 * Creamos persona
	 * @param cartera Cartera de la persona
	 * @param nombre Nombre persona
	 * @param maquinaExp Maquina expendedora
	 * @param productoInteres Producto que quiere comprar
	 */
	public PlantillaPersona(Cartera cartera,String nombre,Maquina maquinaExp,int productoInteres) {
		this.nombre = nombre;
		this.cartera = cartera;
		this.maquinaExp = maquinaExp;
		this.productoInteres = productoInteres;
	}
	
	public void run() {
		maquinaExp.añadirDinero(cartera);
		if (maquinaExp.cogerProducto(productoInteres)) {
			System.out.println(nombre+" Pudo coger producto");
			maquinaExp.cogerVuelta(cartera);
		}
	}
}
