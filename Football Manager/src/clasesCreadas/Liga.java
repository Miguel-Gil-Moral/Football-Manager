package clasesCreadas;

import java.util.ArrayList;
import java.util.Random;

//Clase Lliga:
//✅ Atributos: Nombre, cantidad de equipos, lista de equipos.
//✅ Métodos: agregarEquipos(), disputarPartidos(), consultarGolesFavor(), consultarGolesContra(), mostrarClasificación.
public class Liga {
    private final String NOMBRE;
    private final int CANTIDAD_EQUIPOS;
    private String[][] equipos;
    private String[][] encuentroEquipos, resultadoPartidos;
    private ArrayList<String> tiempoGol = new ArrayList<>();

    public Liga(String nombre, int cantidadEquipos) {
        this.NOMBRE = nombre;
        this.CANTIDAD_EQUIPOS = cantidadEquipos;
    }

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

    public void consultarGolesFavor() {

    }

    public void consultarGolesContra() {

    }

    public void mostrarClasificacion() { //Revisar los párametros que se usan realmete.
        //Mostrará la clasificación de la liga actual, mostrará el nombre del equipo, puntos, partidos, goles a favor y goles en contra.
        //Haz que cuando la lista de ligas esté vacía, vaya directamente a la opción de disputarNuevaLiga, si hay una no hace falta que vaya a ese método.
        System.out.println("Mostrar Clasificación:");
        System.out.printf("%-1s %-16s %-1s %-16s %-1s %-16s %-1s %-16s %-1s \n",
                "|", "Equipo Local", "|", "golesLocal", "|", "golesVisitante", "|", "Equipo Visitante", "|");
        for (String[] eq : equipos) {
            int fila = 0;
            System.out.printf("%-1s %-16s %-1s %-16s %-1s %-16s %-1s %-16s %-1s \n",
                    "|", equipos[fila][0], "|", equipos[fila][1], "|", equipos[fila][2], "|", equipos[fila][3], "|");
        fila++;
        }

            //Debe de estar ordenada por puntos y en caso de tener mismos puntos, diferencia entre goles a favor y en contra.
            //Cuando haya una liga, haz opciones para que el usuario elija si quiere ver los goles a favor, en contra, toda la clasificación, e incluso el tiempo donde marcaron los goles

            //printf
            //mostrar todos los equipos y sus puntos

    }

    public void setEquipos(String[][] equipos) {
        this.equipos = equipos;
    }

    public int getCANTIDAD_EQUIPOS() {
        return CANTIDAD_EQUIPOS;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    @Override
    public String toString() {
        return NOMBRE + ";" +
                CANTIDAD_EQUIPOS + "\n";
    }
}
