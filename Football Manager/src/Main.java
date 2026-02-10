import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Miguel Gil Moral, Mario De Molina Martín
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        String rol = pedirRol();

        int opcion, opcionSubmenu;

        System.out.println("Welcome to Politècnics Football Manager:");
        switch(rol){
            case "Administrador":
                opcion = menuAdmin();
                break;
            case "Gestor de Equipos":
                opcion = menuGestorEquipos();
                if (opcion == 2){
                    opcionSubmenu = submenuGestorEquipos();
                }
                break;
        }
    }

    //Al inicio pedir si es Admin o un gestor de equipos, no hace falta poner la contraseña ni nada parecido
    /**
     * @since 1.0
     * @return Nombre del rol escogido por el usuario de estos siguientes: <ul>
     *     <li>Administrador</li>
     *     <li>Gestor de Equipos</li>
     * </ul>
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

    //Un menu principal para admin:
    //      1- Veure classificació lliga actual 🏆
    //
    //      2- Donar d'alta equip
    //
    //      3- Donar d'alta jugador/a o entrenador/a
    //
    //      4- Consultar dades equip
    //
    //      5- Consultar dades jugador/a equip
    //
    //      6- Disputar nova lliga
    //
    //      7- Realitzar sessió entrenament (del mercat fitxatges)
    //
    //      8- Desar dades equips
    //
    //      0- Sortir
    /**
     * @since 1.0
     * @return Opción escogida por el administrador
     */
    public static int menuAdmin(){
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

    //Menu principal para gestor de equipos:
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
     * @since 1.0
     * @return Opción escogida por el Gestor de equipos
     */
    public static int menuGestorEquipos(){
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
     * @since 1.0
     * @return Opción escogida por el Gestor de equipos
     */
    public static int submenuGestorEquipos(){
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

    //Gestionará un conjunto de equipos, mercado de fichajes, y permitirá generar ligas entre estos equipos.
    //Mercado de fichajes en un fichero

    //Seguir los principios de clean code
    //Revisar para aplicar patrones de refactoring
    //Colocar JavaDoc mientras vamos acabando con las funciones o métodos

    //Cargar jugadores y entrenadores disponibles del fichero al iniciar el programa
    //Cargar todos los equipos y su información relacionada

    //Clase Main gestionará los menus y dispondrá de los listados con todos los equipos, jugadores y entrenadores disponibles para fichar, y un objeto que represente la liga
    //La información de la liga no se guardará en la ejecución del programa ni de la aplicación

    //Clase Jugadores y Entrenadores
    //Atributos (Los dos): nombre, apellido, día de nacimiento, nivel motivación(1-10), sueldo salarial
    //Atributos (Entrenador): número torneos ganados, ha estado seleccionador nacional(booleano)
    //Atributos (Jugadores): Dorsal, posición en la que juegan (POR, DEF, MIG, DAV), puntuación del 30 al 100, calidad.
    //Los atributos de nombre, apellido y el día de nacimiento no se pueden modificar cuando se da de alta el jugador.

    //Método jugadores: canviDePosicio(), tendrá un 5 de probabilidad de generar un cambio de posición del jugador. Si se produce, saldrá un mensaje y hacer el cambios ademas de sumar 1 punto de calidad del jugador.
    //Método entrenadores: incrementarSou(), incrementara el salario actual en un 0,5%

    //Clase equipos
    //Atributos: nombre, año fundación, ciudad, nombre del estadio(opcional), nombre presidente(opcional)
    //Podemos crear equipos sin indicar obligatoriamente los campos opcionales
    //Los equipos de los datos anteriores, tienen un entrenador y un listado de jugadores

    //Método equipos: calcularMediaEquipo(), ha de calcular la media del equipo en base a la calidad de los jugadores

    //Menu principal de admin (opción 2):
    //Se pedirá el primer nombre para verificar que no este dado de alta en la aplicación.
    //Solo se pedirá el resto de datos si el equipo no existe, en caso contrario se mostrará un mensaje de error y volverá a pedir otro nombre, se repetirá tantas veces hasta que ponga un nombre que exista.
    //Cuando tenga el nombre del equipo, pedirá los demás datos principales, estarán también las dos opciones opcionales.

    //Menu principal de admin (opción 3):
    //Preguntará si quiere dar de alta a un jugador o a un entrenador.
    //Al dar de alta, todos los datos serán obligatorio.
    //Para asegurar que todos los valores són valídos, la calidad del jugador se generará con un número aleatorio, la motivación comenzará siempre en 5, y los valores de las posiciones se extraerán de la clase Jugador.
    //El nuevo jugador o entrenador creado se guardará en una lista que contiene el mercado de fichajes.
    //(Opcional) Actualizar el fichero.txt al final de la ejecución del programa para que los jugadores o entrenadores estén disponibles en el mercado para la siguiente ejecución del programa.

    //Menu principal de admin (opción 4):
    //Pedirá que equipo se quieren consultar los datos (por el nombre). Si no se encuentra se mostrará un mensaje de error y volverá al menú principal.
    //Si el equipo se encuentra se mostrará todos los datos incluyendo la del entrenador y el listado de los jugadores.

    //Menu principal de admin (opción 5):
    //Pedirá por el nombre que equipo quien quiere consultar un jugador por su nombre. Si no se encuentra el equipo mostrará un mensaje de error y se volverá al menu principal.
    //Si el equipo se encuentra se pedirá el nombre y el dorsal del jugador. Con estos datos se buscará jugador del equipo y se mostrará sus datos.
    //Si el jugador no se encuentra se avisará al usuario y se volverá al menu principal.

    //Menu principal de admin (opción 8):
    //Se guardará los datos de todos los equipos de la aplicación incluyendo la suya y todos los datos de los entrenadores y los jugadores para poder recuperarlos para la siguiente ejecución del programa.
    //El formato y la cantidad del fichero es totalmente libre.

    //Menu principal de gestor de equipos (opción 2):
    //Al seleccionar la opción, se pedirá el nombre del equipo. Si no se encuentra se mostrará un mensaje de error y se volverá al menu principal.
    //Si se encuentra el equipo, se mostrará un submenu específico.

    //Submenu gestionar mi equipo (opción 1):
    //Se eliminará el equipo de la lista de la aplicación, confirmación por parte del usuario.

    //Submenu gestionar mi equipo (opción 2):
    //Se pedirá el nombre del presidente y se actualizará los siguientes casos:
    //Si se proporciona el mismo presidente que ya había, mostrará un mensaje de error.
    //Si el equipo no tiene ninguna persona asignada a la presidencia, se informará al usuario del hecho con un mensaje.

    //Submenu gestionar mi equipo (opción 3):
    //Prescinde al entrenador, previa confirmación por el usuario. El equipo se queda sin entrenador y este se pasará a formar parte de la lista del mercado de fichajes de la aplicación.

    //Submenu gestionar mi equipo (opción 4):
    //Preguntará que se quiere fichar, después mostrará todos los jugadores o entrenadores disponibles y se podrá seleccionar quien quiere fichar.
    //Fichar a un jugador o entrenador implica eliminarlo de la lista del mercado de fichajes de la aplicación y agregarlo al equipo que estamos gestionando.
    //(Opcional) Actualizar el fichero.txt al final de la ejecución del programa para que el jugador fichado no esté disponible en el mercado de fichajes en la siguiente ejecución del programa.

    //Menu principal admin (opción 7):
    //Permitirá actualizar la calidad y el nivel de motivación de los jugadores y entrenadores disponibles al mercado de fichajes.
    //Se necesita recorrer la lista de jugadores y entrenadores disponibles y ejecutar el método entrenament() para cada elemento de la lista.
    //Según si es jugador o entrenador se ejecutaran los métodos canviPosicio() para los jugadores, y incrementarSou() para los entrenadores.

    //Menu principal gestor de equipos (opción 5):
    //Asegurar que el equipo donde el jugador esta y el equipo donde se quiere transferir existan.
    //En caso afirmativo se pedirá el nuevo dorsal del jugador transferido (habrá que verificar si está disponible).
    //Se pedirá el dorsal hasta que se proporcione uno que esté disponible.

    //Clase Lliga:
    //Atributos: Nombre, cantidad de equipos, lista de equipos.
    //Métodos: agregarEquipos(), disputarPartidos(), consultarGolesFavor(), consultarGolesContra(), mostrarClasificación.

    //Menu principal gestor de equipos(opción 1):
    //Mostrará la clasificación de la liga actual, mostrará el nombre del equipo, puntos, partidos, goles a favor y goles en contra.
    //Debe de estar ordenada por puntos y en caso de tener mismos puntos, diferencia entre goles a favor y en contra.

    //Menu principal de admin (opción 6):
    //Pedirá los datos básicos para crear una nueva liga: Nombre, número de equipos que participaran.
    //Se le asignará al objeto "Lliga" de la clase a la aplicación.
    //Una vez creada la liga, se pedirá a todos los equipos que participen, asegurándose de no agregar un equipo repetido.
    //Una vez agregado todos los equipos a la liga, se disputarán automáticamente los partidos cuantas sean necesarios para completar la liga(Podemos hacer que puedan hacer un partido con cada uno o hacer salida y vuelta).

    //Puntuación:
    //Partidos ganados: 3 puntos
    //Partidos empatados: 1 puntos
    //Partidos perdidos: 0 puntos

    //Se necesita cuantos goles se han generado por cada equipo a favor y en contra, es necesaria para la clasificación.
    //La calificación media de los equipos y la motivación han de influir de alguna manera en las posibilidades de victoria del equipo.

    //Por si somos atrevidos:thumbsup:
    //Aparte de guardar clasificaciones con los resultados de los partidos, goleadores, etc... Podemos crear un sistema capaz de consultar datos de cada partido de manera individual, no solo el resultado final de la liga.
    //Pensar como guardar los goleadores de cada partido y sobre todo en que minuto ha marcado.

    //Adicional:
    //Saber cuantos jugadores se han creado hasta el momento en la aplicación.
    //La clase Jugador y Entrenador que tengan una herencia con una clase general con un nombre coherente.
    //La clase nueva tendrá de método llamado entrenament() que aumentara la motivación en 0.2 puntos.
    //Los jugadores extienden el método entrenamiento de la clase padre.
    //Ademas de ejecutar el código de la clase padre, la calidad del jugador aumentara en 0.1(70%), 0.2(20%) o 0.3(10%) puntos en función de un valor aleatorio.
    //Aparte de realizar incrementos, se mostrará quien ha estado en el resultado.
    //Los entrenadores sobreescribirán completamente el método entrenament() de la clase padre.
    //Si el entrenador es seleccionador nacional aumentará la motivación a 0.3 puntos, si no lo es lo hará a 0.15.
    //Implementar métodos equals() y hashcode() de los jugadores con la finalidad de crear 1 o más comparadores para poder ordenarlos en diferentes partes de la aplicación.
    //Dos jugadores se consideran iguales si coinciden con el mismo nombre y su dorsal.
    //Podemos ordenar los jugadores de dos maneras diferentes:
    //Por su calidad (mayor a menor). Si son iguales se ordenara de mayor a menor la motivación, y si también son iguales se ordena alfabéticamente por el apellido.
    //Se aplicará cada vez que listemos los jugadores del mercado de fichajes.
    //Por su posición (Orden alfabético). Si tienen la misma posición, ordenaremos de mayor a menor la calidad.
    //Se aplicará cada vez que listemos los jugadores de un equipo.

    //Consideraciones finales:
    //Las clases deben de tener diferentes constructores para adaptarse a los requisitos del enunciado para la creación de objetos.
    //Hace falta ser curioso con la visibilidad de diferentes elementos, intentando que todo el acceso a la información sea siempre la más restrictiva posible.
    //Hace falta asegurarse que cada clase sea responsable de sus propias cosas.
}