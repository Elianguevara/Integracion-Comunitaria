package model;

public class ProviderZone {
    private Zone zone;
    private Provider provider;

    public ProviderZone() {
    }

    public ProviderZone(Zone zone, Provider provider) {
        this.zone = zone;
        this.provider = provider;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
