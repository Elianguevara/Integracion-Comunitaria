package model;

public enum RoleType {
    CLIENTE("cliente"),
    PROVEEDOR("proveedor"),
    AMBOS("ambos");

    private final String value;

    RoleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RoleType fromValue(String value) {
        for (RoleType role : RoleType.values()) {
            if (role.value.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Valor de role no válido: " + value);
    }
}
