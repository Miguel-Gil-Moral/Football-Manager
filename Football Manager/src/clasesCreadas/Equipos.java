package clasesCreadas;


//Clase equipos
//✅ Atributos: nombre, año fundación, ciudad, nombre del estadio(opcional), nombre presidente(opcional)
//Podemos crear equipos sin indicar obligatoriamente los campos opcionales
//Los equipos de los datos anteriores, tienen un entrenador y un listado de jugadores

import java.util.*;

/**
 * Representa a un equipo de futbol con su informacion institucional.
 */
public class Equipos {
    private final String NOMBRE, CIUDAD;
    private final int ANYO_FUNDACION;
    private final String nombreEstadio;
    private String nombrePresidente;
    private Entrenador entrenador;
    private List<Jugador> jugadores;
    /**
     * Crea un equipo con sus datos principales.
     *
     * @param nombre nombre del equipo
     * @param anyoFundacion anyo de fundacion del equipo
     * @param ciudad ciudad del equipo
     * @param nombreEstadio nombre del estadio del equipo
     * @param nombrePresidente nombre del presidente del equipo
     */
    public Equipos(String nombre, int anyoFundacion, String ciudad, String nombreEstadio, String nombrePresidente) {
        this.NOMBRE = nombre;
        this.ANYO_FUNDACION = anyoFundacion;
        this.CIUDAD = ciudad;
        this.nombreEstadio = nombreEstadio;
        this.nombrePresidente = nombrePresidente;
    }

    /**
     * Calcula la media de motivacion de un equipo dentro de una matriz.
     *
     * @return media de motivacion calculada
     */
    public double calcularMediaMotivacion() {
        double suma = 0;
        int hayEntrendor;

        for (Jugador jg : jugadores) {
            suma += jg.nivMotivacion;
        }
        if (entrenador != null) {
            suma += entrenador.nivMotivacion;
            hayEntrendor = 1;
        } else {
            hayEntrendor = 0;
        }
        return suma  / (jugadores.size() + hayEntrendor);
    }

    //✅ Submenu gestionar mi equipo (opción 4):
    //✅ Preguntará qué se quiere fichar, después mostrará todos los jugadores o entrenadores disponibles y se podrá seleccionar quien quiere fichar.
    //✅ Fichar a un jugador o entrenador implica eliminarlo de la lista del mercado de fichajes de la aplicación y agregarlo al equipo que estamos gestionando.
    //✅ (Opcional) Actualizar el fichero.txt al final de la ejecución del programa para que el jugador fichado no esté disponible en el mercado de fichajes en la siguiente ejecución del programa.
    /**
     * Permite fichar una persona del mercado para el equipo indicado.
     *
     * @param listaMercado personas disponibles en el mercado
     * @since 1.0
     */

