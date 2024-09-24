package avion;

import pista.Pista;
import copControl.Mapa;
import copControl.Posicion;

public class AvionSimple extends Avion{
	


	public AvionSimple(Posicion posIni, Posicion posFin,Mapa mapaDeMovimiento) {
		super(posIni, posFin,mapaDeMovimiento);
		esControlable=true;
		this.radio=10;
		}

	public void moverHacia(Posicion unaPosicion){
		this.trayectoria.setDestino(unaPosicion);
		
	}
	
	@Override
	public boolean puedeAterrizar(Pista pista) {
		return pista.puedeAterrizar(this);
	}




	
	

}
