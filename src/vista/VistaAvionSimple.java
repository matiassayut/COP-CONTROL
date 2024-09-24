package vista;

import java.awt.Color;

import avion.AvionSimple;



import fiuba.algo3.titiritero.dibujables.Circulo;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public class VistaAvionSimple extends Circulo  {

	public VistaAvionSimple(ObjetoPosicionable modelo) {
		super(20,  modelo);
		this.setColor(Color.RED);
	}
	
	@Override
	public Color getColor() {
		System.out.println("GetColor");	
		AvionSimple objeto = (AvionSimple)this.getPosicionable();
		
		if(objeto.estaMarcado()){
			return Color.CYAN;
		}else{
			return Color.RED;
		}
	}


}
