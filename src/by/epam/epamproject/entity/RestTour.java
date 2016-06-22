package by.epam.epamproject.entity;


/**
 * The type Sightseeing tour.
 */
public class RestTour extends Tour {
    private boolean amusementIncluded;
    private HotelCharacteristic hotelCharacteristic = new HotelCharacteristic();


    public RestTour() {
        super();
    }

    public RestTour(Tour tour) {
        super(tour);
    }

    public HotelCharacteristic getHotelCharacteristic() {
        return hotelCharacteristic;
    }

    public void setHotelCharacteristic(HotelCharacteristic hotelCharacteristic) {
        this.hotelCharacteristic = hotelCharacteristic;
    }

    public boolean isAmusementIncluded() {
        return amusementIncluded;
    }

    public void setAmusementIncluded(boolean amusementIncluded) {
        this.amusementIncluded = amusementIncluded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RestTour restTour = (RestTour) o;

        if (amusementIncluded != restTour.amusementIncluded) return false;
        if (hotelCharacteristic != null ? !hotelCharacteristic.equals(restTour.hotelCharacteristic) : restTour.hotelCharacteristic != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (amusementIncluded ? 1 : 0);
        result = 31 * result + (hotelCharacteristic != null ? hotelCharacteristic.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RestTour{" +
                super.toString() +
                ", amusementIncluded=" + amusementIncluded +
                ", hotelCharacteristic=" + hotelCharacteristic +
                '}';
    }
}
