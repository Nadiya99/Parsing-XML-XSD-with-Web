package by.epam.epamproject.parser;

import by.epam.epamproject.entity.HotelCharacteristic;
import by.epam.epamproject.entity.RestTour;
import by.epam.epamproject.entity.Tour;
import by.epam.epamproject.entity.TreatmentTour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

public class ToursStAXBuilder extends AbstractToursBuilder {
    private static Logger log = LogManager.getLogger(ToursStAXBuilder.class);

    private XMLInputFactory inputFactory;
    private static final String UNDERSCORE = "_";
    private static final String DASH = "-";

    public ToursStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public ToursStAXBuilder(Set<Tour> tours) {
        super(tours);
    }

    @Override
    public void buildSetTours(String filename) {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName().replaceAll(DASH, UNDERSCORE);

                    if (ToursEnum.valueOf(name.toUpperCase()) == ToursEnum.TREATMENT_VOUCHER || ToursEnum.valueOf(name.toUpperCase()) == ToursEnum.REST_VOUCHER) {
                        Tour tour = buildTour(reader);
                        tours.add(tour);
                    }
                }
            }
        } catch (XMLStreamException e) {
            log.error("StAX parsing error! " + e.getMessage());
        } catch (FileNotFoundException e) {
            log.error("File not found! " + e);
        } catch (IOException e) {
            log.error("File input/output error! " + e);
        }
    }

    private Tour buildTour(XMLStreamReader reader) throws XMLStreamException {
        Tour tour = new Tour();
        TreatmentTour treatmentTour = new TreatmentTour();
        RestTour restTour = new RestTour();
        tour.setName(reader.getAttributeValue(null, ToursEnum.NAME.getValue()));
        if (reader.getAttributeValue(null, ToursEnum.NAME.getValue()) != null) {
            treatmentTour.setSpecialization(reader.getAttributeValue(null, ToursEnum.SPECIALIZATION.getValue()));
        }

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName().replaceAll(DASH, UNDERSCORE);
                    fillTourField(tour, treatmentTour, restTour, name, reader);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName().replaceAll(DASH, UNDERSCORE);
                    if (ToursEnum.valueOf(name.toUpperCase()) == ToursEnum.REST_VOUCHER || ToursEnum.valueOf(name.toUpperCase()) == ToursEnum.TREATMENT_VOUCHER) {
                        if (ToursEnum.valueOf(name.toUpperCase()) == ToursEnum.REST_VOUCHER) {
                            restTour.setAllValues(tour);
                            return restTour;
                        } else {
                            treatmentTour.setAllValues(tour);
                            return treatmentTour;
                        }
                    }
                    break;
            }
        }
        return tour;
    }

    private HotelCharacteristic getXMLHotelCharacteristic(XMLStreamReader reader) throws XMLStreamException {
        HotelCharacteristic hotelCharacteristic = new HotelCharacteristic();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName().replaceAll(DASH, UNDERSCORE);
                    fillHotelCharacteristicField(hotelCharacteristic, name, reader);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName().replaceAll(DASH, UNDERSCORE);
                    if (ToursEnum.valueOf(name.toUpperCase()) == ToursEnum.AIR_CONDITIONING_INCLUDED) {
                        return hotelCharacteristic;
                    }
                    break;
            }
        }
        return hotelCharacteristic;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    private void fillHotelCharacteristicField(HotelCharacteristic hotelCharacteristic, String name, XMLStreamReader reader) throws XMLStreamException {
        switch (ToursEnum.valueOf(name.toUpperCase())) {
            case MEAL_PLAN:
                hotelCharacteristic.setMealPlan(getXMLText(reader));
                break;
            case STARS_CATEGORY:
                hotelCharacteristic.setStarsCategory(getXMLText(reader));
                break;
            case TV_INCLUDED:
                hotelCharacteristic.setTvIncluded(Boolean.parseBoolean(getXMLText(reader)));
                break;
            case AIR_CONDITIONING_INCLUDED:
                hotelCharacteristic.setAirConditioningIncluded(Boolean.parseBoolean(getXMLText(reader)));
                break;
        }
    }

    private void fillTourField(Tour tour, TreatmentTour treatmentTour, RestTour restTour, String name, XMLStreamReader reader) throws XMLStreamException {
        switch (ToursEnum.valueOf(name.toUpperCase())) {
            case COUNTRY:
                tour.setCountry(getXMLText(reader));
                break;
            case COST:
                tour.setCost(Double.parseDouble(getXMLText(reader)));
                break;
            case DURATION:
                tour.setDuration(Integer.parseInt(getXMLText(reader)));
                break;
            case TRANSPORT:
                tour.setTransport(getXMLText(reader));
                break;
            case ACCOMMODATION:
                tour.setAccommodation(getXMLText(reader));
                break;
            case AMUSEMENT_INCLUDED:
                restTour.setAmusementIncluded(Boolean.parseBoolean(getXMLText(reader)));
                break;
            case PROCEDURES:
                treatmentTour.setProcedures(getXMLText(reader));
                break;
            case SANATORIUM_TYPE:
                treatmentTour.setType(getXMLText(reader));
                break;
            case HOTEL_CHARACTERISTIC:
                restTour.setHotelCharacteristic(getXMLHotelCharacteristic(reader));
                break;
        }
    }
}

