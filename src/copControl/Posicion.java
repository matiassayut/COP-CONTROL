package copControl;

import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Posicion {
	private Point coordenadas;
	
	public Posicion(int i, int j) {	
		coordenadas = new Point(i, j);
				
	}
	
	
	public double getCoordenadaX(){
		return this.coordenadas.getX();
	}
	
	public double getCoordenadaY(){
		return this.coordenadas.getY();
	}
	
	public boolean igualA (Posicion otraPosicion){
		return ((this.getCoordenadaX()== otraPosicion.getCoordenadaX()) && (this.getCoordenadaY()== otraPosicion.getCoordenadaY()));
		
	}
	
	public double distanciaHasta(Posicion otraPosicion){
		
		return this.coordenadas.distance(otraPosicion.getCoordenadaX(),otraPosicion.getCoordenadaY());
		
	}
	
	/**
	 * llamar dentro de getVecinoDeDistanciaMinima para mantener actualizada la lista de vecinos
	 */
	private List<Posicion> getVecinos(){
		
		List<Posicion>vecinos = new LinkedList<Posicion>();
		
		int posX=(int)this.getCoordenadaX();
		int posY=(int)this.getCoordenadaY();
		int incDerecha=(int) (this.getCoordenadaX()+1);
		int incIzquierda=(int) (this.getCoordenadaX()-1);
		int incArriba=(int) (this.getCoordenadaY()+1);
		int incAbajo=(int) (this.getCoordenadaY()-1);
		
		Posicion posDerecha = new Posicion(incDerecha, posY);
		Posicion posIzquierda = new Posicion(incIzquierda,posY);
		Posicion posArriba = new Posicion(posX,incArriba);
		Posicion posAbajo = new Posicion(posX,incAbajo);
		
		Posicion posDiagArribaIzquierda = new Posicion(incIzquierda,incArriba);
		Posicion posDiagArribaDerecha = new Posicion(incDerecha,incArriba);
		Posicion posDiagAbajoIzquierda = new Posicion(incIzquierda,incAbajo);
		Posicion posDiagAbajoDerecha = new Posicion(incDerecha,incAbajo);
		
		vecinos.add(posDerecha);
		vecinos.add(posAbajo);
		vecinos.add(posIzquierda);
		vecinos.add(posArriba);
		vecinos.add(posDiagArribaDerecha);
		vecinos.add(posDiagArribaIzquierda);
		vecinos.add(posDiagAbajoIzquierda);
		vecinos.add(posDiagAbajoDerecha);
		
		return vecinos;
	}
	
	//retorna una Posicion de un vecino con distancia minima a la Posicion de Destino Final
	public Posicion getVecinoDeDistanciaMinima(Posicion posicionFinal){
		
		Map<Double, Posicion> distancias = new HashMap<Double, Posicion>();
		double distancia=0;
		
		
		for (Posicion posicionVecina : this.getVecinos()) {
			distancia= posicionVecina.distanciaHasta(posicionFinal);
			distancias.put(distancia, posicionVecina);
		}
		//Ordeno por distancia minima
		TreeMap<Double, Posicion> treeMap = new TreeMap<Double, Posicion>(distancias);
		//Obtengo la minima distancia
		
		Posicion posicionMinima=treeMap.get(treeMap.firstKey())  ;
		
		return posicionMinima;
		
	}

	@Override
	public String toString(){
		Double x= this.getCoordenadaX();
		Double y = this.getCoordenadaY();
		String salida ="Las coordenadas son: "+x.toString()+","+y.toString();
		return salida;
		
	}

}
