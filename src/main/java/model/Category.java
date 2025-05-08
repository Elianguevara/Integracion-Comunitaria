package model;

import java.sql.Timestamp;

/**
 * Entidad que representa una categoría.
 * Tabla: category
 */
public class Category extends BaseEntity {

    // Corresponde a id_category (PK)
    private Integer idCategory;

    // Corresponde a la columna "name" (varchar(45))
    private String name;

    public Category() {
    }

    public Category(User idUserCreate, User idUserUpdate, Timestamp dateCreate, Timestamp dateUpdate,
                    Integer idCategory, String name) {
        super(idUserCreate, idUserUpdate, dateCreate, dateUpdate);
        this.idCategory = idCategory;
        this.name = name;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        // Para JComboBox o impresión
        return (name != null) ? name : "Sin categoría";
    }
}
