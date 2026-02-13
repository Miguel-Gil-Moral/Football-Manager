package clasesCreadas;


public abstract class Persona {
    protected String nombre, apellido, fechaNacimiento;
    protected double nivMotivacion;
    protected int sueldoSalarial;

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

    protected void entrenamiento() {
        this.nivMotivacion += 0.2;
    }
}