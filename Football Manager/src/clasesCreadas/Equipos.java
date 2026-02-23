package clasesCreadas;


//Clase equipos
//✅ Atributos: nombre, año fundación, ciudad, nombre del estadio(opcional), nombre presidente(opcional)
//Podemos crear equipos sin indicar obligatoriamente los campos opcionales
//Los equipos de los datos anteriores, tienen un entrenador y un listado de jugadores


//Método equipos: calcularMediaEquipo(), ha de calcular la media del equipo en base a la calidad de los jugadores
public class Equipos {
    private String nombre;
    private String ciudad;
    private String nombreEstadio;
    private String nombrePresidente;
    private int anyoFundacion;


    public Equipos(String nombre, int anyoFundacion, String ciudad) {
        this.nombre = nombre;
        this.anyoFundacion = anyoFundacion;
        this.ciudad = ciudad;
    }

    public Equipos(String nombre, String ciudad, String nombreEstadio, String nombrePresidente, int anyoFundacion) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.nombreEstadio = nombreEstadio;
        this.nombrePresidente = nombrePresidente;
        this.anyoFundacion = anyoFundacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getNombreEstadio() {
        return nombreEstadio;
    }

    public String getNombrePresidente() {
        return nombrePresidente;
    }

    public int getAnyoFundacion() {
        return anyoFundacion;
    }

    public void setNombrePresidente(String nombrePresidente) {
        this.nombrePresidente = nombrePresidente;
    }


    public void setNombreEstadio(String nombreEstadio) {
        this.nombreEstadio = nombreEstadio;
    }


    public void calcularMediaEquipo() {


    }
}
