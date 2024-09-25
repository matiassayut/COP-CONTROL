package unitTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import copControl.Mapa;
import copControl.Posicion;
import copControl.Trayectoria;

public class TrayectoriaUnitTest {

    private Trayectoria trayectoria;
    private Posicion posIni;
    private Posicion posFin;
    private Mapa mapaDeMovimiento;

    @BeforeEach
    void setUp() {
        //posIni = new Posicion(0, 0);
    	posIni = mock(Posicion.class);  // Mock de Posicion
        posFin = new Posicion(10, 10);
        mapaDeMovimiento = mock(Mapa.class);
        trayectoria = new Trayectoria(posIni, posFin, mapaDeMovimiento);
    }

    @Test
    void testInicializacion() {
        assertEquals(posIni, trayectoria.getPosicionActual(), "La posición inicial debería coincidir.");
        assertEquals(posFin, trayectoria.getDestinoActual(), "El destino inicial debería coincidir.");
    }

    @Test
    void testAvanzar() {
        Posicion nuevaPosicion = new Posicion(5, 5);

        when(posIni.getVecinoDeDistanciaMinima(posFin)).thenReturn(nuevaPosicion);

       
        trayectoria.avanzar();

        assertEquals(nuevaPosicion, trayectoria.getPosicionActual(), "La posición actual debería haber cambiado.");
        
    }


    @Test
    void testSetDestino() {
        Posicion nuevoDestino = new Posicion(20, 20);
        trayectoria.setDestino(nuevoDestino);
        assertEquals(nuevoDestino, trayectoria.getDestinoActual(), "El nuevo destino debería estar en la trayectoria.");
    }

    @Test
    void testDetener() {
        trayectoria.detener();
        assertEquals(trayectoria.getPosicionActual(), trayectoria.getDestinoActual(), "La posición actual debería ser el destino después de detenerse.");
    }

    @Test
    void testBorrarDestinos() {
        trayectoria.borrarDestinos();
        assertNull(trayectoria.getDestinoActual(), "Debería no haber destinos después de borrar.");
    }
}
