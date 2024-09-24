package Inicializadores;

import java.util.LinkedList;
import java.util.List;

import pista.Helipuerto;
import pista.Pista;
import pista.PistaDoble;
import pista.PistaLarga;
import pista.PistaSimple;
import pista.PosicionesEntradaVaciaException;

import copControl.Dificultad;
import copControl.Juego;
import copControl.Jugador;
import copControl.Mapa;
import copControl.Nivel;
import copControl.Posicion;

public class InicializadorJuego {


	public InicializadorJuego (){	
		
	}
	
	public static Juego juegoInicializado(){
		return new Juego(jugadorInicializado(), nivelesJuego());
		
	}

	
	private static Jugador jugadorInicializado() {
		return new Jugador("Manolo");
	}

	private static List<Nivel> nivelesJuego (){
		List<Nivel> niveles = new LinkedList<Nivel>();
		niveles.add(nivelInicializado());
		niveles.add(nivel2Inicializado());// agregado de segundo nivel
		return niveles;
	}
	
	//
	//
	//   Nivel 1
	//
	//
	
	private static Nivel nivelInicializado(){
		return new Nivel(mapaInicializado(), dificultadInicializada());		
	}
	
	
	
	private static Dificultad dificultadInicializada() {
		return new Dificultad(5, 50, 50);
	}

	private static Mapa mapaInicializado(){
		return new Mapa(pistasInicializadas());
	}
	
	private static List<Pista> pistasInicializadas(){
		List<Pista> pistas = new LinkedList<Pista>();
		pistas.add(pistaSimpleInicializada());
		pistas.add(helipuertoInicializado());
		pistas.add(pistaDobleInicializada());
		pistas.add(pistaLargaInicializada());
		return pistas;		
	}
	
	
	private static PistaLarga pistaLargaInicializada(){
		Posicion posicionPista = new Posicion(350,370);
		try {
			return new PistaLarga(posicionPista);
		} catch (PosicionesEntradaVaciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static PistaDoble pistaDobleInicializada(){
		Posicion posicionPistaDoble = new Posicion(100,200);
		try {
			return new PistaDoble(posicionPistaDoble);
		} catch (PosicionesEntradaVaciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private static PistaSimple pistaSimpleInicializada() {
		try {
			return new PistaSimple(posicionPistaSimple());
		} catch (PosicionesEntradaVaciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static Helipuerto helipuertoInicializado() {
		try {
			return new Helipuerto(posicionHelipuerto());
		} catch (PosicionesEntradaVaciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static Posicion posicionPistaSimple() {
		Posicion posicionEntrada = new Posicion(100, 75);
		return posicionEntrada;
	}
	
	private static Posicion posicionHelipuerto() {
		Posicion posicionEntrada = new Posicion(400, 90);
		return posicionEntrada;
	}
	
	//   Fin Nivel 1
	//
	//   Inicio Nivel 2
	//
	//
	//
	
	private static Dificultad dificultad2Inicializada() {
		return new Dificultad(5, 40, 50);
	}
	
	private static Nivel nivel2Inicializado(){
		return new Nivel(mapa2Inicializado(), dificultad2Inicializada());		
	}
	private static Mapa mapa2Inicializado(){
		return new Mapa(pistas2Inicializadas());
	}
	private static List<Pista> pistas2Inicializadas(){
		List<Pista> pistas = new LinkedList<Pista>();
		pistas.add(pistaSimple2Inicializada());
		pistas.add(helipuerto2Inicializado());
		
		return pistas;		
	}
	private static Posicion posicion2Helipuerto() {
		Posicion posicionEntrada = new Posicion(200, 300);
		return posicionEntrada;
	}
	private static Helipuerto helipuerto2Inicializado() {
		try {
			return new Helipuerto(posicion2Helipuerto());
		} catch (PosicionesEntradaVaciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private static PistaSimple pistaSimple2Inicializada() {
		try {
			return new PistaSimple(posicion2PistaSimple());
		} catch (PosicionesEntradaVaciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private static Posicion posicion2PistaSimple() {
		Posicion posicionEntrada = new Posicion(250, 250);
		return posicionEntrada;
	}
	//
	//
	//
	//
	//   FIN NIVEL 2
	//
	//
	
}
