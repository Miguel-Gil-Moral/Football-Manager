package clasesCreadas;

import java.util.*;

//Clase Lliga:
//✅ Atributos: Nombre, cantidad de equipos, lista de equipos.
//✅ Métodos: agregarEquipos(), disputarPartidos(), consultarGolesFavor(), consultarGolesContra(), mostrarClasificación.
/**
 * Representa una liga con sus equipos, emparejamientos, resultados y
 * registro temporal de goles.
 */
public class Liga {
    private final String NOMBRE;
    private final int CANTIDAD_EQUIPOS;
    private Object[][] equipos, resultadoPartidos;
    private String[][] encuentroEquipos;
    private final ArrayList<String> tiempoGol = new ArrayList<>();

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
     */
    public void agregarEquipos(ArrayList<Equipos> listaEquipos) {
        int i = 0;
        encuentroEquipos = new String[(CANTIDAD_EQUIPOS - 1) * CANTIDAD_EQUIPOS][2];
        equipos = new String[CANTIDAD_EQUIPOS][5];

        for (Equipos eq : listaEquipos) {
            if (i < CANTIDAD_EQUIPOS) {
                equipos[i][0] = eq.getNOMBRE(); //Nombre equipo
                equipos[i][1] = 0; //Puntos
                equipos[i][2] = 0; //Partidos disputados
                equipos[i][3] = 0; //Goles a favor
                equipos[i][4] = 0; //Goles en contra
                i++;
            }
        }

        int fila = 0;
        for (i = 0; i < CANTIDAD_EQUIPOS; i++) {
            for (int j = 0; j < CANTIDAD_EQUIPOS; j++) {
                if (i != j) {
                    encuentroEquipos[fila][0] = (String)equipos[i][0];
                    encuentroEquipos[fila][1] = (String)equipos[i][0];
                    fila++;
                }
            }
        }
    }

