package by.epam.epamproject.parser;


public enum ToursEnum {
    TOURS("tours"),
    NAME("name"),
    DURATION("duration"),
    COUNTRY("country"),
    COST("cost"),
    TRANSPORT("transport"),
    ACCOMMODATION("accommodation"),
    AMUSEMENT_INCLUDED("amusement-included"),
    HOTEL_CHARACTERISTIC("hotel-characteristic"),
    SPECIALIZATION("specialization"),
    PROCEDURES("procedures"),
    SANATORIUM_TYPE("sanatorium-type"),
    MEAL_PLAN("meal-plan"),
    STARS_CATEGORY("stars-category"),
    TV_INCLUDED("tv-included"),
    AIR_CONDITIONING_INCLUDED("air-conditioning-included"),
    REST_VOUCHER("rest-voucher"),
    TREATMENT_VOUCHER("treatment-voucher");

    private String value;

    ToursEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
