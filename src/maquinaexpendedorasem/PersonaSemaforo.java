package maquinaexpendedorasem;

import java.util.concurrent.Semaphore;

import comunes.Cartera;
import comunes.Maquina;
import maquinaexpendedoramon.MaquinaMonitor;

public class PersonaSemaforo extends Thread {
	String nombre;
	Cartera cartera;
	Maquina maquinaExp;
	int productoInteres;
	Semaphore mutex, propio;
	
	/**
	 * Creamos persona
	 * @param cartera Cartera de la persona
	 * @param nombre Nombre persona
	 * @param maquinaExp Maquina expendedora
	 * @param productoInteres Producto que quiere comprar
	 */
	public PersonaSemaforo(Cartera cartera,String nombre,Maquina maquinaExp,int productoInteres, Semaphore mutex, Semaphore propio) {
		this.nombre = nombre;
		this.cartera = cartera;
		this.maquinaExp = maquinaExp;
		this.productoInteres = productoInteres;
		this.propio = propio;
		this.mutex = mutex;
	}

	public void run() {
		try {
			Thread.sleep(50);
			propio.acquire();
			mutex.acquire();	
			maquinaExp.añadirDinero(cartera,nombre,productoInteres);
			maquinaExp.cogerProducto(productoInteres);
			//Tanto si coge o no coge producto coge vuelta en caso de no tener dinero suficiente le devuelve el suyo
			maquinaExp.cogerVuelta(cartera,nombre);
			mutex.release();
			propio.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}