package model;

/**
 * Entidad que representa un tipo de jornal o régimen de trabajo semanal.
 */
public class TypeJornal extends BaseEntity {
    // Identificador único del tipo de jornal (tipo de semana laboral).
    private Integer idTypeJornal;

    // Código o identificador del tipo de jornal.
    private String typeJornal;

    // Nombre descriptivo del tipo de jornal.
    private String name;

    public Integer getIdTypeJornal() {
        return this.idTypeJornal;
    }

    public void setIdTypeJornal(Integer idTypeJornal) {
        this.idTypeJornal = idTypeJornal;
    }

    public String getTypeJornal() {
        return this.typeJornal;
    }

    public void setTypeJornal(String typeJornal) {
        this.typeJornal = typeJornal;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
