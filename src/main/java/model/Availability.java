package model;

/**
 * Entidad que representa una franja horaria de disponibilidad semanal de un proveedor.
 */
public class Availability extends BaseEntity {
    // Identificador único de la disponibilidad.
    private Integer idAvailability;

    // Hora desde la cual el proveedor está disponible.
    private Hour fromHour;

    // Hora hasta la cual el proveedor está disponible.
    private Hour untilHour;

    // Semana o día de la semana al que corresponde esta disponibilidad.
    private Week week;

    // Proveedor al que pertenece esta disponibilidad.
    private Provider provider;

    public Integer getIdAvailability() {
        return this.idAvailability;
    }

    public void setIdAvailability(Integer idAvailability) {
        this.idAvailability = idAvailability;
    }

    public Hour getFromHour() {
        return this.fromHour;
    }

    public void setFromHour(Hour fromHour) {
        this.fromHour = fromHour;
    }

    public Hour getUntilHour() {
        return this.untilHour;
    }

    public void setUntilHour(Hour untilHour) {
        this.untilHour = untilHour;
    }

    public Week getWeek() {
        return this.week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
