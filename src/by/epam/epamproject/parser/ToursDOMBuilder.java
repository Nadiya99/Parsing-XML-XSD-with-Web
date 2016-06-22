package by.epam.epamproject.parser;


import by.epam.epamproject.entity.RestTour;
import by.epam.epamproject.entity.Tour;
import by.epam.epamproject.entity.TreatmentTour;
import by.epam.epamproject.entity.HotelCharacteristic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Set;

public class ToursDOMBuilder extends AbstractToursBuilder {
    private static Logger log = LogManager.getLogger(ToursDOMBuilder.class);

    private DocumentBuilder docBuilder;

    public ToursDOMBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            log.error("Parser config error: " + e);
        }
    }

    public ToursDOMBuilder(Set<Tour> students) {
        super(students);
    }

    @Override
    public void buildSetTours(String filename) {
        try {
            Document doc = docBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList tourList = root.getElementsByTagName(ToursEnum.REST_VOUCHER.getValue());

            for (int i = 0; i < tourList.getLength(); i++) {
                Element tourElement = (Element) tourList.item(i);
                Tour tour = buildRestTour(tourElement);
                tours.add(tour);
            }

            tourList = root.getElementsByTagName(ToursEnum.TREATMENT_VOUCHER.getValue());
            for (int i = 0; i < tourList.getLength(); i++) {
                Element tourElement = (Element) tourList.item(i);
                Tour tour = buildTreatmentTour(tourElement);
                tours.add(tour);
            }
        } catch (IOException e) {
            log.error("File error or I/O error: " + e);
        } catch (SAXException e) {
            log.error("Parsing failure: " + e);
        }
    }

    private Tour buildRestTour(Element tourElement) {
        Tour tour = buildTour(tourElement);
        RestTour restTour = new RestTour(tour);

        restTour.setAmusementIncluded(Boolean.parseBoolean(getElementTextContent(tourElement, ToursEnum.AMUSEMENT_INCLUDED.getValue())));
        HotelCharacteristic characteristic = new HotelCharacteristic();

        Element characteristicElement = (Element) tourElement.getElementsByTagName(ToursEnum.HOTEL_CHARACTERISTIC.getValue()).item(0);

        characteristic.setAirConditioningIncluded(Boolean.parseBoolean(getElementTextContent(characteristicElement, ToursEnum.AIR_CONDITIONING_INCLUDED.getValue())));
        characteristic.setMealPlan(getElementTextContent(characteristicElement, ToursEnum.MEAL_PLAN.getValue()));
        characteristic.setStarsCategory(getElementTextContent(characteristicElement, ToursEnum.STARS_CATEGORY.getValue()));
        characteristic.setTvIncluded(Boolean.parseBoolean(getElementTextContent(characteristicElement, ToursEnum.TV_INCLUDED.getValue())));
        restTour.setHotelCharacteristic(characteristic);
        return restTour;
    }

    private Tour buildTreatmentTour(Element tourElement) {
        Tour tour = buildTour(tourElement);
        TreatmentTour treatmentTour = new TreatmentTour(tour);

        treatmentTour.setProcedures(getElementTextContent(tourElement, ToursEnum.PROCEDURES.getValue()));
        treatmentTour.setSpecialization(tourElement.getAttribute(ToursEnum.SPECIALIZATION.getValue()));
        treatmentTour.setType(getElementTextContent(tourElement, ToursEnum.SANATORIUM_TYPE.getValue()));

        return treatmentTour;
    }

    private Tour buildTour(Element tourElement) {
        Tour tour = new Tour();

        tour.setAccommodation(getElementTextContent(tourElement, ToursEnum.ACCOMMODATION.getValue()));
        tour.setCost(Double.parseDouble(getElementTextContent(tourElement, ToursEnum.COST.getValue())));
        tour.setCountry(getElementTextContent(tourElement, ToursEnum.COUNTRY.getValue()));
        tour.setDuration(Integer.parseInt(getElementTextContent(tourElement, ToursEnum.DURATION.getValue())));
        tour.setName(tourElement.getAttribute(ToursEnum.NAME.getValue()));
        tour.setTransport(getElementTextContent(tourElement, ToursEnum.TRANSPORT.getValue()));

        return tour;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
