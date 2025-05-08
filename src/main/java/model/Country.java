package model;

import java.sql.Timestamp;

/**
 * Entidad que representa un país.
 */
public class Country extends BaseEntity {
    // Identificador único del país.
    private Integer idCountry;

    // Nombre del país.
    private String name;

    public Country() {
    }

    public Country(User idUserCreate, User idUserUpdate, Timestamp dateCreate, Timestamp dateUpdate, Integer idCountry, String name) {
        super(idUserCreate, idUserUpdate, dateCreate, dateUpdate);
        this.idCountry = idCountry;
        this.name = name;
    }

    public Integer getIdCountry() {
        return this.idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
