package clasesCreadas;

//Clase Jugadores y Entrenadores
//✅ Atributos (Los dos): nombre, apellido, día de nacimiento, nivel motivación(1-10), sueldo salarial
//✅ Atributos (Entrenador): número torneos ganados, ha estado seleccionador nacional(booleano)
//✅ Los atributos de nombre, apellido y el día de nacimiento no se pueden modificar cuando se da de alta el jugador.
/**
 * Representa a un entrenador con experiencia competitiva y posible
 * trayectoria como seleccionador nacional.
 */
public class Entrenador extends Persona {
    private int numTorneosGanados;
    private boolean seleccionadorNacional;

    /**
     * Crea un entrenador con sus datos personales y profesionales.
     *
     * @param nombre nombre del entrenador
     * @param apellido apellido del entrenador
     * @param fechaNacimiento fecha de nacimiento del entrenador
     * @param nivMotivacion nivel de motivacion inicial
     * @param sueldoSalarial sueldo salarial inicial
     * @param numTorneosGanados numero de torneos ganados
     * @param seleccionadorNacional indica si ha sido seleccionador nacional
     */
    public Entrenador(String nombre, String apellido, String fechaNacimiento, double nivMotivacion, int sueldoSalarial, int numTorneosGanados, boolean seleccionadorNacional) {
        super(nombre, apellido, fechaNacimiento, nivMotivacion, sueldoSalarial);
        this.numTorneosGanados = numTorneosGanados;
        this.seleccionadorNacional = seleccionadorNacional;
    }

    //✅ Método entrenadores: incrementarSou(), incrementara el salario actual en un 0,5%.
    /**
     * Incrementa el salario actual del entrenador.
     */
    public void incrementarSalario() {
        sueldoSalarial += (int) (sueldoSalarial * 1.05);
    }

    /**
     * Asigna el equipo actual del entrenador.
     *
     * @param nombreEquipo nombre del equipo asignado
     */
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    /**
     * Devuelve el numero de torneos ganados.
     *
     * @return numero de torneos ganados
     */
    public int getNumTorneosGanados() {
        return numTorneosGanados;
    }

    /**
     * Indica si el entrenador ha sido seleccionador nacional.
     *
     * @return true si ha sido seleccionador nacional
     */
    public boolean isSeleccionadorNacional() {
        return seleccionadorNacional;
    }

    /**
     * Devuelve la representacion textual del entrenador.
     *
     * @return cadena con los datos del entrenador
     */
    @Override
    public String toString() {
        return "E;" + super.toString() + ";" +
                numTorneosGanados + ";" +
                seleccionadorNacional;
    }

    //✅ Los entrenadores sobreescribirán completamente el método entrenament() de la clase padre.
    //✅ Si el entrenador es seleccionador nacional aumentará la motivación a 0.3 puntos, si no lo es lo hará a 0.15.
    /**
     * Ejecuta el entrenamiento del entrenador actualizando su motivacion.
     */
    @Override
    public void entrenamiento() {
        if (seleccionadorNacional) {
            nivMotivacion += 0.30;
        } else {
            nivMotivacion += 0.15;
        }
    }
}
