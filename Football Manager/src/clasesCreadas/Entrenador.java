package clasesCreadas;


//Clase Jugadores y Entrenadores
//✅ Atributos (Los dos): nombre, apellido, día de nacimiento, nivel motivación(1-10), sueldo salarial
//✅ Atributos (Entrenador): número torneos ganados, ha estado seleccionador nacional(booleano)
//Los atributos de nombre, apellido y el día de nacimiento no se pueden modificar cuando se da de alta el jugador.
//Método entrenadores: incrementarSou(), incrementara el salario actual en un 0,5%.
public class Entrenador extends Persona {
    protected String nombre, apellido, fechaNacimiento;
    protected double nivMotivacion;
    protected int sueldoSalarial;
    private int numTorneosGanados;
    private boolean seleccionadorNacional;


    public Entrenador(String nombre, String apellido, String fechaNacimiento, double nivMotivacion, int sueldoSalarial, int numTorneosGanados, boolean seleccionadorNacional) {
        super(nombre, apellido, fechaNacimiento, nivMotivacion, sueldoSalarial);
        this.numTorneosGanados = numTorneosGanados;
        this.seleccionadorNacional = seleccionadorNacional;
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
