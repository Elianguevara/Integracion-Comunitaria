package model;

import java.sql.Timestamp;

/**
 * Entidad que representa un proveedor de servicios o productos.
 * Adaptada para coincidir con la estructura real de la tabla 'provider'.
 */
public class Provider extends BaseEntity {
    // Identificador único del proveedor (id_provider).
    private Integer idProvider;

    // Nombre o razón social (name).
    private String name;

    // Dirección (address).
    private String address;

    // Latitud GPS (gps_lat).
    private Float gpsLat;

    // Longitud GPS (gps_long).
    private Float gpsLong;

    // Tipo de proveedor (relación con la tabla type_provider => id_type_provider).
    private TypeProvider typeProvider;

    // Categoría (relación con la tabla category => id_category).
    private Category category;

    // Calificación o puntaje (relación con la tabla grade_provider => id_grade_provider).
    private GradeProvider gradeProvider;

    // Profesión (relación con la tabla profession => id_profession).
    private Profession profession;

    // Oferta asociada (relación con la tabla offer => id_offer).
    private Offer offer;

    // Disponibilidad (relación con la tabla availability => id_avability).
    private Availability availability;

    // Usuario propietario (relación con la tabla user => id_user).
    private User user;

    /**
     * Constructor por defecto.
     */
    public Provider() {
    }

    /**
     * Constructor con todos los campos, incluyendo los de auditoría heredados de BaseEntity.
     */
    public Provider(
            User idUserCreate,
            User idUserUpdate,
            Timestamp dateCreate,
            Timestamp dateUpdate,
            Integer idProvider,
            String name,
            String address,
            Float gpsLat,
            Float gpsLong,
            TypeProvider typeProvider,
            Category category,
            GradeProvider gradeProvider,
            Profession profession,
            Offer offer,
            Availability availability,
            User user
    ) {
        super(idUserCreate, idUserUpdate, dateCreate, dateUpdate);
        this.idProvider = idProvider;
        this.name = name;
        this.address = address;
        this.gpsLat = gpsLat;
        this.gpsLong = gpsLong;
        this.typeProvider = typeProvider;
        this.category = category;
        this.gradeProvider = gradeProvider;
        this.profession = profession;
        this.offer = offer;
        this.availability = availability;
        this.user = user;
    }

    public Integer getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(Integer idProvider) {
        this.idProvider = idProvider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(Float gpsLat) {
        this.gpsLat = gpsLat;
    }

    public Float getGpsLong() {
        return gpsLong;
    }

    public void setGpsLong(Float gpsLong) {
        this.gpsLong = gpsLong;
    }

    public TypeProvider getTypeProvider() {
        return typeProvider;
    }

    public void setTypeProvider(TypeProvider typeProvider) {
        this.typeProvider = typeProvider;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public GradeProvider getGradeProvider() {
        return gradeProvider;
    }

    public void setGradeProvider(GradeProvider gradeProvider) {
        this.gradeProvider = gradeProvider;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
