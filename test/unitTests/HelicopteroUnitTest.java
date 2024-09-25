package unitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
import pista.Pista;
import copControl.Mapa;
import copControl.Posicion;
import avion.Helicoptero;

public class HelicopteroUnitTest {
	
	
	// clase unit test hecha por el profesor

    private Helicoptero helicoptero;
    private Posicion posIni;
    private Posicion posFin;
    private Mapa mapaDeMovimiento;
    private Pista pista;

    @BeforeEach
    void setUp() {
        posIni = new Posicion(0, 0); // Suponiendo que Posicion tiene un constructor así
        posFin = new Posicion(10, 10); // Suponiendo que Posicion tiene un constructor así
       // mapaDeMovimiento = Mockito.mock(Mapa.class);
        helicoptero = new Helicoptero(posIni, posFin, mapaDeMovimiento);
        //pista = Mockito.mock(Pista.class);
    }

    @Test
    void testEstaDetenidoInicialmente() {
        assertFalse(helicoptero.estaDetenido(), "El helicóptero debería estar en movimiento inicialmente.");
    }

    @Test
    void testDetener() {
        helicoptero.detener();
        assertTrue(helicoptero.estaDetenido(), "El helicóptero debería estar detenido después de llamar a detener().");
    }

    @Test
    void testArrancar() {
        helicoptero.detener(); // Primero detenemos el helicóptero
        helicoptero.arrancar(); // Luego lo arrancamos
        assertFalse(helicoptero.estaDetenido(), "El helicóptero debería estar en movimiento después de llamar a arrancar().");
    }
/*
    @Test
    void testMoverHacia() {
        Posicion nuevaPos = new Posicion(5, 5); // Suponiendo que Posicion tiene un constructor así
        helicoptero.moverHacia(nuevaPos);
        // Asumimos que hay un método en la trayectoria que nos permite obtener el destino
        assertEquals(nuevaPos, helicoptero.getTrayectoria().getDestino(), "El destino debería ser la nueva posición.");
    }
*/
    /*
    @Test
    void testPuedeAterrizar() {
        Mockito.when(pista.puedeAterrizar(helicoptero)).thenReturn(true);
        assertTrue(helicoptero.puedeAterrizar(pista), "El helicóptero debería poder aterrizar en la pista.");
    }
*/

    @Test
    void testAvanzar() {
        helicoptero.arrancar(); // Aseguramos que el helicóptero está en movimiento
        helicoptero.avanzar();
        // Verificamos el comportamiento esperado después de avanzar
        // Aquí asumimos que hay algún método o estado que se modifica cuando se avanza
        // Por ejemplo, verificar la posición del helicóptero o el estado de la trayectoria
        // assertEquals(expectedState, helicoptero.getTrayectoria().getEstado(), "El helicóptero debería avanzar.");
    }

}
