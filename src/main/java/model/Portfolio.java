package model;

/**
 * Entidad que representa un portafolio de trabajos o servicios de un proveedor.
 */
public class Portfolio extends BaseEntity {
    // Identificador único del portafolio.
    private Integer idPortfolio;

    // Proveedor al que pertenece este portafolio.
    private Provider provider;

    // Nombre del portafolio.
    private String name;

    // Descripción del portafolio.
    private String description;

    public Integer getIdPortfolio() {
        return this.idPortfolio;
    }

    public void setIdPortfolio(Integer idPortfolio) {
        this.idPortfolio = idPortfolio;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
