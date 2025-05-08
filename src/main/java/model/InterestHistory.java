package model;

/**
 * Entidad para registrar historial de cambios o detalles adicionales de un interés específico.
 */
public class InterestHistory extends BaseEntity {
    // Identificador único del historial de interés.
    private Integer idInterestHistory;

    // Nombre o detalle del historial de interés.
    private String name;

    // Interés asociado a este registro de historial.
    private Interest interest;

    public Integer getIdInterestHistory() {
        return this.idInterestHistory;
    }

    public void setIdInterestHistory(Integer idInterestHistory) {
        this.idInterestHistory = idInterestHistory;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Interest getInterest() {
        return this.interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }
}
