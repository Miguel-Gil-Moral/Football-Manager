package clasesCreadas;

import java.util.ArrayList;
import java.util.Random;

public class Partido {
    private String equipoLocal, equipoVisitante, nombreLiga;
    private int golesLocal, golesVisitante;
    private ArrayList<String> tiempoGolesMarcados = new ArrayList<>();
    private int puntosLocal, puntosVisitante;
    private double probabilidadGolLocal = 0.05, probabilidadGolVisitante = 0.05;

    public Partido(String nombreLiga, String equipoLocal, String equipoVisitante) {

    }

    public void aumentarProbabilidadGol(int mediaMotivacionLocal, int mediaMotivacionVisitante) {
        if (mediaMotivacionLocal <= 100 && mediaMotivacionLocal > 66) {
            probabilidadGolLocal = 0.07;
        } else if (mediaMotivacionLocal <= 33 && mediaMotivacionLocal >= 0) {
            probabilidadGolLocal = 0.03;
        }
        if (mediaMotivacionVisitante <= 100 && mediaMotivacionVisitante > 66) {
            probabilidadGolVisitante = 0.07;
        } else if (mediaMotivacionVisitante <= 33 && mediaMotivacionVisitante >= 0) {
            probabilidadGolVisitante = 0.03;
        }
    }

    public void iaPartidos() {
        Random random = new Random();
        double probabilidadLocal, probabilidadVisitante;
        int i = 0;

        for (int minuto = 0; minuto < 90; minuto++){
            for (int segundo = 0; segundo < 60; segundo++){
                if (i > 10){
                    probabilidadLocal = random.nextDouble(100 + 1);
                    probabilidadVisitante = random.nextDouble(100 + 1);
                    if (probabilidadLocal <= probabilidadGolLocal) {
                        golesLocal += 1;
                        tiempoGolesMarcados.add(String.valueOf(minuto + " : " + segundo));
                    } else if (probabilidadVisitante <= probabilidadGolVisitante) {
                        golesVisitante += 1;
                        tiempoGolesMarcados.add(String.valueOf(minuto + " : " + segundo));
                    }
                }
                i++;
            }
        }
    }

    public void darPuntos() {
        if (golesLocal > golesVisitante) {
            puntosLocal += 3;
        } else if (golesLocal < golesVisitante) {
            puntosVisitante += 3;
        } else {
            puntosLocal += 1;
            puntosVisitante += 1;
        }
    }

    @Override
    public String toString() {
        return nombreLiga + ";" +
                equipoLocal + ";" +
                golesLocal + ";" +
                equipoVisitante + ";" +
                golesVisitante + ";" +
                puntosLocal + ";" +
                puntosVisitante;
    }
}