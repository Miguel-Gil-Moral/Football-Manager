package clasesCreadas;

import java.util.Comparator;

public class ComparatorPuntos implements Comparator<String[]> {

    @Override
    public int compare(String[] p1, String[] p2) {
        int puntos = Integer.parseInt(p1[1]) - Integer.parseInt(p2[1]);

        if (puntos != 0) {
            return puntos;
        }

        return Integer.parseInt(p1[3]) - Integer.parseInt(p2[3]);
    }
}
