package model;

/**
 * Entidad que representa un artículo, perteneciente a una categoría.
 */
public class Article extends BaseEntity {
    // Identificador único del artículo.
    private Integer idArticle;

    // Nombre del artículo.
    private String name;

    // Categoría a la que pertenece el artículo.
    private Category category;

    public Integer getIdArticle() {
        return this.idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
