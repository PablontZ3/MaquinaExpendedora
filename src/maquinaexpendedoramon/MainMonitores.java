package maquinaexpendedoramon;

import java.util.List;
import java.util.Random;

import comunes.Cartera;
import comunes.Maquina;
import comunes.Moneda;
import comunes.PlantillaPersona;
import comunes.PlantillaReponedor;

public class MainMonitores {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Indicamos los clientes y generamos unos nombres para estos
				int cantPersonas = 10;
		        List<String> nombres = List.of("Pablo","Francisco","Nicolas","Pablito","Fran","Nico","Paul","Francis","Nicole","Pablete");
		        
		        // Creamos las monedas que utilizaran los clientes desde su cartera y
		        // que recogerá la máquina en su cartera
				Moneda dCm = new Moneda("0.10€", 0.10);
				Moneda vCm = new Moneda("0.20€", 0.20);
				Moneda cCm = new Moneda("0.50€", 0.50);
				Moneda uEm = new Moneda("1.00€", 1.00);
				Moneda dEm = new Moneda("2.00€", 2.00);
				
				// Zona crítica
				MaquinaMonitor maquina = new MaquinaMonitor(new Cartera(dCm,vCm,cCm,uEm,dEm,200,100,40,20,10));
				Random random = new Random();
				
				// Creamos los hilos y los lanzamos
				for (int i = 0; i < cantPersonas; i++) {
					String nombreAleatorio = nombres.get(random.nextInt(nombres.size()));
					
					new PlantillaPersona(new Cartera(dCm,vCm,cCm,uEm,dEm,random.nextInt(10),
							random.nextInt(5),random.nextInt(4),random.nextInt(2),random.nextInt(3)),
							nombreAleatorio,maquina,random.nextInt(16)+1).start();
				}
				
				new PlantillaReponedor(maquina).start();	
	}

}
