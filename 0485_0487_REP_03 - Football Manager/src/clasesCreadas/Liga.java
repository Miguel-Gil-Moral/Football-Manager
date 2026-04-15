package clasesCreadas;

import java.util.*;

/**
 * Representa una liga con sus equipos, emparejamientos, resultados y
 * registro temporal de goles.
 */
public class Liga {
    private final String NOMBRE;
    private final int CANTIDAD_EQUIPOS;
    private String[][] equipos;
    private String[][] encuentroEquipos, resultadoPartidos;
    private ArrayList<String> tiempoGol = new ArrayList<>();

    /**
     * Crea una liga con nombre y cantidad fija de equipos.
     *
     * @param nombre nombre de la liga
     * @param cantidadEquipos cantidad de equipos participantes
     */
    public Liga(String nombre, int cantidadEquipos) {
        this.NOMBRE = nombre;
        this.CANTIDAD_EQUIPOS = cantidadEquipos;
    }

    /**
     * Agrega equipos a la liga e inicializa sus estadisticas base.
     *
     * @param listaEquipos lista de equipos candidatos a participar
     * @return matriz base de equipos de la liga
     */
    public String[][] agregarEquipos(ArrayList<Equipos> listaEquipos) {
        int i = 0;
        encuentroEquipos = new String[(CANTIDAD_EQUIPOS - 1) * CANTIDAD_EQUIPOS][2];
        equipos = new String[CANTIDAD_EQUIPOS][5];

        for (Equipos eq : listaEquipos) {
            if (i < CANTIDAD_EQUIPOS) {
                equipos[i][0] = eq.getNOMBRE(); //Nombre equipo
                equipos[i][1] = "0"; //Puntos
                equipos[i][2] = "0"; //Partidos disputados
                equipos[i][3] = "0"; //Goles a favor
                equipos[i][4] = "0"; //Goles en contra
                i++;
            }
        }

        int fila = 0;
        for (i = 0; i < CANTIDAD_EQUIPOS; i++) {
            for (int j = 0; j < CANTIDAD_EQUIPOS; j++) {
                if (i != j) {
                    encuentroEquipos[fila][0] = equipos[i][0];
                    encuentroEquipos[fila][1] = equipos[j][0];
                    fila++;
                }
            }
        }
        return equipos;
    }

    /**
     * Disputa los partidos programados de la liga.
     *
     * @param probabilidadesEquipos probabilidades de gol por equipo
     * @return matriz con los resultados de los partidos disputados
     */
    public String[][] disputarPartidos(String[][] probabilidadesEquipos) {
        Random random = new Random();
        resultadoPartidos = new String[encuentroEquipos.length][4];
        double probabilidadLocal, probabilidadVisitante, probabilidadGolLocal, probabilidadGolVisitante;
        int i = 0, fila = 0, posicionEquipoLocal = 0, posicionEquipoVisitante = 1;

        System.out.println("Disputando partidos...");
        for (String[] partidos : encuentroEquipos) {
            if (posicionEquipoLocal == posicionEquipoVisitante) {
                posicionEquipoVisitante++;
            }
            probabilidadGolLocal = Double.parseDouble(probabilidadesEquipos[posicionEquipoLocal][1]);
            probabilidadGolVisitante = Double.parseDouble(probabilidadesEquipos[posicionEquipoVisitante][1]);
            int golesLocal = 0, golesVisitante = 0;
            for (int minuto = 0; minuto < 90; minuto++) {
                for (int segundo = 0; segundo < 60; segundo++) {
                    if (i > 10) {
                        probabilidadLocal = random.nextDouble(100 + 1);
                        probabilidadVisitante = random.nextDouble(100 + 1);
                        if (probabilidadLocal <= probabilidadGolLocal) {
                            golesLocal += 1;
                            tiempoGol.add(String.format(partidos[0] + ";" + partidos[1] + ";" + minuto + ":" + segundo + ";" + partidos[0])); //Partido jugado, minuto del gol, equipo goleador
                        } else if (probabilidadVisitante <= probabilidadGolVisitante) {
                            golesVisitante += 1;
                            tiempoGol.add(String.format(partidos[0] + ";" + partidos[1] + ";" + minuto + ":" + segundo + ";" + partidos[1]));
                        }
                    }
                    i++;
                }
            }
            resultadoPartidos[fila][0] = partidos[0]; //Equipo Local
            resultadoPartidos[fila][1] = String.valueOf(golesLocal);
            resultadoPartidos[fila][2] = String.valueOf(golesVisitante);
            resultadoPartidos[fila][3] = partidos[1]; //Equipo Visitante
            fila++;
            if (posicionEquipoVisitante < this.CANTIDAD_EQUIPOS - 1) {
                posicionEquipoVisitante++;
            } else {
                posicionEquipoVisitante = 0;
                posicionEquipoLocal++;
            }
        }

        return resultadoPartidos;
    }

