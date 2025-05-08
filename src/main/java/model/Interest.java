package model;

/**
 * Entidad que representa el interés de un cliente en una determinada categoría.
 */
public class Interest extends BaseEntity {
    // Identificador único del interés.
    private Integer idInterest;

    // Cliente que tiene el interés.
    private Customer customer;

    // Categoría de interés del cliente.
    private Category category;

    public Integer getIdInterest() {
        return this.idInterest;
    }

    public void setIdInterest(Integer idInterest) {
        this.idInterest = idInterest;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
