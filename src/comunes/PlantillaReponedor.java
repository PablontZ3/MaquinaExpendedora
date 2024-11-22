package comunes;

public class PlantillaReponedor extends Thread {
	String nombre = "Paquiño";
	Maquina maquinaExp;
	
	public PlantillaReponedor(Maquina maquinaExp) {
		this.maquinaExp = maquinaExp;
	}
	
	public void run() {
		try {
			
			Thread.sleep(1000);
			
			maquinaExp.repProducto(nombre);//Repone maquina a las 12am
			
			Thread.sleep(1000);
			
            maquinaExp.repProducto(nombre);//Repone maquina a las 12pm
            
            maquinaExp.getDRecaudado(nombre);//Recoge dinero a las 12pm
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
