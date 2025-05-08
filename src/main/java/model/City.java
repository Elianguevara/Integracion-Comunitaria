package model;

import java.sql.Timestamp;

/**
 * Entidad que representa una ciudad, con referencia al departamento al que pertenece.
 */
public class City extends BaseEntity {

    // Identificador único de la ciudad (corresponde a id_city en la tabla).
    private Integer idCity;

    // Departamento al que pertenece la ciudad (relación con la tabla department).
    private Department department;

    // Nombre de la ciudad.
    private String name;

    // Código postal de la ciudad.
    // En la BD es varchar(10), aquí se maneja como String.
    private String postalCode;



    // Getters y Setters

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


}