    /**
     * Disputa los partidos programados de la liga.
     *
     * @param probabilidadesEquipos probabilidades de gol por equipo
     * @return matriz con los resultados de los partidos disputados
     */
    public Object[][] disputarPartidos(Object[][] probabilidadesEquipos) {
        Random random = new Random();
        resultadoPartidos = new String[encuentroEquipos.length][4];
        double probabilidadLocal, probabilidadVisitante, probabilidadGolLocal, probabilidadGolVisitante;
        int i = 0, fila = 0, posicionEquipoLocal = 0, posicionEquipoVisitante = 1;

        System.out.println("Disputando partidos...");
        for (String[] partidos : encuentroEquipos) {
            if (posicionEquipoLocal == posicionEquipoVisitante) {
                posicionEquipoVisitante++;
            }
            probabilidadGolLocal = (double)probabilidadesEquipos[posicionEquipoLocal][1];
            probabilidadGolVisitante = (double) probabilidadesEquipos[posicionEquipoVisitante][1];
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
        for (Object[] eq : equipos) {
            System.out.println(eq[0] + " " + eq[3]);
        }
    }

    /**
     * Muestra por pantalla los goles en contra de cada equipo.
     */
    public void consultarGolesContra() {
        System.out.println("Equipo - Goles en Contra");
        for (Object[] eq : equipos) {
            System.out.println(eq[0] + " " + eq[4]);
        }
    }

    /**
     * Muestra la clasificacion actual de la liga.
     */

    public void mostrarClasificacion() { //Revisar los parámetros que se usan realmente.
        //Mostrará la clasificación de la liga actual, mostrará el nombre del equipo, puntos, partidos, goles a favor y goles en contra.
        //Haz que cuando la lista de ligas esté vacía, vaya directamente a la opción de disputarNuevaLiga, si hay una no hace falta que vaya a ese método.

        List<Object[]> clasificacion = new ArrayList<>(Arrays.asList(equipos));

        clasificacion.sort(new ComparatorPuntos());
        Collections.reverse(clasificacion);

        System.out.println("Mostrar Clasificación:");
        System.out.printf("%-1s %-19s %-1s %-19s %-1s %-19s %-1s %-19s %-1s %-19s %-1s \n",
                "|", "Equipo", "|", "Puntos", "|", "Partidos disputados", "|", "Goles a favor", "|", "Goles en contra", "|");

        for (Object[] cl : clasificacion) {
            System.out.printf("%-1s %-19s %-1s %-19s %-1s %-19s %-1s %-19s %-1s %-19s %-1s \n",
                    "|", cl[0], "|", cl[1], "|", cl[2], "|", cl[3], "|", cl[4], "|");
            //ORDENARLO CON COLLECTIONS
        }

            //Debe de estar ordenada por puntos y en caso de tener mismos puntos, diferencia entre goles a favor y en contra.
            //Cuando haya una liga, haz opciones para que el usuario elija si quiere ver los goles a favor, en contra, toda la clasificación, e incluso el tiempo donde marcaron los goles

            //printf
            //mostrar todos los equipos y sus puntos

    }

    /**
     * Compara dos equipos de una clasificacion segun sus puntos y, en caso de
     * empate, segun los goles a favor.
     */
    public static class ComparatorPuntos implements Comparator<Object[]> {

        /**
         * Compara dos filas de la clasificacion.
         *
         * @param p1 fila del primer equipo
         * @param p2 fila del segundo equipo
         * @return valor negativo, cero o positivo segun el orden relativo
         */
        @Override
        public int compare(Object[] p1, Object[] p2) {
            int puntos = (int)p1[1] - (int)p2[1];

            if (puntos != 0) {
                return puntos;
            }

            return (int)p1[3] - (int)p2[3];
        }
    }

    /**
     * Actualiza puntos y estadisticas de los equipos tras disputar los partidos.
     *
     * @param resultadoPartidos resultados obtenidos en la jornada
     * @param CANTIDAD_EQUIPOS cantidad de equipos participantes
     * @param equipos matriz de equipos con sus estadisticas
     */
    public void actualizarPuntuacion(Object[][] resultadoPartidos, int CANTIDAD_EQUIPOS, Object[][] equipos) {
        System.out.println("Otorgando puntos...");
        for (Object[] rs : resultadoPartidos) {
            for (int i = 0; i < CANTIDAD_EQUIPOS; i++) {
                int puntosLocal = 0, puntosVisitante = 0;
                int golesLocal = (int)rs[1];
                int golesVisitante = (int)rs[2];
                if (golesLocal > golesVisitante) {
                    puntosLocal += 3;
                } else if (golesLocal < golesVisitante) {
                    puntosVisitante += 3;
                } else {
                    puntosLocal += 1;
                    puntosVisitante += 1;
                }
                otorgarPuntos(equipos, rs, i, puntosLocal, golesLocal, golesVisitante, puntosVisitante);
            }
        }
    }

    /**
     * Prepara los datos necesarios para disputar los partidos de una liga.
     *
     * @since 1.0
     * @param listaEquipos Lista con todos los equipos que participaran
     */
    public void prepararPartidos(ArrayList<Equipos> listaEquipos) {
        agregarEquipos(listaEquipos);

        Object[][] mediaEquipo = new Object[CANTIDAD_EQUIPOS][3];

        int posicionNombreEquipo = 0;
        for (Equipos eq : listaEquipos) {
            if (posicionNombreEquipo < CANTIDAD_EQUIPOS) {
                mediaEquipo[posicionNombreEquipo][0] = eq.getNOMBRE();
                mediaEquipo[posicionNombreEquipo][1] = eq.calcularMediaCalidad();
                mediaEquipo[posicionNombreEquipo][2] = eq.calcularMediaMotivacion();
                posicionNombreEquipo++;
            }
        }
        Object[][] proabilidadGol = aumentarProbabilidadGol(mediaEquipo);

        resultadoPartidos = disputarPartidos(proabilidadGol);

        actualizarPuntuacion(resultadoPartidos, CANTIDAD_EQUIPOS, equipos);

        setEquipos(equipos);
    }

    /**
     * Genera la probabilidad de gol de cada equipo a partir de sus medias.
     *
     * @return matriz con equipos y su probabilidad de gol
     */
    public Object[][] aumentarProbabilidadGol(Object[][] mediaEquipo) {
        int fila = 0;
        Object[][] probabilidadesEquipos = new Object[mediaEquipo.length][2];
        for (Object[] str : mediaEquipo) {
            double probabilidadGol = 0.03;
            if (fila < mediaEquipo.length) {
                probabilidadesEquipos[fila][0] = str[0];
                double mediaCalidad = (double)str[1];
                if (mediaCalidad <= 100 && mediaCalidad > 60) {
                    probabilidadGol += 0.0025;
                } else if (mediaCalidad <= 30 && mediaCalidad >= 0) {
                    probabilidadGol -= 0.0025;
                }
                double mediaMotivacion = (double)str[2];
                if (mediaMotivacion <= 10 && mediaMotivacion > 6) {
                    probabilidadGol += 0.0025;
                } else if (mediaMotivacion <= 3 && mediaMotivacion >= 0) {
                    probabilidadGol -= 0.0025;
                }
                probabilidadesEquipos[fila][1] = String.valueOf(probabilidadGol);
                fila++;
            }
        }
        return probabilidadesEquipos;
    }

    /**
     * Aplica a un equipo concreto los puntos y estadisticas derivados de un partido.
     *
     * @since 1.0
     * @param equipos El equipo asignado con sus puntos, partidos disputados, goles a favor y en contra
     * @param rs El resultado del partido el cual quedaron los equipos
     * @param i El numero de la iteracion para la posicion del array equipos
     * @param puntosLocal Puntos totales que lleva el equipo local
     * @param golesLocal Goles marcados durante el partido por parte del equipo local
     * @param golesVisitante Goles marcados durante el partido por parte del equipo visitante
     * @param puntosVisitante Puntos totales que lleva el equipo visitante
     */
    public void otorgarPuntos(Object[][] equipos, Object[] rs, int i, int puntosLocal, int golesLocal, int golesVisitante, int puntosVisitante) {
        int puntos = (int)equipos[i][1];
        int partidosDisputados = (int)equipos[i][2];
        int golesFavor = (int)equipos[i][3];
        int golesContra = (int)equipos[i][4];
        if (rs[0].equals(equipos[i][0])) {
            partidosDisputados++;
            puntos += puntosLocal;
            golesFavor += golesLocal;
            golesContra += golesVisitante;
        } else if (rs[3].equals(equipos[i][0])) {
            partidosDisputados++;
            puntos += puntosVisitante;
            golesFavor += golesVisitante;
            golesContra += golesLocal;
        }
        equipos[i][1] = puntos;
        equipos[i][2] = partidosDisputados;
        equipos[i][3] = golesFavor;
        equipos[i][4] = golesContra;
    }

    /**
     * Muestra el detalle temporal de los goles registrados en una liga.
     */
    public void verTiempoGol() {
        System.out.println("Mostrar Tiempo Gol:");
        System.out.printf("%-1s %-15s %-1s %-15s %-1s %-15s %-1s \n",
                "|", "Partido jugado", "|", "Minuto del gol", "|", "Equipo goleador", "|");

        //for (Liga l : tiempoGol()) {}
        for (String tg : tiempoGol) {
            String[] separado =  tg.split(";");
            System.out.printf("%-1s %-18s %-1s %-18s %-1s %-18s %-1s %-18s %-1s \n",
                    "|", separado[0], "|", separado[1], "|", separado[2], "|", separado[3], "|");
        }
    }

    /**
     * Sustituye la matriz interna de equipos de la liga.
     *
     * @param equipos nueva matriz de equipos y estadisticas
     */
    public void setEquipos(Object[][] equipos) {
        this.equipos = equipos;
    }

    /**
     * Devuelve el nombre de la liga.
     *
     * @return nombre de la liga
     */
    public String getNOMBRE() {
        return NOMBRE;
    }
}
