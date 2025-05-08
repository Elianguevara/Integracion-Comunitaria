package model;

/**
 * Entidad que representa una notificación enviada a un cliente o proveedor.
 */
public class Notification extends BaseEntity {
    // Identificador único de la notificación.
    private Integer idNotification;

    // Proveedor al que se dirige la notificación.
    private Provider provider;

    // Cliente al que se dirige la notificación.
    private Customer customer;

    // Tipo de notificación.
    private String type;

    // Mensaje de la notificación.
    private String message;

    // Indicador de si la notificación ya fue vista (true/false).
    private Boolean viewed;

    public Integer getIdNotification() {
        return this.idNotification;
    }

    public void setIdNotification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isViewed() {
        return this.viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }
}
