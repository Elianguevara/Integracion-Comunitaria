package model;

/**
 * Entidad que representa un formulario o componente de interfaz (parte de los módulos de seguridad).
 */
public class Form extends BaseEntity {
    // Identificador único del formulario o pantalla.
    private Integer idForm;

    // Nombre del formulario o funcionalidad.
    private String name;

    public Integer getIdForm() {
        return this.idForm;
    }

    public void setIdForm(Integer idForm) {
        this.idForm = idForm;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
