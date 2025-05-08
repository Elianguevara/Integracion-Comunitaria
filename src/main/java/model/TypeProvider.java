package model;

import java.sql.Timestamp;

/**
 * Entidad que representa un tipo de proveedor.
 * Tabla: type_provider
 */
public class TypeProvider extends BaseEntity {

    // Correspondiente a id_type_provider (PK)
    private Integer idTypeProvider;

    // Correspondiente a la columna "type" (varchar(70))
    private String type;

    // Corresponde a la columna "business" (tinyint(1))
    // Puede usarse Short, Byte o Boolean (con conversión en DAO).
    private Short business;

    // Corresponde a la columna "transport" (tinyint(1))
    private Short transport;

    public TypeProvider() {
    }

    public TypeProvider(User idUserCreate, User idUserUpdate, Timestamp dateCreate, Timestamp dateUpdate,
                        Integer idTypeProvider, String type, Short business, Short transport) {
        super(idUserCreate, idUserUpdate, dateCreate, dateUpdate);
        this.idTypeProvider = idTypeProvider;
        this.type = type;
        this.business = business;
        this.transport = transport;
    }

    public Integer getIdTypeProvider() {
        return idTypeProvider;
    }

    public void setIdTypeProvider(Integer idTypeProvider) {
        this.idTypeProvider = idTypeProvider;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Short getBusiness() {
        return business;
    }

    public void setBusiness(Short business) {
        this.business = business;
    }

    public Short getTransport() {
        return transport;
    }

    public void setTransport(Short transport) {
        this.transport = transport;
    }

    @Override
    public String toString() {
        // Puedes ajustar la representación para JComboBox, por ejemplo:
        return (type != null) ? type : "Sin tipo";
    }
}
