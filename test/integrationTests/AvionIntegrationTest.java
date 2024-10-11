package integrationTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import avion.AvionPesado;
import avion.AvionSimple;
import copControl.Mapa;
import copControl.Posicion;
import pista.PistaLarga;
import pista.PistaSimple;
import pista.PosicionesEntradaVaciaException;

class AvionIntegrationTest {
    private Mapa mapa = new Mapa();

    @Test
    void testAvanzarAvionSimpleHaciaUnaPosicion() {
    	//Se testea la clase AvionSimple con la clase Posicion
    	Posicion posicionInicial = new Posicion(10, 10);
        Posicion posicionFinal = new Posicion(50, 50);
    	AvionSimple avion = new AvionSimple(posicionInicial, posicionFinal, mapa);
        avion.avanzar();
        Posicion nuevaPosicion = avion.getPosicionActual();
        assertNotEquals(posicionInicial, nuevaPosicion, "El avion no cambio de posicion");
    }
    
    @Test
    void testAvionPesadoNoPuedeAterrizarEnPistaSimple() throws PosicionesEntradaVaciaException {
    	//Se testea la clase AvionPesado con la clase PistaSimple
    	Posicion posicionInicial = new Posicion(10, 10);
        Posicion posicionFinal = new Posicion(50, 50);
    	AvionPesado avion = new AvionPesado(posicionInicial, posicionFinal, mapa);
        PistaSimple pista = new PistaSimple(posicionFinal);
        
        assertFalse(avion.puedeAterrizar(pista), "El avion Pesado pudo aterrizar en la pista simple");
    }
    
    @Test
    void testAvionPesadoPuedeAterrizarEnPistaLarga() throws PosicionesEntradaVaciaException {
    	//Se testea la clase AvionPesado con la clase PistaLarga
    	Posicion posicionInicial = new Posicion(10, 10);
        Posicion posicionFinal = new Posicion(50, 50);
    	AvionPesado avion = new AvionPesado(posicionInicial, posicionFinal, mapa);
        PistaLarga pista = new PistaLarga(posicionFinal);
        
        assertTrue(avion.puedeAterrizar(pista), "El avion Pesado no pudo aterrizar en la pista larga");
    }
    
    @Test
    public void testAvionSimpleNoPuedeAterrizarAntesDeLlegarADestino() throws PosicionesEntradaVaciaException {
    	//Se testea AvionSimple, Mapa y Posicion.
        Posicion posicionInicial = new Posicion(10, 10);
        Posicion posicionFinal = new Posicion(50, 50);
        PistaSimple pistaSimple = new PistaSimple(new Posicion(50, 50));

        Mapa mapaDeJuego = new Mapa();
        AvionSimple avion = new AvionSimple(posicionInicial, posicionFinal, mapaDeJuego);

        avion.avanzar();
        assertFalse(avion.aterrizar(pistaSimple), "El avion ha aterrizado antes de llegar a destino");
    }
    
    @Test
    public void testGeneracionPosicionesExtremosAleatorios() {
        //generar dos posiciones extremas al azar
        List<Posicion> posicionesExtremos = mapa.getPosicionesExtremos();

        //las posiciones deben estar en los extremos del mapa
        assertEquals(2, posicionesExtremos.size());
        assertTrue(mapa.esPosicionExtremo(posicionesExtremos.get(0)), "La posicion 1 no esta en el extremo");
        assertTrue(mapa.esPosicionExtremo(posicionesExtremos.get(1)), "La posicion 2 no esta en el extremo");
    }
    
}

