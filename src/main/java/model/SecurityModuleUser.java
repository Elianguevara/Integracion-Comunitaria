package model;

/**
 * Entidad que representa la configuración de acceso de un usuario a un formulario o módulo específico.
 */
public class SecurityModuleUser extends BaseEntity {
    // Identificador único del registro de módulo de seguridad de usuario.
    private Integer idSecurityModuleUser;

    // Formularío o funcionalidad a la que se refiere este registro.
    private Form form;

    // Estado o permisos sobre el formulario (por ejemplo, habilitado/deshabilitado).
    private String state;

    // Usuario al que se le aplica este permiso o estado.
    private User user;

    public Integer getIdSecurityModuleUser() {
        return this.idSecurityModuleUser;
    }

    public void setIdSecurityModuleUser(Integer idSecurityModuleUser) {
        this.idSecurityModuleUser = idSecurityModuleUser;
    }

    public Form getForm() {
        return this.form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
