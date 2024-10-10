package integrationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import copControl.Mapa;
import copControl.Posicion;
import copControl.Trayectoria;

public class TrayectoriaIntegrationTest {

	
	@Test
	public void testAvanzarConPosicionesReales() {
	    Posicion posInicial = new Posicion(0, 0);
	    Posicion posFinal = new Posicion(10, 10);
	    Mapa mapaReal = new Mapa();  // Mapa real, no un mock
	    
	    Trayectoria trayectoria = new Trayectoria(posInicial, posFinal, mapaReal);
	    
	    trayectoria.avanzar();
	    
	    assertNotEquals(posInicial, trayectoria.getPosicionActual(), "La posición debería haber cambiado en un escenario real.");
	}
	
	@Test
	public void testAvanzarConPosicionesYMapaReales() {
	   
		Mapa mapaReal = new Mapa();
	    Posicion posInicial = new Posicion(0, 0);
	    Posicion posFinal = new Posicion(10, 10);
	    
	    Trayectoria trayectoria = new Trayectoria(posInicial, posFinal, mapaReal);
	    
	    trayectoria.avanzar();
	    
	 
	    assertNotEquals(posInicial, trayectoria.getPosicionActual(), "La posición debería haber cambiado en un escenario real.");
	}

	@Test
	public void testDetenerTrayectoriaConMapaReal() {
	    Mapa mapaReal = new Mapa();
	    Posicion posInicial = new Posicion(0, 0);
	    Posicion posFinal = new Posicion(10, 10);
	    
	    Trayectoria trayectoria = new Trayectoria(posInicial, posFinal, mapaReal);
	    
	    trayectoria.avanzar(); // Se supone que avanza hacia el destino
	    trayectoria.detener(); // Ahora debe detenerse
	    
	    // Verificar que la posición actual sea el destino
	    assertEquals(trayectoria.getPosicionActual(), trayectoria.getDestinoActual(), "Debería detenerse en la posición actual.");
	}

	@Test
	public void testTrayectoriaMultiplesDestinos() {
	    Mapa mapaReal = new Mapa();
	    Posicion posInicial = new Posicion(0, 0);
	    Posicion posMedio = new Posicion(5, 5);
	    Posicion posFinal = new Posicion(10, 10);
	    
	    Trayectoria trayectoria = new Trayectoria(posInicial, posFinal, mapaReal);
	    trayectoria.setDestino(posMedio);  // Agregar destino intermedio
	    
	    // Avanzar hasta el destino intermedio
	    trayectoria.avanzar();
	    assertEquals(posMedio, trayectoria.getPosicionActual(), "Debería haber avanzado al destino intermedio.");
	    
	    // Avanzar hasta el destino final
	    trayectoria.avanzar();
	    assertEquals(posFinal, trayectoria.getPosicionActual(), "Debería haber avanzado al destino final.");
	}

	@Test
	public void testAvanzarYGenerarNuevoDestinoAlChocarBorde() {
	    Mapa mapaReal = new Mapa();
	    Posicion posInicial = new Posicion(1, 1); // Cercano a un borde
	    Trayectoria trayectoria = new Trayectoria(posInicial, mapaReal);
	    
	    // Simular que se avanza hacia un borde
	    trayectoria.avanzar();
	    
	    // Verificar que al llegar al borde se genere una nueva posición aleatoria en el extremo
	    Posicion nuevaPosicion = trayectoria.getDestinoActual();
	    assertTrue(mapaReal.esPosicionExtremo(nuevaPosicion), "Debería haber generado un nuevo destino en el borde del mapa.");
	}

}
