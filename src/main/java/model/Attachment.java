package model;

/**
 * Entidad que representa un archivo adjunto que puede asociarse a una petición o a un portafolio.
 */
public class Attachment extends BaseEntity {
    // Identificador único del archivo adjunto.
    private Integer idAttachment;

    // Petición asociada (si corresponde).
    private Petition petition;

    // Portafolio asociado (si corresponde).
    private Portfolio portfolio;

    // Nombre del archivo adjunto.
    private String name;

    // Ruta o ubicación del archivo.
    private String path;

    public Integer getIdAttachment() {
        return this.idAttachment;
    }

    public void setIdAttachment(Integer idAttachment) {
        this.idAttachment = idAttachment;
    }

    public Petition getPetition() {
        return this.petition;
    }

    public void setPetition(Petition petition) {
        this.petition = petition;
    }

    public Portfolio getPortfolio() {
        return this.portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
