package model;

/**
 * Entidad que representa un módulo del sistema (agrupación de formularios o funcionalidades).
 */
public class Module extends BaseEntity {
    // Identificador único del módulo.
    private Integer idModule;

    public Integer getIdModule() {
        return this.idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }
}
