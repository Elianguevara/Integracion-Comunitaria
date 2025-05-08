package model;

/**
 * Entidad que representa un tipo de oferta, clasificando las ofertas dentro de una categoría.
 */
public class TypeOffer extends BaseEntity {
    // Identificador único del tipo de oferta.
    private Integer idTypeOffer;

    // Categoría asociada a este tipo de oferta.
    private Category category;

    // Nombre del tipo de oferta.
    private String name;

    public Integer getIdTypeOffer() {
        return this.idTypeOffer;
    }

    public void setIdTypeOffer(Integer idTypeOffer) {
        this.idTypeOffer = idTypeOffer;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
