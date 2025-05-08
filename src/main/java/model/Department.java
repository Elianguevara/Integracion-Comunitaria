package model;

/**
 * Entidad que representa un departamento (división administrativa dentro de una provincia).
 */
public class Department extends BaseEntity {
    // Identificador único del departamento.
    private Integer idDepartment;

    // Provincia a la que pertenece el departamento.
    private Province province;

    // País al que pertenece el departamento.
    private Country country;

    // Nombre del departamento.
    private String name;

    public Integer getIdDepartment() {
        return this.idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public Province getProvince() {
        return this.province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
