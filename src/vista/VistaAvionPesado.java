package vista;

 


import java.awt.Color;

import avion.AvionPesado;
import fiuba.algo3.titiritero.dibujables.Circulo;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public class VistaAvionPesado extends Circulo  {

	public VistaAvionPesado(ObjetoPosicionable modelo) {
		super(20,  modelo);
		this.setColor(Color.ORANGE);
	}
	
	@Override
	public Color getColor() {
		System.out.println("GetColor");	
		AvionPesado objeto = (AvionPesado)this.getPosicionable();
		
		if(objeto.estaMarcado()){
			return Color.CYAN;
		}else{
			return Color.ORANGE;
		}
	}


}