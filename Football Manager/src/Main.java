import clasesCreadas.*;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Miguel Gil Moral, Mario De Molina Martín
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Persona> listaFichajes = cargarFichajes(), listaFichados = cargarPersonasFichadas();
        ArrayList<Equipos> listaEquipos = cargarEquipos();
        ArrayList<Liga> listaLigas = cargarLigas();
        String rol = pedirRol();
        int opcion, opcionSubmenu;
        boolean salirBucle = false, salirBucleSubmenu = false, equipoExistente;

        do {
            System.out.println("Welcome to Politècnics Football Manager:");
            switch (rol) {
                case "Administrador":
                    opcion = menuAdmin();
                    switch (opcion) {
                        case 1:
                            verClasificacionLiga(listaLigas);
                            break;
                        case 2:
                            darAltaEquipo(listaEquipos);
                            break;
                        case 3:
                            darAltaPersona(listaFichajes);
                            break;
                        case 4:
                            consultarDatosEquipo(listaEquipos);
                            break;
                        case 5:
                            consultarDatosJugador(listaFichados, listaEquipos);
                            break;
                        case 6:
                            disputarNuevaLiga();
                            break;
                        case 7:
                            realizarEntrenamientoMercado();
                            break;
                        case 8:
                            guardarDatosEquipo();
                            break;
                        case 0:
                            salirBucle = true;
                            break;
                    }
                    break;
                case "Gestor de Equipos":
                    opcion = menuGestorEquipos();
                    switch (opcion) {
                        case 1:
                            verClasificacionLiga(listaLigas);
                            break;
                        case 2:
                            //Menu principal de gestor de equipos (opción 2):
                            //Al seleccionar la opción, se pedirá el nombre del equipo. Si no se encuentra se mostrará un mensaje de error y se volverá al menu principal.
                            //Si se encuentra el equipo, se mostrará un submenu específico.
                            String nombreEquipo = pedirNombreEquipo();
                            equipoExistente = revisarEquipo(listaEquipos, nombreEquipo);
                            if (equipoExistente) {
                                opcionSubmenu = submenuGestorEquipos();
                                do {
                                    switch (opcionSubmenu) {
                                        case 1:
                                            darBajaEquipo(listaEquipos, nombreEquipo);
                                            break;
                                        case 2:
                                            modificarPresidente(listaEquipos);
                                            break;
                                        case 3:
                                            destituirEntrenador();
                                            break;
                                        case 4:
                                            ficharPersona();
                                            break;
                                        case 0:
                                            salirBucleSubmenu = true;
                                            break;
                                    }
                                } while (!salirBucleSubmenu);
                            } else {
                                System.out.println("No existe el equipo que desea gestionar");
                            }
                            break;
                        case 3:
                            consultarDatosEquipo(listaEquipos);
                            break;
                        case 4:
                            consultarDatosJugador(listaFichados, listaEquipos);
                            break;
                        case 5:
                            transferirJugador(listaFichados, listaEquipos);
                            break;
                        case 6:
                            guardarDatosEquipo();
                            break;
                        case 0:
                            salirBucle = true;
                            break;
                    }
                    break;
            }
        } while (!salirBucle);
        actualizarFichero(listaFichajes, listaFichados, listaEquipos, listaLigas);
    }

    //✅ Cargar jugadores y entrenadores disponibles del fichero al iniciar el programa.

    /**
     * @return Todos los jugadores/as y entrenadores/as guardados de un fichero de texto en una lista de personas
     * @since 1.0
     */
    public static ArrayList<Persona> cargarFichajes() {
        String linea;
        String[] separado;
        ArrayList<Persona> listaFichajes = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/ficheros/mercat_fitxatges.txt"));
            while ((linea = br.readLine()) != null) {
                separado = linea.split(";");
                if (separado[0].equals("J")) {
                    listaFichajes.add(new Jugador(separado[1], separado[2], separado[3], Double.parseDouble(separado[4]),
                            Integer.parseInt(separado[5]), Integer.parseInt(separado[6]),
                            separado[7], Integer.parseInt(separado[8])));
                } else if (separado[0].equals("E")) {
                    listaFichajes.add(new Entrenador(separado[1], separado[2], separado[3], Double.parseDouble(separado[4]),
                            Integer.parseInt(separado[5]), Integer.parseInt(separado[6]), Boolean.parseBoolean(separado[7])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo");
        }
        return listaFichajes;
    }

    /**
     * @return La lista con todos los equipos existentes
     * @since 1.0
     */
    public static ArrayList<Equipos> cargarEquipos() {
        String linea;
        String[] separado;
        ArrayList<Equipos> listaEquipos = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/ficheros/equipos.txt"));
            while ((linea = br.readLine()) != null) {
                separado = linea.split(";");
                listaEquipos.add(new Equipos(separado[0], Integer.parseInt(separado[1]), separado[2], separado[3], separado[4]));
            }
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo");
        }

        return listaEquipos;
    }

    /**
     * @return Lista con todos los jugadores y entrenadores fichados en un equipo
     * @since 1.0
     */
    public static ArrayList<Persona> cargarPersonasFichadas() {
        String linea;
        String[] separado;
        ArrayList<Persona> listaFichados = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/ficheros/jugadores_fichados.txt"));
            while ((linea = br.readLine()) != null) {
                separado = linea.split(";");
                if (separado[0].equals("J")) {
                    Jugador jg = new Jugador(separado[1], separado[2], separado[3], Double.parseDouble(separado[4]),
                            Integer.parseInt(separado[5]), Integer.parseInt(separado[6]),
                            separado[7], Integer.parseInt(separado[8]));
                    jg.setNombreEquipo(separado[9]);
                    listaFichados.add(jg);
                } else if (separado[0].equals("E")) {
                    Entrenador en = new Entrenador(separado[1], separado[2], separado[3], Double.parseDouble(separado[4]),
                            Integer.parseInt(separado[5]), Integer.parseInt(separado[6]), Boolean.parseBoolean(separado[7]));
                    en.setNombreEquipo(separado[8]);
                    listaFichados.add(en);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo");
        }
        return listaFichados;
    }

    public static ArrayList<Liga> cargarLigas() {
        ArrayList<Liga> listaLigas = new ArrayList<>();
        String linea;
        String[] separado;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/ficheros/ligas.txt"));
            while ((linea = br.readLine()) != null) {
                separado = linea.split(";");
                listaLigas.add(new Liga(separado[0], Integer.parseInt(separado[1])));
            }
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo");
        }
        return listaLigas;
    }

    //✅ Al inicio pedir si es Admin o un gestor de equipos, no hace falta poner la contraseña ni nada parecido

    /**
     * @return Nombre del rol escogido por el usuario de estos siguientes: <ul>
     * <li>Administrador</li>
     * <li>Gestor de Equipos</li>
     * </ul>
     * @since 1.0
     */
    public static String pedirRol() {
        Scanner sc = new Scanner(System.in);
        int rolInt;
        String rol = "";
        boolean salirBucle;
        do {
            try {
                System.out.println("Ingrese el nombre del rol:");
                System.out.println("1- Administrador");
                System.out.println("2- Gestor de Equipos");
                System.out.print("Opción: ");
                rolInt = sc.nextInt();
                salirBucle = true;
                switch (rolInt) {
                    case 1:
                        rol = "Administrador";
                        break;
                    case 2:
                        rol = "Gestor de Equipos";
                        break;
                    default:
                        System.out.println("Opción invalida, seleccione 1 o 2");
                        salirBucle = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción invalida, seleccione las opciones que se muestra en pantalla");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);
        return rol;
    }

    //✅ Un menu principal para admin:
    //✅       1- Veure classificació lliga actual 🏆
    //✅
    // ✅      2- Donar d'alta equip
    //✅
    // ✅      3- Donar d'alta jugador/a o entrenador/a
    //✅
    //✅       4- Consultar dades equip
    //✅
    //✅       5- Consultar dades jugador/a equip
    //✅
    //✅       6- Disputar nova lliga
    //✅
    //✅       7- Realitzar sessió entrenament (del mercat fitxatges)
    //✅
    //✅       8- Desar dades equips
    //✅
    // ✅      0- Sortir

    /**
     * @return Opción escogida por el administrador
     * @since 1.0
     */
    public static int menuAdmin() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        boolean salirBucle;
        do {
            try {
                System.out.println("1- Ver clasificación de la liga actual");
                System.out.println("2- Dar de alta un equipo");
                System.out.println("3- Dar de alta un jugador/a o entrenador/a");
                System.out.println("4- Consultar datos del equipo");
                System.out.println("5- Consultar datos de un jugador/a de un equipo");
                System.out.println("6- Disputar nueva liga");
                System.out.println("7- Realizar sesión de entrenamiento (Mercado de fichajes)");
                System.out.println("8- Guardar datos de los equipos");
                System.out.println("0- Salir");
                System.out.print("Opción: ");
                opcion = sc.nextInt();
                salirBucle = true;
                if (opcion < 0 || opcion > 8) {
                    System.out.println("Opción invalido, seleccione las opciones que se muestra en pantalla");
                    salirBucle = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción invalido, seleccione las opciones que se muestra en pantalla");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);
        return opcion;
    }

    //Menu principal gestor de equipos(opción 1):
    //Mostrará la clasificación de la liga actual, mostrará el nombre del equipo, puntos, partidos, goles a favor y goles en contra.
    //Debe de estar ordenada por puntos y en caso de tener mismos puntos, diferencia entre goles a favor y en contra.

    /**
     * @since 1.0
     */
    public static void verClasificacionLiga(ArrayList<Liga> listaLigas) {
        Scanner sc = new Scanner(System.in);
        String nombreLiga;
        boolean ligaExsistente = false;


        System.out.println("Ingrese el nombre del liga actual: ");
        nombreLiga = sc.next();
        for ( Liga l : listaLigas) {
            if (l.getNombre().equals(nombreLiga)) {
                System.out.println("Liga: " + l.getNombre());


                ligaExsistente = true;


                }
        }
        if (!ligaExsistente) {
            System.out.println("No exsiste");
        }

//        do {
//                String nombreEquipo = pedirNombreEquipo();
//
//                for (Equipos eq : listaEquipos) {
//                    if (eq.getNombre().equals(nombreEquipo)) {
//                        System.out.println("Nombre: " + eq.getNombre());
//                        System.out.println("Ciudad: " + eq.getCiudad());
//                        System.out.println("Año fundación: " + eq.getAnyoFundacion());
//                        System.out.println("Estadio: " + eq.getNombreEstadio());
//                        System.out.println("Presidente: " + eq.getNombrePresidente());
//                    } else {
//                        System.out.println("No existe este equipo ;(");
//                    }
//
//                }
//
//        } while (!salirBucle);

        /*
        public static ArrayList<Equipos> cargarEquipos() {
        String linea;
        String[] separado;
        ArrayList<Equipos> listaEquipos = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/ficheros/equipos.txt"));
            while ((linea = br.readLine()) != null) {
                separado = linea.split(";");
                listaEquipos.add(new Equipos(separado[0], Integer.parseInt(separado[1]), separado[2], separado[3], separado[4]));
            }
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo");
        }

        return listaEquipos;
    }
        */

    }

    //✅ Menu principal de admin (opción 2):
    //✅ Se pedirá el primer nombre para verificar que no este dado de alta en la aplicación.
    //✅ Solo se pedirá el resto de datos si el equipo no existe, en caso contrario se mostrará un mensaje de error y volverá a pedir otro nombre, se repetirá tantas veces hasta que ponga un nombre que exista.
    //✅ Cuando tenga el nombre del equipo, pedirá los demás datos principales, estarán también las dos opciones opcionales.

    /**
     * @return Lista con el equipo añadido que se ha dado de alta
     * @since 1.0
     */
    public static ArrayList<Equipos> darAltaEquipo(ArrayList<Equipos> listaEquipos) {
        Scanner sc = new Scanner(System.in);
        boolean salirBucle;
        String nombre, ciudad, nombreEstadio, nombrePresidente;
        int anyoFundacion = 0;

        do {
            System.out.print("Ingrese el nombre del equipo: ");
            nombre = sc.next();
            salirBucle = true;
            for (Equipos eq : listaEquipos) {
                String nombreEquipo = eq.getNombre();
                if (nombreEquipo.equals(nombre)) {
                    System.out.println("El nombre del equipo ya existe en uno, escoja otro nombre");
                    salirBucle = false;
                }
            }
        } while (!salirBucle);
        do {
            try {
                System.out.print("¿En que año se fundo el equipo?: ");
                anyoFundacion = sc.nextInt();
                salirBucle = true;
                if (anyoFundacion < 1850 || anyoFundacion > 2026) {
                    System.out.println("Opción invalida, escriba un año entre 1850 y 2026");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción invalida, escriba solo números enteros");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);
        System.out.print("¿En que ciudad reside?: ");
        ciudad = sc.next();
        sc.nextLine();
        System.out.print("Escribe el nombre del estadio (Opcional): ");
        nombreEstadio = sc.nextLine();
        System.out.print("Escribe el nombre del presidente (Opcional): ");
        nombrePresidente = sc.nextLine();

        Equipos equipos = new Equipos(nombre, anyoFundacion, ciudad);
        if (nombreEstadio.isEmpty()) { //Iba a ponerlo con .length, pero el programa me lo recomendó de esta manera. Esta prueba de que no lo hice con chatgpt. Merequetenge
            equipos.setNombrePresidente(nombrePresidente);
        } else if (nombrePresidente.isEmpty()) {
            equipos.setNombreEstadio(nombreEstadio);
        } else {
            equipos.setNombreEstadio(nombreEstadio);
            equipos.setNombrePresidente(nombrePresidente);
        }

        return listaEquipos;
    }

    //✅ Menu principal de admin (opción 3):
    //✅ Preguntará si quiere dar de alta a un jugador o a un entrenador.
    //✅ Al dar de alta, todos los datos serán obligatorio.
    //✅ Para asegurar que todos los valores són valídos, la calidad del jugador se generará con un número aleatorio
    //✅ La motivación comenzará siempre en 5, y los valores de las posiciones se extraerán de la clase Jugador.
    //✅ El nuevo jugador o entrenador creado se guardará en una lista que contiene el mercado de fichajes.
    //✅ (Opcional) Actualizar el fichero.txt al final de la ejecución del programa para que los jugadores o entrenadores estén disponibles en el mercado para la siguiente ejecución del programa.

    /**
     * @return Lista con el nuevo jugador/a o entrenador/a añadido en la lista de fichajes
     * @since 1.0
     */
    public static ArrayList<Persona> darAltaPersona(ArrayList<Persona> listaFichajes) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        boolean salirBucle;

        do {
            try {
                System.out.println("Que rol quiere dar de alta:");
                System.out.println("1- Jugador/a");
                System.out.println("2- Entrenador/a");
                System.out.print("Opción: ");
                int opcion = sc.nextInt();
                String nombre = pedirNombrePersona();
                System.out.print("Ingrese el apellido de la persona: ");
                String apellido = sc.next();
                String fechaNacimiento = asignarNacimiento();
                int sueldoSalarial = asignarSueldoSalarial();
                salirBucle = true;
                switch (opcion) {
                    case 1:
                        int dorsalJugador = pedirDorsalJugador();
                        int calidadJugador = random.nextInt(100);
                        String posicionJugador = asignarPosicionJugador();
                        Jugador jugador = new Jugador(nombre, apellido, fechaNacimiento, 5, sueldoSalarial, dorsalJugador, posicionJugador, calidadJugador);
                        listaFichajes.add(jugador);
                        break;
                    case 2:
                        int numTorneosGanados = asignarTorneosGanado();
                        boolean seleccionadorNacional = esSeleccionadorNacional();
                        Entrenador entrenador = new Entrenador(nombre, apellido, fechaNacimiento, 5, sueldoSalarial, numTorneosGanados, seleccionadorNacional);
                        listaFichajes.add(entrenador);
                        break;
                    default:
                        System.out.println("Opción invalido, escoja 1 o 2");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción invalida, escoja una opción de la pantalla");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);
        return listaFichajes;
    }

    /**
     * @return El nombre de la persona
     * @since 1.0
     */
    private static String pedirNombrePersona() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la persona: ");
        return sc.next();
    }

    /**
     * @return Fecha de nacimiento en formato dd/MM/aaaa de tipo String
     * @since 1.0
     */
    public static String asignarNacimiento() {
        Scanner sc = new Scanner(System.in);
        boolean salirBucle;
        int diaNacimiento = 0, mesNacimiento = 0, anoNacimiento = 0;

        do {
            try {
                System.out.println("Introduzca su fecha de nacimiento");
                System.out.print("Día: ");
                diaNacimiento = sc.nextInt();
                System.out.print("Més: ");
                mesNacimiento = sc.nextInt();
                System.out.print("Año: ");
                anoNacimiento = sc.nextInt();
                salirBucle = true;
                if (diaNacimiento < 0 || diaNacimiento > 31
                        || mesNacimiento < 0 || mesNacimiento > 12
                        || anoNacimiento < 1980 || anoNacimiento > 2009) {
                    System.out.println("El valor del día, mes o año no son correctos, por favor establece un valor coherente entre el año 1980 y 2009");
                    salirBucle = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor invalido, escriba números enteros");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);

        return String.format(diaNacimiento + "/"
                + mesNacimiento + "/"
                + anoNacimiento);
    }

    /**
     * @return El sueldo que ganará la persona
     * @since 1.0
     */
    public static int asignarSueldoSalarial() {
        Scanner sc = new Scanner(System.in);
        boolean salirBucle;
        int sueldoSalarial = 0;
        do {
            try {
                System.out.print("Introduzca sueldo de sueldo: ");
                sueldoSalarial = sc.nextInt();
                salirBucle = true;
                if (sueldoSalarial < 0) {
                    System.out.println("Valor invalido, no puede trabajar sin tener un sueldo");
                    salirBucle = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor invalido, escriba un número enteros");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);
        return sueldoSalarial;
    }

    /**
     * @return Número del dorsal no escogido para el jugador
     * @since 1.0
     */
    public static int pedirDorsalJugador() {
        Scanner sc = new Scanner(System.in);
        boolean salirBucle;
        int dorsalJugador = 0;

        do {
            try {
                System.out.print("Introduzca un dorsal el jugador: ");
                dorsalJugador = sc.nextInt();
                salirBucle = true;
                if (dorsalJugador == 0) {
                    System.out.println("Opción invalida, coloque un número de dorsal");
                    salirBucle = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción invalida, por favor coloque números enteros");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);
        return dorsalJugador;
    }

    /**
     * @return La posición que jugara el jugador de estos siguientes: <ul>
     * <li>POR: Posición de portero</li>
     * <li>DEF: Posición de defensa</li>
     * <li>MIG: Posición de mediocampista</li>
     * <li>DAV: Posición de delantero</li>
     * </ul>
     * @since 1.0
     */
    public static String asignarPosicionJugador() {
        Scanner sc = new Scanner(System.in);
        boolean salirBucle;
        int opcion;
        String posicionJugador = "";

        do {
            try {
                System.out.println("Escoja la posición del jugador que va a jugar:");
                System.out.println("1- POR (Portero)");
                System.out.println("2- DEF (Defensa)");
                System.out.println("3- MIG (Mediocampista)");
                System.out.println("4- DAV (Delantero)");
                System.out.print("Opción: ");
                opcion = sc.nextInt();
                salirBucle = true;
                switch (opcion) {
                    case 1:
                        posicionJugador = "POR";
                        break;
                    case 2:
                        posicionJugador = "DEF";
                        break;
                    case 3:
                        posicionJugador = "MIG";
                        break;
                    case 4:
                        posicionJugador = "DAV";
                        break;
                    default:
                        System.out.println("Opción invalida, escoga un número entre el 1 y 4");
                        salirBucle = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción invalida, escoga las opciones que se muestra en pantalla");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);
        return posicionJugador;
    }

    /**
     * @return La cantidad de torneos que el entrenador ha ganado
     * @since 1.0
     */
    public static int asignarTorneosGanado() {
        Scanner sc = new Scanner(System.in);
        boolean salirBucle;
        int torneosGanado = 0;

        do {
            try {
                System.out.print("¿Cuantos torneos gano este entrenador?: ");
                torneosGanado = sc.nextInt();
                salirBucle = true;
                if (torneosGanado < 0) {
                    System.out.println("Opción invalida, escriba un valor mayor o igual a 0");
                    salirBucle = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción invalida, escriba solo números enteros");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);
        return torneosGanado;
    }

    /**
     * @return Resultado sobre el entrenador de estos siguientes:<ul>
     * <li>True: El entrenador ha estado seleccionador nacional</li>
     * <li>False: El entrenador no ha estado seleccionador nacional</li>
     * </ul>
     * @since 1.0
     */
    public static boolean esSeleccionadorNacional() {
        Scanner sc = new Scanner(System.in);
        boolean salirBucle, esSeleccionadorNacional = false;

        do {
            try {
                System.out.print("¿El entrenador ha estado seleccionador nacional?: ");
                esSeleccionadorNacional = sc.nextBoolean();
                salirBucle = true;
            } catch (InputMismatchException e) {
                System.out.println("Opción invalida, escriba solo true o false");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);
        return esSeleccionadorNacional;
    }

    //Menu principal de admin (opción 4):
    //✅ Pedirá el equipo del que se quiere consultar los datos (a través del nombre). Si no se encuentra, se mostrará un mensaje de error volviendo así al menú principal.
    //Si el equipo se encuentra se mostrará todos los datos incluyendo la del entrenador y el listado de los jugadores.

    /**
     * @param listaEquipos Lista con todos los equipos existentes
     * @since 1.0
     */
    public static void consultarDatosEquipo(ArrayList<Equipos> listaEquipos) {  //Está todavía por mejorar (yo le he puesto bool)
        String nombreEquipo = pedirNombreEquipo();
        boolean equipoExistente = false;

        for (Equipos eq : listaEquipos) {
            if (eq.getNombre().equals(nombreEquipo)) {
                System.out.println("Nombre: " + eq.getNombre());
                System.out.println("Ciudad: " + eq.getCiudad());
                System.out.println("Año fundación: " + eq.getAnyoFundacion());
                System.out.println("Estadio: " + eq.getNombreEstadio());
                System.out.println("Presidente: " + eq.getNombrePresidente());
                equipoExistente = true;
            } else {
                System.out.println("No existe este equipo ;(");
            }

        }
    }

    //✅ Menu principal de admin (opción 5):
    // Solicitará el nombre del equipo, para solicitar seguidamente el nombre del jugador en cuestión. Si no se encuentra el equipo mostrará un mensaje de error y se volverá al menu principal.
    // Si el equipo se encuentra se pedirá el nombre y el dorsal del jugador. Con estos datos se buscará jugador del equipo y se mostrará sus datos.
    // Si el jugador no se encuentra se avisará al usuario y se volverá al menu principal.

    /**
     * @param listaFichados Lista con todos los jugadores fichados en un equipo
     * @param listaEquipos  Lista con todos los equipos para la comprobación de la existencia de ese equipo
     * @since 1.0
     */
    public static void consultarDatosJugador(ArrayList<Persona> listaFichados, ArrayList<Equipos> listaEquipos) {

        Scanner sc = new Scanner(System.in);
        boolean equipoEncontrado = false;


        //1) PEDIR nombre de equipo

        String nombreEquipo = pedirNombreEquipo();


        /*2) BUSCAR equipo en listaEquipos
         *    - si NO existe: informar y terminar*/

        for (Equipos eq : listaEquipos) {
            if (eq.getNombre().equals(nombreEquipo)) {
                equipoEncontrado = true;
            }
        }


        //3) PEDIR nombre de jugador

        if (equipoEncontrado) {
            System.out.print("Introduce el nombre del jugador: ");
            String nombreJugador = sc.next();


            //4) PEDIR dorsal

            int dorsal = pedirDorsalJugador();


            /*  5) BUSCAR jugador por (nombre + dorsal) en la fuente correcta
                    - si NO existe: informar y terminar */

            for (Persona pr : listaFichados) {
                if (pr.getNombre().equals(nombreJugador)) {    //6) MOSTRAR datos comunes (Persona) + datos exclusivos (Jugador)
                    System.out.println("Nombre: " + pr.getNombre());
                    System.out.println("Apellido: " + pr.getApellido());
                    System.out.println("Fecha Nacimiento: " + pr.getFechaNacimiento());
                    System.out.println("Motivación: " + pr.getNivMotivacion());
                    System.out.println("Sueldo: " + pr.getSueldoSalarial());
                    System.out.println("Dorsal: " + ((Jugador) pr).getDorsal());       //mostrar los datos (atributos) exclusivos de jugador
                    System.out.println("Posición: " + ((Jugador) pr).getPosicion());  //mostrar los datos (atributos) exclusivos de jugador
                    System.out.println("Calidad: " + ((Jugador) pr).getCalidad());   //mostrar los datos (atributos) exclusivos de jugador
                }
            }

        } else {
            System.out.println("¡No existe el equipo " + nombreEquipo + "!...");

            //7) TERMINAR (el menú exterior vuelve a mostrarse por su bucle)
        }
    }

    private static String pedirNombreEquipo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Escribe el nombre del equipo: ");
        return sc.next();
    }

    //Menu principal de admin (opción 6):
    //✅ Pedirá los datos básicos para crear una nueva liga: Nombre, número de equipos que participaran.
    //✅ Se le asignará al objeto "Lliga" de la clase a la aplicación.
    //Una vez creada la liga, se pedirá a todos los equipos que participen, asegurándose de no agregar un equipo repetido.
    //Una vez agregado todos los equipos a la liga, se disputarán automáticamente los partidos cuantas sean necesarios para completar la liga(Podemos hacer que puedan hacer un partido con cada uno o hacer salida y vuelta).

    /**
     * @since 1.0
     */
    public static void disputarNuevaLiga() {
        Scanner sc = new Scanner(System.in);
        boolean salirBucle;
        String nombre;
        int cantidadEquipos = 0;

        System.out.print("Escoga un nombre para la liga: ");
        nombre = sc.next();
        do {
            try {
                System.out.print("¿Cuantos equipos participaran?: ");
                cantidadEquipos = sc.nextInt();
                salirBucle = true;
            } catch (InputMismatchException e) {
                System.out.println("Opción incorrecta, escriba la cantidad de equipos en números enteros");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);

        Liga lliga = new Liga(nombre, cantidadEquipos);
    }

    //Menu principal admin (opción 7):
    //Permitirá actualizar la calidad y el nivel de motivación de los jugadores y entrenadores disponibles al mercado de fichajes.
    //Se necesita recorrer la lista de jugadores y entrenadores disponibles y ejecutar el método entrenament() para cada elemento de la lista.
    //Según si es jugador o entrenador se ejecutaran los métodos canviPosicio() para los jugadores, y incrementarSou() para los entrenadores.

    /**
     * @since 1.0
     */
    public static void realizarEntrenamientoMercado() {

    }

    //Menu principal de admin (opción 8):
    //Se guardará los datos de todos los equipos de la aplicación incluyendo la suya y todos los datos de los entrenadores y los jugadores para poder recuperarlos para la siguiente ejecución del programa.
    //El formato y la cantidad del fichero es totalmente libre.

    /**
     * @since 1.0
     */
    public static void guardarDatosEquipo() {
    }

    //✅Menu principal para gestor de equipos:
    //      1- Veure classificació lliga actual 🏆
    //
    //      2- Gestionar el meu equip ⚽
    //
    //      3- Consultar dades equip
    //
    //      4- Consultar dades jugador/a equip
    //
    //      5- Transferir jugador/a
    //
    //      6 - Desar dades equips
    //
    //      0- Sortir

    /**
     * @return Opción escogida por el Gestor de equipos
     * @since 1.0
     */
    public static int menuGestorEquipos() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        boolean salirBucle;

        do {
            try {
                System.out.println("1- Ver clasificación de la liga actual");
                System.out.println("2- Gestionar mi equipo");
                System.out.println("3- Consultar datos del equipo");
                System.out.println("4- Consultar datos de un jugador/a del equipo");
                System.out.println("5- Transferir jugador/a");
                System.out.println("6- Guardar datos del equipo");
                System.out.println("0- Salir");
                System.out.print("Opción: ");
                opcion = sc.nextInt();
                salirBucle = true;
                if (opcion < 0 || opcion > 5) {
                    System.out.println("Opción invalido, seleccione las opciones que se muestra en pantalla");
                    salirBucle = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción invalido, seleccione las opciones que se muestra en pantalla");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);
        return opcion;
    }

    //Menu principal gestor de equipos (opción 5):
    //✅ Asegurar que el equipo donde el jugador esta y el equipo donde se quiere transferir existan.
    //✅ En caso afirmativo, se pedirá el nuevo dorsal del jugador transferido (habrá que verificar si está disponible).
    //Se pedirá el dorsal hasta que se proporcione uno que esté disponible.

    /**
     * @param listaFichados Lista con todos los jugadores fichados en un equipo
     * @param listaEquipos  Lista con todos los equipos creados
     * @since 1.0
     */
    public static void transferirJugador(ArrayList<Persona> listaFichados, ArrayList<Equipos> listaEquipos) {
        Scanner sc = new Scanner(System.in);
        boolean salirBucle, dorsalLibre = false;
        int dorsal;

        System.out.print("Escriba el equipo donde se encuentra el jugador: ");
        String equipo1 = sc.next();
        System.out.print("Escriba el equipo donde quiere transferir al jugador: ");
        String equipo2 = sc.next();

        for (Equipos eq : listaEquipos) {
            if (eq.getNombre().equals(equipo1) && eq.getNombre().equals(equipo2)) {
                do {
                    try {
                        do {
                            System.out.print("Escriba el nuevo dorsal del jugador: ");
                            dorsal = sc.nextInt();
                            salirBucle = true;
                            for (Persona pr : listaFichados) {
                                if (((Jugador) pr).getDorsal() != dorsal) { //(EN UN CONCEPTO SIMILAR AL CASTEO (INT --> FLOAT)) LA MENCION A LAS CLASES HIJAS SE HACE INTRODUCIÉNDOLAS ENTRE PARÉNTESIS.
                                    dorsalLibre = true;
                                }
                            }
                        } while (!dorsalLibre);
                    } catch (InputMismatchException e) {
                        System.out.println("Opción invalida, escriba solo números enteros");
                        salirBucle = false;
                        sc.next();
                    }
                } while (!salirBucle);


            } else {
                System.out.println("Uno de los equipos introducidos no existen");
            }
        }
    }

    public static boolean revisarEquipo(ArrayList<Equipos> listaEquipos, String nombreEquipo) {
        Scanner sc = new Scanner(System.in);
        boolean encontrado = false;

        for (Equipos eq : listaEquipos) {
            if (eq.getNombre().equals(nombreEquipo)) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    //Submenu para gestor de mi propio equipo(opcion 2):
    //      1- Donar de baixa l'equip
    //
    //      2- Modificar president/a
    //
    //      3- Destituir entrenador/a
    //
    //      4- Fitxar jugador/a o entrenador/a
    //
    //      0- Sortir

    /**
     * @return Opción escogida por el Gestor de equipos
     * @since 1.0
     */
    public static int submenuGestorEquipos() {
        Scanner sc = new Scanner(System.in);
        int opcionSubmenu = 0;
        boolean salirBucle;

        do {
            try {
                System.out.println("Team Manager:");
                System.out.println("1- Dar de baja el equipo");
                System.out.println("2- Modificar presidente/a");
                System.out.println("3- Destituir entrenador/a");
                System.out.println("4- Fichar jugador/a o entrenador/a");
                System.out.println("0- Salir");
                System.out.print("Opción: ");
                opcionSubmenu = sc.nextInt();
                salirBucle = true;
                if (opcionSubmenu < 0 || opcionSubmenu > 4) {
                    System.out.println("Opción invalido, seleccione las opciones que se muestra en pantalla");
                    salirBucle = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción invalido, seleccione las opciones que se muestra en pantalla");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);

        return opcionSubmenu;
    }

    //Submenu gestionar mi equipo (opción 1):
    //Se eliminará el equipo de la lista de la aplicación, confirmación por parte del usuario.

    /**
     * @since 1.0
     */
    public static void darBajaEquipo(ArrayList<Equipos> listaEquipos, String nombreEquipo) {
        Scanner sc = new Scanner(System.in);
        cargarEquipos();
        boolean darBaja;
        int indice = 0;
        int temporal = 0;
        System.out.println("¿Qué equipo quieres dar de baja?");
        nombreEquipo = sc.nextLine();
        for (indice )
        for (Equipos eq : listaEquipos) {
            if (eq.getNombre().equals(nombreEquipo)) {
                temporal = indice;
                darBaja = true;
            }
            indice++;
        }
        //si darBaja=true se elimina remove + i

    }
    /*
    for ( Liga l : listaLigas) {
        if (l.getNombre().equals(nombreLiga)) {
            System.out.println("Liga: " + l.getNombre());


            ligaExsistente = true;


        }
    }
        if (!ligaExsistente) {
        System.out.println("No exsiste");
    }
    */

    //Submenu gestionar mi equipo (opción 2):
    //Se pedirá el nombre del presidente y se actualizará los siguientes casos:
    //Si se proporciona el mismo presidente que ya había, mostrará un mensaje de aviso.
    //Si el equipo no tiene ninguna persona asignada a la presidencia, se informará al usuario del hecho con un mensaje.
    /**
     * @since 1.0
     */
    public static void modificarPresidente(ArrayList<Equipos> listaEquipos) {

    }

    //Submenu gestionar mi equipo (opción 3):
    //Prescinde al entrenador, previa confirmación por el usuario.
    //El equipo se queda sin entrenador y este se pasará a formar parte de la lista del mercado de fichajes de la aplicación.
    /**
     * @since 1.0
     */
    public static void destituirEntrenador() {

    }

    //Submenu gestionar mi equipo (opción 4):
    //Preguntará que se quiere fichar, después mostrará todos los jugadores o entrenadores disponibles y se podrá seleccionar quien quiere fichar.
    //Fichar a un jugador o entrenador implica eliminarlo de la lista del mercado de fichajes de la aplicación y agregarlo al equipo que estamos gestionando.
    //(Opcional) Actualizar el fichero.txt al final de la ejecución del programa para que el jugador fichado no esté disponible en el mercado de fichajes en la siguiente ejecución del programa.
    /**
     * @since 1.0
     */
    public static void ficharPersona() {

    }

    /**
     * @since 1.0
     * @param listaFichajes Lista con todos los jugadores y entrenadores guardados
     * @param listaFichados Lista con todos los jugadores y entrenadores fichados en un equipo guardados
     * @param listaEquipos Lista con todos los equipos guardados
     * @param listaLigas Lista con todas las ligas guardadas
     */
    public static void actualizarFichero(ArrayList<Persona> listaFichajes, ArrayList<Persona> listaFichados, ArrayList<Equipos> listaEquipos, ArrayList<Liga> listaLigas){
        String[] rutaArchivos = {"src/ficheros/mercat_fitxatges.txt", "src/ficheros/jugadores_fichados.txt", "src/ficheros/equipos.txt", "src/ficheros/ligas.txt"};
        int i = 0;

        do {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivos[i]))){
                switch (rutaArchivos[i]) {
                    case "src/ficheros/mercat_fitxatges.txt":
                        for (Persona persona : listaFichajes) {
                            if (persona instanceof Jugador) {
                                bw.write(((Jugador)persona).toString() + "\n");
                            } else if (persona instanceof Entrenador) {
                                bw.write(((Entrenador) persona).toString() + "\n");
                            }
                        }
                        break;
                    case "src/ficheros/jugadores_fichados.txt":
                        for (Persona persona : listaFichados) {
                            if (persona instanceof Jugador) {
                                bw.write(((Jugador)persona).toString() + ";" + persona.getNombreEquipo() + "\n");
                            } else if (persona instanceof Entrenador) {
                                bw.write(((Entrenador) persona).toString()+ ";" + persona.getNombreEquipo() + "\n");
                            }
                        }
                        break;
                    case "src/ficheros/equipos.txt":
                        for (Equipos eq : listaEquipos) {
                            bw.write(eq.toString());
                        }
                        break;
                    case "src/ficheros/ligas.txt":
                        for (Liga lg : listaLigas) {
                            bw.write(lg.toString());
                        }
                        break;
                }
                i++;
            } catch (IOException e) {
                System.out.println("Error al escribir el fichero de texto");
            }
        } while (i < 4);
    }

    //Gestionará un conjunto de equipos, mercado de fichajes, y permitirá generar ligas entre estos equipos.
    //Mercado de fichajes en un fichero.

    //Seguir los principios de clean code.
    //Revisar para aplicar patrones de refactoring.
    //Colocar JavaDoc mientras vamos acabando con las funciones o métodos.

    //Cargar todos los equipos y su información relacionada.

    //Clase Main gestionará los menus y dispondrá de los listados con todos los equipos, jugadores y entrenadores disponibles para fichar, y un objeto que represente la liga
    //La información de la liga no se guardará en la ejecución del programa ni de la aplicación.

    //✅ Puntuación:
    //✅ Partidos ganados: 3 puntos
    //✅ Partidos empatados: 1 puntos
    //✅ Partidos perdidos: 0 puntos

    //Se necesita cuantos goles se han generado por cada equipo a favor y en contra, es necesaria para la clasificación.
    //La calificación media de los equipos y la motivación han de influir de alguna manera en las posibilidades de victoria del equipo.

    //Por si somos atrevidos:thumbsup:
    //Aparte de guardar clasificaciones con los resultados de los partidos, goleadores, etc... Podemos crear un sistema capaz de consultar datos de cada partido de manera individual, no solo el resultado final de la liga.
    //Pensar como guardar los goleadores de cada partido y sobre todo en que minuto ha marcado.

    //Adicional:
    //Saber cuantos jugadores se han creado hasta el momento en la aplicación.
    //✅ La clase Jugador y Entrenador que tengan una herencia con una clase general con un nombre coherente.
    //✅ La clase nueva tendrá de método llamado entrenament() que aumentara la motivación en 0.2 puntos.
    //✅ Los jugadores extienden el método entrenamiento de la clase padre.
    //Ademas de ejecutar el código de la clase padre, la calidad del jugador aumentara en 0.1(70%), 0.2(20%) o 0.3(10%) puntos en función de un valor aleatorio.
    //Aparte de realizar incrementos, se mostrará quien ha estado en el resultado.
    //✅ Los entrenadores sobreescribirán completamente el método entrenament() de la clase padre.
    //✅ Si el entrenador es seleccionador nacional aumentará la motivación a 0.3 puntos, si no lo es lo hará a 0.15.
    //Se aplicará cada vez que listemos los jugadores del mercado de fichajes.
    //Por su posición (Orden alfabético). Si tienen la misma posición, ordenaremos de mayor a menor la calidad.
    //Se aplicará cada vez que listemos los jugadores de un equipo.

    //Consideraciones finales:
    //Las clases deben de tener diferentes constructores para adaptarse a los requisitos del enunciado para la creación de objetos.
    //Hace falta ser curioso con la visibilidad de diferentes elementos, intentando que todo el acceso a la información sea siempre la más restrictiva posible.
    //Hace falta asegurarse que cada clase sea responsable de sus propias cosas.
}