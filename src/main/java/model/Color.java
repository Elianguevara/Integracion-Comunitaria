package model;

/**
 * Entidad que representa un color (utilizado posiblemente para codificar calificaciones).
 */
public class Color extends BaseEntity {
    // Identificador único del color.
    private Integer idColor;

    // Nombre o descripción del color.
    private String colorName;

    public Integer getIdColor() {
        return this.idColor;
    }

    public void setIdColor(Integer idColor) {
        this.idColor = idColor;
    }

    public String getColorName() {
        return this.colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
}
