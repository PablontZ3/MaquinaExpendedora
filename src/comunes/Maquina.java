package comunes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Maquina {
	// Atributos
    private HashMap<Integer, Producto> productosMaquina = new HashMap<>(); // Productos disponibles en la máquina.
    private HashMap<Integer, Integer> cantProductos = new HashMap<>(); // Cantidad de cada producto en stock.
    private BigDecimal dRecaudado = BigDecimal.ZERO; // Dinero recaudado
    private BigDecimal dIntroducido = BigDecimal.ZERO; // Dinero introducido por el cliente
    private BigDecimal dVuelta = BigDecimal.ZERO; // Dinero a devolver
    private ArrayList<Integer> prodRep = new ArrayList<>(); // Lista de productos a reponer.
    private Cartera monedasMaquina; // Cartera de la máquina que almacena las monedas disponibles.
    private HashMap<Moneda, Integer> monedasVueltaOIntroducidas = new HashMap<>(); // Monedas usadas para cambio o introducidas.

    /**
     * Constructor de la clase Maquina.
     * Inicializa los productos disponibles y sus cantidades, y asigna una cartera inicial.
     * @param cartera Cartera inicial de la máquina.
     */
    public Maquina(Cartera cartera) {
    	// Como double y float generaban dinero a la hora del redondeo dedinero uso este formato
    	dRecaudado = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
        dIntroducido = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
        dVuelta = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);

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
	public void añadirDinero(Cartera carteraPersona,String nombre,int pSeleccionado) {
		Producto productoActual = productosMaquina.get(pSeleccionado);
		double precioProducto = productoActual.getPrecio();
		
		dIntroducido = BigDecimal.valueOf(0);
		
		monedasVueltaOIntroducidas.clear();
		
		insertarDinero(0.1,precioProducto,carteraPersona);
		insertarDinero(0.2,precioProducto,carteraPersona);
		insertarDinero(0.5,precioProducto,carteraPersona);
		insertarDinero(1,precioProducto,carteraPersona);
		insertarDinero(2,precioProducto,carteraPersona);
		
		monedasMaquina.añadirSaldo(monedasVueltaOIntroducidas);
		carteraPersona.retirarSaldo(monedasVueltaOIntroducidas);
		
		System.out.println(nombre+" has introducido "+dIntroducido);
	}
	
	/**
	 * Insertamos monedas en la maquina
	 * @param vMoneda moneda que insertamos
	 */
	private void insertarDinero(double vMoneda,double precioPro,Cartera cartera) {
		int cMoneda= 0;
		while (dIntroducido.doubleValue()<precioPro && cartera.getCantMoneda(cartera.getTipoMoneda(vMoneda))>cMoneda) {
			cMoneda++;
			System.out.println("Has introducido "+vMoneda+"€");
			dIntroducido = dIntroducido.add(BigDecimal.valueOf(vMoneda));
		}
		monedasVueltaOIntroducidas.put(cartera.getTipoMoneda(vMoneda), cMoneda);
	}
	
	/**
     * Devuelve el cambio al cliente.
     * @param carteraPersona Cartera del cliente donde se devolverá el cambio.
     */
	public void cogerVuelta(Cartera carteraPersona,String nombre) {
		monedasVueltaOIntroducidas.clear();
		
		System.out.println(nombre+" has recogido "+dVuelta);
		
		generearVuelta(2);
		generearVuelta(1);
		generearVuelta(0.5);
		generearVuelta(0.2);
		generearVuelta(0.1);
		
		monedasMaquina.retirarSaldo(monedasVueltaOIntroducidas);
		carteraPersona.añadirSaldo(monedasVueltaOIntroducidas);
	}
	
	/**
	 * Generamos la vuelta de cada moneda
	 * @param vMoneda moneda con la qeu contaremos
	 */
	private void generearVuelta(double vMoneda) {
		int cMoneda= 0;
		while (dVuelta.doubleValue()>=vMoneda && monedasMaquina.getCantMoneda(monedasMaquina.getTipoMoneda(vMoneda))>cMoneda) {
			cMoneda++;
			dVuelta = dVuelta.subtract(BigDecimal.valueOf(vMoneda));
		}
		monedasVueltaOIntroducidas.put(monedasMaquina.getTipoMoneda(vMoneda), cMoneda);
	}
	
	/**
     * Procesa la compra de un producto seleccionado.
     * @param pSelecionado ID del producto seleccionado.
     */
	public void cogerProducto(int pSeleccionado) {
		Producto productoActual = productosMaquina.get(pSeleccionado);
		if (productoActual != null) {
			if (cantProductos.get(pSeleccionado)>0) {
				if (productoActual.getPrecio()<=dIntroducido.doubleValue()) {
					dVuelta = dIntroducido.subtract(BigDecimal.valueOf(productoActual.getPrecio()));
					System.out.println("Has comprado un/a "+productoActual.getNombre()+" CUESTA: "+productoActual.getPrecio()+" CAMBIO: "+dVuelta);
				}else {
					dVuelta = BigDecimal.valueOf(dIntroducido.doubleValue());
					System.out.println("No has introducido dinero suficiente "+productoActual.getNombre()+ " CUESTA: "+productoActual.getPrecio()+" INTRODUCIDO: "+dIntroducido);
				}
			}else {
				dVuelta = BigDecimal.valueOf(dIntroducido.doubleValue());
				System.out.println("No quedan "+productoActual.getNombre()+" en la maquina expendedora. AVISANDO REPONEDOR");
				//Miramos si el reponedor ya esta informado de este producto
				if (!prodRep.contains(pSeleccionado)) {
					prodRep.add(pSeleccionado);
				}
			}
		}
	}
	
	/**
     * Repone los productos agotados en la máquina.
     */
	public void repProducto(String nombre) {
		for (Integer numP : prodRep) {
			cantProductos.replace(numP, 10);
			System.out.println(nombre+" ha repuesto "+productosMaquina.get(numP).getNombre());
		}
		prodRep.clear();
	}
	
	/**
     * Obtiene el dinero recaudado por la máquina.
     * @return Dinero recaudado.
     */
	public void getDRecaudado(String nombre) {
		dRecaudado = BigDecimal.valueOf(monedasMaquina.getTotalDinero()-100).setScale(2, RoundingMode.HALF_EVEN); 
		System.out.println(nombre+" ha recaudado "+dRecaudado);
	}
}