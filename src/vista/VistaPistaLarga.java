package vista;

import java.awt.Color;

import fiuba.algo3.titiritero.dibujables.Cuadrado;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;


public class VistaPistaLarga extends Cuadrado{

	public VistaPistaLarga(ObjetoPosicionable objetoPosicionable) {
		super(40,20, objetoPosicionable);
		this.setColor(Color.MAGENTA);
	}

}
