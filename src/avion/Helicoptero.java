package avion;


import pista.Pista;

import copControl.Mapa;
import copControl.Posicion;

public class Helicoptero extends Avion{

	
	private boolean estaDetenido;
	/**
	 * @return the estaDetenido
	 */
	public boolean estaDetenido() {
		return estaDetenido;
	}



	public Helicoptero(Posicion posIni, Posicion posFin,Mapa mapaDeMovimiento) {
		super(posIni, posFin,mapaDeMovimiento);
		esControlable=true;
		this.esDetenible=true;
		this.radio=7;
		estaDetenido=false;
	}

	public void moverHacia(Posicion unaPosicion) {
		this.trayectoria.setDestino(unaPosicion);
		
	}

	@Override
	public boolean puedeAterrizar(Pista pista) {
		return pista.puedeAterrizar(this);
	}
	
	@Override
	public void detener() {
		// TODO Auto-generated method stub
		this.trayectoria.borrarDestinos();
		this.moverHacia(this.trayectoria.getPosicionActual());
		this.estaDetenido=true;
		
	}
	
	
	@Override
	public void avanzar(){
		if (!this.estaDetenido){
			this.trayectoria.avanzar();
		}
		
	}

	@Override
	public void arrancar() {
		this.estaDetenido=false;
	}

}
