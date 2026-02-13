package clasesCreadas;


//Clase equipos
//✅ Atributos: nombre, año fundación, ciudad, nombre del estadio(opcional), nombre presidente(opcional)
//Podemos crear equipos sin indicar obligatoriamente los campos opcionales
//Los equipos de los datos anteriores, tienen un entrenador y un listado de jugadores


//Método equipos: calcularMediaEquipo(), ha de calcular la media del equipo en base a la calidad de los jugadores
public class Equipos {
    private String nombre, ciudad, nombreEstadio, nombrePresidente;
    private int anyoFundacion;


    public Equipos(String nombre, int anyoFundacion, String ciudad) {
        this.nombre = nombre;
        this.anyoFundacion = anyoFundacion;
        this.ciudad = ciudad;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombrePresidente(String nombrePresidente) {
        this.nombrePresidente = nombrePresidente;
    }


    public void setNombreEstadio(String nombreEstadio) {
        this.nombreEstadio = nombreEstadio;
    }


    private void calcularMediaEquipo() {


    }
}
