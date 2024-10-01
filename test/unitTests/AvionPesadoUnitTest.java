package unitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

//import org.mockito.Mockito;
import pista.Pista;
import copControl.Mapa;
import copControl.Posicion;
import avion.AvionPesado;


public class AvionPesadoUnitTest {
	private AvionPesado avionPesado;
	private Posicion posInicial;
	private Posicion posFinal;
	private Mapa mapaDeMovimiento;
	private Pista pista;
	
	@BeforeEach
    void setUp() {
        posInicial = new Posicion(0, 0); // Suponiendo que Posicion tiene un constructor así
        posFinal = new Posicion(10, 10); // Suponiendo que Posicion tiene un constructor así
       // mapaDeMovimiento = Mockito.mock(Mapa.class);
        avionPesado = new AvionPesado(posInicial, posFinal, mapaDeMovimiento);
        pista = Mockito.mock(Pista.class);
    }
	
	@Test
    void testPuedeAterrizar() {
		Mockito.when(pista.puedeAterrizar(avionPesado)).thenReturn(true);
		assertTrue(avionPesado.puedeAterrizar(pista), "El Avion Pesado debería poder aterrizar en la pista.");
    }

	@Test
	void testEsControlable() {
		assertTrue(avionPesado.esControlable(), "El avion pesado no es controlable.");
	}
	
	@Test
	void testNoEsDetenible() {
		assertFalse(avionPesado.esDetenible(), "El avion pesado es detenible");
	}
	
	@Test
	void testIsInPosicionInicial() {
		assertEquals(posInicial ,avionPesado.getPosicionActual(), "El avion pesado no esta en la posicion inicial");
	}
	
	@Test
	void testIsDestinoPosicionFinal() {
		assertEquals(posFinal, avionPesado.getDestinoActual(), "El destino del avion pesado no es la posicion final");
	}
	
	@Test
	void testEstaVolando() {
		assertTrue(avionPesado.getEstaVolando(), "El avion pesado no esta volando");
	}
	
}
