package model;

import java.sql.Timestamp;

/**
 * Entidad que representa un tipo de género posible para un cliente.
 */
public class Gender extends BaseEntity {
    // Identificador único del género.
    private Integer idGender;
    // Nombre o tipo de género (por ejemplo, Masculino, Femenino, Otro).
    private String gender;

    public Gender() { }

    public Gender(Integer idGender, String gender) {
        this.idGender = idGender;
        this.gender = gender;
    }

    public Integer getIdGender() {
        return this.idGender;
    }

    public void setIdGender(Integer idGender) {
        this.idGender = idGender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Gender [idGender=" + idGender + ", gender=" + gender + "]";
    }
}
