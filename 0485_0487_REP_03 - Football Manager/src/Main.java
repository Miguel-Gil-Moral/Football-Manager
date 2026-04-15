import clasesCreadas.*;

import java.io.*;
import java.util.*;

/**
 * Clase principal de la aplicacion. Gestiona menus, carga de datos y
 * operaciones generales del sistema.
 *
 * @author Miguel Gil Moral, Mario De Molina Martin
 * @version 1.3
 */
public class Main {
    /**
     * Punto de entrada de la aplicacion.
     *
     * @param args argumentos recibidos por linea de comandos
     */
    public static void main(String[] args) {
        ArrayList<Persona> listaMercado = cargarMercado(), listaFichados = cargarPersonasFichadas();
        ArrayList<Equipos> listaEquipos = cargarEquipos();
        ArrayList<Liga> listaLigas = new ArrayList<>();
        String rol = pedirRol();
        int opcion, opcionSubmenu, numCreados = 0;
        boolean salirBucle = false, salirBucleSubmenu = false, equipoExistente;

        do {
            System.out.println("Welcome to Politècnics Football Manager:");
            switch (rol) {
                case "Administrador":
                    opcion = menuAdmin();
                    switch (opcion) {
                        case 1:
                            verClasificacionLiga(listaLigas, listaEquipos, listaFichados);
                            break;
                        case 2:
                            darAltaEquipo(listaEquipos);
                            numCreados++;
                            break;
                        case 3:
                            darAltaPersona(listaMercado);
                            numCreados++;
                            break;
                        case 4:
                            consultarDatosEquipo(listaEquipos, listaFichados);
                            break;
                        case 5:
                            consultarDatosJugador(listaFichados, listaEquipos);
                            break;
                        case 6:
                            listaLigas.add(disputarNuevaLiga(listaEquipos, listaFichados));
                            break;
                        case 7:
                            realizarEntrenamientoMercado(listaMercado);
                            break;
                        case 8:
                            guardarDatosEquipo(listaFichados, listaEquipos);
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
                            verClasificacionLiga(listaLigas, listaEquipos, listaFichados);
                            break;
                        case 2:
                            String nombreEquipo = pedirNombreEquipo();
                            equipoExistente = revisarEquipo(listaEquipos, nombreEquipo);
                            Equipos equipoSubmenu = null;
                            for (Equipos equipos : listaEquipos) {
                                if (equipos.getNOMBRE().equals(nombreEquipo)) {
                                    equipoSubmenu = equipos;
                                }
                            }
                            if (equipoExistente) {
                                do {
                                    opcionSubmenu = submenuGestorEquipos();
                                    switch (opcionSubmenu) {
                                        case 1:
                                            darBajaEquipo(listaEquipos, listaFichados, listaMercado, equipoSubmenu);
                                            salirBucleSubmenu = true;
                                            break;
                                        case 2:
                                            modificarPresidente(equipoSubmenu);
                                            break;
                                        case 3:
                                            destituirEntrenador(listaMercado, listaFichados, equipoSubmenu);
                                            break;
                                        case 4:
                                            ficharPersona(listaMercado, listaFichados, equipoSubmenu);
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
                            consultarDatosEquipo(listaEquipos, listaFichados);
                            break;
                        case 4:
                            consultarDatosJugador(listaFichados, listaEquipos);
                            break;
                        case 5:
                            transferirJugador(listaFichados, listaEquipos);
                            break;
                        case 6:
                            guardarDatosEquipo(listaFichados, listaEquipos);
                            break;
                        case 0:
                            salirBucle = true;
                            break;
                    }
                    break;
            }
        } while (!salirBucle);
        System.out.println("Se han dado de alta " + numCreados + " personas");
        actualizarFichero(listaMercado);
    }

    /**
     * Carga las personas disponibles en el mercado de fichajes desde fichero.
     *
     * @return Todos los jugadores/as y entrenadores/as guardados de un fichero de texto en una lista de personas
     * @since 1.0
     */
    public static ArrayList<Persona> cargarMercado() {
        String linea;
        String[] separado;
        ArrayList<Persona> listaMercado = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/ficheros/mercat_fitxatges.txt"));
            while ((linea = br.readLine()) != null) {
                separado = linea.split(";");
                if (separado[0].equals("J")) {
                    listaMercado.add(new Jugador(separado[1], separado[2], separado[3], Double.parseDouble(separado[4]),
                            Integer.parseInt(separado[5]), Integer.parseInt(separado[6]),
                            separado[7], Integer.parseInt(separado[8])));
                } else if (separado[0].equals("E")) {
                    listaMercado.add(new Entrenador(separado[1], separado[2], separado[3], Double.parseDouble(separado[4]),
                            Integer.parseInt(separado[5]), Integer.parseInt(separado[6]), Boolean.parseBoolean(separado[7])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo");
        }
        return listaMercado;
    }

    /**
     * Carga los equipos registrados en la aplicacion desde fichero.
     *
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
     * Carga las personas ya fichadas por equipos desde fichero.
     *
     * @return Lista con todos los jugadores y entrenadores fichados en un equipo
     * @since 1.0
     */
    public static ArrayList<Persona> cargarPersonasFichadas() {
        String linea;
        String[] separado;
        ArrayList<Persona> listaFichados = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/ficheros/personas_fichadas.txt"));
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

    /**
     * Solicita al usuario el rol con el que desea acceder a la aplicacion.
     *
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

    /**
     * Muestra las opciones principales del menu de administrador.
     *
     * @return Opcion escogida por el administrador
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

    /**
     * Muestra las opciones de consulta de una liga y ejecuta la accion elegida.
     *
     * @param listaLigas ligas disponibles en la aplicacion
     * @param listaEquipos equipos registrados en la aplicacion
     * @param listaFichados personas fichadas por equipos
     * @since 1.3
     */
    public static void verClasificacionLiga(ArrayList<Liga> listaLigas, ArrayList<Equipos> listaEquipos, ArrayList<Persona> listaFichados) {
        Scanner sc = new Scanner(System.in);
        String nombreLiga; //Es cuando selecciona la liga
        boolean ligaExsistente = false;

        if (listaLigas.isEmpty()) {
            System.out.println("La lista de ligas está vacía. Es por esto que disputarás una nueva liga. Pulsa Enter para empezar.");
            String respuestaDisputarNuevaLiga =  sc.nextLine();
            if (respuestaDisputarNuevaLiga.isEmpty()) {
                listaLigas.add(disputarNuevaLiga(listaEquipos, listaFichados)); //Créditos a Miguel :)))
            }
        } else {
            for (Liga l : listaLigas) {
                System.out.println(l.getNOMBRE());
            }
        }

        int opcion; //Es lo que escoge el usuario
        boolean salirBucle = false, salirBucleOpcion;
        do {
            try {
                System.out.println("Ingrese el nombre del liga actual: ");
                nombreLiga = sc.nextLine();

                for (Liga l : listaLigas) {
                    if (l.getNOMBRE().equals(nombreLiga)) {
                        ligaExsistente = true;
                        salirBucle = true;
                        do {
                            System.out.println("¿Quieres ver toda la clasificación, los goles a favor, en contra o tiempo donde marcaron cada gol?");
                            System.out.printf("%-1s %-16s %-1s %-16s %-1s %-16s %-1s %-16s %-1s %-16s %-1s \n",
                                    "|", "1 = mostrarClasificacion", "|", "2 = consultarGolesFavor", "|", "3 = consultarGolesContra", "|", "4 = verTiempoGol", "|", "0 = salirMenu", "|");

                            System.out.print("Opción: ");
                            opcion = sc.nextInt();
                            salirBucleOpcion = false;
                            switch (opcion) {
                                case 1:
                                    l.mostrarClasificacion();
                                    break;
                                case 2:
                                    l.consultarGolesFavor();
                                    break;

                                case 3:
                                    l.consultarGolesContra();
                                    break;
                                case 4:
                                    verTiempoGol(l, sc);
                                    break;
                                case 0:
                                    System.out.println("Saliendo del menu");
                                    salirBucleOpcion = true;
                                    break;
                                default:
                                    System.out.println("Opción inválida, seleccione entre 0 y 4");
                                    salirBucle = false;
                            }
                        } while (!salirBucleOpcion);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción invalido, seleccione las opciones que se muestra en pantalla");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);
    }

    /**
     * Muestra el detalle temporal de los goles registrados en una liga.
     * @since 1.1
     * @param l liga cuyos goles se desean consultar
     */
    private static void verTiempoGol(Liga l, Scanner sc) {
        boolean salirBucle;
        String equipoRevisar;
        sc.nextLine();
        do {
            System.out.println("¿Que equipo quiere revisar los minutos marcados?:");
            equipoRevisar = sc.nextLine();
            salirBucle = true;
            if (equipoRevisar.isEmpty()) {
                System.out.println("El nombre esta vacío");
                salirBucle = false;
            }
        } while (!salirBucle);

        System.out.println("Mostrar Tiempo Gol:");
        System.out.printf("%-1s %-25s %-1s %-25s %-1s %-25s %-1s %-25s %-1s \n",
                "|", "Equipo Local", "|", "Equipo Visitante", "|", "Minuto del gol", "|", "Equipo goleador", "|");

        ArrayList <String> tiempoGol = l.getTiempoGol();
        for (String tg : tiempoGol) {
            String[] separado =  tg.split(";");
            String equipoLocal = separado[0], equipoVisitante = separado[1];
            if (equipoLocal.equals(equipoRevisar) || equipoVisitante.equals(equipoRevisar)) {
                System.out.printf("%-1s %-25s %-1s %-25s %-1s %-25s %-1s %-25s %-1s \n",
                        "|", separado[0], "|", separado[1], "|", separado[2], "|", separado[3], "|");
            }
        }
    }

    /**
     * Solicita los datos necesarios para dar de alta un nuevo equipo.
     *
     * @param listaEquipos lista de equipos disponibles
     * @since 1.1
     */
    public static void darAltaEquipo(ArrayList<Equipos> listaEquipos) {
        Scanner sc = new Scanner(System.in);
        boolean salirBucle, equipoExiste;
        String nombre = "", ciudad = "", nombreEstadio, nombrePresidente;
        int anyoFundacion = 0;

        do {
            try {
                System.out.print("Ingrese el nombre del equipo: ");
                nombre = sc.nextLine();
                System.out.print("¿En que año se fundo el equipo?: ");
                anyoFundacion = sc.nextInt();
                sc.nextLine();
                System.out.print("¿En que ciudad reside?: ");
                ciudad = sc.nextLine();
                salirBucle = true;
                equipoExiste = revisarEquipo(listaEquipos, nombre);
                if (anyoFundacion < 1850 || anyoFundacion > 2026) {
                    System.out.println("Opción invalida, escriba un año entre 1850 y 2026");
                    salirBucle = false;
                } else if (equipoExiste) {
                    System.out.println("El nombre del equipo ya existe, escriba otro");
                    salirBucle = false;
                } else if (nombre.isEmpty() || ciudad.isEmpty()) {
                    System.out.println("El equipo o la ciudad estan vacíos, por favor rellenalos");
                    salirBucle = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción invalida, escriba solo números enteros");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);

        System.out.print("Escribe el nombre del estadio (Opcional): ");
        nombreEstadio = sc.nextLine();
        System.out.print("Escribe el nombre del presidente (Opcional): ");
        nombrePresidente = sc.nextLine();

        if (nombreEstadio.isEmpty() && nombrePresidente.isEmpty()) {
            nombreEstadio = "null";
            nombrePresidente = "null";
        } else if (nombrePresidente.isEmpty()) {
            nombrePresidente = "null";
        } else if (nombreEstadio.isEmpty()) {
            nombreEstadio = "null";
        }

        Equipos equipos = new Equipos(nombre, anyoFundacion, ciudad, nombreEstadio, nombrePresidente);

        listaEquipos.add(equipos);

    }

    /**
     * Solicita los datos de una nueva persona y la agrega al mercado.
     *
     * @param listaMercado lista de personas disponibles para fichar
     * @since 1.0
     */
    public static void darAltaPersona(ArrayList<Persona> listaMercado) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        boolean salirBucle, nombreVacio;
        String apellido;

        do {
            try {
                System.out.println("Que rol quiere dar de alta:");
                System.out.println("1- Jugador/a");
                System.out.println("2- Entrenador/a");
                System.out.print("Opción: ");
                int opcion = sc.nextInt();
                sc.nextLine();
                String nombre = pedirNombrePersona(sc);
                do {
                    System.out.print("Ingrese el apellido de la persona: ");
                    apellido = sc.nextLine();
                    nombreVacio = false;
                    if (apellido.isEmpty()) {
                        System.out.print("Por favor, escriba el apellido del jugador: ");
                        nombreVacio = true;
                    }
                } while (nombreVacio);
                String fechaNacimiento = asignarNacimiento(sc);
                int sueldoSalarial = asignarSueldoSalarial(sc);
                salirBucle = true;
                switch (opcion) {
                    case 1:
                        Jugador jugador = new Jugador(nombre, apellido, fechaNacimiento, 5, sueldoSalarial,
                                pedirDorsalJugador(sc), asignarPosicionJugador(sc), random.nextInt(100));
                        listaMercado.add(jugador);
                        break;
                    case 2:
                        Entrenador entrenador = new Entrenador(nombre, apellido, fechaNacimiento, 5,
                                sueldoSalarial, asignarTorneosGanado(sc), esSeleccionadorNacional(sc));
                        listaMercado.add(entrenador);
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
    }

    /**
     * Solicita y valida el nombre de una persona.
     *
     * @param sc lector de entrada utilizado en la operacion
     * @return El nombre de la persona
     * @since 1.0
     */
    private static String pedirNombrePersona(Scanner sc) {
        String nombre;
        boolean salirBucle;
        System.out.print("Ingrese el nombre de la persona: ");
        do {
            nombre = sc.nextLine();
            salirBucle = true;
            if (nombre.isEmpty()) {
                System.out.print("Por favor, escriba el nombre de la persona: ");
                salirBucle = false;
            }
        } while (!salirBucle);
        return nombre;
    }

    /**
     * Solicita y construye la fecha de nacimiento de una persona.
     *
     * @param sc lector de entrada utilizado en la operacion
     * @return Fecha de nacimiento en formato dd/MM/aaaa de tipo String
     * @since 1.0
     */
    public static String asignarNacimiento(Scanner sc) {
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
     * Solicita y valida el sueldo salarial de una persona.
     *
     * @param sc lector de entrada utilizado en la operacion
     * @return El sueldo que ganara la persona
     * @since 1.2
     */
    public static int asignarSueldoSalarial(Scanner sc) {
        boolean salirBucle;
        int sueldoSalarial = 0;
        do {
            try {
                System.out.print("Introduzca sueldo de la persona: ");
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
     * Solicita y valida el dorsal de un jugador.
     *
     * @param sc lector de entrada utilizado en la operacion
     * @return Número del dorsal no escogido para el jugador
     * @since 1.0
     */
    public static int pedirDorsalJugador(Scanner sc) {
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
     * Solicita y devuelve la posicion de juego de un jugador.
     *
     * @param sc lector de entrada utilizado en la operacion
     * @return Posicion asignada al jugador de estos siguientes: <ul>
     * <li>POR: Posicion de portero</li>
     * <li>DEF: Posicion de defensa</li>
     * <li>MIG: Posicion de mediocampista</li>
     * <li>DAV: Posicion de delantero</li>
     * </ul>
     * @since 1.0
     */
    public static String asignarPosicionJugador(Scanner sc) {
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
     * Solicita el número de torneos ganados por un entrenador.
     *
     * @param sc lector de entrada utilizado en la operacion
     * @return La cantidad de torneos que el entrenador ha ganado
     * @since 1.0
     */
    public static int asignarTorneosGanado(Scanner sc) {
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
     * Solicita si un entrenador ha sido seleccionador nacional.
     *
     * @param sc lector de entrada utilizado en la operacion
     * @return Resultado sobre el entrenador de estos siguientes:<ul>
     * <li>True: El entrenador ha estado seleccionador nacional</li>
     * <li>False: El entrenador no ha estado seleccionador nacional</li>
     * </ul>
     * @since 1.0
     */
    public static boolean esSeleccionadorNacional(Scanner sc) {
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

    /**
     * Muestra la información general de un equipo y de sus personas asociadas.
     *
     * @param listaEquipos Lista con todos los equipos existentes
     * @param listaFichados Lista con todos los jugadores fichados por un equipo
     * @since 1.0
     */
    public static void consultarDatosEquipo(ArrayList<Equipos> listaEquipos, ArrayList<Persona> listaFichados) {
        String nombreEquipo = pedirNombreEquipo();
        boolean equipoExistente = false;
        for (Equipos eq : listaEquipos) {
            if (eq.getNOMBRE().equals(nombreEquipo)) {
                equipoExistente = true;
                System.out.println("DATOS EQUIPO:");
                System.out.println("Nombre: " + eq.getNOMBRE());
                System.out.println("Ciudad: " + eq.getCIUDAD());
                System.out.println("Año fundación: " + eq.getANYO_FUNDACION());
                System.out.println("Estadio: " + eq.getNombreEstadio());
                System.out.println("Presidente: " + eq.getNombrePresidente());
                System.out.println();
                System.out.printf("%-1s %-10s %-1s %-11s %-1s %-19s %-1s %-10s %-1s %-9s %-1s %-2s %-1s %-3s %-1s %-3s %-1s %-5s %-1s %-5s %-1s \n",
                        "|", "NOMBRE", "|", "APELLIDO", "|", "FECHA DE NACIMIENTO", "|", "MOTIVACIÓN", "|", "SALARIO", "|", "DORSAL", "|", "CALIDAD", "|", "POSICIÓN", "|", "TORNEOS GANADOS", "|", "SELECCIONADOR NACIONAL", "|");
                for (Persona p : listaFichados) {
                    if (p.getNombreEquipo().equals(nombreEquipo)) { //SI PERSONA ESTÁ EN EL EQUIPO QUE EL USUARIO HA SELECCIONADO,
                        System.out.printf("%-1s %-10s %-1s %-11s %-1s %-19s %-1s %-10.2f %-1s %-9d %-1s",
                                //p.getNivMotivacion() --> %.2f
                                "|", p.getNOMBRE(), "|", p.getAPELLIDO(), "|", p.getFECHA_NACIMIENTO(), "|", p.getNivMotivacion(), "|", p.getSueldoSalarial(), "| ");   //printf, tip de Miguel
                        if (p instanceof Jugador) {     //instanceof, tip de Miguel
                            System.out.printf("%-6d %-1s %-7s %-1s %-8s %-1s %-15s %-1s %-22s %-1s \n",
                                    ((Jugador) p).getDorsal(), "|", ((Jugador) p).getCalidad(), "|", ((Jugador) p).getPosicion(), "|", "-", "|", "-", "|");
                        }
                        if (p instanceof Entrenador) {
                            System.out.printf("%-6s %-1s %-7s %-1s %-8s %-1s %-15s %-1s %-22s %-1s \n",
                                    "-", "|", "-", "|", "-", "|", ((Entrenador) p).getNumTorneosGanados(), "|", ((Entrenador) p).isSeleccionadorNacional(), "|");
                        }
                    }
                }

            }
        }
        if (!equipoExistente) {
            System.out.println("El equipo " + nombreEquipo + " no exsiste");
        }
    }

    /**
     * Busca un jugador concreto de un equipo y muestra sus datos.
     *
     * @param listaFichados Lista con todos los jugadores fichados en un equipo
     * @param listaEquipos  Lista con todos los equipos para la comprobacion de la existencia de ese equipo
     * @since 1.2
     */
    public static void consultarDatosJugador(ArrayList<Persona> listaFichados, ArrayList<Equipos> listaEquipos) {

        Scanner sc = new Scanner(System.in);
        boolean equipoEncontrado, jugadorEncontrado = false;

        //1) PEDIR nombre de equipo

        String nombreEquipo = pedirNombreEquipo();

        /*2) BUSCAR equipo en listaEquipos
         *    - si NO existe: informar y terminar*/

        equipoEncontrado = revisarEquipo(listaEquipos, nombreEquipo);

        //3) PEDIR nombre de jugador

        if (equipoEncontrado) {
            String nombreJugador = pedirNombrePersona(sc);

            //4) PEDIR dorsal

            int dorsal = pedirDorsalJugador(sc);

            /*  5) BUSCAR jugador por (nombre + dorsal) en la fuente correcta
                    - si NO existe: informar y terminar */

            for (Persona pr : listaFichados) {
                if (pr.getNOMBRE().equals(nombreJugador) && ((Jugador) pr).getDorsal() == dorsal) {    //6) MOSTRAR datos comunes (Persona) + datos exclusivos (Jugador)
                    jugadorEncontrado = true;
                    System.out.println("Nombre: " + pr.getNOMBRE());
                    System.out.println("Apellido: " + pr.getAPELLIDO());
                    System.out.println("Fecha Nacimiento: " + pr.getFECHA_NACIMIENTO());
                    System.out.println("Motivación: " + pr.getNivMotivacion());
                    System.out.println("Sueldo: " + pr.getSueldoSalarial());
                    System.out.println("Dorsal: " + ((Jugador) pr).getDorsal());       //mostrar los datos (atributos) exclusivos de jugador
                    System.out.println("Posición: " + ((Jugador) pr).getPosicion());  //mostrar los datos (atributos) exclusivos de jugador
                    System.out.println("Calidad: " + ((Jugador) pr).getCalidad());   //mostrar los datos (atributos) exclusivos de jugador
                    System.out.println("Equipo: " + pr.getNombreEquipo());
                }
            }
            if (!jugadorEncontrado) {
                System.out.println("El jugador junto con su dorsal no se ha encontrado");
            }
        } else {
            System.out.println("¡No exsiste el equipo " + nombreEquipo + "!...");

            //7) TERMINAR (el menú exterior vuelve a mostrarse por su bucle)
        }
    }

    /**
     * Solicita el nombre de un equipo y válida que no este vacio.
     *
     * @since 1.0
     * @return Nombre del equipo insertado por el usuario
     */
    private static String pedirNombreEquipo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Escribe el nombre del equipo: ");
        String nombreEquipo;

        boolean equipoNoVacio;
        do {
            nombreEquipo = sc.nextLine();
            if (nombreEquipo.isEmpty()) {
                equipoNoVacio = false;
                System.out.println("El equipo está vacío");
            } else {
                equipoNoVacio = true;
            }
        }  while (!equipoNoVacio);
        return nombreEquipo;
    }

    /**
     * Crea una nueva liga y prepara sus partidos iniciales.
     *
     * @param listaEquipos equipos disponibles para participar
     * @param listaFichados personas fichadas por equipos
     * @return liga creada y preparada
     * @since 1.0
     */
    public static Liga disputarNuevaLiga(ArrayList<Equipos> listaEquipos, ArrayList<Persona> listaFichados) { //CLASES Y LISTAS CORRESPONDIENTES
        Scanner sc = new Scanner(System.in);
        boolean salirBucle;
        String nombre;
        int cantidadEquipos = 0;

        System.out.print("Escoja un nombre para la liga: ");
        do {
            nombre = sc.nextLine();
            salirBucle = true;
            if (nombre.isEmpty()) {
                System.out.print("El nombre de la liga esta vacío, escriba un nombre: ");
                salirBucle = false;
            }
        } while (!salirBucle);

        do {
            try {
                System.out.print("¿Cuantos equipos participaran?: ");
                cantidadEquipos = sc.nextInt();
                salirBucle = true;
                if (cantidadEquipos % 2 != 0) {
                    System.out.println("Cantidad incorrecta, coloque un número sea par");
                    salirBucle = false;
                } else if (cantidadEquipos <= 0) {
                    System.out.println("Cantidad incorrecta, coloque un numero mayor a 0");
                    salirBucle = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción incorrecta, escriba la cantidad de equipos en números enteros");
                sc.next();
                salirBucle = false;
            }
        } while (!salirBucle);

        Liga liga = new Liga(nombre, cantidadEquipos);

        prepararPartidos(liga, listaEquipos, listaFichados);

        return liga;
    }

    /**
     * Prepara los datos necesarios para disputar los partidos de una liga.
     *
     * @since 1.0
     * @param liga Liga con toda la información de sus atributos
     * @param listaEquipos Lista con todos los equipos que participaran
     * @param listaFichados Lista con todas las personas fichadas en un equipo para sacar su motivacion y calidad
     */
    public static void prepararPartidos(Liga liga, ArrayList<Equipos> listaEquipos, ArrayList<Persona> listaFichados) {
        String[][] equipos = liga.agregarEquipos(listaEquipos);

        int tamanyoEquipos = getTamanyoEquipos(listaFichados);

        String[][] equiposMotivacion = new String[liga.getCANTIDAD_EQUIPOS()][tamanyoEquipos + 1];
        String[][] equiposCalidad = new String[liga.getCANTIDAD_EQUIPOS()][tamanyoEquipos + 1];
        String[][] mediaEquipo = new String[liga.getCANTIDAD_EQUIPOS()][3];

        for (int i = 0; i < liga.getCANTIDAD_EQUIPOS(); i++) {
            Arrays.fill(equiposMotivacion[i], "0");
            Arrays.fill(equiposCalidad[i], "0");
            Arrays.fill(mediaEquipo[i], "0");
        }
        int posicionNombreEquipo = 0;
        for (Equipos eq : listaEquipos) {
            if (posicionNombreEquipo < liga.getCANTIDAD_EQUIPOS()) {
                equiposMotivacion[posicionNombreEquipo][0] = eq.getNOMBRE();
                equiposCalidad[posicionNombreEquipo][0] = eq.getNOMBRE();
                mediaEquipo[posicionNombreEquipo][0] = eq.getNOMBRE();
                posicionNombreEquipo++;
            }
        }

        int fila = 0, columna = 1;
        for (Persona pr : listaFichados) {
            if (!pr.getNombreEquipo().equals(equipos[fila][0])) { //Recomendado por el refactor
                columna = 1;
                fila++;
            }
            equiposMotivacion[fila][columna] = String.valueOf(pr.getNivMotivacion());
            if (pr instanceof Jugador) {
                equiposCalidad[fila][columna] = String.valueOf(((Jugador) pr).getCalidad());
            }
            columna++;
        }

        for (Equipos eq : listaEquipos) {
            for (int posicion = 0; posicion < liga.getCANTIDAD_EQUIPOS(); posicion++) {
                mediaEquipo[posicion][1] = String.valueOf(eq.calcularMediaEquipo(equiposCalidad, posicion));
                mediaEquipo[posicion][2] = String.valueOf(calcularMediaMotivacion(equiposMotivacion, posicion));
            }
        }

        String[][] resultadoPartidos = liga.disputarPartidos(aumentarProbabilidadGol(mediaEquipo));

        actualizarPuntuacion(resultadoPartidos, liga.getCANTIDAD_EQUIPOS(), equipos);

        liga.setEquipos(equipos);
    }

    /**
     * Calcula el tamaño maximo detectado en las plantillas de los equipos.
     *
     * @param listaFichados personas fichadas por equipos
     * @return Tamaño maximo de plantilla encontrada
     */
    public static int getTamanyoEquipos(ArrayList<Persona> listaFichados) {
        String equipo, equipoActual = "";
        int tamanyoEquipos = 1, tamanyoEquipoActual = 1;
        for (Persona p : listaFichados) {
            equipo = p.getNombreEquipo();
            if (p.getNombreEquipo().equals(equipoActual)) {
                if (tamanyoEquipos < tamanyoEquipoActual) {
                    tamanyoEquipos = tamanyoEquipoActual;
                }
            } else {
                tamanyoEquipoActual = 1;
                equipoActual = equipo;
            }
            tamanyoEquipoActual++;
        }
        return tamanyoEquipos;
    }

    /**
     * Calcula la media de motivacion de un equipo dentro de una matriz.
     *
     * @param equiposMotivacion matriz con las motivaciones de los equipos
     * @param posicion posicion del equipo dentro de la matriz
     * @return media de motivacion calculada
     */
    public static double calcularMediaMotivacion(String[][] equiposMotivacion, int posicion) {
        double suma = 0;
        for (int i = 1; i < equiposMotivacion[0].length; i++) {
            suma += Double.parseDouble(equiposMotivacion[posicion][i]);
        }
        return suma / equiposMotivacion.length;
    }

    /**
     * Genera la probabilidad de gol de cada equipo a partir de sus medias.
     *
     * @param mediaEquipo matriz con nombre, media de calidad y motivacion
     * @return matriz con equipos y su probabilidad de gol
     */
    public static String[][] aumentarProbabilidadGol(String[][] mediaEquipo) {
        int fila = 0;
        String[][] probabilidadesEquipos = new String[mediaEquipo.length][2];
        for (String[] str : mediaEquipo) {
            double probabilidadGol = 0.03;
            if (fila < mediaEquipo.length) {
                probabilidadesEquipos[fila][0] = str[0];
                double mediaCalidad = Double.parseDouble(str[1]);
                if (mediaCalidad <= 100 && mediaCalidad > 60) {
                    probabilidadGol += 0.0025;
                } else if (mediaCalidad <= 30 && mediaCalidad >= 0) {
                    probabilidadGol -= 0.0025;
                }
                double mediaMotivacion = Double.parseDouble(str[2]);
                if (mediaMotivacion <= 10 && mediaMotivacion > 6) {
                    probabilidadGol += 0.0025;
                } else if (mediaMotivacion <= 3 && mediaMotivacion >= 0) {
                    probabilidadGol -= 0.0025;
                }
                probabilidadesEquipos[fila][1] = String.valueOf(probabilidadGol);
                fila++;
            }
        }
        return probabilidadesEquipos;
    }

    /**
     * Actualiza puntos y estadisticas de los equipos tras disputar los partidos.
     *
     * @param resultadoPartidos resultados obtenidos en la jornada
     * @param CANTIDAD_EQUIPOS cantidad de equipos participantes
     * @param equipos matriz de equipos con sus estadisticas
     */
    public static void actualizarPuntuacion(String[][] resultadoPartidos, int CANTIDAD_EQUIPOS, String[][] equipos) {
        System.out.println("Otorgando puntos...");
        for (String[] rs : resultadoPartidos) {
            for (int i = 0; i < CANTIDAD_EQUIPOS; i++) {
                int puntosLocal = 0, puntosVisitante = 0;
                int golesLocal = Integer.parseInt(rs[1]);
                int golesVisitante = Integer.parseInt(rs[2]);
                if (golesLocal > golesVisitante) {
                    puntosLocal += 3;
                } else if (golesLocal < golesVisitante) {
                    puntosVisitante += 3;
                } else {
                    puntosLocal += 1;
                    puntosVisitante += 1;
                }
                otorgarPuntos(equipos, rs, i, puntosLocal, golesLocal, golesVisitante, puntosVisitante);
            }
        }
    }

    /**
     * Aplica a un equipo concreto los puntos y estadisticas derivados de un partido.
     *
     * @since 1.0
     * @param equipos El equipo asignado con sus puntos, partidos disputados, goles a favor y en contra
     * @param rs El resultado del partido el cual quedaron los equipos
     * @param i El número de la iteracion para la posicion del array equipos
     * @param puntosLocal Puntos totales que lleva el equipo local
     * @param golesLocal Goles marcados durante el partido por parte del equipo local
     * @param golesVisitante Goles marcados durante el partido por parte del equipo visitante
     * @param puntosVisitante Puntos totales que lleva el equipo visitante
     */
    private static void otorgarPuntos(String[][] equipos, String[] rs, int i, int puntosLocal, int golesLocal, int golesVisitante, int puntosVisitante) {
        int puntos = Integer.parseInt(equipos[i][1]);
        int partidosDisputados = Integer.parseInt(equipos[i][2]);
        int golesFavor = Integer.parseInt(equipos[i][3]);
        int golesContra = Integer.parseInt(equipos[i][4]);
        if (rs[0].equals(equipos[i][0])) {
            partidosDisputados++;
            puntos += puntosLocal;
            golesFavor += golesLocal;
            golesContra += golesVisitante;
        } else if (rs[3].equals(equipos[i][0])) {
            partidosDisputados++;
            puntos += puntosVisitante;
            golesFavor += golesVisitante;
            golesContra += golesLocal;
        }
        equipos[i][1] = String.valueOf(puntos);
        equipos[i][2] = String.valueOf(partidosDisputados);
        equipos[i][3] = String.valueOf(golesFavor);
        equipos[i][4] = String.valueOf(golesContra);
    }

    /**
     * Ejecuta sesiones de entrenamiento sobre las personas del mercado.
     *
     * @since 1.0
     * @param listaMercado Lista con todos los jugadores del mercado para realizar los entrenamientos
     */
    public static void realizarEntrenamientoMercado(ArrayList<Persona> listaMercado) {
        for (Persona p : listaMercado) {
            if (p instanceof Jugador) {
                ((Jugador) p).entrenamiento();
                ((Jugador) p).cambiarDePosicion();
            } else if (p instanceof Entrenador) {
                ((Entrenador) p).entrenamiento();
                ((Entrenador) p).incrementarSalario();
            }
        }
    }

    /**
     * Guarda en fichero los equipos y las personas fichadas.
     *
     * @since 1.0
     * @param listaEquipos Lista con todos los equipos para guardar
     * @param listaFichados Lista con todas las personas fichadas en un equipo
     */
    public static void guardarDatosEquipo(ArrayList<Persona> listaFichados, ArrayList<Equipos> listaEquipos) {
        String[] rutaArchivos = {"src/ficheros/personas_fichadas.txt", "src/ficheros/equipos.txt"};
        int i = 0;
        do {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivos[i]))) {
                switch (rutaArchivos[i]) {
                    case "src/ficheros/personas_fichadas.txt":
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
                }
                i++;
            } catch (IOException e) {
                System.out.println("No se ha podido escribir el archivo" + rutaArchivos[i]);
            }
        } while (i < rutaArchivos.length);
        System.out.println("Todos los equipos se han guardado correctamente");
    }

    /**
     * Muestra las opciones principales del gestor de equipos.
     *
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
                if (opcion < 0 || opcion > 6) {
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

    /**
     * Gestiona la transferencia de un jugador entre equipos.
     *
     * @param listaFichados Lista con todos los jugadores fichados en un equipo
     * @param listaEquipos  Lista con todos los equipos creados
     * @since 1.0
     */
    public static void transferirJugador(ArrayList<Persona> listaFichados, ArrayList<Equipos> listaEquipos) {
        Scanner sc = new Scanner(System.in);
        boolean salirBucle, dorsalLibre = true, jugadorEncontrado = false;

        String equipo1, equipo2;
        do {
            System.out.print("Escriba el equipo donde se encuentra el jugador: ");
            equipo1 = sc.nextLine();
            System.out.print("Escriba el equipo donde quiere transferir al jugador: ");
            equipo2 = sc.nextLine();
            salirBucle = true;
            if (equipo1.isEmpty() || equipo2.isEmpty()) {
                System.out.println("Uno de los equipos esta vacío, rellene los campos");
                salirBucle = false;
            }
        } while (!salirBucle);

        boolean equipo1Encontrado = revisarEquipo(listaEquipos, equipo1);
        boolean equipo2Encontrado = revisarEquipo(listaEquipos, equipo2);

        if (equipo1Encontrado && equipo2Encontrado) {
            do {
                try {
                    do {
                        String nombreJugador = pedirNombrePersona(sc);
                        int dorsalJugador = pedirDorsalJugador(sc);
                        for (Persona p : listaFichados) {
                            if (p.getNOMBRE().equals(nombreJugador) && ((Jugador) p).getDorsal() == dorsalJugador) {
                                System.out.println("El jugador se ha encontrado");
                                jugadorEncontrado = true;
                            }
                        }
                        if (jugadorEncontrado) {
                            System.out.print("Escriba el nuevo dorsal del jugador: ");
                            int dorsalNuevo = sc.nextInt();
                            salirBucle = true;
                            dorsalLibre = true;
                            for (Persona pr : listaFichados) {
                                if (((Jugador) pr).getDorsal() == dorsalNuevo && pr.getNombreEquipo().equals(equipo2)) { //(EN UN CONCEPTO SIMILAR AL CASTEO (INT --> FLOAT)) LA MENCIÓN A LAS CLASES HIJAS SE HACE INTRODUCIÉNDOLAS ENTRE PARÉNTESIS.
                                    System.out.println("Dorsal ya existe, escoja otro dorsal");
                                    dorsalLibre = false;
                                }
                            }
                            if (dorsalLibre) {
                                for (Persona persona : listaFichados) {
                                    if (persona.getNOMBRE().equals(nombreJugador) && ((Jugador) persona).getDorsal() == dorsalJugador) {
                                        ((Jugador) persona).setDorsal(dorsalNuevo);
                                        persona.setNombreEquipo(equipo2);
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
        } else {
            System.out.println("Uno o dos equipos no se han encontrado");
        }
    }

    /**
     * Comprueba si un equipo existe dentro de una lista dada.
     *
     * @since 1.0
     * @param listaEquipos Lista con todos los equipos disponibles para la revision
     * @param nombreEquipo El nombre del equipo para revisar si existe en la lista
     * @return Devolvera el resultado de la revision en estos casos: <ul>
     *     <li>True: Se encontro el equipo de los que existen en la lista</li>
     *     <li>False: No se encontro el equipo de los que existen en la lista</li>
     * </ul>
     */
    public static boolean revisarEquipo(ArrayList<Equipos> listaEquipos, String nombreEquipo) {
        boolean encontrado = false;

        for (Equipos eq : listaEquipos) {
            if (eq.getNOMBRE().equals(nombreEquipo)) {
                System.out.println("El equipo que buscas existe");
                encontrado = true;
            }
        }
        return encontrado;
    }

    /**
     * Muestra las opciones de gestion interna de un equipo.
     *
     * @return Opcion escogida por el Gestor de equipos
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

    /**
     * Da de baja un equipo y devuelve sus personas al mercado cuando procede.
     *
     * @since 1.1
     * @param listaEquipos Lista para borrar el equipo
     * @param listaFichados Lista con todos los jugadores que están fichados en el equipo
     * @param listaMercado Lista para trasladar el jugador del equipo eliminado al mercado
     * @param equipoSubmenu Equipo el cual le daremos baja
     */
    public static void darBajaEquipo(ArrayList<Equipos> listaEquipos, ArrayList<Persona> listaFichados, ArrayList<Persona> listaMercado, Equipos equipoSubmenu) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Quieres borrar el equipo " + equipoSubmenu.getNOMBRE() + " de la lista de equipos? («true» o «false»).");
        boolean respuestaUsuario = sc.nextBoolean();
        boolean salirBucle, salirBucleFor;

        do {
            try {
                salirBucle = true;
                if (respuestaUsuario) {
                    do {
                        try {
                            for (Equipos eq : listaEquipos) {
                                if (eq.getNOMBRE().equals(equipoSubmenu.getNOMBRE())) {
                                    listaEquipos.remove(eq);
                                }
                            }
                            salirBucleFor = true;
                        } catch (ConcurrentModificationException e) {
                            salirBucleFor = false;
                        }
                    } while (!salirBucleFor);
                    for (Persona persona : listaFichados) {
                        if (persona.getNombreEquipo().equals(equipoSubmenu.getNOMBRE())) {
                            persona.setNombreEquipo(null);
                            listaMercado.add(persona);
                        }
                    }
                } else {
                    System.out.println(equipoSubmenu.getNOMBRE() + " no se borró.");
                }
            } catch (InputMismatchException e) {
                System.out.println("No es una respuesta válida, escribe «True» o «False».");
                salirBucle = false;
            }
        } while (!salirBucle);
    }

    /**
     * Solicita un nombre de presidente y valida la situacion del equipo indicado.
     *
     * @since 1.1
     * Submenu gestionar mi equipo (opcion 2):
     *     Se pedira el nombre del presidente y se actualizara en los siguientes casos:
     *     Si se proporciona el mismo presidente que ya habia, mostrara un mensaje de aviso.
     *     Si el equipo no tiene ninguna persona asignada a la presidencia, se informara al usuario del hecho con un mensaje.
     * @param equipoSubmenu nombre del equipo que se va a revisar
     */
    public static void modificarPresidente(Equipos equipoSubmenu) {
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
                if (equipoSubmenu.getNombrePresidente() != null && !equipoSubmenu.getNombrePresidente().equalsIgnoreCase(nombrePresidente)) {
                    System.out.println("El equipo " + equipoSubmenu.getNOMBRE() + " ya tiene de presidente a " + nombrePresidente);
                } else {
                    System.out.println("El equipo " + equipoSubmenu.getNOMBRE() + " no tiene de presidente a " + nombrePresidente);
                }
                if (!equipoEncontrado) {
                    System.out.println("El equipo no existe.");
                    repetir = true;
                }
            }
        } while (repetir);
    }

    /**
     * Destituye al entrenador de un equipo y lo devuelve al mercado.
     *
     * @param listaMercado personas disponibles en el mercado
     * @param listaFichados personas fichadas por equipos
     * @param equipoSubmenu nombre del equipo afectado
     * @since 1.1
     */
    public static void destituirEntrenador(ArrayList<Persona> listaMercado, ArrayList<Persona> listaFichados, Equipos equipoSubmenu) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Hacer al entrenador prescindible? (true / false)");
        boolean prescindeEntrenador = sc.nextBoolean();
        boolean fichado = false;
        if (prescindeEntrenador) {
            try {
                for (Persona p : listaFichados) { //mal bucle
                    //contenido en el bucle
                    if (p instanceof Entrenador && p.getNombreEquipo().equals(equipoSubmenu.getNOMBRE())) {
                        p.setNombreEquipo(null);
                        listaFichados.remove(p);
                        listaMercado.add(p);
                        fichado = true;
                    }
                }
                if (!fichado) {
                    System.out.println("No se encontró ningún entrenador en el equipo " + equipoSubmenu.getNOMBRE());
                } else {
                    System.out.println("No se ha destituido al entrenador.");
                }
            } catch (ConcurrentModificationException e) {
                System.out.println("El entrenador ha sido destituido del equipo" + equipoSubmenu.getNOMBRE());
            }
        }
    }
    
    /**
     * Permite fichar una persona del mercado para el equipo indicado.
     *
     * @param listaMercado personas disponibles en el mercado
     * @param listaFichados personas fichadas por equipos
     * @param equiposSubmenu nombre del equipo que realiza el fichaje
     * @since 1.1
     */
    public static void ficharPersona(ArrayList<Persona> listaMercado, ArrayList<Persona> listaFichados, Equipos equiposSubmenu) {
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
                                System.out.println(p.getNOMBRE());
                            }
                        }
                        System.out.println("¿A quién quieres fichar?");
                        quienFichar = sc.nextLine();
                do {
                    try {
                        salirBucleFor = true;
                        for (Persona p : listaMercado) {
                            if (p instanceof Jugador && p.getNOMBRE().equals(quienFichar)) {
                                p.setNombreEquipo(equiposSubmenu.getNOMBRE());
                                listaFichados.add(p);
                                listaMercado.remove(p);
                                fichado = true;
                            }
                        }
                        if (!fichado) {
                            System.out.println(quienFichar + "no se borró de " + equiposSubmenu);
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
                                        p.setNombreEquipo(equiposSubmenu.getNOMBRE());
                                        listaFichados.add(p);
                                        listaMercado.remove(p);
                                        fichado = true;
                                    }
                                }
                                if (!fichado) {
                                    System.out.println(quienFichar + "no se borró de " + equiposSubmenu);
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

    /**
     * Actualiza el fichero del mercado con el estado actual de los fichajes.
     *
     * @since 1.0
     * @param listaMercado Lista con todos los jugadores y entrenadores guardados
     */
    public static void actualizarFichero(ArrayList<Persona> listaMercado){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/ficheros/mercat_fitxatges.txt"))){
            for (Persona persona : listaMercado) {
                if (persona instanceof Jugador) {
                    bw.write(((Jugador)persona).toString() + "\n");
                } else if (persona instanceof Entrenador) {
                    bw.write(((Entrenador) persona).toString() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero del mercado de fichajes");
        }
    }
}
