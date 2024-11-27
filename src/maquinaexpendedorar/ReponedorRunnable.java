package maquinaexpendedorar;

import comunes.Maquina;
import maquinaexpendedoramon.MaquinaMonitor;

public class ReponedorRunnable implements Runnable{
	String nombre = "Paquiño";
	Maquina maquinaExp;
	MaquinaMonitor maquinaExpMon;
	
	public ReponedorRunnable(Maquina maquinaExp) {
		this.maquinaExp = maquinaExp;
	}
	
	public ReponedorRunnable(MaquinaMonitor maquinaExpMon) {
		this.maquinaExpMon = maquinaExpMon;
	}
	
	public void run() {
		try {
			Thread.sleep(1000);
			if (maquinaExp != null) {
				while (maquinaExp.verCantRep()>0) {
					maquinaExp.repProducto(nombre);
					Thread.sleep(3000);//Descansa antes de comprobar otra vez si tiene que repones
				}
	            maquinaExp.getDRecaudado(nombre);//Recoge dinero
			} else {
				while (maquinaExpMon.verCantRep()>0) {
					maquinaExpMon.repProducto(nombre);
					Thread.sleep(3000);//Descansa antes de comprobar otra vez si tiene que repones
				}
	            maquinaExpMon.getDRecaudado(nombre);//Recoge dinero
			}
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
