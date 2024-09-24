package avion;


import pista.Pista;
import copControl.Mapa;
import copControl.Posicion;
import copControl.Trayectoria;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public abstract class Avion  implements  ObjetoPosicionable{
	protected Trayectoria trayectoria;
	protected boolean estaVolando;
	protected boolean esControlable;
	protected int radio;
	protected boolean estaMarcado;
	protected boolean esDetenible;
	
	public boolean estaMarcado(){
		
		return this.estaMarcado;
	}
	
	/**
	 * @return the radio
	 */
	public int getRadio() {
		return radio;
	}

	/**
	 * @return
	 */
	public Posicion getPosicionActual() {
		return this.trayectoria.getPosicionActual();
	}
	/**
	 * @param posIni
	 * @param posFin
	 */
	public Avion(Posicion posIni,Posicion posFin,Mapa mapaDeMovimiento){
		this.estaVolando=true;
		this.trayectoria= new Trayectoria(posIni,posFin,mapaDeMovimiento);
		this.estaMarcado=false;
		this.esDetenible=false;
	}
	
	public Avion(Posicion posIni,Mapa mapaDeMovimiento) {
		this.estaVolando=true;
		this.trayectoria= new Trayectoria(posIni,mapaDeMovimiento);
		this.estaMarcado=false;
		this.esDetenible=false;
		
	}
	/**
	 * @return
	 */
	public boolean getEstaVolando(){
		return this.estaVolando;		
	}
	
	public void avanzar(){
		
	
			trayectoria.avanzar();
		}	
	

	public Posicion getDestinoActual(){
		return trayectoria.getDestinoActual();
	}
		
		
	/**
	 * @param pista
	 * @return
	 */
	abstract public boolean puedeAterrizar(Pista pista);
	
	//borrar metodo es para testear por consola 
	/*public void mostrarPosEnConsola(){
		if(this.getDestinoActual().igualA(this.getPosicionActual())){
			System.out.println("Avion llego a destino!! ");
		}
		else {
			System.out.println("Avion llego a a (x,y): ");
			System.out.println((int)this.getPosicionActual().getCoordenadaX());
			System.out.println((int)this.getPosicionActual().getCoordenadaY());
		
			System.out.println("Destino de Avion es (x,y): ");
			System.out.println((int)this.getDestinoActual().getCoordenadaX());
			System.out.println((int)this.getDestinoActual().getCoordenadaY());
		}
		
		
	}*/
	
	public boolean esControlable(){
		return esControlable;
	}
	
	public abstract void moverHacia(Posicion unaPosicion);
	
		
	public boolean aterrizar(Pista pista){
		
		boolean condicion1 =(pista.estaEnZonaAterrizaje(this)) ;
		boolean condicion2= this.puedeAterrizar(pista);
		return (condicion1 && condicion2);
		
		
	}
	

	
	public int getX() {
		return ((int)this.getPosicionActual().getCoordenadaX());
	}

	
	public int getY() {
		return ((int)this.getPosicionActual().getCoordenadaY());
	}

	
    
    public boolean esPosicionContenida(Posicion unaPosicion) {
    	double x1 = (unaPosicion.getCoordenadaX()-(this.getRadio()/2));
        double y1 = unaPosicion.getCoordenadaY()-(this.getRadio()/2);
    	double x2 = (double)this.getX();
    	double y2 = (double)this.getY();
        double dx = x1 - x2;
        double dy = y1 - y2;
        double distance = Math.sqrt(dx*dx + dy*dy);
        boolean marcado=distance <= this.radio;
        if(marcado){
        	this.estaMarcado=true;
        	
        }
        
        return marcado;
    }

	public void desmarcar() {
		this.estaMarcado=false;
		
	}
	
	public boolean esDetenible(){
		return this.esDetenible;
	}

	public void detener() {
		// TODO Auto-generated method stub
		
	}

	public void iniciarNuevaTrayectoria(Posicion nuevaPosicion){
		this.trayectoria.borrarDestinos();
		
	}

	public void arrancar() {
		// TODO Auto-generated method stub
		
	}

	
}
