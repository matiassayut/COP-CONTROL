package copControl;

import avion.Avion;

/**
 * @author agu
 *
 */
public class Jugador {
	private String nombre;
	private Avion avionMarcado;
	private Nivel nivelActual;
	
	public Jugador(String nombre) {
		this.nombre=nombre;
		this.avionMarcado=null;
	}

	public String getNombre() {
		return this.nombre;
	}

	/**
	 * @param nivelActual
	 */
	public void setNivelActual(Nivel nivelActual){
		this.nivelActual= nivelActual;
		
	}



	public void moverHacia(int x, int y) {
		Posicion unaPosicion= new Posicion(x, y);
		System.out.println("Click: "+unaPosicion.toString());
		
		
		Avion posibleAvion=this.nivelActual.getAvionEnPosicion(unaPosicion);
		
		
		if(this.avionMarcado == null){		
			if (posibleAvion!=null){				
				if (posibleAvion.esControlable()){ //con esto aseguro que nunca avionMarcado sea un avionComputarizado (noControlable)
					this.avionMarcado=posibleAvion;
				
				}								
			}
			
		}else{
		
			if (posibleAvion==this.avionMarcado){
				
				if (posibleAvion.esDetenible()){
					posibleAvion.detener();
				}
				this.avionMarcado.desmarcar();
				this.avionMarcado=null;
			}else{
				//Se examina si el posible avion es nulo u otro avion
				if (posibleAvion==null){
					if(avionMarcado.esDetenible()){
						this.avionMarcado.arrancar();
					}
					this.avionMarcado.iniciarNuevaTrayectoria(unaPosicion);
					this.avionMarcado.moverHacia(unaPosicion);
				}
				else{
				//Si es otro avion se desmarca el actual y se marca el nuevo	
					this.avionMarcado.desmarcar();
					this.avionMarcado=posibleAvion;
				}
			}
		}		
	}
		
}
