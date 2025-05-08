package model;

/**
 * Entidad que representa una zona geográfica, que pertenece a una provincia y un país.
 */
public class Zone extends BaseEntity {
    // Identificador único de la zona.
    private Integer idZone;

    // Nombre de la zona.
    private String name;

    // País al que pertenece la zona.
    private Country country;

    // Provincia a la que pertenece la zona.
    private Province province;

    public Integer getIdZone() {
        return this.idZone;
    }

    public void setIdZone(Integer idZone) {
        this.idZone = idZone;
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

    public Province getProvince() {
        return this.province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
