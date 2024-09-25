package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import avion.AvionSimple;
import copControl.Mapa;
import copControl.Posicion;

class AvionSimpleUnitTest {
	
	private AvionSimple avionSimple;
	private Posicion posIni;
    private Posicion posFin;
    private Mapa mapaDeMovimiento;
    
    @BeforeEach
    void setUp() {
        posIni = new Posicion(0, 0);
        posFin = new Posicion(10, 10);
        avionSimple = new AvionSimple(posIni, posFin, mapaDeMovimiento);
    }


	@Test
	void testEsControlable() {
		assertTrue(avionSimple.esControlable(), "El avion simple no es controlable.");
	}
	
	@Test
	void testNoEsDetenible() {
		assertFalse(avionSimple.esDetenible(), "El avion simple es detenible");
	}
	
	@Test
	void testIsInPosicionInicial() {
		assertEquals(posIni,avionSimple.getPosicionActual(), "El avion simple no esta en la posicion inicial");
	}
	
	@Test
	void testIsDestinoPosicionFinal() {
		assertEquals(posFin,avionSimple.getDestinoActual(), "El destino del avion simple no es la posicion final");
	}
	
	@Test
	void testEstaVolando() {
		assertTrue(avionSimple.getEstaVolando(), "El avion simple no esta volando");
	}
	
}
