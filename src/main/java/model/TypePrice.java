package model;

/**
 * Entidad que representa los distintos tipos o modalidades de precio que se pueden aplicar.
 */
public class TypePrice extends BaseEntity {
    // Identificador único del tipo de tarifa/precio.
    private Integer idTypePrice;

    // Nombre de la modalidad de precio.
    private String name;

    // Indicador (por ejemplo 0/1) de si se cobra por hora.
    private Short hour;

    // Indicador de si se cobra por trabajo realizado.
    private Short work;

    // Indicador de si se cobran insumos o materiales.
    private Short inventory;

    // Indicador de si se cobra transporte.
    private Short transportation;

    public Integer getIdTypePrice() {
        return this.idTypePrice;
    }

    public void setIdTypePrice(Integer idTypePrice) {
        this.idTypePrice = idTypePrice;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getHour() {
        return this.hour;
    }

    public void setHour(Short hour) {
        this.hour = hour;
    }

    public Short getWork() {
        return this.work;
    }

    public void setWork(Short work) {
        this.work = work;
    }

    public Short getInventory() {
        return this.inventory;
    }

    public void setInventory(Short inventory) {
        this.inventory = inventory;
    }

    public Short getTransportation() {
        return this.transportation;
    }

    public void setTransportation(Short transportation) {
        this.transportation = transportation;
    }
}
