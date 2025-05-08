package model;

import java.sql.Timestamp;

/**
 * Entidad que representa una profesión.
 * Tabla: profession
 */
public class Profession extends BaseEntity {

    // Corresponde a id_profession (PK)
    private Integer idProfession;

    // Corresponde a la columna "name" (varchar(45))
    private String name;

    // Relación con la tabla category (id_category)
    private Category category;

    public Profession() {
    }

    public Profession(User idUserCreate, User idUserUpdate, Timestamp dateCreate, Timestamp dateUpdate,
                      Integer idProfession, String name, Category category) {
        super(idUserCreate, idUserUpdate, dateCreate, dateUpdate);
        this.idProfession = idProfession;
        this.name = name;
        this.category = category;
    }

    public Integer getIdProfession() {
        return idProfession;
    }

    public void setIdProfession(Integer idProfession) {
        this.idProfession = idProfession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        // Para JComboBox o impresión
        return (name != null) ? name : "Sin profesión";
    }
}
