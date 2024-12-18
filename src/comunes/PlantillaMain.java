package comunes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlantillaMain {

	public static void main(String[] args) {
        int cantPersonas = 10;
        // Lista de nombres
        List<String> nombres = List.of(
        	    "Pablo", "Francisco", "Nicolas", "Pablito", "Fran", "Nico", 
        	    "Paul", "Francis", "Nicole", "Pablete", 
        	    "Paolo", "Pablín", "Pablucho", "Paula", "Paulina", "Pau", 
        	    "Pancho", "Cisco", "Paco", "Pablito", "Francisca", "Nicolás", 
        	    "Nikki", "Nick", "Nicky", "Nikolai", "Nicoletta", "Franck", 
        	    "Franco", "Franchesca", "Franny", "Nicolina", "Nilo", 
        	    "Paulo", "Nicol", "Niklaus", "Pablote"
        	);

        Moneda dCm = new Moneda("0.10€", 0.10);
        Moneda vCm = new Moneda("0.20€", 0.20);
        Moneda cCm = new Moneda("0.50€", 0.50);
        Moneda uEm = new Moneda("1.00€", 1.00);
        Moneda dEm = new Moneda("2.00€", 2.00);

        Maquina maquinaExp = new Maquina(new Cartera(dCm, vCm, cCm, uEm, dEm, 200, 100, 40, 20, 10));
        Random random = new Random();

        // Lista para guardar los hilos de las personas
        List<Thread> hilosPersonas = new ArrayList<>();

        // Creamos los hilos
        for (int i = 0; i < cantPersonas; i++) {
            String nombreAleatorio = nombres.get(random.nextInt(nombres.size()));

            Thread personaHilo = new PlantillaPersona(
                    new Cartera(dCm, vCm, cCm, uEm, dEm, random.nextInt(10), random.nextInt(5), random.nextInt(4), random.nextInt(2), random.nextInt(1)),
                    nombreAleatorio,
                    maquinaExp,
                    random.nextInt(16) + 1
            );

            hilosPersonas.add(personaHilo);
            personaHilo.start();
        }

        Thread reponedorHilo = new PlantillaReponedor(maquinaExp);
        reponedorHilo.start();

        // Usamos join para asegurar la correcta ejecucion de tods los hilos
        for (Thread hilo : hilosPersonas) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            reponedorHilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
