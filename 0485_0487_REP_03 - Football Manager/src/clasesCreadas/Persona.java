package clasesCreadas;


/**
 * Clase base abstracta para las personas gestionadas por la aplicacion.
 * Reune la información comun de jugadores y entrenadores.
 */
public abstract class Persona {
    protected final String NOMBRE, APELLIDO, FECHA_NACIMIENTO;
    protected double nivMotivacion;
    protected int sueldoSalarial;
    protected String nombreEquipo;

    /**
     * Crea una persona con sus datos identificativos y laborales.
     *
     * @param nombre nombre de la persona
     * @param apellido apellido de la persona
     * @param fechaNacimiento fecha de nacimiento de la persona
     * @param nivMotivacion nivel de motivacion inicial
     * @param sueldoSalarial sueldo salarial asignado
     */
    public Persona(String nombre, String apellido, String fechaNacimiento, double nivMotivacion, int sueldoSalarial) {
        this.NOMBRE = nombre;
        this.APELLIDO = apellido;
        this.FECHA_NACIMIENTO = fechaNacimiento;
        this.nivMotivacion = nivMotivacion;
        this.sueldoSalarial = sueldoSalarial;
    }

    /**
     * Devuelve la representacion textual base de la persona.
     *
     * @return cadena con los datos principales de la persona
     */
    @Override
    public String toString() {
        return NOMBRE + ";" +
                APELLIDO + ";" +
                FECHA_NACIMIENTO + ";" +
                (int) nivMotivacion + ";" +
                sueldoSalarial;
    }

    /**
     * Asigna el equipo actual de la persona.
     *
     * @param nombreEquipo nombre del equipo al que pertenece
     */
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    /**
     * Devuelve el nombre de la persona.
     *
     * @return nombre de la persona
     */
    public String getNOMBRE() {
        return NOMBRE;
    }

    /**
     * Devuelve el apellido de la persona.
     *
     * @return apellido de la persona
     */
    public String getAPELLIDO() {
        return APELLIDO;
    }

    /**
     * Devuelve la fecha de nacimiento de la persona.
     *
     * @return fecha de nacimiento en formato de texto
     */
    public String getFECHA_NACIMIENTO() {
        return FECHA_NACIMIENTO;
    }

    /**
     * Devuelve el nivel actual de motivacion.
     *
     * @return nivel de motivacion actual
     */
    public double getNivMotivacion() {
        return nivMotivacion;
    }

    /**
     * Devuelve el sueldo salarial actual.
     *
     * @return sueldo salarial actual
     */
    public int getSueldoSalarial() {
        return sueldoSalarial;
    }

    /**
     * Devuelve el nombre del equipo asociado.
     *
     * @return nombre del equipo actual
     */
    public String getNombreEquipo() {
        return nombreEquipo;
    }

    /**
     * Aplica el entrenamiento basico de la clase base.
     */
    protected void entrenamiento() {
        this.nivMotivacion += 0.2;
    }
}
