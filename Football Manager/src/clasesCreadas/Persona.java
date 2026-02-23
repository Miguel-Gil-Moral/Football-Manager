package clasesCreadas;


public abstract class Persona {
    protected final String nombre, apellido, fechaNacimiento;
    protected double nivMotivacion;
    protected int sueldoSalarial;
    protected String nombreEquipo;

    public Persona(String nombre, String apellido, String fechaNacimiento, double nivMotivacion, int sueldoSalarial) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nivMotivacion = nivMotivacion;
        this.sueldoSalarial = sueldoSalarial;
    }

    @Override
    public String toString() {
        return nombre + ";" +
                apellido + ";" +
                fechaNacimiento + ";" +
                nivMotivacion + ";" +
                sueldoSalarial;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public double getNivMotivacion() {
        return nivMotivacion;
    }

    public int getSueldoSalarial() {
        return sueldoSalarial;
    }

    public abstract void entrenamiento();
}