package jpqlJpa;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipCode;

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    private String getZipCode() {
        return zipCode;
    }

    private void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
