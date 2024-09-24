package pista;

import copControl.Posicion;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

import avion.Avion;
import avion.AvionComputarizado;
import avion.AvionPesado;
import avion.AvionSimple;
import avion.Helicoptero;


public abstract class Pista implements ObjetoPosicionable{
	protected Posicion 	posicionEntrada;
	protected int radioAterrizaje;
	
	public Pista(Posicion posicionEntrada) throws PosicionesEntradaVaciaException {
		
		if (posicionEntrada == null){
			throw new PosicionesEntradaVaciaException();
		}
		this.posicionEntrada=posicionEntrada;
	}
	
	/**
	 * @param avionSimple
	 * @return
	 */
	public abstract boolean puedeAterrizar(AvionSimple avionSimple);
	/**
	 * @param avionPesado
	 * @return
	 */
	public abstract boolean puedeAterrizar(AvionPesado avionPesado);
	/**
	 * @param avionComputarizado
	 * @return
	 */
	public abstract boolean puedeAterrizar(AvionComputarizado avionComputarizado);
	/**
	 * @param helicoptero
	 * @return
	 */
	public abstract boolean puedeAterrizar(Helicoptero helicoptero);
	
	
	public Posicion getPosicionEntrada() {
		// TODO Auto-generated method stub
		return this.posicionEntrada;
	}

	
	public boolean estaEnZonaAterrizaje(Avion avion) {
		
		Posicion posicion1= this.getPosicionEntrada();
		int radio1 = this.radioAterrizaje;
		Posicion posicion2 = avion.getPosicionActual();
		int radio2 = avion.getRadio();	
		
		return intersects(posicion1.getCoordenadaX(), posicion1.getCoordenadaY(), radio1,
				posicion2.getCoordenadaX(), posicion2.getCoordenadaY(), radio2);
	}

	
	
    // does circle (x1, y1, r1) intersect circle (x2, y2, r2)?
    protected boolean intersects(double x1, double y1, double r1,
                                     double x2, double y2, double r2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        double distance = Math.sqrt(dx*dx + dy*dy);
        return distance <= r1 + r2;
        
    }

}
