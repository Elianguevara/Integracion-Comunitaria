package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Representa la entidad "petition" que extiende de BaseEntity.
 */
public class Petition extends BaseEntity {

    // ID primario de la tabla "petition".
    private int idPetition;
    // ID del tipo de petición (FK a otra tabla).
    private TypePetition typePetition; // Objeto completo
    // Descripción de la petición.
    private String description;
    // Fecha de inicio de la petición.
    private Date dateSince;
    // Fecha de finalización de la petición.
    private Date dateUntil;
    // ID del cliente (FK a otra tabla).
    private  Customer customer;

    public Petition() {
        super();
    }

    public Petition(User idUserCreate, User idUserUpdate, Timestamp dateCreate, Timestamp dateUpdate, int idPetition, TypePetition typePetition, String description, Date dateSince, Date dateUntil, Customer customer) {
        super(idUserCreate, idUserUpdate, dateCreate, dateUpdate);
        this.idPetition = idPetition;
        this.typePetition = typePetition;
        this.description = description;
        this.dateSince = dateSince;
        this.dateUntil = dateUntil;
        this.customer = customer;
    }

    // Getters y setters

    public int getIdPetition() {
        return idPetition;
    }

    public void setIdPetition(int idPetition) {
        this.idPetition = idPetition;
    }

    public TypePetition getTypePetition() {
        return typePetition;
    }

    public void setTypePetition(TypePetition typePetition) {
        this.typePetition = typePetition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateSince() {
        return dateSince;
    }

    public void setDateSince(Date dateSince) {
        this.dateSince = dateSince;
    }

    public Date getDateUntil() {
        return dateUntil;
    }

    public void setDateUntil(Date dateUntil) {
        this.dateUntil = dateUntil;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
