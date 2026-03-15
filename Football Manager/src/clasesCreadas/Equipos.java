package clasesCreadas;


//Clase equipos
//✅ Atributos: nombre, año fundación, ciudad, nombre del estadio(opcional), nombre presidente(opcional)
//Podemos crear equipos sin indicar obligatoriamente los campos opcionales
//Los equipos de los datos anteriores, tienen un entrenador y un listado de jugadores

public class Equipos {
    private final String NOMBRE, CIUDAD;
    private final int ANYO_FUNDACION;
    private String nombreEstadio;
    private String nombrePresidente;

    public Equipos(String nombre, int anyoFundacion, String ciudad, String nombreEstadio, String nombrePresidente) {
        this.NOMBRE = nombre;
        this.ANYO_FUNDACION = anyoFundacion;
        this.CIUDAD = ciudad;
        this.nombreEstadio = nombreEstadio;
        this.nombrePresidente = nombrePresidente;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public String getCIUDAD() {
        return CIUDAD;
    }

    public String getNombreEstadio() {
        return nombreEstadio;
    }

    public String getNombrePresidente() {
        return nombrePresidente;
    }

    public int getANYO_FUNDACION() {
        return ANYO_FUNDACION;
    }

    public void setNombrePresidente(String nombrePresidente) {
        this.nombrePresidente = nombrePresidente;
    }

    //Método equipos: calcularMediaEquipo(), ha de calcular la media del equipo en base a la calidad de los jugadores
    public double calcularMediaEquipo(String[][] equiposCalidad, int posicion) {
        double suma = 0;
        for (int i = 1; i < equiposCalidad.length; i++) {
            suma += Double.parseDouble(equiposCalidad[posicion][i]);
        }
        return suma / equiposCalidad.length;
    }

    @Override
    public String toString() {
        return NOMBRE + ";" +
                ANYO_FUNDACION + ";" +
                CIUDAD + ";" +
                nombreEstadio + ";" +
                nombrePresidente + "\n";
    }
}
