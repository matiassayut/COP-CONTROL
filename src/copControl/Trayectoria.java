package copControl;



import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



public class Trayectoria {
	
	private Posicion posicionActual;
	private List<Posicion> destinos;
	private Mapa mapaDeMovimiento; 
	private Posicion posicionAnterior;
	
	public Trayectoria(Posicion posIni, Mapa mapaDeMovimiento){
		this.posicionActual=posIni;
		destinos= new LinkedList<Posicion>();
		this.mapaDeMovimiento=mapaDeMovimiento;
		this.posicionAnterior=this.posicionActual;
	}
	
	public Trayectoria(Posicion posIni, Posicion posFin,Mapa mapaDeMovimiento) {
		posicionActual = posIni;
		//seteo la posicion fin como si fuesen click para calcular de la misma forma su trayectoria inicial al aparecer en mapa
		destinos = new LinkedList<Posicion>();
		this.setDestino(posFin);
		this.mapaDeMovimiento = mapaDeMovimiento;
		this.posicionAnterior=this.posicionActual;
		
	}

	
	/**
	 * @return
	 */

	


	public Posicion getPosicionActual() {
		return this.posicionActual;
	}

	//recorrer posicion por posicion calculando trayectoria entre destinos, debe de ser llamado por juego cada sierto tiempo (velocidad de nivel)
	public void avanzar(){   
		
	
			
			this.actualizarProximoDestino();
			if (!this.destinos.isEmpty()){
				this.posicionAnterior=this.posicionActual;
				this.posicionActual= this.posicionActual.getVecinoDeDistanciaMinima(this.getDestinoActual());
				
			}
			else{
				//Si choc@ contr@ un borde
				if (mapaDeMovimiento.esPosicionExtremo(this.posicionActual)){
					this.destinos.add(this.mapaDeMovimiento.generarPosicionExtremoAlAzar());
				}
				else{	
					//Si me quedo sin destinos 
					this.destinos.add(this.generarPosicionExtremoRecta());
				}
			}
	
	}

	//retorna posicion de un destino ( click ) a la cual debe dirigirse parcialmente el avion
	public Posicion getDestinoActual(){  
		
			Posicion destinoActual=null;
			if(!destinos.isEmpty()){
				destinoActual=destinos.get(0);  //el primer destino de la lista de destinos siempre debe de ser el parcial al cual quiero ir e irse borrando de la lista cuando llego a algun destino
			}
			
			return destinoActual;
	
		
	}
	
	public void setDestino(Posicion unaPos){   //usar cuando hay click, llamado por avion.moverHacia(unaPos)
		destinos.add(unaPos);
	}
	
	//para que cuando llega a un destino lo borre de la lista. Llamar siempre al principio de trayectoria.avanzar()
	private void actualizarProximoDestino(){
		
			boolean llegoAunDestino=false;
			Iterator<Posicion> itDestinos = destinos.iterator();
			while (itDestinos.hasNext() && !llegoAunDestino){
				llegoAunDestino= posicionActual.igualA(itDestinos.next());
				if (llegoAunDestino){
					itDestinos.remove();
				}
			}

		
	}
	
	
	public void detener(){
		
		this.setDestino(this.getPosicionActual());
		
	}

	public void borrarDestinos() {
		this.destinos.clear();
		
	}
	
	
	
	private Posicion generarPosicionExtremoRecta(){
		
		
		int deltaX = ((int)this.posicionActual.getCoordenadaX()-(int)this.posicionAnterior.getCoordenadaX());
		int deltaY = ((int)this.posicionActual.getCoordenadaY()-(int)this.posicionAnterior.getCoordenadaY());
		Posicion posicionNueva= this.posicionActual;
		
		
		while (!mapaDeMovimiento.esPosicionExtremo(posicionNueva)){
			//System.out.println("generaPosExtremo");
			if (deltaX>0){
				deltaX=deltaX+1;
			}else{
				if(deltaX<0){
					deltaX=deltaX-1;
				}
				
			}
			
			if (deltaY>0){
				deltaY=deltaY+1;
			}else{
				if(deltaY<0){
					deltaY=deltaY-1;
				}
				
			}
			posicionNueva = new Posicion(deltaX+(int)this.posicionActual.getCoordenadaX(),deltaY+(int)this.posicionActual.getCoordenadaY()); 
		
		}
		
		return posicionNueva;
		
	}
	
}
