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


    protected void entrenamiento() {
        this.nivMotivacion += 0.2;
    }
}
