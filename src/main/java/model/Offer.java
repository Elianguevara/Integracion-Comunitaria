package model;

import java.sql.Timestamp;

/**
 * Entidad que representa una oferta de servicio o producto realizada por un proveedor.
 */
public class Offer extends BaseEntity {
    // Identificador único de la oferta.
    private Integer idOffer;

    // Tipo de proveedor asociado a la oferta.
    private TypeProvider typeProvider;

    // Nombre de la oferta.
    private String name;

    // Fecha y hora de apertura de la oferta.
    private Timestamp dateOpen;

    // Fecha y hora de cierre de la oferta.
    private Timestamp dateClose;

    // Descripción detallada de la oferta.
    private String description;

    public Integer getIdOffer() {
        return this.idOffer;
    }

    public void setIdOffer(Integer idOffer) {
        this.idOffer = idOffer;
    }

    public TypeProvider getTypeProvider() {
        return this.typeProvider;
    }

    public void setTypeProvider(TypeProvider typeProvider) {
        this.typeProvider = typeProvider;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDateOpen() {
        return this.dateOpen;
    }

    public void setDateOpen(Timestamp dateOpen) {
        this.dateOpen = dateOpen;
    }

    public Timestamp getDateClose() {
        return this.dateClose;
    }

    public void setDateClose(Timestamp dateClose) {
        this.dateClose = dateClose;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
