package clasesCreadas;

import java.util.Comparator;

/**
 * Compara dos equipos de una clasificacion según sus puntos y, en caso de
 * empate, según los goles a favor.
 */
public class ComparatorPuntos implements Comparator<String[]> {

    /**
     * Compara dos filas de la clasificacion.
     *
     * @param p1 fila del primer equipo
     * @param p2 fila del segundo equipo
     * @return valor negativo, cero o positivo segun el orden relativo
     */
    @Override
    public int compare(String[] p1, String[] p2) {
        int puntos = Integer.parseInt(p1[1]) - Integer.parseInt(p2[1]);

        if (puntos != 0) {
            return puntos;
        }

        return Integer.parseInt(p1[3]) - Integer.parseInt(p2[3]);
    }
}
