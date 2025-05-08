package model;

/**
 * Entidad que representa un tipo de petición o solicitud que puede hacer un cliente.
 */
public class TypePetition extends BaseEntity {
    // Identificador único del tipo de petición.
    private Integer idTypePetition;

    // Nombre del tipo de petición.
    private String name;

    public Integer getIdTypePetition() {
        return this.idTypePetition;
    }

    public void setIdTypePetition(Integer idTypePetition) {
        this.idTypePetition = idTypePetition;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
