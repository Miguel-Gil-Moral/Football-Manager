import clasesCreadas.Equipos;
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
        ArrayList<Equipos> listaEquipos = Main.cargarEquipos();
        String nombreEquipo = "RC Celta de Vigo";

        assertTrue(Main.revisarEquipo(listaEquipos, nombreEquipo));
    }

    @Test
    void mostrarClasificacion() {
        String[][] mediaEquipo = {
                {"FC Barcelona", "50", "5"},
                {"Real Madrid CF", "50", "5"}
        };

        String[][] esperado = {
                {"FC Barcelona", "0.03"},
                {"Real Madrid CF", "0.03"}
        };

        assertArrayEquals(esperado, Main.aumentarProbabilidadGol(mediaEquipo));
    }

    @Test
    public void equiposNombrePresidente() {
        Equipos equipo = new Equipos("FC Barcelona", 1899, "Barcelona", "Camp Nou", "Joan Laporta");

        assertEquals("Joan Laporta", equipo.getNombrePresidente());
    }
}