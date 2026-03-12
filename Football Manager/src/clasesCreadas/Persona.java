package clasesCreadas;


public abstract class Persona {
    protected final String NOMBRE, APELLIDO, FECHA_NACIMIENTO;
    protected double nivMotivacion;
    protected int sueldoSalarial;
    protected String nombreEquipo;

    public Persona(String nombre, String apellido, String fechaNacimiento, double nivMotivacion, int sueldoSalarial) {
        this.NOMBRE = nombre;
        this.APELLIDO = apellido;
        this.FECHA_NACIMIENTO = fechaNacimiento;
        this.nivMotivacion = nivMotivacion;
        this.sueldoSalarial = sueldoSalarial;
    }

    @Override
    public String toString() {
        return NOMBRE + ";" +
                APELLIDO + ";" +
                FECHA_NACIMIENTO + ";" +
                (int) nivMotivacion + ";" +
                sueldoSalarial;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public String getAPELLIDO() {
        return APELLIDO;
    }

    public String getFECHA_NACIMIENTO() {
        return FECHA_NACIMIENTO;
    }

    public double getNivMotivacion() {
        return nivMotivacion;
    }

    public int getSueldoSalarial() {
        return sueldoSalarial;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    //✅ La clase nueva tendrá de método llamado entrenament() que aumentara la motivación en 0.2 puntos.
    public void entrenamiento() {
        this.nivMotivacion += 0.2;
    }
}