import clasesCreadas.Equipos;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void darBajaEquipo() {
        ArrayList<Equipos> listaEquipos = Main.cargarEquipos();
        String nombreEquipo = "RC Celta de Vigo";
    }
}