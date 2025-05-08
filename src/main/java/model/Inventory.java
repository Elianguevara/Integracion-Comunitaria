package model;

/**
 * Entidad que representa un registro de inventario, incluyendo la cantidad y costo de un artículo, posiblemente asociado a una postulación.
 */
public class Inventory extends BaseEntity {
    // Identificador único del inventario.
    private Integer idInventory;

    // Artículo asociado en el inventario.
    private Article article;

    // Cantidad disponible del artículo.
    private Integer cant;

    // Costo del artículo o elemento inventariado.
    private Float cost;

    // Postulación asociada (si este inventario pertenece a una oferta para una petición).
    private Postulation postulation;

    public Integer getIdInventory() {
        return this.idInventory;
    }

    public void setIdInventory(Integer idInventory) {
        this.idInventory = idInventory;
    }

    public Article getArticle() {
        return this.article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getCant() {
        return this.cant;
    }

    public void setCant(Integer cant) {
        this.cant = cant;
    }

    public Float getCost() {
        return this.cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Postulation getPostulation() {
        return this.postulation;
    }

    public void setPostulation(Postulation postulation) {
        this.postulation = postulation;
    }
}
