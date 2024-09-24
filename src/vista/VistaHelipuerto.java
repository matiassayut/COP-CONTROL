package vista;

import java.awt.Color;

import fiuba.algo3.titiritero.dibujables.Cuadrado;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public class VistaHelipuerto extends Cuadrado{
	
	public VistaHelipuerto(ObjetoPosicionable objetoPosicionable) {
		super(30, 30, objetoPosicionable);
		this.setColor(Color.GRAY);
	}


}
