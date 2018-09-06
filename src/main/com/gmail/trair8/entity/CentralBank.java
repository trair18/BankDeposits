package main.com.gmail.trair8.entity;

import java.util.Objects;

public class CentralBank extends Bank{
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CentralBank that = (CentralBank) o;
        return Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), country);
    }

    @Override
    public String toString() {
        return "CentralBank{" +
                "country='" + country + '\'' +
                ", deposits=" + deposits +
                '}';
    }
}
