package clasesCreadas;


//Clase equipos
//✅ Atributos: nombre, año fundación, ciudad, nombre del estadio(opcional), nombre presidente(opcional)
//Podemos crear equipos sin indicar obligatoriamente los campos opcionales
//Los equipos de los datos anteriores, tienen un entrenador y un listado de jugadores

import java.util.ArrayList;

public class Equipos {
    private final String nombre, ciudad;
    private final int anyoFundacion;
    private String nombreEstadio;
    private String nombrePresidente;

    public Equipos(String nombre, int anyoFundacion, String ciudad, String nombreEstadio, String nombrePresidente) {
        this.nombre = nombre;
        this.anyoFundacion = anyoFundacion;
        this.ciudad = ciudad;
        this.nombreEstadio = nombreEstadio;
        this.nombrePresidente = nombrePresidente;
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

    //Método equipos: calcularMediaEquipo(), ha de calcular la media del equipo en base a la calidad de los jugadores
    public double calcularMediaEquipo(ArrayList<Double> calidadEquipo) {
        double suma = 0;
        for (Double dbl : calidadEquipo) {
            suma += dbl;
        }
        return suma / calidadEquipo.size();
    }

    @Override
    public String toString() {
        return nombre + ";" +
                anyoFundacion + ";" +
                ciudad + ";" +
                nombreEstadio + ";" +
                nombrePresidente + "\n";
    }
}
