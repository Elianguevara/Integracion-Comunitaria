package model;

import java.sql.Timestamp;

public class ZoneCity extends BaseEntity{
    private Integer idZoneCity;
    private Zone zone;
    private City city;

    public ZoneCity() {
    }

    public ZoneCity(User idUserCreate, User idUserUpdate, Timestamp dateCreate, Timestamp dateUpdate, Integer idZoneCity, Zone zone, City city) {
        super(idUserCreate, idUserUpdate, dateCreate, dateUpdate);
        this.idZoneCity = idZoneCity;
        this.zone = zone;
        this.city = city;
    }

    public Integer getIdZoneCity() {
        return idZoneCity;
    }

    public void setIdZoneCity(Integer idZoneCity) {
        this.idZoneCity = idZoneCity;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
