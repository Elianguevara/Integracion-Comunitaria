package model;

/**
 * Entidad que representa un perfil de usuario, que puede indicar roles o permisos (por ejemplo, si es administrador).
 */
public class Profile extends BaseEntity {
    // Identificador único del perfil de usuario.
    private Integer idProfile;

    // Nombre del perfil.
    private String name;

    // Indicador de si el perfil tiene privilegios de administrador.
    private Boolean admin;

    public Integer getIdProfile() {
        return this.idProfile;
    }

    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isAdmin() {
        return this.admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
