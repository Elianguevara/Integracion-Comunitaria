package model;

/**
 * Entidad que representa un recurso de texto traducible a diferentes idiomas.
 */
public class Language extends BaseEntity {
    // Identificador único del recurso de idioma.
    private Integer idLanguage;

    // Identificador del componente al que se refiere este texto (por ejemplo, ID de formulario o elemento de interfaz).
    private Integer componentId;

    // Texto en español.
    private String valueEs;

    // Texto en inglés.
    private String valueEn;

    public Integer getIdLanguage() {
        return this.idLanguage;
    }

    public void setIdLanguage(Integer idLanguage) {
        this.idLanguage = idLanguage;
    }

    public Integer getComponentId() {
        return this.componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public String getValueEs() {
        return this.valueEs;
    }

    public void setValueEs(String valueEs) {
        this.valueEs = valueEs;
    }

    public String getValueEn() {
        return this.valueEn;
    }

    public void setValueEn(String valueEn) {
        this.valueEn = valueEn;
    }
}
