package vista;

import java.awt.Color;

import fiuba.algo3.titiritero.dibujables.Cuadrado;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public class VistaPistaSimple extends Cuadrado{

	public VistaPistaSimple(ObjetoPosicionable objetoPosicionable) {
		super(30,20, objetoPosicionable);
		this.setColor(Color.YELLOW);
	}

}
