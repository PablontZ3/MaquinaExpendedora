package comunes;

import java.util.HashMap;

public class Cartera {
	private HashMap<Moneda,Integer> monedas = new HashMap<Moneda, Integer>();
	
	/**
	 * Crear cartera
	 * @param dCm Tipo moneda
	 * @param vCm Tipo moneda
	 * @param cCm Tipo moneda
	 * @param uEm Tipo moneda
	 * @param dEm Tipo moneda
	 * @param dC Cantidad moneda
	 * @param vC Cantidad moneda
	 * @param cC Cantidad moneda
	 * @param uE Cantidad moneda
	 * @param dE Cantidad moneda
	 */
	public Cartera(Moneda dCm,Moneda vCm,Moneda cCm,Moneda uEm,Moneda dEm,int dC,int vC,int cC,int uE,int dE) {
		monedas.put(dCm, dC);
		monedas.put(vCm, vC);
		monedas.put(cCm, cC);
		monedas.put(uEm, uE);
		monedas.put(dEm, dE);
	}
	
	/**
	 * ver tipo moneda segun valor
	 * @param valorM valor de la moneda
	 * @return tipo de moneda
	 */
	public Moneda getTipoMoneda(double valorM) {
		Moneda monedaT = null;
		for (Moneda moneda : monedas.keySet()) {
			if (moneda.getValor()==valorM) {
				return moneda;
			}
		}
		return monedaT;
	}
	
	/**
	 * Ver cantidad total de monedas de un tipo
	 * @param moneda monedas a buscar
	 * @return cantidad de esa moneda
	 */
	public int getCantMoneda(Moneda moneda) {
		return monedas.get(moneda);
	}
	
	/**
	 * Dinero total en la cartera
	 * @return dinero total en la cartera
	 */
	public double getTotalDinero() {
		double dinero = 0;
		for (Moneda moneda : monedas.keySet()) {
			dinero += monedas.get(moneda)*moneda.getValor();
		}
		return dinero;
	}
	
	/**
	 * Añadimos saldo a la cartera
	 * @param monedasAñadir lista de las monedas qeu hay que añadir
	 */
	public void añadirSaldo(HashMap<Moneda,Integer> monedasAñadir) {
		for (Moneda moneda : monedasAñadir.keySet()) {
			int cantActual = monedas.get(moneda)+monedasAñadir.get(moneda);
			monedas.replace(moneda, cantActual);
		}
	}
	
	/**
	 * Sacamos saldo de la cartera
	 * @param monedasRetirar lista de las monedas que hay que quitar
	 */
	public void retirarSaldo(HashMap<Moneda,Integer> monedasRetirar) {
		for (Moneda moneda : monedasRetirar.keySet()) {
			int cantActual = monedas.get(moneda)-monedasRetirar.get(moneda);
			monedas.replace(moneda, cantActual);
		}
	}
	
	/**
	 * Este metodo es unicamente para pruebas
	 */
	public void mostrarCartera() {
		for (Moneda moneda : monedas.keySet()) {
			System.out.println(moneda.getValor()+" Cantidad: "+monedas.get(moneda));
		}
	}
}