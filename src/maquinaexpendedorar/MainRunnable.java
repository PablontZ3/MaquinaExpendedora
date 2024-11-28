package maquinaexpendedorar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import comunes.Cartera;
import comunes.Maquina;
import comunes.Moneda;

public class MainRunnable {
	public static void main(String[] args) {
        // Lógica para la automatización de la instanciación de personas
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
        
        // Creación de los distintos tipos de moneda
        Moneda dCm = new Moneda("0.10€", 0.10);
        Moneda vCm = new Moneda("0.20€", 0.20);
        Moneda cCm = new Moneda("0.50€", 0.50);
        Moneda uEm = new Moneda("1.00€", 1.00);
        Moneda dEm = new Moneda("2.00€", 2.00);

        // Inicialización de la máquina expendedora
        Maquina maquinaExp = new Maquina(new Cartera(dCm, vCm, cCm, uEm, dEm, 200, 100, 40, 20, 10));
        Random random = new Random();

        // Lista para guardar los hilos de las personas
        List<Thread> hilosPersonas = new ArrayList<>();

        // Creación y lanzamiento de los hilos de las personas
        for (int i = 0; i < cantPersonas; i++) {
            String nombreAleatorio = nombres.get(random.nextInt(nombres.size()));
            PersonaRunnable p = new PersonaRunnable(
                new Cartera(dCm, vCm, cCm, uEm, dEm, random.nextInt(10), random.nextInt(5), random.nextInt(4), random.nextInt(2), random.nextInt(1)),
                nombreAleatorio,
                maquinaExp,
                random.nextInt(16) + 1
            );
            Thread hiloPersona = new Thread(p);
            hilosPersonas.add(hiloPersona);
            hiloPersona.start();
        }

        // Creación y lanzamiento del hilo del reponedor
        ReponedorRunnable r = new ReponedorRunnable(maquinaExp);
        Thread hiloReponedor = new Thread(r);
        hiloReponedor.start();

     // Usamos join para asegurar la correcta ejecucion de tods los hilos
        for (Thread hilo : hilosPersonas) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            hiloReponedor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}