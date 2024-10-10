package unitTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import avion.AvionSimple;
import copControl.Mapa;
import copControl.Posicion;
import pista.Pista;

class AvionSimpleUnitTest {
	
	private AvionSimple avionSimple;
	private Posicion posIni;
    private Posicion posFin;
    private Mapa mapaDeMovimiento;
    private Pista pista;
    
    @BeforeEach
    void setUp() {
        posIni = new Posicion(0, 0);
        posFin = new Posicion(10, 10);
        mapaDeMovimiento = mock(Mapa.class);
        avionSimple = new AvionSimple(posIni, posFin, mapaDeMovimiento);
        pista = mock(Pista.class);
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
	
    @Test
    void testPuedeAterrizar() {
        when(pista.puedeAterrizar(avionSimple)).thenReturn(true);
        assertTrue(avionSimple.puedeAterrizar(pista), "El avion simple debería poder aterrizar en la pista.");
    }
    
    @Test
    void testColocarAvionEnMapa() {
    	doNothing().when(mapaDeMovimiento).colocarAvionEnAire(avionSimple);
        when(mapaDeMovimiento.tieneAvionesVolando()).thenReturn(true);
        mapaDeMovimiento.colocarAvionEnAire(avionSimple);
        assertTrue(mapaDeMovimiento.tieneAvionesVolando(), "El mapa debería indicar que hay un avión volando.");
    }
	
}
