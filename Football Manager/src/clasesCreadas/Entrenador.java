package clasesCreadas;

//Clase Jugadores y Entrenadores
//✅ Atributos (Los dos): nombre, apellido, día de nacimiento, nivel motivación(1-10), sueldo salarial
//✅ Atributos (Entrenador): número torneos ganados, ha estado seleccionador nacional(booleano)
//Los atributos de nombre, apellido y el día de nacimiento no se pueden modificar cuando se da de alta el jugador.
//Método entrenadores: incrementarSou(), incrementara el salario actual en un 0,5%.
public class Entrenador extends Persona {
    private int numTorneosGanados;
    private boolean seleccionadorNacional;

    public Entrenador(String nombre, String apellido, String fechaNacimiento, double nivMotivacion, int sueldoSalarial, int numTorneosGanados, boolean seleccionadorNacional) {
        super(nombre, apellido, fechaNacimiento, nivMotivacion, sueldoSalarial);
        this.numTorneosGanados = numTorneosGanados;
        this.seleccionadorNacional = seleccionadorNacional;
    }

    private void incrementarSalario() {

    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    @Override
    public String toString() {
        return "E;" + super.toString() + ";" +
                numTorneosGanados + ";" +
                seleccionadorNacional;
    }

    @Override
    public void entrenamiento() {
        if (seleccionadorNacional) {
            this.nivMotivacion += 0.30;
        } else {
            this.nivMotivacion += 0.15;
        }
    }
}
