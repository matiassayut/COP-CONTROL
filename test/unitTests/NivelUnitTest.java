package unitTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import copControl.Mapa;
import copControl.Dificultad;
import copControl.Nivel;
import avion.*;

public class NivelUnitTest {
	private Nivel nivel;
	private Mapa mapa;
	private Dificultad dificultad;
	private AvionSimple avionSimple;
	@BeforeEach
	void setUp() {
		mapa = new Mapa();
		avionSimple = mock(AvionSimple.class);
		dificultad = mock(Dificultad.class); //Dificultad modelada
		nivel = new Nivel(mapa, dificultad);
		
	}
	
	@Test
	void testEstaGanado() {
		//Todos los aviones Aterrizaorn?
		assertTrue(nivel.estaGanado(),"Ganaste el nivel");
	}
	
	@Test
	void testPistaAdecuada(){
		//Aviones volando
		assertTrue(nivel.tienePistaAdecuada(avionSimple), "Pista adecuada para avion simple");
	}
	
	@Test
	void testFrecuenciaAparicion(){
		assertTrue(nivel.getFrecuenciaDeAparicionDeNuevoAvion() > 0, "Frecuencia Valida");
		 
	}
	
	@Test
	void testAvionesPorAterrizar(){
		assertTrue(nivel.getCantidadDeAvionesPorAterrizar() >= 0, "Cantidad correcta de aviones");
		 
	}
	
	@Test
	void testVelocidad(){
		assertTrue(nivel.getVelocidadDeAviones() >= 0, "Velocidad Correcta");
		 
	}
	
	
	
	
}
