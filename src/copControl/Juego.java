package copControl;


import java.util.Iterator;
import java.util.List;

import fiuba.algo3.titiritero.modelo.ObjetoVivo;
import Observador.Observable;
import avion.AvionSimple;
import avion.AvionPesado;
import avion.AvionComputarizado;
import avion.Helicoptero;
import avion.Avion;


public class Juego extends Observable implements ObjetoVivo {
	
	private Nivel nivelActual;  
	private Integer cantidadAvionesAterrizados;
	private Jugador jugador;
	private List<Nivel> niveles;
	private boolean jugandose;
	private int intervaloEntreNuevoAvion;
	private boolean estaGanado;
	

	public Juego(Jugador jugador,List<Nivel> niveles){
		this.jugador=jugador;
		this.niveles=niveles;
		this.nivelActual=niveles.get(0);
		this.cantidadAvionesAterrizados=0;
		this.jugandose=true;
		this.estaGanado=false;
		this.intervaloEntreNuevoAvion=0;
		this.jugador.setNivelActual(nivelActual);
	}
	/**
	 * @return the nivelActual
	 */
	public Nivel getNivelActual() {
		return nivelActual;
	}

	public boolean estaJugandose(){
		return this.jugandose;
	}
		
	

	/**
	 * @return the cantidadAvionesAterrizados
	 */
	public Integer getCantidadAvionesAterrizados() {
		return cantidadAvionesAterrizados;
	}

	/**
	 * @return the jugador
	 */
	public Jugador getJugador() {
		return jugador;
	}

	/**
	 * @return the niveles
	 */
	public List<Nivel> getNiveles() {
		return niveles;
	}

	//llamar en hilo de gameLoop por un timer con tiempo=seteado a convenir
	public void huboChoque(){
		
		if( this.nivelActual.huboChoque()){
			
			this.jugandose=false;
		}		
	}

	//llamar en hilo de gameLoop por un timer con tiempo=seteado a convenir
	public void chequearAterrizajes(){
		cantidadAvionesAterrizados=cantidadAvionesAterrizados+nivelActual.aterrizarAviones();
	}

	//llamar en hilo de gameLoop por un timer con tiempo=nivel->dificultad->velocidad
	public void actualizarMovimientoDeAviones(){
		this.nivelActual.avanzarAvionesEnAire();
	}
	
	private Avion crearAvionAlAzar(){
		Integer M= 1; 
		Integer N= 4;
		// Valor random entre M y N, ambos incluidos.
		Integer enteroAlAzarEntreMyN = (int) Math.floor(Math.random()*(N-M+1)+M);  
		List<Posicion> posicionesExtremo = nivelActual.getPosicionesExtremos();
		Avion unAvion=null;
		switch (enteroAlAzarEntreMyN) {
			//caso AvionSimple
			case 1:
				AvionSimple unAvionSimple = new AvionSimple(posicionesExtremo.get(0),posicionesExtremo.get(1),this.nivelActual.getMapa());
				return unAvionSimple;
			//caso AvionPesado
			case 2:
				AvionPesado unAvionPesado = new AvionPesado(posicionesExtremo.get(0),posicionesExtremo.get(1),this.nivelActual.getMapa());
				return unAvionPesado;
			//caso Helicoptero
			case 3:
				Helicoptero unHelicoptero = new Helicoptero(posicionesExtremo.get(0),posicionesExtremo.get(1),this.nivelActual.getMapa());
				return unHelicoptero;
			//caso AvionComputarizado
			case 4:

				AvionComputarizado unAvionComputarizado = new AvionComputarizado(posicionesExtremo.get(0), this.nivelActual.getMapa());
				//setea posicion de destino, la cual es una pista en la que puede aterrizar
				unAvionComputarizado.moverHacia(this.nivelActual.getPosPistaAdecuada(unAvionComputarizado));
				return unAvionComputarizado;
		}
		
		return unAvion;
	}
	
	//llamar en hilo de gameLoop por un timer con tiempo=nivel->dificultad->velocidadDeAparicion
	public void colocarAvion() {
		
		if(this.nivelActual.getFrecuenciaDeAparicionDeNuevoAvion()<= this.cantidadDeCiclos()){
			
			boolean tienePistaAdecuada= false;
			while (!tienePistaAdecuada){
				
				Avion unAvion= this.crearAvionAlAzar();
				if (nivelActual.tienePistaAdecuada(unAvion)){
					nivelActual.colocarAvionEnAire(unAvion);
					tienePistaAdecuada=true;
				}
				
			}	
			this.reiniciarCiclos();
			
		}

		
	}
	
	private void reiniciarCiclos() {
		this.intervaloEntreNuevoAvion=0;
		
	}
	private int cantidadDeCiclos() {
		return this.intervaloEntreNuevoAvion;
	}
	
	public void avanzarNivel(){		
		
		if (!esUltimoNivel()){
			nivelActual= niveles.get(niveles.indexOf(nivelActual)+1);
			cantidadAvionesAterrizados=0;
			jugador.setNivelActual(nivelActual);
			System.out.println("Nivel Ganado!!!!!!!!!!!!!!    Avanzar!! ");
		}
		else{
			this.estaGanado=true;
			this.jugandose=false;
			System.out.println("Nivel Y Juego Ganado!!!!!!!!!!!!!!");
		}
	}
	
	private boolean esUltimoNivel(){
		//el primer numero de nivel es el nivel 0
		int numeroDeNivelActual = this.niveles.indexOf(nivelActual);
		return (numeroDeNivelActual==this.niveles.size()-1);
	}
	
	public boolean esJuegoGanado(){

		Iterator<Nivel> nivelIt= this.niveles.iterator();
		while(nivelIt.hasNext() && this.estaGanado){
			Nivel nivel= nivelIt.next();
			this.estaGanado=nivel.estaGanado();
		}
		
		if( this.estaGanado){			
			this.jugandose=false;
		}
		return this.estaGanado;
	}
	
	/* (non-Javadoc)
	 * @see fiuba.algo3.titiritero.modelo.ObjetoVivo#vivir()
	 */
	@Override
	public void vivir() {
		
		if(this.estaJugandose() && !this.esJuegoGanado()){
			
			if(this.nivelActual.seGano(cantidadAvionesAterrizados)){
			
				this.avanzarNivel();
			
			}else{
				
				this.colocarAvion();
				
				this.huboChoque();

				this.chequearAterrizajes();
				
				this.actualizarMovimientoDeAviones();				
				
			}	
			
		}
		this.pasoUnCiclo();
		notificarObservadores();
		
	}
	private void pasoUnCiclo() {
		this.intervaloEntreNuevoAvion++;
	}
	public int getVelocidadActual() {
		return this.nivelActual.getVelocidadDeAviones();
	}


}


