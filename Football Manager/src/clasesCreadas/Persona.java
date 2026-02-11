package clasesCreadas;

import java.time.LocalDate;

public abstract class Persona {
    protected String nombre, apellido;
    protected LocalDate fechaNacimiento;
    protected double nivMotivacion, sueldoSalarial;

    protected void entrenamiento() {
        this.nivMotivacion += 0.2;
    }
}
