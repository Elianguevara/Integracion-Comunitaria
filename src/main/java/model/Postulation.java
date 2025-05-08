package model;

/**
 * Entidad que representa una postulación de un proveedor a una petición de un cliente,
 * mapeando los campos de la tabla 'postulation' y heredando campos de auditoría de BaseEntity.
 */
public class Postulation extends BaseEntity {

    // Correspondiente a la PK: idpostulation int(11) AI PK
    private int idPostulation;

    // id_petition int(11) -> Mapeado como objeto Petition
    private Petition petition;

    // id_provider int(11) -> Mapeado como objeto Provider
    private Provider provider;

    // winner varchar(45) -> Guardará "YES", "NO", etc.
    private String winner;

    // proposal varchar(45)
    private String proposal;

    // cost int(11)
    private int cost;

    // id_state varchar(11)
    private String idState;

    // state varchar(55)
    private String state;

    // current varchar(45) -> "YES", "NO", etc.
    private String current;

    // ------------------------------------------------------------------
    // Getters & Setters
    // ------------------------------------------------------------------

    public int getIdPostulation() {
        return idPostulation;
    }

    public void setIdPostulation(int idPostulation) {
        this.idPostulation = idPostulation;
    }

    public Petition getPetition() {
        return petition;
    }

    public void setPetition(Petition petition) {
        this.petition = petition;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getIdState() {
        return idState;
    }

    public void setIdState(String idState) {
        this.idState = idState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }
}
