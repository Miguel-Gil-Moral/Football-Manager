package clasesCreadas;

//Clase Jugadores y Entrenadores
//✅ Atributos (Los dos): nombre, apellido, día de nacimiento, nivel motivación(1-10), sueldo salarial
//✅ Atributos (Entrenador): número torneos ganados, ha estado seleccionador nacional(booleano)
//✅ Los atributos de nombre, apellido y el día de nacimiento no se pueden modificar cuando se da de alta el jugador.
public class Entrenador extends Persona {
    private int numTorneosGanados;
    private boolean seleccionadorNacional;

    public Entrenador(String nombre, String apellido, String fechaNacimiento, double nivMotivacion, int sueldoSalarial, int numTorneosGanados, boolean seleccionadorNacional) {
        super(nombre, apellido, fechaNacimiento, nivMotivacion, sueldoSalarial);
        this.numTorneosGanados = numTorneosGanados;
        this.seleccionadorNacional = seleccionadorNacional;
    }

    //✅ Método entrenadores: incrementarSou(), incrementara el salario actual en un 0,5%.
    public void incrementarSalario() {
        sueldoSalarial += (int) (sueldoSalarial * 1.05);
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getNumTorneosGanados() {
        return numTorneosGanados;
    }

    public boolean isSeleccionadorNacional() {
        return seleccionadorNacional;
    }

    @Override
    public String toString() {
        return "E;" + super.toString() + ";" +
                numTorneosGanados + ";" +
                seleccionadorNacional;
    }

    //✅ Los entrenadores sobreescribirán completamente el método entrenament() de la clase padre.
    //✅ Si el entrenador es seleccionador nacional aumentará la motivación a 0.3 puntos, si no lo es lo hará a 0.15.
    @Override
    public void entrenamiento() {
        if (seleccionadorNacional) {
            nivMotivacion += 0.30;
        } else {
            nivMotivacion += 0.15;
        }
    }
}