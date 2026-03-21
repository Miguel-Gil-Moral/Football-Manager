package clasesCreadas;

import java.util.Random;

//Clase Jugadores y Entrenadores
//✅ Atributos (Los dos): nombre, apellido, día de nacimiento, nivel motivación(1-10), sueldo salarial
//✅ Atributos (Jugadores): Dorsal, posición en la que juegan (POR, DEF, MIG, DAV), puntuación del 30 al 100, calidad.
//✅ Los atributos de nombre, apellido y el día de nacimiento no se pueden modificar cuando se da de alta el jugador.
//✅ Método jugadores: canviDePosicio(), tendrá un 5 de probabilidad de generar un cambio de posición del jugador.
//✅ Si se produce, saldrá un mensaje y hacer el cambios ademas de sumar 1 punto de calidad del jugador.
/**
 * Representa a un jugador de futbol con dorsal, posicion y calidad.
 */
public class Jugador extends Persona {
    private int dorsal;
    private double calidad;
    private String posicion;

    /**
     * Crea un jugador con sus datos personales y deportivos.
     *
     * @param nombre nombre del jugador
     * @param apellido apellido del jugador
     * @param fechaNacimiento fecha de nacimiento del jugador
     * @param nivMotivacion nivel de motivacion inicial
     * @param sueldoSalarial sueldo salarial inicial
     * @param dorsal dorsal asignado al jugador
     * @param posicion posicion del jugador en el campo
     * @param calidad calidad inicial del jugador
     */
    public Jugador(String nombre, String apellido, String fechaNacimiento, double nivMotivacion, int sueldoSalarial, int dorsal, String posicion, double calidad) {
        super(nombre, apellido, fechaNacimiento, nivMotivacion, sueldoSalarial);
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.calidad = calidad;
    }

    /**
     * Intenta cambiar la posicion del jugador de forma aleatoria.
     */
    public void cambiarDePosicion() {
        Random rand = new Random();
        boolean salirBucle;
        int numProbabilidad = rand.nextInt(100);
        String posicionNueva = "";
        do {
            int cambioPosicion = rand.nextInt(0, 4);
            salirBucle = true;
            switch (cambioPosicion) {
                case 0:
                    posicionNueva = "POR";
                    break;
                case 1:
                    posicionNueva = "DEF";
                    break;
                case 2:
                    posicionNueva = "MIG";
                    break;
                case 3:
                    posicionNueva = "DAV";
                    break;
            }
            if (posicion.equals(posicionNueva)) {
                salirBucle = false;
            }
        } while (!salirBucle);


        if (numProbabilidad < 5) {
            this.posicion = posicionNueva;
            System.out.println("Se ha producido un cambio de posición en el jugador " + NOMBRE);
            this.calidad += 1;
        }
    }

    /**
     * Devuelve el dorsal actual del jugador.
     *
     * @return dorsal del jugador
     */
    public int getDorsal() {
        return dorsal;
    }

    /**
     * Modifica el dorsal del jugador.
     *
     * @param dorsal nuevo dorsal del jugador
     */
    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    /**
     * Devuelve la posicion actual del jugador.
     *
     * @return posicion actual del jugador
     */
    public String getPosicion() {
        return posicion;
    }

    /**
     * Devuelve la calidad actual del jugador.
     *
     * @return calidad actual del jugador
     */
    public double getCalidad() {
        return calidad;
    }

    /**
     * Devuelve la representacion textual del jugador.
     *
     * @return cadena con los datos del jugador
     */
    @Override
    public String toString() {
        return "J;" + super.toString() + ";" +
                dorsal + ";" +
                posicion + ";" +
                (int) calidad;
    }

    //✅ Además de ejecutar el código de la clase padre, la calidad del jugador aumentará en 0.1(70%), 0.2(20%) o 0.3(10%) puntos en función de un valor aleatorio.
    //✅ Aparte de realizar incrementos, se mostrará quien ha estado en el resultado.
    /**
     * Ejecuta el entrenamiento del jugador y mejora su calidad.
     */
    @Override
    public void entrenamiento() {
        super.entrenamiento();
        Random rand = new Random();
        int numProbabilidad = rand.nextInt(100 + 1);
        if (numProbabilidad <= 10 && numProbabilidad > 0) {
            this.calidad += 0.3;
        } else if (numProbabilidad <= 20 && numProbabilidad > 10) {
            this.calidad += 0.2;
        } else {
            this.calidad += 0.1;
        }
        System.out.println("El jugador " + NOMBRE + " subió puntos de calidad");
    }
//(No sabemos usarlo)Implementar métodos equals() y hashcode() de los jugadores con la finalidad de crear 1 o más comparadores para poder ordenarlos en diferentes partes de la aplicación.
    //(No sabemos usarlo)Dos jugadores se consideran iguales si coinciden con el mismo nombre y su dorsal.
    //(No sabemos usarlo)Podemos ordenar los jugadores de dos maneras diferentes:
    //(No sabemos usarlo)Por su calidad (mayor a menor). Si son iguales se ordenará de mayor a menor la motivación, y si también son iguales se ordena alfabéticamente por el apellido.

//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        Jugador jugador = (Jugador) o;
//        return Double.compare(nivMotivacion, jugador.nivMotivacion) == 0 && Double.compare(sueldoSalarial, jugador.sueldoSalarial) == 0 && dorsal == jugador.dorsal && calidad == jugador.calidad && Objects.equals(nombre, jugador.nombre) && Objects.equals(apellido, jugador.apellido) && Objects.equals(fechaNacimiento, jugador.fechaNacimiento) && Objects.equals(posicion, jugador.posicion);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(nombre, apellido, fechaNacimiento, nivMotivacion, sueldoSalarial, dorsal, calidad, posicion);
//    }
}
