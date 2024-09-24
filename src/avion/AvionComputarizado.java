package avion;


import pista.Pista;
import copControl.Mapa;
import copControl.Posicion;

public class AvionComputarizado extends Avion {

	public AvionComputarizado(Posicion posIni, Mapa mapaDeMovimiento) {
		super(posIni, mapaDeMovimiento);
		esControlable=false;
		this.radio=10;
		
	}

	@Override
	public boolean puedeAterrizar(Pista pista) {
		return pista.puedeAterrizar(this);
	}

	public void moverHacia(Posicion unaPosicion){
		//no deberia dejar llamarse a este metodo en esta clase
		this.trayectoria.setDestino(unaPosicion);
	}

}
