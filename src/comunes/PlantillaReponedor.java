package comunes;

public class PlantillaReponedor extends Thread {
	String nombre = "Paquiño";
	Maquina maquinaExp;
	
	public PlantillaReponedor(Maquina maquinaExp) {
		this.maquinaExp = maquinaExp;
	}
	
	public void run() {
		try {
			while (maquinaExp.verCantRep()>0) {
				maquinaExp.repProducto(nombre);
				Thread.sleep(5000);//Descansa antes de comprobar otra vez si tiene que repones
			}
            maquinaExp.getDRecaudado(nombre);//Recoge dinero
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
