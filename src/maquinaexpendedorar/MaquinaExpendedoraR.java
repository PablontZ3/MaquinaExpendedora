package maquinaexpendedorar;

import java.util.List;
import java.util.Random;

import comunes.Cartera;
import comunes.Maquina;
import comunes.Moneda;

public class MaquinaExpendedoraR {
	public static void main(String[] args) {
			//Logica para la automatizacion de la instaciacion de personas
			int cantPersonas = 10;
		  	List<String> nombres = List.of("Pablo","Francisco","Nicolas","Pablito","Fran","Nico","Paul","Francis","Nicole","Pablete");
		  	//Creacion de los distintos tipos de moneda
			Moneda dCm = new Moneda("0.10€", 0.10);
			Moneda vCm = new Moneda("0.20€", 0.20);
			Moneda cCm = new Moneda("0.50€", 0.50);
			Moneda uEm = new Moneda("1.00€", 1.00);
			Moneda dEm = new Moneda("2.00€", 2.00);
			
			Maquina maquinaExp = new Maquina(new Cartera(dCm,vCm,cCm,uEm,dEm,200,100,40,20,10));
			Random random = new Random();
			
			for (int i = 0; i < cantPersonas; i++) {
				String nombreAleatorio = nombres.get(random.nextInt(nombres.size()));
				new PlantillaPersonaRunnable(new Cartera(dCm,vCm,cCm,uEm,dEm,random.nextInt(10),random.nextInt(5),random.nextInt(4),random.nextInt(2),random.nextInt(1)),nombreAleatorio,maquinaExp,random.nextInt(16)+1).run();
			}
			
			new PlantillaReponedorRunnable(maquinaExp).run();
	}
}
