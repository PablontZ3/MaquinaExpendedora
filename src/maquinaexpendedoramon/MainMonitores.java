package maquinaexpendedoramon;

import java.util.ArrayList;
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
				List<String> nombres = List.of(
					    "Pablo", "Francisco", "Nicolas", "Pablito", "Fran", "Nico", 
					    "Paul", "Francis", "Nicole", "Pablete", 
					    "Paolo", "Pablín", "Pablucho", "Paula", "Paulina", "Pau", 
					    "Pancho", "Cisco", "Paco", "Pablito", "Francisca", "Nicolás", 
					    "Nikki", "Nick", "Nicky", "Nikolai", "Nicoletta", "Franck", 
					    "Franco", "Franchesca", "Franny", "Nicolina", "Nilo", 
					    "Paulo", "Nicol", "Niklaus", "Pablote"
					);
		        
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
				
				// Lista para guardar los hilos de los clientes
			    List<Thread> hilosClientes = new ArrayList<>();

			    // Creamos los hilos para los clientes y los lanzamos
			    for (int i = 0; i < cantPersonas; i++) {
			        String nombreAleatorio = nombres.get(random.nextInt(nombres.size()));
			        Thread cliente = new PlantillaPersona(
			            new Cartera(dCm, vCm, cCm, uEm, dEm, random.nextInt(10), random.nextInt(5), random.nextInt(4), random.nextInt(2), random.nextInt(1)),
			            nombreAleatorio, 
			            maquina, 
			            random.nextInt(16) + 1
			        );
			        hilosClientes.add(cliente);
			        cliente.start();
			    }

			    // Creamos y lanzamos el hilo para el reponedor
			    Thread reponedor = new PlantillaReponedor(maquina);
			    reponedor.start();

			    // Usamos join para asegurar la correcta ejecucion de todos los hilos
			    for (Thread cliente : hilosClientes) {
			        try {
			            cliente.join();
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			    }

			    try {
			        reponedor.join();
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }
			}
}
