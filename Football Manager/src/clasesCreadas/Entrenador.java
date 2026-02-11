package clasesCreadas;

import java.time.LocalDate;

//Clase Jugadores y Entrenadores
//Atributos (Los dos): nombre, apellido, día de nacimiento, nivel motivación(1-10), sueldo salarial
//Atributos (Entrenador): número torneos ganados, ha estado seleccionador nacional(booleano)
//Los atributos de nombre, apellido y el día de nacimiento no se pueden modificar cuando se da de alta el jugador.
//Método entrenadores: incrementarSou(), incrementara el salario actual en un 0,5%.
public class Entrenador extends Persona {
    protected String nombre, apellido;
    protected LocalDate fechaNacimiento;
    protected double nivMotivacion, sueldoSalarial;
    private int numTorneosGanados;
    private boolean seleccionadorNacional;

    public Entrenador() {
    }

    private void incrementarSalario() {

    }

    @Override
    protected void entrenamiento() {
        if (seleccionadorNacional) {
            this.nivMotivacion += 0.30;
        } else {
            this.nivMotivacion += 0.15;
        }
    }
}
