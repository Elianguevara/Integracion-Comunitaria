package model;

/**
 * Entidad para almacenar parámetros de configuración global de la aplicación.
 */
public class Parameter extends BaseEntity {
    // Identificador único del parámetro de configuración.
    private Integer idParameter;

    // Hora tomada (parámetro específico, por ejemplo hora de respaldo).
    private String takenHour;

    // Nombre de la aplicación.
    private String nameApplication;

    // Versión de la aplicación.
    private String version;

    // Archivo adjunto asociado a este parámetro (por ejemplo, logo, imagen, etc.).
    private Attachment attachment;

    // Subtítulo o texto secundario de la aplicación.
    private String subtitle;

    // Información de derechos de autor.
    private String authorRight;

    public Integer getIdParameter() {
        return this.idParameter;
    }

    public void setIdParameter(Integer idParameter) {
        this.idParameter = idParameter;
    }

    public String getTakenHour() {
        return this.takenHour;
    }

    public void setTakenHour(String takenHour) {
        this.takenHour = takenHour;
    }

    public String getNameApplication() {
        return this.nameApplication;
    }

    public void setNameApplication(String nameApplication) {
        this.nameApplication = nameApplication;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Attachment getAttachment() {
        return this.attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAuthorRight() {
        return this.authorRight;
    }

    public void setAuthorRight(String authorRight) {
        this.authorRight = authorRight;
    }
}
