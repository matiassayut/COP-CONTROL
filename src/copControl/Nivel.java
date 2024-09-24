package copControl;

import java.util.List;
import java.util.Iterator;


import pista.Pista;

import avion.Avion;
import avion.AvionComputarizado;

public class Nivel {
	private Mapa mapa;
	private Dificultad dificultad;
	private boolean ganado;
	
	public Nivel(Mapa mapa, Dificultad dificultad) {
		this.mapa=mapa;
		this.dificultad=dificultad;
		this.ganado=false;
	}

	public Avion getAvionEnPosicion(Posicion unaPosicion) {
		Avion avionRecivido= null; 
		List<Avion> avionesEnAire=this.mapa.getAvionesEnAire();
		Iterator<Avion> it= avionesEnAire.iterator();

		while (it.hasNext()){
			Avion avionActual = it.next();
			if(avionActual.esPosicionContenida(unaPosicion)){
				avionRecivido= avionActual;
				return avionRecivido;
			}		
		}
		
		return avionRecivido;
	}

	//remueve del aire los aviones que esten en condiciones de aterrizar
	public Integer aterrizarAviones() {
		Integer cantidadDeAterrizados=0;
		
		List<Pista> pistas = this.mapa.getPistas();
		//Avion avionAterrizado=null;
		//Recorro las pistas del mapa
		for (Pista pista : pistas) {
			Iterator<Avion> itAvionesEnAire =this.mapa.getAvionesEnAire().iterator();
			while(itAvionesEnAire.hasNext()){				
				Avion avion=itAvionesEnAire.next();
				//Si alguna de las posiciones de entrada de la pista coincide con la posicion del avion
				if(avion.aterrizar(pista)){
					cantidadDeAterrizados++;
					itAvionesEnAire.remove();
					System.out.println("aterrizo un avion");
				}
			}
		
		}
		return cantidadDeAterrizados;
	}
	

	public List<Posicion> getPosicionesExtremos() {
		
		return mapa.getPosicionesExtremos();
	}

	public void colocarAvionEnAire(Avion unAvion) {
		mapa.colocarAvionEnAire(unAvion);
	
	}
	public boolean tieneAvionesVolando(){
		return mapa.tieneAvionesVolando();
	}
	
	
	public boolean tienePistaAdecuada(Avion unAvion){       
		List<Pista> pistas=this.mapa.getPistas();
		
		Iterator<Pista> it= pistas.iterator();
		boolean tienePistaAdecuada=false;
		
		while ((it.hasNext()) && !tienePistaAdecuada)
		{
			tienePistaAdecuada= unAvion.puedeAterrizar(it.next());
		}
		
		
		return tienePistaAdecuada;
	
	}

	/**
	 * @return
	 */
	public boolean huboChoque() {
		
		List<Avion> avionesEnAire =this.mapa.getAvionesEnAire();
		boolean chocoConOtroAvion=false;
		Avion avionActual=null;
		Iterator<Avion> it= avionesEnAire.iterator();
		while(it.hasNext() && (!chocoConOtroAvion)){
			avionActual = it.next();
					
			chocoConOtroAvion = this.chocoConOtroAvion(avionActual,avionesEnAire);
			
		}		
	
		return chocoConOtroAvion;
	}

	private boolean chocoConOtroAvion(Avion avionActual,List<Avion> avionesEnAire) {
		
		boolean posicionIdentica= false;
		
		Iterator<Avion> it= avionesEnAire.iterator();
		Avion avionAComparar = null;
		while(it.hasNext() && (!posicionIdentica)){
			 avionAComparar = it.next();
			if(avionAComparar != avionActual){
				posicionIdentica= choqueAvion(avionAComparar, avionActual);
			}
			
			if(posicionIdentica ){
				System.out.println("Posicion Avion actual:"+avionActual.getPosicionActual().toString());
				System.out.println("Posicion Avion Comparador: "+avionAComparar.getPosicionActual().toString());		
				
			}
			//posicionIdentica = avionAComparar.getPosicionActual().igualA(avionActual.getPosicionActual());
		}
		
		return posicionIdentica;
	}
	
	private boolean choqueAvion(Avion avion1,Avion avion2){
		
		Posicion posicion1= avion1.getPosicionActual();
		int radio1 = avion1.getRadio();
		Posicion posicion2 = avion2.getPosicionActual();
		int radio2 = avion2.getRadio();	
		
		return intersects(posicion1.getCoordenadaX(), posicion1.getCoordenadaY(), radio1,
				posicion2.getCoordenadaX(), posicion2.getCoordenadaY(), radio2);
		
	
	}
	
	
    // does circle (x1, y1, r1) intersect circle (x2, y2, r2)?
    private boolean intersects(double x1, double y1, double r1,
                                     double x2, double y2, double r2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        double distance = Math.sqrt(dx*dx + dy*dy);
        return distance <= r1 + r2;
    }


	public List<Avion> getAvionesVolando() {
		return mapa.getAvionesEnAire();
	}
	
	public void avanzarAvionesEnAire(){
	
		 
		
			List<Avion> avionesEnAire=this.mapa.getAvionesEnAire();
			Iterator<Avion> it= avionesEnAire.iterator();

			while (it.hasNext()){
				
				Avion avionActual = it.next();
				if (avionActual.getDestinoActual()== null)
				{
					avionActual.moverHacia(this.mapa.getPosicionesExtremos().get(0));
				}
				avionActual.avanzar();
			}
		
		
	}

	public Posicion getPosPistaAdecuada(AvionComputarizado unAvion) {
		
		
		 return (this.mapa.getPosPistaAdecuada(unAvion));
		}
		
		
		
	

	public Mapa getMapa() {
		
		return this.mapa;
	}

	public int getFrecuenciaDeAparicionDeNuevoAvion() {
		return this.dificultad.getfrecuenciaDeAparicion();
	}

	public Integer getCantidadDeAvionesPorAterrizar() {
		return this.dificultad.getCantidadDeAvionesPorAterrizar();
	}
	
	/**
	 * @return
	 */
	public boolean seGano(int cantidadAvionesAterrizados ) {
		return(this.getCantidadDeAvionesPorAterrizar()==cantidadAvionesAterrizados);
		
	}
	
	public boolean estaGanado(){
		return this.ganado;
		
	}

	public int getVelocidadDeAviones() {
		return this.dificultad.getVelocidad();
	}
}
