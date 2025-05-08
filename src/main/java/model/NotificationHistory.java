package model;

/**
 * Entidad que registra un historial de notificaciones (por ejemplo, notificaciones antiguas o cambios en el estado de una notificación).
 */
public class NotificationHistory extends BaseEntity {
    // Identificador único del historial de notificación.
    private Integer idNotificationHistory;

    // Notificación asociada a este registro de historial.
    private Notification notification;

    public Integer getIdNotificationHistory() {
        return this.idNotificationHistory;
    }

    public void setIdNotificationHistory(Integer idNotificationHistory) {
        this.idNotificationHistory = idNotificationHistory;
    }

    public Notification getNotification() {
        return this.notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
