package model;

import java.sql.Timestamp;

public class ProviderAddress extends BaseEntity {
    private Integer IdProviderAddress;
    private Provider provider;
    private Country country;
    private Province province;
    private Department department;
    private City city;
    private String street;
    private String numberStreet;
    private String dpto;
    private String floorDpto;

    public ProviderAddress() {
    }

    public ProviderAddress(User idUserCreate, User idUserUpdate, Timestamp dateCreate, Timestamp dateUpdate, Integer idProviderAddress, Provider provider, Country country, Province province, Department departament, City city, String street, String numberStreet, String dpto, String floorDpto) {
        super(idUserCreate, idUserUpdate, dateCreate, dateUpdate);
        IdProviderAddress = idProviderAddress;
        this.provider = provider;
        this.country = country;
        this.province = province;
        this.department = department;
        this.city = city;
        this.street = street;
        this.numberStreet = numberStreet;
        this.dpto = dpto;
        this.floorDpto = floorDpto;
    }

    public Integer getIdProviderAddress() {
        return IdProviderAddress;
    }

    public void setIdProviderAddress(Integer idProviderAddress) {
        IdProviderAddress = idProviderAddress;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartament(Department departament) {
        this.department = departament;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumberStreet() {
        return numberStreet;
    }

    public void setNumberStreet(String numberStreet) {
        this.numberStreet = numberStreet;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public String getFloorDpto() {
        return floorDpto;
    }

    public void setFloorDpto(String floorDpto) {
        this.floorDpto = floorDpto;
    }
}
