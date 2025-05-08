package model;

/**
 * Entidad que representa una configuración de semana laboral, asociada a un tipo de jornal.
 */
public class Week extends BaseEntity {
    // Identificador único del registro de semana.
    private Integer idWeek;

    // Nombre de la configuración semanal (por ejemplo, semana estándar).
    private String name;

    // Tipo de jornal (semana laboral) al que corresponde esta semana.
    private TypeJornal typeJornal;

    public Integer getIdWeek() {
        return this.idWeek;
    }

    public void setIdWeek(Integer idWeek) {
        this.idWeek = idWeek;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeJornal getTypeJornal() {
        return this.typeJornal;
    }

    public void setTypeJornal(TypeJornal typeJornal) {
        this.typeJornal = typeJornal;
    }
}
