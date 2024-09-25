package integrationTests;

import pista.Helipuerto;
import pista.PistaSimple;
import pista.PosicionesEntradaVaciaException;
import avion.AvionSimple;
import copControl.Mapa;
import copControl.Posicion;

//librerias de Junit 5
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//

public class AvionSimpleIntegrationTest{
	
	// clase integration test hecha por el profesor
	
	@BeforeEach
	public void setUp(){
		
		mapaDeJuego = new Mapa();
	}
	
	Mapa mapaDeJuego;
	
	@Test
	public void testUnAvionSimplePuedeAterrizarEnUnaPistaSimple() throws PosicionesEntradaVaciaException{
		Posicion posicionInicial= new Posicion(1,1);
		Posicion posicionFinal= new Posicion(5,5);
		PistaSimple pistaSimple = new PistaSimple(posicionFinal);
		
		AvionSimple avion = new AvionSimple(posicionInicial, posicionFinal,mapaDeJuego);
		
		assertTrue(avion.puedeAterrizar(pistaSimple));
	}
	@Test
	public void testUnAvionSimpleNoPuedeAterrizarEnUnHelipuerto() throws PosicionesEntradaVaciaException{
		Posicion posicionInicial= new Posicion(1,1);
		Posicion posicionFinal= new Posicion(5,5);
		Helipuerto helipuerto = new Helipuerto(posicionFinal);
		
		AvionSimple avion = new AvionSimple(posicionInicial, posicionFinal,mapaDeJuego);
		
		assertFalse(avion.puedeAterrizar(helipuerto));
	}
	@Test
	public void testUnaPosicionDentroDelRadioDelAvionDebeDevolverTrue() {
		Posicion posicionInicial= new Posicion(30,30);
		Posicion posicionFinal= new Posicion(3,9);
		Posicion posicionDePrueba=new Posicion(31,31);
		
		
		AvionSimple avion = new AvionSimple(posicionInicial, posicionFinal,mapaDeJuego);
		assertTrue(avion.esPosicionContenida(posicionDePrueba));
		
	}
}
