package model;

/**
 * Entidad que representa la asociación entre un perfil y un módulo de seguridad (posiblemente un grupo de permisos).
 */
public class SecurityProfileGroup extends BaseEntity {
    // Identificador único del grupo de perfil de seguridad.
    private Integer idSecurityProfileGroup;

    // Perfil al que pertenece este grupo de seguridad.
    private Profile profile;

    // Módulo al que pertenece este grupo de seguridad.
    private Module module;

    // Nombre del grupo de perfil de seguridad.
    private String name;

    public Integer getIdSecurityProfileGroup() {
        return this.idSecurityProfileGroup;
    }

    public void setIdSecurityProfileGroup(Integer idSecurityProfileGroup) {
        this.idSecurityProfileGroup = idSecurityProfileGroup;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Module getModule() {
        return this.module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