    public void ficharPersona(ArrayList<Persona> listaMercado) {
        Scanner sc = new Scanner(System.in);
        boolean salirBucle, fichado = false, salirBucleFor;
        String quienFichar;

        do {
            try {
                System.out.println("¿Qué se quiere fichar? | 1 = Jugador, 2 = Entrenador");
                int queFichar = sc.nextInt();
                sc.nextLine();
                salirBucle = true;
                switch (queFichar) {
                    case 1: //Jugador
                        for (Persona p : listaMercado) {
                            if (p instanceof Jugador) {
                                System.out.println(NOMBRE);
                            }
                        }
                        System.out.println("¿A quién quieres fichar?");
                        quienFichar = sc.nextLine();
                        do {
                            try {
                                salirBucleFor = true;
                                for (Persona p : listaMercado) {
                                    if (p instanceof Jugador && NOMBRE.equals(quienFichar)) {
                                        jugadores.add((Jugador)p);
                                        listaMercado.remove(p);
                                        fichado = true;
                                    }
                                }
                                if (!fichado) {
                                    System.out.println(quienFichar + "no se borró de " + NOMBRE);
                                }
                            }
                            catch (ConcurrentModificationException e){
                                salirBucleFor = false;
                            }
                        } while (!salirBucleFor);
                        break;
                    case 2: //Entrenador
                        for (Persona p : listaMercado) {
                            if (p instanceof Entrenador) {
                                System.out.println(p.getNOMBRE());
                            }
                        }
                        System.out.println("¿A quién quieres fichar?");
                        quienFichar = sc.nextLine();
                        do {
                            try {
                                salirBucleFor = true;
                                for (Persona p : listaMercado) {
                                    if (p instanceof Entrenador && p.getNOMBRE().equals(quienFichar)) {
                                        entrenador = (Entrenador)p;
                                        listaMercado.remove(p);
                                        fichado = true;
                                    }
                                }
                                if (!fichado) {
                                    System.out.println(quienFichar + "no se borró de " + NOMBRE);
                                }
                            }
                            catch (ConcurrentModificationException e){
                                salirBucleFor = false;
                            }
                        } while (!salirBucleFor);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error al escribir el fichero del mercado de fichajes");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);
    }

    //Submenu gestionar mi equipo (opción 3):
    //✅Prescinde al entrenador, previa confirmación por el usuario.
    //El equipo se queda sin entrenador y este se pasará a formar parte de la lista del mercado de fichajes de la aplicación.
    /**
     * Destituye al entrenador de un equipo y lo devuelve al mercado.
     *
     * @since 1.0
     */
    public Entrenador destituirEntrenador() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Hacer al entrenador prescindible? (true / false)");
        boolean prescindeEntrenador = sc.nextBoolean();
        Entrenador ent = null;
        if (prescindeEntrenador) {
            ent = entrenador;
            entrenador = null;
            System.out.println("El entrenador ha sido destituido del equipo.");
        } else {
            System.out.println("No se ha destituido al entrenador.");
        }
        return ent;
    }

    /**
     * Gestiona la transferencia de un jugador entre equipos.
     *
     * @param equipoTransferir  Lista con todos los equipos creados
     * @since 1.0
     */
    public void transferirJugador(Equipos equipoTransferir) {
        Scanner sc = new Scanner(System.in);
        boolean salirBucle, dorsalLibre = true, jugadorEncontrado = false;

        do {
            salirBucle = true;
            try {
                do {
                    String nombreJugador = sc.nextLine();
                    int dorsalJugador = sc.nextInt();
                    for (Jugador p : jugadores) {
                        if (p.getNOMBRE().equals(nombreJugador) && p.getDorsal() == dorsalJugador) {
                            System.out.println("El jugador se ha encontrado");
                            jugadorEncontrado = true;
                        }
                    }
                    if (jugadorEncontrado) {
                        System.out.print("Escriba el nuevo dorsal del jugador: ");
                        int dorsalNuevo = sc.nextInt();
                        dorsalLibre = true;
                        for (Jugador pr : jugadores) {
                            if (pr.getDorsal() == dorsalNuevo && NOMBRE.equals(equipoTransferir.getNOMBRE())) { //(EN UN CONCEPTO SIMILAR AL CASTEO (INT --> FLOAT)) LA MENCIÓN A LAS CLASES HIJAS SE HACE INTRODUCIÉNDOLAS ENTRE PARÉNTESIS.
                                System.out.println("Dorsal ya existe, escoja otro dorsal");
                                dorsalLibre = false;
                            }
                        }
                        if (dorsalLibre) {
                            for (Jugador persona : jugadores) {
                                if (persona.getNOMBRE().equals(nombreJugador) && persona.getDorsal() == dorsalJugador) {
                                    persona.setDorsal(dorsalNuevo);
                                    List<Jugador> listaJugadores = equipoTransferir.getJugadores();
                                    listaJugadores.add(persona);
                                    equipoTransferir.setJugadores(listaJugadores);
                                }
                            }
                        }
                    } else {
                        System.out.println("El jugador junto con el dorsal no se ha encontrado, vuelve a intentarlo");
                    }
                } while (!dorsalLibre);
            } catch (InputMismatchException e) {
                System.out.println("Opción invalida, escriba el dorsal solo con números enteros");
                salirBucle = false;
                sc.next();
            }
        } while (!salirBucle);
    }

    /**
     * Solicita un nombre de presidente y valida la situacion del equipo indicado.
     *
     * @since 1.0
     * @param listaEquipos Submenu gestionar mi equipo (opcion 2):
     *     Se pedira el nombre del presidente y se actualizara en los siguientes casos:
     *     Si se proporciona el mismo presidente que ya habia, mostrara un mensaje de aviso.
     *     Si el equipo no tiene ninguna persona asignada a la presidencia, se informara al usuario del hecho con un mensaje.
     * @param nombreEquipo nombre del equipo que se va a revisar
     */
    public void modificarPresidente(ArrayList<Equipos> listaEquipos, String nombreEquipo) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Nombre presidente:");
        String nombrePresidente = sc.nextLine();

        boolean repetir;

        do {
            repetir = false;
            boolean equipoEncontrado = false;

            if (nombrePresidente == null) {
                System.out.println("Escribe el nombre del presidente.");
                nombrePresidente = sc.nextLine();
                repetir = true;
            } else {
                for (Equipos eq : listaEquipos) {
                    if (eq.getNOMBRE().equals(nombreEquipo)) {
                        equipoEncontrado = true;

                        if (eq.getNombrePresidente() != null && !eq.getNombrePresidente().equalsIgnoreCase(nombrePresidente)) {
                            System.out.println("El equipo " + eq.getNOMBRE() + " ya tiene de presidente a " + nombrePresidente);
                        } else {
                            System.out.println("El equipo " + eq.getNOMBRE() + " no tiene de presidente a " + nombrePresidente);
                        }
                    }
                }

                if (!equipoEncontrado) {
                    System.out.println("El equipo no existe.");
                    repetir = true;
                }
            }
        } while (repetir);
    }

    /**
     * Da de baja un equipo y devuelve sus personas al mercado cuando procede.
     *
     * @since 1.0
     * @param listaEquipos Lista con todos los equipos disponibles
     * @param listaMercado Lista para trasladar el jugador del equipo eliminado al mercado
     */
    public void darBajaEquipo(ArrayList<Equipos> listaEquipos, ArrayList<Persona> listaMercado) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Quieres borrar el equipo " + NOMBRE + " de la lista de equipos? («true» o «false»).");
        boolean respuestaUsuario = sc.nextBoolean();
        boolean salirBucle, salirBucleFor;

        do {
            try {
                salirBucle = true;
                if (respuestaUsuario) {
                    do {
                        try {
                            for (Equipos eq : listaEquipos) {
                                if (eq.getNOMBRE().equals(NOMBRE)) {
                                    listaEquipos.remove(eq);
                                }
                            }
                            salirBucleFor = true;
                        } catch (ConcurrentModificationException e) {
                            salirBucleFor = false;
                        }
                    } while (!salirBucleFor);
                    for (Jugador jg : jugadores) {
                        listaMercado.add(jg);
                    } listaMercado.add(entrenador);
                } else {
                    System.out.println(NOMBRE + " no se borró.");
                }
            } catch (InputMismatchException e) {
                System.out.println("No es una respuesta válida, escribe «True» o «False».");
                salirBucle = false;
            }
        } while (!salirBucle);
    }

