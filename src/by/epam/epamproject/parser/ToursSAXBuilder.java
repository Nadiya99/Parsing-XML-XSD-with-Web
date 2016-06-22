package by.epam.epamproject.parser;


import by.epam.epamproject.entity.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class ToursSAXBuilder extends AbstractToursBuilder {
    private static Logger log = LogManager.getLogger(ToursSAXBuilder.class);

    private TourHandler tourHandler = new TourHandler();
    private XMLReader reader;

    public ToursSAXBuilder() {
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(tourHandler);
        } catch (SAXException e) {
            log.error("SAX by.epam.epamproject.parser error: " + e);
        }
    }

    public ToursSAXBuilder(Set<Tour> tours) {
        super(tours);
    }

    @Override
    public void buildSetTours(String filename) {
        try {
            reader.parse(filename);
        } catch (SAXException e) {
            log.error("SAX by.epam.epamproject.parser error: " + e);
        } catch (IOException e) {
            log.error("I/О error: " + e);
        }
        tours = tourHandler.getTours();
    }
}
