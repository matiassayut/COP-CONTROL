package unitTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import avion.Avion;
import copControl.Dificultad;
import copControl.Mapa;
import copControl.Nivel;
import copControl.Posicion;
import pista.Pista;

public class NivelUnitTest {

	private Mapa mockMapa;
	private Nivel nivel;
	private Avion mockAvion1;
	private Avion mockAvion2;
	private Avion mockAvion3;
	private Posicion mockPosicion;
	private Dificultad mockDificultad;
	private Pista mockPista;
	private Pista mockPista1;
	private Posicion mockPosicionAvion1;
	private Posicion mockPosicionAvion2;

	@BeforeEach
	void setUp() {

		mockMapa = mock(Mapa.class);
		mockAvion1 = mock(Avion.class);
		mockAvion2 = mock(Avion.class);
		mockAvion3 = mock(Avion.class);

		mockPosicion = mock(Posicion.class);
		mockDificultad = mock(Dificultad.class);

		mockPista = mock(Pista.class);
		mockPista1 = mock(Pista.class);

		mockPosicionAvion1 = mock(Posicion.class);
		mockPosicionAvion2 = mock(Posicion.class);

		nivel = new Nivel(mockMapa, mockDificultad);

	}

	@Test
	public void testGetAvionEnPosicion_EncuentraAvion() {

		when(mockMapa.getAvionesEnAire()).thenReturn(List.of(mockAvion1, mockAvion2));

		when(mockAvion1.esPosicionContenida(mockPosicion)).thenReturn(false);
		when(mockAvion2.esPosicionContenida(mockPosicion)).thenReturn(true);

		Avion avionEncontrado = nivel.getAvionEnPosicion(mockPosicion);

		assertEquals(mockAvion2, avionEncontrado);

	}

	@Test
	public void testGetAvionEnPosicion_NoEncuentraAvion() {
		when(mockMapa.getAvionesEnAire()).thenReturn(List.of(mockAvion1, mockAvion2));

		when(mockAvion1.esPosicionContenida(mockPosicion)).thenReturn(false);
		when(mockAvion2.esPosicionContenida(mockPosicion)).thenReturn(false);

		Avion avionEncontrado = nivel.getAvionEnPosicion(mockPosicion);

		assertNull(avionEncontrado);

	}

	@Test
	public void testAterrizarAviones() {

		when(mockAvion1.aterrizar(mockPista)).thenReturn(true);
		when(mockAvion2.aterrizar(mockPista)).thenReturn(true);
		when(mockAvion3.aterrizar(mockPista)).thenReturn(false);

		when(mockMapa.getPistas()).thenReturn(List.of(mockPista));

		ArrayList<Avion> aviones = new ArrayList<>();
		aviones.add(mockAvion1);
		aviones.add(mockAvion2);
		aviones.add(mockAvion3);

		when(mockMapa.getAvionesEnAire()).thenReturn(aviones);

		Integer cantidadAterrizados = nivel.aterrizarAviones();

		assertEquals(2, cantidadAterrizados);

		// Verificar que el avión fue removido de la lista de aviones en aire
		assertFalse(mockMapa.getAvionesEnAire().contains(mockAvion1));
		assertFalse(mockMapa.getAvionesEnAire().contains(mockAvion2));
		assertTrue(mockMapa.getAvionesEnAire().contains(mockAvion3));

	}

	@Test
	public void testHuboChoque_positivo() {
		when(mockAvion1.getPosicionActual()).thenReturn(mockPosicionAvion1);
		when(mockAvion2.getPosicionActual()).thenReturn(mockPosicionAvion2);

		// Simular que las posiciones de los aviones causan una colisión
		when(mockPosicionAvion1.getCoordenadaX()).thenReturn(10.0);
		when(mockPosicionAvion1.getCoordenadaY()).thenReturn(10.0);
		when(mockPosicionAvion2.getCoordenadaX()).thenReturn(12.0);
		when(mockPosicionAvion2.getCoordenadaY()).thenReturn(12.0);

		when(mockAvion1.getRadio()).thenReturn(7);
		when(mockAvion2.getRadio()).thenReturn(7);

		List<Avion> avionesEnAire = new ArrayList<>();
		avionesEnAire.add(mockAvion1);
		avionesEnAire.add(mockAvion2);

		when(mockMapa.getAvionesEnAire()).thenReturn(avionesEnAire);

		boolean resultado = nivel.huboChoque();

		// Verificar que el método detecta la colisión
		assertTrue("Debería detectar un choque entre aviones", resultado);
	}

	@Test
	public void testHuboChoque_negativo() {

		when(mockAvion1.getPosicionActual()).thenReturn(mockPosicionAvion1);
		when(mockAvion2.getPosicionActual()).thenReturn(mockPosicionAvion2);

		when(mockPosicionAvion1.getCoordenadaX()).thenReturn(10.0);
		when(mockPosicionAvion1.getCoordenadaY()).thenReturn(10.0);
		when(mockPosicionAvion2.getCoordenadaX()).thenReturn(100.0);
		when(mockPosicionAvion2.getCoordenadaY()).thenReturn(100.0);

		when(mockAvion1.getRadio()).thenReturn(7);
		when(mockAvion2.getRadio()).thenReturn(7);

		List<Avion> avionesEnAire = new ArrayList<>();
		avionesEnAire.add(mockAvion1);
		avionesEnAire.add(mockAvion2);

		when(mockMapa.getAvionesEnAire()).thenReturn(avionesEnAire);

		boolean resultado = nivel.huboChoque();

		assertFalse("No se debe producir ningun choque entre aviones debido a que" + " estan a una distancia lejana",
				resultado);

	}

	@Test
	public void testTienePistaAdecuada() {
		when(mockMapa.getPistas()).thenReturn(List.of(mockPista, mockPista1));

		when(mockAvion1.puedeAterrizar(mockPista)).thenReturn(false);
		when(mockAvion1.puedeAterrizar(mockPista1)).thenReturn(true);

		boolean resultado = nivel.tienePistaAdecuada(mockAvion1);

		assertTrue(resultado);
	}

	@Test
	public void testNoTienePistaAdecuada() {
		when(mockMapa.getPistas()).thenReturn(List.of(mockPista, mockPista1));

		when(mockAvion1.puedeAterrizar(mockPista)).thenReturn(false);
		when(mockAvion1.puedeAterrizar(mockPista1)).thenReturn(false);

		boolean resultado = nivel.tienePistaAdecuada(mockAvion1);

		assertFalse(resultado);
	}

}