    /**
     * Devuelve el nombre del equipo.
     *
     * @return nombre del equipo
     */
    public String getNOMBRE() {
        return NOMBRE;
    }

    /**
     * Devuelve la ciudad del equipo.
     *
     * @return ciudad del equipo
     */
    public String getCIUDAD() {
        return CIUDAD;
    }

    /**
     * Devuelve el nombre del estadio.
     *
     * @return nombre del estadio
     */
    public String getNombreEstadio() {
        return nombreEstadio;
    }

    /**
     * Devuelve el nombre del presidente del equipo.
     *
     * @return nombre del presidente
     */
    public String getNombrePresidente() {
        return nombrePresidente;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    /**
     * Devuelve el anyo de fundacion del equipo.
     *
     * @return anyo de fundacion del equipo
     */
    public int getANYO_FUNDACION() {
        return ANYO_FUNDACION;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * Modifica el presidente asociado al equipo.
     *
     * @param nombrePresidente nuevo presidente del equipo
     */
    public void setNombrePresidente(String nombrePresidente) {
        this.nombrePresidente = nombrePresidente;
    }

    //Método equipos: calcularMediaEquipo(), ha de calcular la media del equipo en base a la calidad de los jugadores
    /**
     * Calcula la media de calidad de un equipo dentro de una matriz.
     *
     * @return media de calidad calculada
     */
    public double calcularMediaCalidad() {
        double suma = 0;

        for (Jugador jg : jugadores) { //ajuste by Miguel, error explosivo - Mario
            suma += jg.getCalidad();
        }
        return suma / jugadores.size();
    }

    /**
     * Devuelve la representacion textual del equipo.
     *
     * @return cadena con los datos del equipo
     */
    @Override
    public String toString() {
        return NOMBRE + ";" +
                ANYO_FUNDACION + ";" +
                CIUDAD + ";" +
                nombreEstadio + ";" +
                nombrePresidente + "\n";
    }
}
