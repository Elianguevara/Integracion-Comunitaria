package model;

/**
 * Entidad intermedia que asocia un elemento de inventario con un registro de historial de interés.
 */
public class InventoryHasInterestHistory extends BaseEntity {
    // Identificador único de la relación entre inventario e historial de interés.
    private Integer idInventoryHasInterestHistory;

    // Inventario asociado.
    private Inventory inventory;

    // Historial de interés asociado.
    private InterestHistory interestHistory;

    public Integer getIdInventoryHasInterestHistory() {
        return this.idInventoryHasInterestHistory;
    }

    public void setIdInventoryHasInterestHistory(Integer idInventoryHasInterestHistory) {
        this.idInventoryHasInterestHistory = idInventoryHasInterestHistory;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public InterestHistory getInterestHistory() {
        return this.interestHistory;
    }

    public void setInterestHistory(InterestHistory interestHistory) {
        this.interestHistory = interestHistory;
    }
}
