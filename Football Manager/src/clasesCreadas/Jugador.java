package clasesCreadas;

import java.util.Random;

//Clase Jugadores y Entrenadores
//✅ Atributos (Los dos): nombre, apellido, día de nacimiento, nivel motivación(1-10), sueldo salarial
//✅ Atributos (Jugadores): Dorsal, posición en la que juegan (POR, DEF, MIG, DAV), puntuación del 30 al 100, calidad.
//✅ Los atributos de nombre, apellido y el día de nacimiento no se pueden modificar cuando se da de alta el jugador.
//✅ Método jugadores: canviDePosicio(), tendrá un 5 de probabilidad de generar un cambio de posición del jugador.
//✅ Si se produce, saldrá un mensaje y hacer el cambios ademas de sumar 1 punto de calidad del jugador.
public class Jugador extends Persona {
    private int dorsal;
    private double calidad;
    private String posicion;

    public Jugador(String nombre, String apellido, String fechaNacimiento, double nivMotivacion, int sueldoSalarial, int dorsal, String posicion, double calidad) {
        super(nombre, apellido, fechaNacimiento, nivMotivacion, sueldoSalarial);
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.calidad = calidad;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

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

    public int getDorsal() {
        return dorsal;
    }

    public String getPosicion() {
        return posicion;
    }

    public double getCalidad() {
        return calidad;
    }

    @Override
    public String toString() {
        return "J;" + super.toString() + ";" +
                dorsal + ";" +
                posicion + ";" +
                calidad;
    }

    //✅ Además de ejecutar el código de la clase padre, la calidad del jugador aumentará en 0.1(70%), 0.2(20%) o 0.3(10%) puntos en función de un valor aleatorio.
    //✅ Aparte de realizar incrementos, se mostrará quien ha estado en el resultado.
    @Override
    public void entrenamiento() {
        super.entrenamiento();
        Random rand = new Random();
        int numProbabilidad = rand.nextInt(100 + 1);
        if (numProbabilidad <= 10) {
            this.calidad += 0.3;
        } else if (numProbabilidad <= 20) {
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
