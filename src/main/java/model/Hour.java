package model;

/**
 * Entidad que representa una hora específica o franja horaria dentro de una semana de trabajo.
 */
public class Hour extends BaseEntity {
    // Identificador único de la hora.
    private Integer idHour;

    // Nombre o descripción de la franja horaria.
    private String name;

    // Semana o configuración semanal a la que pertenece esta hora.
    private Week week;

    public Integer getIdHour() {
        return this.idHour;
    }

    public void setIdHour(Integer idHour) {
        this.idHour = idHour;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Week getWeek() {
        return this.week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }
}
