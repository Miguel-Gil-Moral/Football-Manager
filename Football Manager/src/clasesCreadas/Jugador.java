package clasesCreadas;

import java.util.Objects;
import java.util.Random;

//Clase Jugadores y Entrenadores
//✅ Atributos (Los dos): nombre, apellido, día de nacimiento, nivel motivación(1-10), sueldo salarial
//✅ Atributos (Jugadores): Dorsal, posición en la que juegan (POR, DEF, MIG, DAV), puntuación del 30 al 100, calidad.
//Los atributos de nombre, apellido y el día de nacimiento no se pueden modificar cuando se da de alta el jugador.
//(✅ y medio) Método jugadores: canviDePosicio(), tendrá un 5 de probabilidad de generar un cambio de posición del jugador.
//Si se produce, saldrá un mensaje y hacer el cambios ademas de sumar 1 punto de calidad del jugador.
public class Jugador extends Persona {
    protected String nombre, apellido, fechaNacimiento;
    protected double nivMotivacion;
    protected int sueldoSalarial;
    private int dorsal, calidad;
    private String posicion;

    public Jugador(String nombre, String apellido, String fechaNacimiento, double nivMotivacion, int sueldoSalarial, int dorsal, String posicion, int calidad) {
        super(nombre, apellido, fechaNacimiento, nivMotivacion, sueldoSalarial);
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.calidad = calidad;
    }

    private void cambiarDePosicion(String posicion) {
        Random rand = new Random();
        int numProbabilidad = rand.nextInt(100);


        if (numProbabilidad < 5) {
            this.posicion = posicion;
            System.out.println("Se ha producido un cambio de posición");
            this.calidad += 1;
        }
    }

    @Override
    public String toString() {
        return "J;" + super.toString() + ";" +
                dorsal + ";" +
                posicion + ";" +
                calidad + "\n";
    }

    @Override
    protected void entrenamiento() {
        this.nivMotivacion += 0.2;
    }
//Implementar métodos equals() y hashcode() de los jugadores con la finalidad de crear 1 o más comparadores para poder ordenarlos en diferentes partes de la aplicación.
    //Dos jugadores se consideran iguales si coinciden con el mismo nombre y su dorsal.
    //Podemos ordenar los jugadores de dos maneras diferentes:
    //Por su calidad (mayor a menor). Si son iguales se ordenará de mayor a menor la motivación, y si también son iguales se ordena alfabéticamente por el apellido.

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Double.compare(nivMotivacion, jugador.nivMotivacion) == 0 && Double.compare(sueldoSalarial, jugador.sueldoSalarial) == 0 && dorsal == jugador.dorsal && calidad == jugador.calidad && Objects.equals(nombre, jugador.nombre) && Objects.equals(apellido, jugador.apellido) && Objects.equals(fechaNacimiento, jugador.fechaNacimiento) && Objects.equals(posicion, jugador.posicion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, fechaNacimiento, nivMotivacion, sueldoSalarial, dorsal, calidad, posicion);
    }
}
