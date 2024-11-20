package comunes;

import java.util.ArrayList;
import java.util.HashMap;

public class Maquina {
	private HashMap<Producto, Integer> productosMaquina = new HashMap<Producto, Integer>();
	private double dRecaudado;
	
	public Maquina() {
		dRecaudado = 0;
		productosMaquina.put(new Producto("Coca-Cola", 2.5), 15);
		productosMaquina.put(new Producto("Pepsi", 2.3), 10);
		productosMaquina.put(new Producto("Fanta", 2.0), 8);
		productosMaquina.put(new Producto("Sprite", 2.2), 12);
		productosMaquina.put(new Producto("Agua Mineral", 1.5), 20);
		productosMaquina.put(new Producto("Zumo naranja", 2.7), 6);
		productosMaquina.put(new Producto("Café Frío", 3.0), 5);
		productosMaquina.put(new Producto("Té Helado", 2.8), 9);
		productosMaquina.put(new Producto("Galletas", 1.2), 18);
		productosMaquina.put(new Producto("Lays", 1.8), 15);
		productosMaquina.put(new Producto("Chocolate", 2.5), 14);
		productosMaquina.put(new Producto("Barrita de chocolate", 1.7), 10);
		productosMaquina.put(new Producto("Gominolas", 1.5), 12);
		productosMaquina.put(new Producto("Palitos de Queso", 1.9), 7);
		productosMaquina.put(new Producto("Sandwich", 3.5), 4);
		productosMaquina.put(new Producto("Monster", 3.2), 6);
	}
	public void 
}
