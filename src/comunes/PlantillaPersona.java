package comunes;

import maquinaexpendedoramon.MaquinaMonitor;

public class PlantillaPersona extends Thread {
	String nombre;
	Cartera cartera;
	Maquina maquinaExp;
	MaquinaMonitor maquinaExpMon;
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
	
	public PlantillaPersona(Cartera cartera,String nombre,MaquinaMonitor maquinaExp,int productoInteres) {
		this.nombre = nombre;
		this.cartera = cartera;
		this.maquinaExpMon = maquinaExp;
		this.productoInteres = productoInteres;
	}
	
	public void run() {
		try {
			
			Thread.sleep(50);
			if (maquinaExp != null) {
				maquinaExp.añadirDinero(cartera,nombre,productoInteres);
				maquinaExp.cogerProducto(productoInteres);
				//Tanto si coge o no coge producto coge vuelta en caso de no tener dinero suficiente le devuelve el suyo
			    maquinaExp.cogerVuelta(cartera,nombre);
			} else {
				maquinaExpMon.añadirDinero(cartera,nombre,productoInteres);
				maquinaExpMon.cogerProducto(productoInteres);
			    maquinaExpMon.cogerVuelta(cartera,nombre);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
