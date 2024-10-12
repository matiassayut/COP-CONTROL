package integrationTests;

import avion.AvionPesado;
import avion.AvionSimple;
import avion.Helicoptero;
import copControl.Dificultad;
import copControl.Mapa;
import copControl.Nivel;
import copControl.Posicion;
import pista.Helipuerto;
import pista.PistaSimple;
import pista.PosicionesEntradaVaciaException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class NivelIntegrationTest {

	private Nivel nivel;
	private Mapa mapa;
	private Dificultad dificultad;
	private Helipuerto helipuerto;
	private PistaSimple pistaSimple;

	@Before
	public void setUp() throws PosicionesEntradaVaciaException {

		pistaSimple = new PistaSimple(new Posicion(100, 50));
		helipuerto = new Helipuerto(new Posicion(200, 100));
		mapa = new Mapa();
		mapa.setPistas(Arrays.asList(pistaSimple, helipuerto));
		dificultad = new Dificultad(5, 15, 10);
		nivel = new Nivel(mapa, dificultad);

	}

	@Test
	public void testHuboChoque_Positivo() {

		AvionSimple avionSimple = new AvionSimple(new Posicion(18, 20), new Posicion(23, 30), mapa);
		Helicoptero helicoptero = new Helicoptero(new Posicion(10, 10), new Posicion(15, 15), mapa);
		AvionPesado avionPesado = new AvionPesado(new Posicion(100, 40), new Posicion(123, 48), mapa);
		mapa.colocarAvionEnAire(avionSimple);
		mapa.colocarAvionEnAire(helicoptero);
		mapa.colocarAvionEnAire(avionPesado);

		boolean resultado = nivel.huboChoque();

		assertTrue("Debería detectar un choque entre aviones", resultado);
	}

	@Test
	public void testHuboChoque_Negativo() {

		Helicoptero helicoptero = new Helicoptero(new Posicion(10, 10), new Posicion(15, 15), mapa);
		AvionPesado avionPesado = new AvionPesado(new Posicion(100, 40), new Posicion(123, 48), mapa);

		mapa.colocarAvionEnAire(helicoptero);
		mapa.colocarAvionEnAire(avionPesado);

		boolean resultado = nivel.huboChoque();

		assertFalse("No debería detectar un choque entre aviones", resultado);
	}

	// Como el mapa tiene pista para avion simple deberia pasar el test
	@Test
	public void testTienePistaAdecuada_AvionSimple() {

		AvionSimple avionSimple = new AvionSimple(new Posicion(18, 20), new Posicion(23, 30), mapa);

		assertTrue("AvionSimple debería poder aterrizar debido a que el mapa tiene una PistaSimple",
				nivel.tienePistaAdecuada(avionSimple));
	}

	// Como el mapa tiene pista para avion simple deberia pasar el test
	@Test
	public void testTienePistaAdecuada_Helicoptero() {

		Helicoptero helicoptero = new Helicoptero(new Posicion(10, 10), new Posicion(15, 15), mapa);

		assertTrue("Helicoptero debería poder aterrizar debido a que el mapa tiene un Helipuerto",
				nivel.tienePistaAdecuada(helicoptero));
	}

	// El mapa tiene no tiene pista para avion pesado, entonces no va a tener una
	// pista adecuada
	@Test
	public void testTienePistaAdecuada_AvionPesado() {

		AvionPesado avionPesado = new AvionPesado(new Posicion(100, 40), new Posicion(123, 48), mapa);

		assertFalse("AvionPesado no debería poder aterrizar debido a que el mapa no tiene una PistaLarga",
				nivel.tienePistaAdecuada(avionPesado));
	}

}