    /**
     * Muestra por pantalla los goles a favor de cada equipo.
     */
    public void consultarGolesFavor() {
        System.out.println("Equipo - Goles a Favor");
        for (String[] eq : equipos) {
            System.out.println(eq[0] + " " + eq[3]);
        }
    }

    /**
     * Muestra por pantalla los goles en contra de cada equipo.
     */
    public void consultarGolesContra() {
        System.out.println("Equipo - Goles en Contra");
        for (String[] eq : equipos) {
            System.out.println(eq[0] + " " + eq[4]);
        }
    }

    /**
     * Muestra la clasificacion actual de la liga.
     */

    public void mostrarClasificacion() {
        List<String[]> clasificacion = new ArrayList<>(Arrays.asList(equipos));

        clasificacion.sort(new ComparatorPuntos());
        Collections.reverse(clasificacion);

        System.out.println("Mostrar Clasificación:");
        System.out.printf("%-1s %-10s %-1s %-25s %-1s %-19s %-1s %-19s %-1s %-17s %-1s %-17s %-1s \n",
                "|", "Posición", "|", "Equipo", "|", "Puntos", "|", "Partidos disputados", "|", "Goles a favor", "|", "Goles en contra", "|");

        int posicion = 1;
        for (String[] cl : clasificacion) {
            System.out.printf("%-1s %-10s %-1s %-25s %-1s %-19s %-1s %-19s %-1s %-17s %-1s %-17s %-1s \n",
                    "|", (posicion + "º"), "|", cl[0], "|", cl[1], "|", cl[2], "|", cl[3], "|", cl[4], "|");
            posicion++;
        }
    }

    /**
     * Compara dos equipos de una clasificación según sus puntos y, en caso de
     * empate, según los goles a favor.
     */
    public static class ComparatorPuntos implements Comparator<String[]> {
        /**
         * Compara dos filas de la clasificación.
         *
         * @param p1 fila del primer equipo
         * @param p2 fila del segundo equipo
         * @return valor negativo, cero o positivo según el orden relativo
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

    /**
     * Sustituye la matriz interna de equipos de la liga.
     *
     * @param equipos nueva matriz de equipos y estadisticas
     */
    public void setEquipos(String[][] equipos) {
        this.equipos = equipos;
    }

    /**
     * Devuelve la cantidad de equipos de la liga.
     *
     * @return cantidad de equipos de la liga
     */
    public int getCANTIDAD_EQUIPOS() {
        return CANTIDAD_EQUIPOS;
    }

    /**
     * Devuelve el nombre de la liga.
     *
     * @return nombre de la liga
     */
    public String getNOMBRE() {
        return NOMBRE;
    }

    /**
     * Devuelve el registro temporal de goles.
     *
     * @return lista con el detalle temporal de los goles
     */
    public ArrayList<String> getTiempoGol() {
        return tiempoGol;
    }

    /**
     * Devuelve la representacion textual de la liga.
     *
     * @return cadena con los datos de la liga
     */
    @Override
    public String toString() {
        return NOMBRE + ";" +
                CANTIDAD_EQUIPOS + "\n";
    }
}
