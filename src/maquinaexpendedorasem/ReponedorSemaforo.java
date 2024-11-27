package maquinaexpendedorasem;

import java.util.concurrent.Semaphore;

import comunes.Maquina;

public class ReponedorSemaforo extends Thread {
	String nombre = "Paquiño";
	Maquina maquinaExp;
	Semaphore mutex, propio;
	
	public ReponedorSemaforo(Maquina maquinaExp, Semaphore mutex, Semaphore propio) {
		this.maquinaExp = maquinaExp;
		this.mutex = mutex;
		this.propio = propio;
	}
	
	public void run() {
		try {
			sleep(1000);
				while (maquinaExp.verCantRep()>0) {
					propio.acquire();
					mutex.acquire();
					maquinaExp.repProducto(nombre);
					Thread.sleep(3000);//Descansa antes de comprobar otra vez si tiene que repones
					mutex.release();
					propio.release();
				}
				propio.acquire();
				mutex.acquire();
	            maquinaExp.getDRecaudado(nombre);//Recoge dinero
	            mutex.release();
				propio.release();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}