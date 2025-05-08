package model;

import java.sql.Timestamp;

/**
 * Clase base con campos de auditoría comunes a todas las entidades.
 */
public abstract class BaseEntity {
    // ID del usuario que creó la entidad.
    private User idUserCreate;

    // ID del usuario que actualizó la entidad por última vez.
    private User idUserUpdate;

    // Fecha de creación de la entidad.
    private Timestamp dateCreate;

    // Fecha de última actualización de la entidad.
    private Timestamp dateUpdate;


    public BaseEntity() {
    }

    public BaseEntity(User idUserCreate, User idUserUpdate, Timestamp dateCreate, Timestamp dateUpdate) {
        this.idUserCreate = idUserCreate;
        this.idUserUpdate = idUserUpdate;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
    }

    public User getIdUserCreate() {
        return this.idUserCreate;
    }

    public void setIdUserCreate(User idUserCreate) {
        this.idUserCreate = idUserCreate;
    }

    public User getIdUserUpdate() {
        return this.idUserUpdate;
    }

    public void setIdUserUpdate(User idUserUpdate) {
        this.idUserUpdate = idUserUpdate;
    }

    public Timestamp getDateCreate() {
        return this.dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Timestamp getDateUpdate() {
        return this.dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}
