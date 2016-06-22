package by.epam.epamproject.entity;


/**
 * The type Tour.
 */
public class Tour {
    private String name;
    private int duration;
    private String country;
    private String transport;
    private String accommodation;
    private double cost;


    /**
     * Instantiates a new Tour.
     */
    public Tour() {
        super();
    }

    public Tour(Tour tour) {
        this.name = tour.name;
        this.duration = tour.duration;
        this.country = tour.country;
        this.transport = tour.transport;
        this.accommodation = tour.accommodation;
        this.cost = tour.cost;
    }

    public void setAllValues(Tour tour) {
        this.name = tour.name;
        this.duration = tour.duration;
        this.country = tour.country;
        this.transport = tour.transport;
        this.accommodation = tour.accommodation;
        this.cost = tour.cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tour tour = (Tour) o;

        if (duration != tour.duration) return false;
        if (Double.compare(tour.cost, cost) != 0) return false;
        if (name != null ? !name.equals(tour.name) : tour.name != null) return false;
        if (country != null ? !country.equals(tour.country) : tour.country != null) return false;
        if (transport != null ? !transport.equals(tour.transport) : tour.transport != null) return false;
        if (accommodation != null ? !accommodation.equals(tour.accommodation) : tour.accommodation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + duration;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (transport != null ? transport.hashCode() : 0);
        result = 31 * result + (accommodation != null ? accommodation.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", duration=" + duration +
                ", country='" + country + '\'' +
                ", transport='" + transport + '\'' +
                ", accommodation='" + accommodation + '\'' +
                ", cost=" + cost;
    }
}
