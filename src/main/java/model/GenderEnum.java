package model;

public enum GenderEnum {
    MALE("Masculino"),
    FEMALE("Femenino"),
    OTHER("Otro");

    private final String description;

    GenderEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    // Método para obtener el id asociado (según la base de datos: 1, 2, 3)
    public int getId() {
        switch(this) {
            case MALE:
                return 1;
            case FEMALE:
                return 2;
            case OTHER:
                return 3;
            default:
                return 0;
        }
    }
}
