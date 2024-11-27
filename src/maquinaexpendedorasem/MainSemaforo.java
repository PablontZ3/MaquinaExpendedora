package maquinaexpendedorasem;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

import comunes.Cartera;
import comunes.Maquina;
import comunes.Moneda;
import comunes.PlantillaPersona;
import comunes.PlantillaReponedor;

public class MainSemaforo {

	public static void main(String[] args) {
		int cantPersonas = 10;
		// Lista de nombres
        List<String> nombres = List.of("Pablo","Francisco","Nicolas","Pablito","Fran","Nico","Paul","Francis","Nicole","Pablete");
        	
		Moneda dCm = new Moneda("0.10€", 0.10);
		Moneda vCm = new Moneda("0.20€", 0.20);
		Moneda cCm = new Moneda("0.50€", 0.50);
		Moneda uEm = new Moneda("1.00€", 1.00);
		Moneda dEm = new Moneda("2.00€", 2.00);
		
		Semaphore mutex = new Semaphore(1,true);
		Semaphore sPersona = new Semaphore(1,true);
		
		
		Maquina maquinaExp = new Maquina(new Cartera(dCm,vCm,cCm,uEm,dEm,200,100,40,20,10));
		Random random = new Random();
		
		for (int i = 0; i < cantPersonas; i++) {
			String nombreAleatorio = nombres.get(random.nextInt(nombres.size()));
			
			new PersonaSemaforo(new Cartera(dCm,vCm,cCm,uEm,dEm,random.nextInt(10),random.nextInt(5),random.nextInt(4),random.nextInt(2),random.nextInt(1)),nombreAleatorio,maquinaExp,random.nextInt(16)+1,mutex,sPersona).start();
		}
		
		new ReponedorSemaforo(maquinaExp,mutex,sPersona).start();
	}
}
