package model;

/**
 * Entidad que representa una transacción o evento de auditoría en el sistema, registrando cambios realizados por los usuarios.
 */
public class Transaction extends BaseEntity {
    // Identificador único de la transacción de auditoría.
    private Integer idTransaction;

    // Usuario que realizó la acción registrada en la transacción.
    private User user;

    // Módulo en el que ocurrió la transacción.
    private Module module;

    // Formulario o pantalla en la que ocurrió la transacción.
    private Form form;

    // Tipo de operación realizada (por ejemplo, INSERT, UPDATE, DELETE).
    private String queryType;

    // Valor antiguo (antes de la transacción).
    private String oldValue;

    // Valor nuevo (después de la transacción).
    private String newValue;

    public Integer getIdTransaction() {
        return this.idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Module getModule() {
        return this.module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Form getForm() {
        return this.form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public String getQueryType() {
        return this.queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getOldValue() {
        return this.oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return this.newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
