package model;

/**
 * Entidad que representa una provincia, que pertenece a un país.
 */
public class Province extends BaseEntity {
    // Identificador único de la provincia.
    private Integer idProvince;

    // Nombre de la provincia.
    private String name;

    // País al que pertenece la provincia.
    private Country country;




    public Integer getIdProvince() {
        return this.idProvince;
    }

    public void setIdProvince(Integer idProvince) {
        this.idProvince = idProvince;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
