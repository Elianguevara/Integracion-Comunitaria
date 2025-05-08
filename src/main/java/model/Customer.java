package model;

import java.sql.Timestamp;

/**
 * Entidad que representa a un cliente que utiliza la plataforma.
 */
public class Customer extends BaseEntity {
    // Identificador único del cliente.
    private Integer idCustomer;

    // Género del cliente (tipo de género).
    private Gender gender;

    // Ciudad de residencia del cliente.
    private City city;

    // Nombre del cliente.
    private String name;

    // Fecha de nacimiento del cliente.
    private Timestamp dateYear;

    // Documento Nacional de Identidad del cliente.
    private Integer dni;

    // Correo electrónico del cliente.
    private String email;

    // Número de teléfono del cliente.
    private String phone;

    // Dirección del cliente.
    private String address;

    // Coordenada de latitud GPS del domicilio del cliente.
    private String gpsLat;

    // Coordenada de longitud GPS del domicilio del cliente.
    private String gpsLon;

    private User idUser;


    public Customer() {
    }

    public Customer(User idUserCreate, User idUserUpdate, Timestamp dateCreate, Timestamp dateUpdate) {
        super(idUserCreate, idUserUpdate, dateCreate, dateUpdate);
    }

    public Integer getIdCustomer() {
        return this.idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDateYear() {
        return this.dateYear;
    }

    public void setDateYear(Timestamp dateYear) {
        this.dateYear = dateYear;
    }

    public Integer getDni() {
        return this.dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGpsLat() {
        return this.gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getGpsLon() {
        return this.gpsLon;
    }

    public void setGpsLon(String gpsLon) {
        this.gpsLon = gpsLon;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }
}
