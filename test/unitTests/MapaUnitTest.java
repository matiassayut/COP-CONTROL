package unitTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import avion.AvionSimple;
import copControl.Mapa;
import copControl.Posicion;
import pista.Pista;

public class MapaUnitTest {

	private Mapa mapa;
	private AvionSimple avionMock;
	private Pista pistaMock;
	private Pista pistaMock1;
	private Pista pistaMock2;
	private List<Pista> listaPistas;

	@BeforeEach
	void setUp() throws Exception {

		pistaMock = mock(Pista.class);
		pistaMock1 = mock(Pista.class);
		pistaMock2 = mock(Pista.class);
		listaPistas = new ArrayList<>();
		listaPistas.add(pistaMock);
		listaPistas.add(pistaMock1);
		listaPistas.add(pistaMock2);

		avionMock = mock(AvionSimple.class);
		when(avionMock.getPosicionActual()).thenReturn(new Posicion(100, 100));

		mapa = new Mapa(listaPistas);

	}

	@Test
	public void testTieneAvionesVolandoSinAviones() {
	
		
		assertFalse(mapa.tieneAvionesVolando());
	}

	@Test
	public void testTieneAvionesVolandoConAviones() {
	
		mapa.colocarAvionEnAire(avionMock);
		assertTrue(mapa.tieneAvionesVolando());
	}

	@Test
	public void testGetPosPistaAdecuada_conPistaAdecuada() {

		when(avionMock.puedeAterrizar(pistaMock)).thenReturn(false);
		when(avionMock.puedeAterrizar(pistaMock1)).thenReturn(false);
		when(avionMock.puedeAterrizar(pistaMock2)).thenReturn(true);

		when(pistaMock2.getPosicionEntrada()).thenReturn(new Posicion(1, 2));
		Posicion resultado = mapa.getPosPistaAdecuada(avionMock);
		assertEquals(1, resultado.getCoordenadaX());
		assertEquals(2, resultado.getCoordenadaY());
	}

	@Test
	public void testGetPosPistaAdecuada_sinPistaAdecuada() {

		when(avionMock.puedeAterrizar(pistaMock)).thenReturn(false);
		when(avionMock.puedeAterrizar(pistaMock1)).thenReturn(false);
		when(avionMock.puedeAterrizar(pistaMock2)).thenReturn(false);

		assertNull(mapa.getPosPistaAdecuada(avionMock));

	}

	@Test
	public void testPosicionFueraDeDimension() {
		Mapa mapa = new Mapa();

		// Posición fuera de los límites del mapa
		Posicion posicion = new Posicion(-100, -300);

		assertFalse(mapa.esPosicionExtremo(posicion));
	}

	@Test
	public void testEsPosicionExtremo_bordeIzquierdo() {
		Posicion posicionBordeIzq = new Posicion(0, 250);
		assertTrue(mapa.esPosicionExtremo(posicionBordeIzq));

	}

	
	 
	@Test
	public void testEsPosicionExtremo() {

		// Este sería el centro del mapa. (Dimension=500)
		Posicion posicionBordeIzq = new Posicion(250, 250);
		
		//Deberia pasar el test, sin embargo, el metodo del modelo parece estar mal planteado, debido a que considera como
		//un extremo la posicion (250,250).
		assertFalse(mapa.esPosicionExtremo(posicionBordeIzq));

	}

	
	
	/*
	 * Borde izquierdo: x == 0 y y debe estar entre 0 y 500 
	 * Borde derecho: x == 500 y y debe estar entre 0 y 500. 
	 * Borde superior: y == 0 y x debe estar entre 0 y 500. 
	 * Borde inferior: y == 500 y x debe estar entre 0 y 500.
	 */
	@Test
	public void testGenerarExtremoAlAzar() {

		Posicion posicionGenerada = mapa.generarPosicionExtremoAlAzar();
		double x = posicionGenerada.getCoordenadaX();
		double y = posicionGenerada.getCoordenadaY();

		// Verificar que la posición generada está en uno de los bordes
		assertTrue((x == 0 || x == 500 || y == 0 || y == 500));

		// Verificar que las coordenadas están dentro de los límites del mapa
		assertTrue(x >= 0 && x <= 500, "Coordenada X fuera de los límites: " + x);
		assertTrue(y >= 0 && y <= 500, "Coordenada Y fuera de los límites: " + y);

	}

}
