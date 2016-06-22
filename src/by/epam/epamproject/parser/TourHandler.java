package by.epam.epamproject.parser;


import by.epam.epamproject.entity.HotelCharacteristic;
import by.epam.epamproject.entity.RestTour;
import by.epam.epamproject.entity.Tour;
import by.epam.epamproject.entity.TreatmentTour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class TourHandler extends DefaultHandler {
    private static Logger log = LogManager.getLogger(TourHandler.class);

    private Set<Tour> tours = new HashSet<Tour>();
    private RestTour restTour;
    private TreatmentTour treatmentTour;
    private Tour tour;
    private HotelCharacteristic hotelCharacteristic;
    private ToursEnum currentEnum;
    private EnumSet<ToursEnum> withText;
    private static final String UNDERSCORE = "_";
    private static final String DASH = "-";

    public TourHandler() {
        withText = EnumSet.range(ToursEnum.NAME, ToursEnum.AIR_CONDITIONING_INCLUDED);
    }

    public Set<Tour> getTours() {
        return tours;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (ToursEnum.TREATMENT_VOUCHER.getValue().equals(localName)) {
            tour = new Tour();
            treatmentTour = new TreatmentTour();
            tour.setName(attrs.getValue(0));
            treatmentTour.setSpecialization(attrs.getValue(1));
        } else {
            if (ToursEnum.REST_VOUCHER.getValue().equals(localName)) {
                tour = new Tour();
                hotelCharacteristic = new HotelCharacteristic();
                restTour = new RestTour();
                tour.setName(attrs.getValue(0));
            } else {
                localName = localName.replaceAll(DASH, UNDERSCORE);
                ToursEnum temp = ToursEnum.valueOf(localName.toUpperCase());

                if (withText.contains(temp)) {
                    currentEnum = temp;
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (ToursEnum.REST_VOUCHER.getValue().equals(localName) || ToursEnum.TREATMENT_VOUCHER.getValue().equals(localName)) {
            if (ToursEnum.REST_VOUCHER.getValue().equals(localName)) {
                restTour.setAllValues(tour);
                restTour.setHotelCharacteristic(hotelCharacteristic);
                tours.add(restTour);
            } else {
                treatmentTour.setAllValues(tour);
                tours.add(treatmentTour);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String text = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case COUNTRY:
                    tour.setCountry(text);
                    break;
                case COST:
                    tour.setCost(Double.parseDouble(text));
                    break;
                case DURATION:
                    tour.setDuration(Integer.parseInt(text));
                    break;
                case TRANSPORT:
                    tour.setTransport(text);
                    break;
                case ACCOMMODATION:
                    tour.setAccommodation(text);
                    break;
                case AMUSEMENT_INCLUDED:
                    restTour.setAmusementIncluded(Boolean.parseBoolean(text));
                    break;
                case PROCEDURES:
                    treatmentTour.setProcedures(text);
                    break;
                case SANATORIUM_TYPE:
                    treatmentTour.setType(text);
                    break;
                case MEAL_PLAN:
                    hotelCharacteristic.setMealPlan(text);
                    break;
                case STARS_CATEGORY:
                    hotelCharacteristic.setStarsCategory(text);
                    break;
                case TV_INCLUDED:
                    hotelCharacteristic.setTvIncluded(Boolean.parseBoolean(text));
                    break;
                case AIR_CONDITIONING_INCLUDED:
                    hotelCharacteristic.setAirConditioningIncluded(Boolean.parseBoolean(text));
                    break;
                case HOTEL_CHARACTERISTIC:
                    break;
                default:
                    log.error("This tag is not present. The information is not processed.");
                    break;
            }
        }
        currentEnum = null;
    }
}
