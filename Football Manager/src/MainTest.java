import clasesCreadas.Equipos;
import clasesCreadas.Persona;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void revisarEquipo() {
        ArrayList<Equipos> listaEquipos = Main.cargarEquipos();
        String nombreEquipo = "RC Celta de Vigo";

        assertTrue(Main.revisarEquipo(listaEquipos, nombreEquipo));
    }

    @Test
    void asignarPosicionJugador() {
        String numero = "1\n";
        System.setIn(new ByteArrayInputStream(numero.getBytes()));

        assertEquals("POR", Main.asignarPosicionJugador());
    }

    @Test
    void darAltaPersona() {
        String insertarScanner = "1\nMiguel\nGil\n17\n02\n2005\n10000\n6\n3\n";
        System.setIn(new ByteArrayInputStream(insertarScanner.getBytes()));
        ArrayList<Persona> listaFichajes = Main.cargarFichajes();
        ArrayList<Persona> listaFichajes2 = Main.darAltaPersona(listaFichajes);

        assertNotEquals(listaFichajes, listaFichajes2);
    }
}