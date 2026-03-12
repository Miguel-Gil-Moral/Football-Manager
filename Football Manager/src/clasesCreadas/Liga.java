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

    public void agregarEquipos(ArrayList<Equipos> listaEquipos) {
        int i = 0;
        encuentroEquipos = new String[(CANTIDAD_EQUIPOS - 1) * CANTIDAD_EQUIPOS][2];
        equipos = new String[CANTIDAD_EQUIPOS][5];

        for (Equipos eq : listaEquipos) {
            if (i < CANTIDAD_EQUIPOS) {
                equipos[i][0] = eq.getNombre(); //Nombre equipo
                equipos[i][1] = "0"; //Puntos
                equipos[i][2] = "0"; //Partidos disputados
                equipos[i][3] = "0"; //Goles a favor
                equipos[i][4] = "0"; //Goles en contra
                i++;
            }
        }

        for (i = 0; i < CANTIDAD_EQUIPOS; i++) {
            for (int j = 0; j < CANTIDAD_EQUIPOS; j++) {
                if (i != j) {
                    encuentroEquipos[i][0] = equipos[i][0];
                    encuentroEquipos[i][1] = equipos[j][0];
                }
            }
        }
    }

    public String[][] disputarPartidos(double probabilidadGolLocal, double probabilidadGolVisitante) {
        Random random = new Random();
        resultadoPartidos = new String[encuentroEquipos.length][4];
        double probabilidadLocal, probabilidadVisitante;
        int i = 0, fila = 0;
        for (String[] partidos : encuentroEquipos) {
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
        }

        return resultadoPartidos;
    }

    public void consultarGolesFavor() {

    }

    public void consultarGolesContra() {

    }

    public void mostrarClasificacion() {

    }

    public String[][] getEquipos() {
        return equipos;
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
