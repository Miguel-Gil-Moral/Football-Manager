import clasesCreadas.Equipos;
import clasesCreadas.Liga;
import clasesCreadas.Persona;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

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

        Scanner sc = new Scanner(System.in);

        assertEquals("POR", Main.asignarPosicionJugador(sc));
    }

    @Test
    void darAltaPersona() {
        String insertarScanner = "1\nMiguel\nGil\n17\n02\n2005\n10000\n6\n3\n";
        System.setIn(new ByteArrayInputStream(insertarScanner.getBytes()));
        ArrayList<Persona> listaFichajes2 = Main.cargarFichajes();
        ArrayList<Persona> listaFichajes = new ArrayList<>(listaFichajes2);

        Main.darAltaPersona(listaFichajes2);

        assertNotEquals(listaFichajes, listaFichajes2);
    }

    @Test
    void DarBajaEquipos() {
        ArrayList<Equipos> temporal = Main.cargarEquipos();
        String nombreEquipo = "RC Celta de Vigo";
        String insertarScanner = "True\n";
        System.setIn(new ByteArrayInputStream(insertarScanner.getBytes()));
        ArrayList<Equipos> listaEquipos2 = new ArrayList<>(temporal);
        ArrayList<Equipos> listaEquipos = new ArrayList<>(listaEquipos2);
        //ASSERT
    }

    @Test
    void aumentarProbabilidadGol() {
        String[][] mediaEquipo = {{"FC Barcelona", "0", "0"}, {"Real Madrid CF", "0.03", "0"}}; //PONER TODOS
        assertArrayEquals(mediaEquipo, Main.aumentarProbabilidadGol(mediaEquipo), "Los arrays son iguales");
    }
}