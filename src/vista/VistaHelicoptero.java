package vista;

import java.awt.Color;

import avion.Helicoptero;

import fiuba.algo3.titiritero.dibujables.Circulo;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public class VistaHelicoptero extends Circulo{
	
	public VistaHelicoptero(ObjetoPosicionable modelo) {
		super(15,  modelo);
		this.setColor(Color.BLUE);
	}

	@Override
	public Color getColor() {
		System.out.println("GetColor");	
		Helicoptero objeto = (Helicoptero)this.getPosicionable();
		
		if(objeto.estaMarcado()){
			return Color.CYAN;
		}else{
			return Color.BLUE;
		}
	}

}
