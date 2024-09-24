package avion;

import pista.Pista;
import copControl.Mapa;
import copControl.Posicion;

public class AvionPesado extends Avion{

	public AvionPesado(Posicion posIni, Posicion posFin,Mapa mapaDeMovimiento) {
		super(posIni, posFin,mapaDeMovimiento);
		esControlable=true;
		this.radio=15;
	}

	
	public void moverHacia(Posicion unaPosicion) {
		this.trayectoria.setDestino(unaPosicion);
		
	}


	@Override
	public boolean puedeAterrizar(Pista pista) {
		return pista.puedeAterrizar(this);
	}

	

}
