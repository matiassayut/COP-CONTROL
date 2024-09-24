package pista;



import avion.Avion;
import avion.AvionComputarizado;
import avion.AvionPesado;
import avion.AvionSimple;
import avion.Helicoptero;

import copControl.Posicion;


public class PistaDoble extends Pista {
	private Posicion posicionEntradaSecundaria;
	private int largo;
	
	

	public PistaDoble(Posicion posicionEntrada) throws PosicionesEntradaVaciaException  {
		super (posicionEntrada);
		this.radioAterrizaje=15;
		this.largo=20;
		this.posicionEntrada= posicionEntrada;		
		this.posicionEntradaSecundaria= new Posicion((int)this.getPosicionEntradaPrincipal().getCoordenadaX()+largo,(int)this.getPosicionEntradaPrincipal().getCoordenadaY());
	}

	public int getLargo(){
		return this.largo;
	}
	
	@Override
	public boolean puedeAterrizar(AvionSimple avionSimple) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean puedeAterrizar(AvionPesado avionPesado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean puedeAterrizar(AvionComputarizado avionComputarizado) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean puedeAterrizar(Helicoptero helicoptero) {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * @return Posicion de la primer entrada
	 */
	public Posicion getPosicionEntradaPrincipal(){
		return this.getPosicionEntrada();
		
	}
	
	/**
	 * @return Posicion de la primer entrada
	 */
	public Posicion getPosicionEntradaSecundaria(){
		return this.posicionEntradaSecundaria;
		
	}
	
	

	@Override
	public int getX() {
		return (int)getPosicionEntradaPrincipal().getCoordenadaX();
	}

	@Override
	public int getY() {
		return (int)getPosicionEntradaPrincipal().getCoordenadaY();
	}

	@Override
	public boolean estaEnZonaAterrizaje(Avion avion) {
		
		Posicion posicionPrincipal= this.getPosicionEntradaPrincipal();
		Posicion posicionSecundaria= this.getPosicionEntradaSecundaria();
		int radio = this.radioAterrizaje;
		
		Posicion posicionAvion = avion.getPosicionActual();
		int radioAvion = avion.getRadio();	
		
		boolean entraEnPrincipal= intersects(posicionPrincipal.getCoordenadaX(), posicionPrincipal.getCoordenadaY(), radio,
				posicionAvion.getCoordenadaX(), posicionAvion.getCoordenadaY(), radioAvion);
		
		boolean entraEnSecundaria=intersects(posicionSecundaria.getCoordenadaX(), posicionSecundaria.getCoordenadaY(), radio,
				posicionAvion.getCoordenadaX(), posicionAvion.getCoordenadaY(), radioAvion);
		
		return (entraEnPrincipal||entraEnSecundaria);
	}

}
