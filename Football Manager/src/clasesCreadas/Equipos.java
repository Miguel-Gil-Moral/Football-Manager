package clasesCreadas;


//Clase equipos
//✅ Atributos: nombre, año fundación, ciudad, nombre del estadio(opcional), nombre presidente(opcional)
//Podemos crear equipos sin indicar obligatoriamente los campos opcionales
//Los equipos de los datos anteriores, tienen un entrenador y un listado de jugadores

/**
 * Representa a un equipo de futbol con su informacion institucional.
 */
public class Equipos {
    private final String NOMBRE, CIUDAD;
    private final int ANYO_FUNDACION;
    private String nombreEstadio;
    private String nombrePresidente;

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

    /**
     * Devuelve el anyo de fundacion del equipo.
     *
     * @return anyo de fundacion del equipo
     */
    public int getANYO_FUNDACION() {
        return ANYO_FUNDACION;
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
     * @param equiposCalidad matriz con las calidades de los equipos
     * @param posicion posicion del equipo dentro de la matriz
     * @return media de calidad calculada
     */
    public double calcularMediaEquipo(String[][] equiposCalidad, int posicion) {
        double suma = 0;
        for (int i = 1; i < equiposCalidad[0].length; i++) { //ajuste by Miguel, error explosivo - Mario
            suma += Double.parseDouble(equiposCalidad[posicion][i]);
        }
        return suma / equiposCalidad.length;
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
