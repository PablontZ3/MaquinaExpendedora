package comunes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Maquina {
	// Atributos
    private HashMap<Integer, Producto> productosMaquina = new HashMap<>(); // Productos disponibles en la máquina.
    private HashMap<Integer, Integer> cantProductos = new HashMap<>(); // Cantidad de cada producto en stock.
    private double dRecaudado; // Dinero total recaudado por la máquina.
    private double dIntroducido; // Dinero introducido por el cliente.
    private double dVuelta; // Dinero a devolver al cliente.
    private ArrayList<Integer> prodRep = new ArrayList<>(); // Lista de productos a reponer.
    private Cartera monedasMaquina; // Cartera de la máquina que almacena las monedas disponibles.
    private HashMap<Moneda, Integer> monedasVueltaOIntroducidas = new HashMap<>(); // Monedas usadas para cambio o introducidas.

    /**
     * Constructor de la clase Maquina.
     * Inicializa los productos disponibles y sus cantidades, y asigna una cartera inicial.
     * @param cartera Cartera inicial de la máquina.
     */
    public Maquina(Cartera cartera) {
        dRecaudado = 0;

        // Inicialización de los productos en la máquina.
        productosMaquina.put(1, new Producto("Coca-Cola", 2.5));
        productosMaquina.put(2, new Producto("Pepsi", 2.3));
        productosMaquina.put(3, new Producto("Fanta", 2.0));
        productosMaquina.put(4, new Producto("Sprite", 2.2));
        productosMaquina.put(5, new Producto("Agua Mineral", 1.5));
        productosMaquina.put(6, new Producto("Zumo naranja", 2.7));
        productosMaquina.put(7, new Producto("Café Frío", 3.0));
        productosMaquina.put(8, new Producto("Té Helado", 2.8));
        productosMaquina.put(9, new Producto("Galletas", 1.2));
        productosMaquina.put(10, new Producto("Lays", 1.8));
        productosMaquina.put(11, new Producto("Chocolate", 2.5));
        productosMaquina.put(12, new Producto("Barrita de chocolate", 1.7));
        productosMaquina.put(13, new Producto("Gominolas", 1.5));
        productosMaquina.put(14, new Producto("Palitos de Queso", 1.9));
        productosMaquina.put(15, new Producto("Sandwich", 3.5));
        productosMaquina.put(16, new Producto("Monster", 3.2));

        // Inicialización aleatoria de las cantidades de cada producto.
        for (int i = 1; i <= 16; i++) {
            cantProductos.put(i, (int) (Math.random() * 16));
        }

        monedasMaquina = cartera;
    }
	
    /**
     * Añade dinero a la máquina desde la cartera de un cliente.
     * @param carteraPersona Cartera del cliente que introduce dinero.
     */
	public void añadirDinero(Cartera carteraPersona) {
		int dC = 0;
		int vC = 0;
		int cC = 0;
		int uE = 0;
		int dE = 0;
		
		monedasVueltaOIntroducidas.clear();
		
		while (dIntroducido<4 && carteraPersona.getCantMoneda(carteraPersona.getTipoMoneda(2))>dE) {
			dE++;
			dIntroducido+=2;
		}
		monedasVueltaOIntroducidas.put(carteraPersona.getTipoMoneda(2), dE);
		while (dIntroducido<4 && carteraPersona.getCantMoneda(carteraPersona.getTipoMoneda(1))>uE) {
			uE++;
			dIntroducido+=1;
		}
		monedasVueltaOIntroducidas.put(carteraPersona.getTipoMoneda(1), uE);
		while (dIntroducido<4 && carteraPersona.getCantMoneda(carteraPersona.getTipoMoneda(0.5))>cC) {
			cC++;
			dIntroducido+=0.5;
		}
		monedasVueltaOIntroducidas.put(carteraPersona.getTipoMoneda(0.5), cC);
		while (dIntroducido<4 && carteraPersona.getCantMoneda(carteraPersona.getTipoMoneda(0.2))>vC) {
			vC++;
			dIntroducido+=0.2;
		}
		monedasVueltaOIntroducidas.put(carteraPersona.getTipoMoneda(0.2), vC);
		while (dIntroducido<4 && carteraPersona.getCantMoneda(carteraPersona.getTipoMoneda(0.1))>dC) {
			dC++;
			dIntroducido+=0.1;
		}
		monedasVueltaOIntroducidas.put(carteraPersona.getTipoMoneda(0.1), dC);
		
		monedasMaquina.añadirSaldo(monedasVueltaOIntroducidas);
		carteraPersona.retirarSaldo(monedasVueltaOIntroducidas);
	}
	
	/**
     * Devuelve el cambio al cliente.
     * @param carteraPersona Cartera del cliente donde se devolverá el cambio.
     */
	public void cogerVuelta(Cartera carteraPersona) {
		int dC = 0;
		int vC = 0;
		int cC = 0;
		int uE = 0;
		int dE = 0;
		
		monedasVueltaOIntroducidas.clear();
		
		while (dVuelta>2 && monedasMaquina.getCantMoneda(monedasMaquina.getTipoMoneda(2))>dE) {
			dE++;
			dVuelta-=2;
		}
		monedasVueltaOIntroducidas.put(monedasMaquina.getTipoMoneda(2), dE);
		while (dVuelta>1 && monedasMaquina.getCantMoneda(monedasMaquina.getTipoMoneda(1))>uE) {
			uE++;
			dVuelta-=1;
		}
		monedasVueltaOIntroducidas.put(monedasMaquina.getTipoMoneda(1), uE);
		while (dVuelta>0.5 && monedasMaquina.getCantMoneda(monedasMaquina.getTipoMoneda(0.5))>cC) {
			cC++;
			dVuelta-=0.5;
		}
		monedasVueltaOIntroducidas.put(monedasMaquina.getTipoMoneda(0.5), cC);
		while (dVuelta>0.2 && monedasMaquina.getCantMoneda(monedasMaquina.getTipoMoneda(0.2))>vC) {
			vC++;
			dVuelta-=0.2;
		}
		monedasVueltaOIntroducidas.put(monedasMaquina.getTipoMoneda(0.2), vC);
		while (dVuelta>0.1 && monedasMaquina.getCantMoneda(monedasMaquina.getTipoMoneda(0.1))>dC) {
			dC++;
			dVuelta-=0.1;
		}
		monedasVueltaOIntroducidas.put(monedasMaquina.getTipoMoneda(0.1), dC);
		
		monedasMaquina.retirarSaldo(monedasVueltaOIntroducidas);
		carteraPersona.añadirSaldo(monedasVueltaOIntroducidas);
	}
	
	/**
     * Procesa la compra de un producto seleccionado.
     * @param pSelecionado ID del producto seleccionado.
     */
	public void cogerProducto(int pSelecionado) {
		Producto productoActual = productosMaquina.get(pSelecionado);
		if (productoActual != null) {
			if (cantProductos.get(pSelecionado)<0) {
				if (productoActual.getPrecio()<dIntroducido) {
					dVuelta = dIntroducido-productoActual.getPrecio();
					System.out.println("Has comprado un/a "+productoActual.getNombre()+" CAMBIO: "+dVuelta);
				}else {
					System.out.println("No has introducido dinero suficiente "+productoActual.getNombre()+ " CUESTA: "+productoActual.getPrecio());
				}
			}else {
				System.out.println("No quedan "+productoActual.getNombre()+" en la maquina expendedora. AVISANDO REPONEDOR");
				prodRep.add(pSelecionado);
			}
		}
	}
	
	/**
     * Repone los productos agotados en la máquina.
     */
	public void repProducto() {
		for (Integer numP : prodRep) {
			cantProductos.replace(numP, 10);
		}
	}
	
	/**
     * Obtiene el dinero recaudado por la máquina.
     * @return Dinero recaudado.
     */
	public double getDRecaudado() {
		dRecaudado = monedasMaquina.getTotalDinero()-100;
		return dRecaudado;
	}
}
